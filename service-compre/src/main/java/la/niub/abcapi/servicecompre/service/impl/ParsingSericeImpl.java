package la.niub.abcapi.servicecompre.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import la.niub.abcapi.servicecompre.component.exception.CustomException;
import la.niub.abcapi.servicecompre.component.util.HttpUtil;
import la.niub.abcapi.servicecompre.component.util.StringUtil;
import la.niub.abcapi.servicecompre.model.bo.SolrBitmapBo;
import la.niub.abcapi.servicecompre.model.bo.SolrCommonSearchBo;
import la.niub.abcapi.servicecompre.model.nosql.BitmapCluserModel;
import la.niub.abcapi.servicecompre.model.nosql.HbChartsModel;
import la.niub.abcapi.servicecompre.model.nosql.HbTextModel;
import la.niub.abcapi.servicecompre.model.request.ChartDetailRequest;
import la.niub.abcapi.servicecompre.model.request.DataChartRequest;
import la.niub.abcapi.servicecompre.model.request.ParsingRequest;
import la.niub.abcapi.servicecompre.service.IDataDetailService;
import la.niub.abcapi.servicecompre.service.IParsingService;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Parsing 实现
 *
 * @author amen
 */
@Service
public class ParsingSericeImpl implements IParsingService {

    private static final Logger logger = LogManager.getLogger(ParsingSericeImpl.class);


    private static final String KEY_AUTHOR = "Basic YWxnb3JpdGhtOmU2cVZSU3FfTkxFWg==";

    @Value("${solr.bitmap_import_task}")
    private String bitmapImportTask;

    @Value("${solr.bitmap_parse_state}")
    private String bitmapParseState;

    @Value("${solr.bitmap_push_solr}")
    private String bitmapPushSolr;

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    IDataDetailService dataDetailService;

    /**
     * 插队解析
     * @param params
     * @return
     * @throws Throwable
     */
    @Override
    public Object send(ParsingRequest params) throws Throwable {
        Map<String, Object> request_params = new HashMap<>(6);
        request_params.put("source", String.valueOf(params.getSource()));
        if(!params.getImageId().isEmpty()){
            request_params.put("items", Arrays.asList(params.getImageId().split(",")));
        }
        request_params.put("requester", String.valueOf(params.getRequester()));
        request_params.put("force",Boolean.valueOf(params.getForce()));
        request_params.put("priority",Integer.valueOf(params.getPriority()));
        request_params.put("is_fileId",Boolean.valueOf(params.getIs_fileId()));

        String json = JSON.toJSONString(request_params);

        String resultStr = HttpUtil.postBodyAndHead(bitmapImportTask, json,getAuthKey());

        if (resultStr == null || StringUtils.isEmpty(resultStr)) {
            throw new CustomException(500, "bitmap_import_task error");
        }

        return JSON.parseObject(resultStr);
    }

    /**
     * 查询状态
     * @param params
     * @return
     * @throws Throwable
     */
    @Override
    public Object parseState(ParsingRequest params) throws Throwable {
        Map<String, Object> request_params = new HashMap<>();
        request_params.put("source", String.valueOf(params.getSource()));
        if(!params.getImageId().isEmpty()){
            request_params.put("items", Arrays.asList(params.getImageId().split(",")));
        }

        String json = JSON.toJSONString(request_params);

        String resultStr = HttpUtil.postBodyAndHead(bitmapParseState, json,getAuthKey());

        if (resultStr == null || StringUtils.isEmpty(resultStr)) {
            throw new CustomException(500, "bitmap_import_task error");
        }

        return JSON.parseObject(resultStr);
    }

    /**
     * 从mongo中找到数据然后推送到solr
     * @param params
     * @return
     * @throws Throwable
     */
    @Override
    public  Map<String, Object> post(ParsingRequest params) throws  Throwable {
        //查询数据
        Criteria criteria = new Criteria();
        criteria.and("_id").is(params.getImageId());

        Query query = new Query(criteria);
        query.with(new Sort(new Sort.Order(Sort.Direction.ASC, "_id")));

        List<HbChartsModel> model = mongoTemplate.find(query, HbChartsModel.class);
        Map<String, String> paragraphs = new HashMap<>(5);
        for (HbChartsModel items : model){
            paragraphs.put("id",String.valueOf(params.getId()));
            paragraphs.put("real_id",String.valueOf(params.getRealId()));
            paragraphs.put("image_id",String.valueOf(items.get_id()));
            paragraphs.put("confidence",String.valueOf(items.getConfidence()));
            paragraphs.put("data_file",String.valueOf(items.getData_file()));
            paragraphs.put("bitmap_ver",String.valueOf(items.getBitmap_ver()));
        }

        if (paragraphs.isEmpty()) {
            throw new CustomException(4002, "mongodb is error");
        }
        //更新数据
        List<Map<String, String>> list = new LinkedList<>();
        list.add(paragraphs);
        String json = JSON.toJSONString(list);
        Map<String, String> auth_params = new HashMap<>(0);

        String resultStr = HttpUtil.postBodyAndHead(bitmapPushSolr, json,auth_params);

        if (StringUtils.isEmpty(resultStr)) {
            throw new CustomException(500, "bitmap_import_task error");
        }

        SolrBitmapBo resultObj = JSON.parseObject(resultStr, SolrBitmapBo.class);
        if (resultObj == null || resultObj.getCode() != 200){
            throw new CustomException(500, "push solr error");
        }
        //从新查询数据
        ChartDetailRequest chartParams = new ChartDetailRequest();
//        chartParams.setId(params.getId());
        chartParams.setId(params.getRealId());

        Map<String, Object> ret = dataDetailService.buildChartDataById(chartParams);
        if(ret.containsKey("confidence")){
            ret.put("confidence", paragraphs.get("confidence"));
        }
        if(ret.containsKey("data_file")){
            ret.put("data_file", paragraphs.get("data_file"));
        }
        if(ret.containsKey("bitmap_ver")){
            ret.put("bitmap_ver", paragraphs.get("bitmap_ver"));
        }
        return ret;
    }

    /**
     * 获取版本
     * @param params
     * @return
     */
    public Object version(ParsingRequest params) {
        //查询数据
        Criteria criteria = new Criteria();
        criteria.and("_id").is(params.getType());

        Query query = new Query(criteria);
        query.with(new Sort(new Sort.Order(Sort.Direction.ASC, "_id")));

        BitmapCluserModel model = mongoTemplate.findOne(query, BitmapCluserModel.class);
        return new HashMap<String, Object>(){{
            put("version",model != null && model.getBitmap_ver() != null ? model.getBitmap_ver() : null);
        }};
    }

    private Map<String, String> getAuthKey()
    {
        Map<String, String> auth_params = new HashMap<>(1);
        auth_params.put("Authorization",String.valueOf((KEY_AUTHOR)));

        return auth_params;
    }
}
