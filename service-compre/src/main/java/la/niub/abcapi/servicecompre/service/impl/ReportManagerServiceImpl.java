package la.niub.abcapi.servicecompre.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import la.niub.abcapi.servicecompre.component.cache.KeyBuilder;
import la.niub.abcapi.servicecompre.component.exception.CustomException;
import la.niub.abcapi.servicecompre.component.util.RedisUtil;
import la.niub.abcapi.servicecompre.component.util.StringUtil;
import la.niub.abcapi.servicecompre.config.code.SystemEnumCodeConfig;
import la.niub.abcapi.servicecompre.dao.INoticeParsedDao;
import la.niub.abcapi.servicecompre.dao.IParagraphDao;
import la.niub.abcapi.servicecompre.dao.notice.IHiborDao;
import la.niub.abcapi.servicecompre.dao.notice.IMarketReportDao;
import la.niub.abcapi.servicecompre.dao.reporter.IAbcIndustryDao;
import la.niub.abcapi.servicecompre.model.AbcIndustryModel;
import la.niub.abcapi.servicecompre.model.bo.ComprehensiveChartGraphChartBO;
import la.niub.abcapi.servicecompre.model.bo.ComprehensiveChartGraphTableBO;
import la.niub.abcapi.servicecompre.model.bo.IndustryNameBO;
import la.niub.abcapi.servicecompre.model.cache.ReportCategoryFlatModel;
import la.niub.abcapi.servicecompre.model.cache.ReportCategoryModel;
import la.niub.abcapi.servicecompre.model.cache.SecIndustryFaltModel;
import la.niub.abcapi.servicecompre.model.dao.HiborGroupByDaoModel;
import la.niub.abcapi.servicecompre.model.nosql.HbChartsModel;
import la.niub.abcapi.servicecompre.model.nosql.HbTablesModel;
import la.niub.abcapi.servicecompre.model.nosql.HbTextModel;
import la.niub.abcapi.servicecompre.service.IIndustryService;
import la.niub.abcapi.servicecompre.service.IParseResultService;
import la.niub.abcapi.servicecompre.service.IReportManagerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class ReportManagerServiceImpl implements IReportManagerService{

    @Autowired
    IHiborDao hiborDao;

    @Autowired
    IMarketReportDao marketReportDao;

    @Autowired
    INoticeParsedDao noticeParsedDao;

    @Autowired
    IAbcIndustryDao abcIndustryDao;

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    IParagraphDao paragraphDao;

    @Autowired
    IParseResultService parseResultService;

    @Autowired
    IIndustryService industryService;

    @Override
    public List<ComprehensiveChartGraphChartBO> getReportCharts(Integer reportId) {
//        HiborModel report = hiborDao.selectById((long)reportId);
//        if (report == null){
//            MarketReportModel marketReportModel = marketReportDao.selectById(Long.valueOf(reportId));
//            if (marketReportModel != null){
//                report = ObjectUtil.copy(marketReportModel,HiborModel.class);
//            }
//        }

        // state: 0：刚入库 1：推送成功 2：标记更新 3：标记废弃 4：最终废弃
        List<HbChartsModel> records = noticeParsedDao.findChartsByFileId(reportId);

        List<ComprehensiveChartGraphChartBO> comprehensiveChartGraphChartBOList = new ArrayList<>();
        for (HbChartsModel record : records){
            ComprehensiveChartGraphChartBO item = new ComprehensiveChartGraphChartBO();

            item.setTitle(StringUtils.defaultString(record.getTitle(),""));
            item.setUrl(StringUtils.defaultString(record.getSvgFile(),
                    StringUtils.defaultString(record.getPngFile(),"")));
            item.setId(record.get_id());
            item.setData(record.getData());
            item.setPageIndex(record.getPageIndex());
            item.setArea(record.getArea());

            if (record.getArea() != null && record.getArea().containsKey("x") && record.getArea().containsKey("y")){
                item.setX(record.getArea().get("x"));
                item.setY(record.getArea().get("y"));
            }

            comprehensiveChartGraphChartBOList.add(item);
        }

        return comprehensiveChartGraphChartBOList;
    }

    @Override
    public List<ComprehensiveChartGraphTableBO> getReportTables(Integer reportId) {
        List<HbTablesModel> records = noticeParsedDao.findTablesByFileId(reportId);

        List<ComprehensiveChartGraphTableBO> comprehensiveChartGraphTableBOList = new ArrayList<>();
        for (HbTablesModel record : records){
            ComprehensiveChartGraphTableBO item = new ComprehensiveChartGraphTableBO();

            item.setTitle(StringUtils.defaultString(record.getTitle(),""));
            item.setUrl(StringUtils.defaultString(record.getPngFile(),""));
            item.setPageIndex(record.getPageIndex());
            item.setData(record.getData());
            item.setRow_count(record.getRowCount());
            item.setColumn_count(record.getColumnCount());
            item.setId(record.get_id());

            comprehensiveChartGraphTableBOList.add(item);
        }
        return comprehensiveChartGraphTableBOList;
    }

    /**
     * [getParaFromTextFile 从text_file获取研报段落数据]
     *
     * @param idTextFileMap {src_id, text_file}
     * @return
     */
    @Override
    public Map<String, List<String>> getParaFromTextFile(Map<String, String> idTextFileMap) {
        Map<String, List<String>> paragraphs = new HashMap<>();

        if (idTextFileMap != null && !idTextFileMap.keySet().isEmpty()){
            Map<String, String> fileContents = parseResultService.getFilesFormOssByUrl(idTextFileMap);
            for (Map.Entry<String, String> entry : fileContents.entrySet()){
                List<String> texts = Arrays.asList(entry.getValue().split("\n"));
                paragraphs.put(entry.getKey(),texts);
            }
        }
        return paragraphs;
    }

    @Override
    public Map<String, List<String>> getParaFromMongo(List<Integer> ids) {
        Map<String, List<String>> paragraphs = new HashMap<>();
        List<HbTextModel> hbTextModelList = paragraphDao.getParagraphs(ids);
        if (hbTextModelList == null || hbTextModelList.isEmpty()){
            return paragraphs;
        }

        Map<String, String> in_oss = new HashMap<>();
        for (HbTextModel item : hbTextModelList){
            String _id = String.valueOf(item.get_id());
            if (item.getText_version() != null && item.getText_version() >= 1){
                in_oss.put(_id, StringUtil.nullOrDefault(item.getText_file(),""));
            }else{
                List<String> arrs = new ArrayList<>();
                if (item.getFulltext() == null){
                    paragraphs.put(_id,arrs);
                    continue;
                }
                String[] texts = item.getFulltext().split("\n");
                for (String text : texts){
                    if (text.trim().length() > 0){
                        arrs.add(text.trim());
                    }
                }
                paragraphs.put(_id,arrs);
            }
        }

        if (!in_oss.isEmpty()){
            Map<String, String> fileContents = parseResultService.getFilesFormOssByUrl(in_oss);
            for (Map.Entry<String, String> entry : fileContents.entrySet()){
                List<String> texts = Arrays.asList(entry.getValue().split("\n"));
                paragraphs.put(entry.getKey(),texts);
            }
        }
        return paragraphs;
    }

    @Override
    public List<ReportCategoryModel> getCategoryTree() {
        String cacheResult = redisUtil.get(KeyBuilder.getKeyReportCategory());
        return cacheResult != null ? JSON.parseObject(cacheResult,new TypeReference<List<ReportCategoryModel>>(){})
                : null;
    }

    @Override
    public Map<String,ReportCategoryFlatModel> getCategoryFlat() {
        String cacheResult = redisUtil.get(KeyBuilder.getKeyReportCategoryFlat());
        return cacheResult != null ? JSON.parseObject(cacheResult,new TypeReference<Map<String,ReportCategoryFlatModel>>(){})
                : null;
    }

    @Override
    public String getLowestReportType(List<String> list) {
        if (list == null || list.isEmpty()){
            return null;
        }
        LinkedList<String> types = new LinkedList<>();
        for (String item : list){
            if (StringUtil.isEmpty(item)){
                continue;
            }
            String firstLetter = item.substring(0,1);
            switch (firstLetter){
                case "R":// 一级分类
                case "S"://二级分类
                case "E":  // 市场研究的三级分类
                default:
                    types.add(item);
                    break;
            }
        }

        /** 1 E or D开头的一定是末级节点
        *   2 length最大的是末级节点
        */
        Collections.sort(types,new Comparator<String>(){
            @Override
            public int compare(String arg0, String arg1) {
                String arg0FirstLetter = arg0.substring(0,1);
                String arg1FirstLetter = arg1.substring(0,1);
                if (arg0FirstLetter.equals(arg1FirstLetter)){
                    return arg0.length() - arg1.length();
                }
                if (arg0FirstLetter.equals("E")
                        || arg0FirstLetter.equals("D")){
                    return 1;
                }
                if (arg1FirstLetter.equals("E")
                        || arg1FirstLetter.equals("D")){
                    return -1;
                }
                return arg0.length() - arg1.length();
            }
        });

        return types.getLast();
    }

    @Override
    public IndustryNameBO getIndustryName(List<String> codeList) throws CustomException {
        IndustryNameBO bo = new IndustryNameBO();

        SecIndustryFaltModel secIndustryFaltModel = getFlatIndustryFromRedis();
        Integer count = codeList.size();
        if (count == 1){
            String newCode = transferIndustry(codeList.get(0));
            String name = StringUtil.nullOrDefault(secIndustryFaltModel.getFirst().get(newCode),"");
            bo.setName(name);
            bo.setCode(codeList.get(0));
            return bo;
        }else if (count == 2){
            for (String code : codeList){
                if (code.substring(0,1).equals("S")){
                    String newCode = transferIndustry(code);
                    String name = StringUtil.nullOrDefault(secIndustryFaltModel.getSecond().get(newCode),"");
                    bo.setName(name);
                    bo.setCode(code);
                    return bo;
                }
            }
        }else if (count == 3){
            for (String code : codeList){
                if (code.substring(0,1).equals("T")){
                    String newCode = transferIndustry(code);
                    String name = StringUtil.nullOrDefault(secIndustryFaltModel.getThird().get(newCode),"");
                    bo.setName(name);
                    bo.setCode(code);
                    return bo;
                }
            }
        }else{
            throw new CustomException(SystemEnumCodeConfig.NOT_SUPPORT_INDUSTRY_LEVEL);
        }
        return null;
    }

    @Override
    public SecIndustryFaltModel getFlatIndustryFromRedis() {
        return redisUtil.get(KeyBuilder.getIndustryNameKey(),SecIndustryFaltModel.class);
    }

    /**
     * [transferIndustry 将公告行业分类code由solrweb标准转数据库标准]
     * @param    code                   $code [solrweb标准行业code]
     * @return   [array]        [数据库标准行业code]
     */
    private String transferIndustry(String code){
        String level = code.substring(0,1);
        String last = code.substring(1);
        switch (level) {
            case "F"://一级行业分类
                return last+"000";
            case "S"://二级行业分类
                return last+"00";
            case "T"://三级行业分类
                return last;
            default:
                return last;
        }
    }

    @Override
    public Integer getIndustryReportCount(String indexCode, Date startTime) {
        AbcIndustryModel abcIndustryModel = abcIndustryDao.selectByIndexCode(indexCode);
        if (abcIndustryModel == null){
            return null;
        }
        List<String> induCodes = new ArrayList<>();

        List<String> firstInduCodes = new ArrayList<>();
        firstInduCodes.add(abcIndustryModel.getIndu_code());
        induCodes.add(abcIndustryModel.getIndu_code());

        List<String> secondInduCodes = new ArrayList<>();
        List<AbcIndustryModel> secondAbcIndustryModelList = abcIndustryDao.selectByParentIds(firstInduCodes);
        for (AbcIndustryModel item : secondAbcIndustryModelList){
            secondInduCodes.add(item.getIndu_code());
            induCodes.add(item.getIndu_code());
        }

        List<String> thirdInduCodes = new ArrayList<>();
        List<AbcIndustryModel> thirdAbcIndustryModelList = abcIndustryDao.selectByParentIds(secondInduCodes);
        for (AbcIndustryModel item : thirdAbcIndustryModelList){
            thirdInduCodes.add(item.getIndu_code());
            induCodes.add(item.getIndu_code());
        }

        Integer reportCount = hiborDao.selectCountByIndustryIds(induCodes,startTime,null);
        return reportCount;
    }

    @Override
    public Map<String, Integer> getIndustryReportCount(List<String> indexCodes, Date startTime) {
        List<String> induCodes = new ArrayList<>();
        Map<String,String> induCodeToIndexCodeMap = new HashMap<>();

        List<String> firstInduCodes = new ArrayList<>();
        List<AbcIndustryModel> abcIndustryModelList = abcIndustryDao.selectByIndexCodes(indexCodes);
        for (AbcIndustryModel item : abcIndustryModelList){
            firstInduCodes.add(item.getIndu_code());
            induCodes.add(item.getIndu_code());
            induCodeToIndexCodeMap.put(item.getIndu_code(),item.getIndex_code());
        }

        List<String> secondInduCodes = new ArrayList<>();
        List<AbcIndustryModel> secondAbcIndustryModelList = abcIndustryDao.selectByParentIds(firstInduCodes);
        if (!ObjectUtils.isEmpty(secondAbcIndustryModelList)){
            for (AbcIndustryModel item : secondAbcIndustryModelList){
                secondInduCodes.add(item.getIndu_code());
                induCodes.add(item.getIndu_code());
                String indexCode = induCodeToIndexCodeMap.get(item.getParent_id());
                if (indexCode != null){
                    induCodeToIndexCodeMap.put(item.getIndu_code(),indexCode);
                }
            }
        }

        List<AbcIndustryModel> thirdAbcIndustryModelList = abcIndustryDao.selectByParentIds(secondInduCodes);
        if (!ObjectUtils.isEmpty(thirdAbcIndustryModelList)){
            for (AbcIndustryModel item : thirdAbcIndustryModelList){
                induCodes.add(item.getIndu_code());
                String indexCode = induCodeToIndexCodeMap.get(item.getParent_id());
                if (indexCode != null){
                    induCodeToIndexCodeMap.put(item.getIndu_code(),indexCode);
                }
            }
        }

        Map<String,Integer> reportNumMap = new HashMap<>();
        if (induCodes.isEmpty()){
            return reportNumMap;
        }
        List<HiborGroupByDaoModel> hiborGroupByDaoModelList = hiborDao.selectByIndustryIdsAndTimeGroupByIndustryId(induCodes,startTime,null);
        for (HiborGroupByDaoModel item : hiborGroupByDaoModelList){
            String indexCode = induCodeToIndexCodeMap.get(item.getIndustryId());
            if (indexCode != null){
                Integer num = reportNumMap.get(indexCode);
                if (num != null){
                    reportNumMap.put(indexCode,num+item.getCount());
                }else{
                    reportNumMap.put(indexCode,item.getCount());
                }
            }
        }

//        for (String indexCode : indexCodes){
//            AbcIndustryModel abcIndustryModel = abcIndustryDao.selectByIndexCode(indexCode);
//            if (abcIndustryModel == null){
//                continue;
//            }
//            induCodes.add(abcIndustryModel.getIndu_code());
//            induCodeToIndexCodeMap.put(abcIndustryModel.getIndu_code(),indexCode);
//
//            List<AbcIndustryModel> abcIndustryModelList = industryService.getChildIndustry(abcIndustryModel.getIndu_code());
//            for (AbcIndustryModel item : abcIndustryModelList){
//                induCodes.add(item.getIndu_code());
//                induCodeToIndexCodeMap.put(item.getIndu_code(),indexCode);
//            }
//        }
//
//        Map<String,Integer> reportNumMap = new HashMap<>();
//        if (induCodes.isEmpty()){
//            return reportNumMap;
//        }
//        List<HiborGroupByDaoModel> hiborGroupByDaoModelList = hiborDao.selectByIndustryIdsAndTimeGroupByIndustryId(induCodes,startTime,null);
//        for (HiborGroupByDaoModel item : hiborGroupByDaoModelList){
//            String indexCode = induCodeToIndexCodeMap.get(item.getIndustryId());
//            if (indexCode != null){
//                Integer num = reportNumMap.get(indexCode);
//                if (num != null){
//                    reportNumMap.put(indexCode,num+item.getCount());
//                }else{
//                    reportNumMap.put(indexCode,item.getCount());
//                }
//            }
//        }
        return reportNumMap;
    }
}
