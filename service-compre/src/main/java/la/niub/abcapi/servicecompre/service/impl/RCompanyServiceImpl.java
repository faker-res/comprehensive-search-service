package la.niub.abcapi.servicecompre.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import la.niub.abcapi.servicecompre.dao.reporter.*;
import la.niub.abcapi.servicecompre.model.*;
import la.niub.abcapi.servicecompre.model.bo.ReportRequest;
import la.niub.abcapi.servicecompre.model.bo.SearchReportBO;
import la.niub.abcapi.servicecompre.model.response.rentity.*;
import la.niub.abcapi.servicecompre.service.IRCompanyService;
import la.niub.abcapi.servicecompre.service.ISearch2Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class RCompanyServiceImpl implements IRCompanyService {
    private static Logger logger = LogManager.getLogger(RCompanyServiceImpl.class);
    @Autowired
    ISecBasicInfoDao secBasicInfoDao;
    @Autowired
    IComBasicInfoDao comBasicInfoDao;
    @Autowired
    IOrgBasicInfoDao orgBasicInfoDao;
    @Autowired
    IOrgbasicinfochgDao orgbasicinfochgDao;
    @Autowired
    IComRealControllerDao comRealControllerDao;
    @Autowired
    ISecIndustryNewDao secIndustryNewDao;
    @Autowired
    ISecMainHolderDao secMainHolderDao;
    @Autowired
    IsecMainCirHolderDao secMainCirHolderDao;
    @Autowired
    IIndexIngreStockDao indexIngreStockDao;
    @Autowired
    IIndexRangeChanDao indexRangeChanDao;
    @Autowired
    IRegionDao regionDao;
    @Autowired
    IComManagerDao comManagerDao;
    @Autowired
    ISecPriceDayDao secPriceDayDao;
    @Autowired
    ISearch2Service search2Service;
    @Autowired
    ISystemConstDao systemConstDao;
    @Autowired
    IComForecastDao comForecastDao;
    @Autowired
    IComProfitSheetYoyDao comProfitSheetYoyDao;
    @Autowired
    IComrdExpenseDao comrdExpenseDao;
    @Autowired
    IComProfitDao comProfitDao;
    @Autowired
    INormalValuationIndexDao normalValuationIndexDao;
    @Autowired
    IComBalanceDao comBalanceDao;
    @Autowired
    CustomNavigation customNavigation;
    //获取公司简介
    public Map brief(Map map) {
        Map result = new HashMap();
        RCompanyBrief companyBrief = new RCompanyBrief();
        //判断股票的交易市场
        Long code = 0l;
        if (map.containsKey("code")) {
            code = Long.parseLong(map.get("code").toString());
        }
        SecBasicInfoModel stockInfo = secBasicInfoDao.selectBySecUniCodeHS(code);
        if (stockInfo == null) {
            return result;
        }
        Map parapMap = new HashMap();
        parapMap.put("com_uni_code", stockInfo.getCom_uni_code());
        List<CombasicinfoModel> combasicinfoModels = comBasicInfoDao.find(parapMap);
        if (combasicinfoModels != null && combasicinfoModels.size() > 0) {
            CombasicinfoModel combasicinfoModel = combasicinfoModels.get(0);
            JSONObject json = (JSONObject) JSONObject.toJSON(combasicinfoModel);
            companyBrief = JSONObject.toJavaObject(json, RCompanyBrief.class);
            OrgBasicInfoModel orgBasicInfoModel = orgBasicInfoDao.selectByComUniCode(stockInfo.getCom_uni_code());
            if (orgBasicInfoModel != null) {
                companyBrief.setAccountant(orgBasicInfoModel.getAccountant());
                companyBrief.setLawyer(orgBasicInfoModel.getLawyer());
            }
            Map chgMap = new HashMap();
            chgMap.put("org_uni_code", stockInfo.getCom_uni_code());
            chgMap.put("change_type", "公司名称");
            chgMap.put("orderByField", " begin_date desc ");
            List<OrgbasicinfochgModel> chglist = orgbasicinfochgDao.find(chgMap);
            if (chglist != null && chglist.size() > 0) {
                List beglist = new ArrayList();
                for (OrgbasicinfochgModel orgbasicinfochgModel : chglist) {
                    Map begMap = new HashMap();
                    begMap.put("begin_date", orgbasicinfochgModel.getBegin_date());
                    begMap.put("begin_change", orgbasicinfochgModel.getBegin_change());
                    begMap.put("after_change", orgbasicinfochgModel.getAfter_change());
                    beglist.add(begMap);
                }
                companyBrief.setBegin_change(beglist);
            }
            Map realMap = new HashMap();
            realMap.put("com_uni_code", stockInfo.getCom_uni_code());
            List<ComRealControllerModel> reallist = comRealControllerDao.find(realMap);
            if (reallist != null && reallist.size() > 0) {
                companyBrief.setReal_control(reallist.get(0).getReal_control());
            }
            companyBrief.setId(combasicinfoModel.getCom_uni_code());
            //国家，省份，城市字典转换
            companyBrief.setNati_name(regionDao.selectNameByCode(companyBrief.getNati_name()));
            companyBrief.setProv_name(regionDao.selectNameByCode(companyBrief.getProv_name()));
            companyBrief.setCity_name(regionDao.selectNameByCode(companyBrief.getCity_name()));
            //货币类型转换
            companyBrief.setCurrency_type(systemConstDao.selectBySystemUniCode(companyBrief.getCurrency_type() == null ? 0l : Long.parseLong(companyBrief.getCurrency_type())).getSystem_name());
            result.put("items", companyBrief);
        }
        return result;
    }

    //获取公司董事与高管
    public Map directors(Map map) {
        Map result = new LinkedHashMap();
            List itemList = new ArrayList();
            Map paraMap = new HashMap();
            //判断股票的交易市场
            Long code = 0l;
            if (map.containsKey("code")) {
                code = Long.parseLong(map.get("code").toString());
            }
            String if_position = map.getOrDefault("if_position", "0").toString();
            String type_ = map.getOrDefault("type", "1,2,3,4").toString();
            paraMap.put("if_position", if_position);
            SecBasicInfoModel stockInfo = secBasicInfoDao.selectBySecUniCodeHS(code);
            if (stockInfo == null) {
                return result;
            }
            String[] types = type_.split(",");
            String[] limits = new String[0];
            String[] offsets = new String[0];
            if (map.containsKey("limit")) {
                limits = map.get("limit").toString().split(",");
            }
            if (map.containsKey("offset")) {
                offsets = map.get("offset").toString().split(",");
            }
            for (int i = 0; i < types.length; i++) {
                String type = types[i];
                paraMap.put("offset", "0");
                paraMap.put("limit", "10");
                if (limits.length > i) {
                    paraMap.put("limit", limits[i]);
                }
                if (offsets.length > i) {
                    paraMap.put("offset", offsets[i]);
                }
                Map itemMap = new HashMap();
                if (type.equals("1")) {
                    paraMap.put("post_type", "('董事会')");
                    itemMap.put("key", "董事会");
                } else if (type.equals("2")) {
                    paraMap.put("post_type", "('监事会')");
                    itemMap.put("key", "监事会");
                } else if (type.equals("3")) {
                    paraMap.put("post_type", "('经营层','董秘')");
                    itemMap.put("key", "高管");
                } else if (type.equals("4")) {
                    paraMap.put("post_type", "('其他','证券事务代表')");
                    itemMap.put("key", "委员会");
                }
                paraMap.put("com_uni_code", stockInfo.getCom_uni_code());
                List<Map<String, Object>> lists = comManagerDao.selectManager(paraMap);
                if (lists != null && lists.size()>0) {
                    List<Object> tlist = new ArrayList<>();
                    long total = comManagerDao.getManagerCount(paraMap);
                    for (Map<String, Object> dataMap : lists) {
                        String sex_par = dataMap.getOrDefault("sex_par", "").toString();
                        if (sex_par.equals("0")) {
                            dataMap.put("sex_par", "男");
                        } else if (sex_par.equals("1")) {
                            dataMap.put("sex_par", "女");
                        }
                        String country = dataMap.getOrDefault("country", "").toString();
                        dataMap.put("country", regionDao.selectNameByCode(country));
                        if(dataMap.get("in_date")!=null){
                            dataMap.put("in_date",dateToStamp(dataMap.get("in_date").toString(),"yyyy-MM-dd"));
                        }else{
                            dataMap.put("in_date",null);
                        }
                        if(dataMap.get("off_date")!=null){
                            dataMap.put("off_date",dateToStamp(dataMap.get("off_date").toString(),"yyyy-MM-dd"));
                        }else{
                            dataMap.put("off_date",null);
                        }
                        if(dataMap.get("birth_day")!=null){
                            dataMap.put("birth_day",dateToStamp(dataMap.get("birth_day").toString(),"yyyy"));
                        }else{
                            dataMap.put("birth_day",null);
                        }
                        tlist.add(dataMap);
                    }
                    itemMap.put("items", tlist);
                    itemMap.put("total", total);
                }
                itemList.add(itemMap);
            }
            result.put("items", itemList);
            return result;
    }

    //获取公司行业数据
    public Map industry(Map map) {
        Map result = new HashMap();
        List<RCompanyIndustry> industryList = new ArrayList<>();
        if (map.containsKey("code")) {
            map.put("sec_uni_code", map.get("code").toString());
            if(map.containsKey("industry") ){
                if(map.get("industry").toString().equals("1")){
                    map.put("if_performed","1");
                }
            }
            List<SecIndustryNewModel> seclist = secIndustryNewDao.find(map);
            if (seclist != null) {
                long total = secIndustryNewDao.getCount(map);
                for (SecIndustryNewModel scIndustryNewModel : seclist) {
                    RCompanyIndustry rcompanyIndustry = new RCompanyIndustry();
                    StringBuffer indu_name = new StringBuffer();
                    if (scIndustryNewModel.getFirst_indu_name() != null && !scIndustryNewModel.getFirst_indu_name().isEmpty()) {
                        indu_name.append(scIndustryNewModel.getFirst_indu_name());
                    }
                    if (scIndustryNewModel.getSecond_indu_name() != null && !scIndustryNewModel.getSecond_indu_name().isEmpty()) {
                        indu_name.append("-" + scIndustryNewModel.getSecond_indu_name());
                    }
                    if (scIndustryNewModel.getThird_indu_name() != null && !scIndustryNewModel.getThird_indu_name().isEmpty()) {
                        indu_name.append("-" + scIndustryNewModel.getThird_indu_name());
                    }
                    if (scIndustryNewModel.getFourth_indu_code() != null && !scIndustryNewModel.getFourth_indu_code().isEmpty()) {
                        indu_name.append("-" + scIndustryNewModel.getFirst_indu_name());
                    }
                    rcompanyIndustry.setId(scIndustryNewModel.getId() + "");
                    rcompanyIndustry.setIndu_name(indu_name.toString());
                    rcompanyIndustry.setIndu_standard_name(scIndustryNewModel.getIndu_standard_name());
                    rcompanyIndustry.setStk_code(scIndustryNewModel.getStk_code());
                    rcompanyIndustry.setSub_beg_date(scIndustryNewModel.getSub_beg_date());
                    rcompanyIndustry.setSub_end_date(scIndustryNewModel.getSub_end_date());
                    rcompanyIndustry.setIf_performed(scIndustryNewModel.getIf_performed());
                    industryList.add(rcompanyIndustry);
                }
                result.put("total", total);
                result.put("items", industryList);
            }
        }
        return result;
    }

    //获取公司指数
    public Map indexmark(Map map) {
        Map result = new HashMap();
        List<RCompanyIndexMark> indexMarkList = new ArrayList<>();
        if (map.containsKey("code")) {
            String code = "000000" + map.get("code").toString();
            code = code.substring(code.length() - 6);
            map.put("sec_code", code);
            map.put("orderByField", "in_date desc");
            if(map.containsKey("index") && map.get("index").toString().equals("1")){
                map.put("qsql"," and out_date IS NULL ");
            }
            List<Map> listMaps = indexIngreStockDao.selectMarkBySecCode(map);
            if (listMaps != null) {
                long total = indexIngreStockDao.getMarkCount(map);
                for (Map dataMap : listMaps) {
                    JSONObject json = (JSONObject) JSONObject.toJSON(dataMap);
                    RCompanyIndexMark rcompanyIndexMark = JSONObject.toJavaObject(json, RCompanyIndexMark.class);
//                    if(rcompanyIndexMark.getIndex_type()!=null && !rcompanyIndexMark.getIndex_type().isEmpty()){
//                        SystemConstModel systemConstModel=systemConstDao.selectBySystemUniCode(Long.parseLong(rcompanyIndexMark.getIndex_type()));
//                        rcompanyIndexMark.setIndex_type(systemConstModel.getSystem_name());
//                    }
                    if (rcompanyIndexMark.getOut_date() != null) {
                        rcompanyIndexMark.setStatus("历史");
                    } else {
                        rcompanyIndexMark.setStatus("最新");
                    }
                    indexMarkList.add(rcompanyIndexMark);
                }
                result.put("items", indexMarkList);
                result.put("total", total);
            }
        }
        return result;
    }

    //获取公司概念
    public Map notion(Map map) {
        Map result = new HashMap();
        List notionData = new ArrayList();
        if (map.containsKey("code")) {
            String code = "000000" + map.get("code").toString();
            code = code.substring(code.length() - 6);
            map.put("sec_code", code);
            map.put("orderByField", "in_date desc");
            List<Map> listMaps = indexIngreStockDao.selectNotionBySecCode(map);
            if (listMaps != null) {
                long total = indexIngreStockDao.getNotionCount(map);
                for (Map dataMap : listMaps) {
                    Map data = new HashMap();
                    data.put("id", dataMap.get("id").toString());
                    data.put("index_name", dataMap.get("index_name").toString());
                    data.put("sec_code", dataMap.get("sec_code"));
                    data.put("index_code", dataMap.get("index_code"));
                    data.put("differ_range_w", "");
                    data.put("differ_range_m", "");
                    data.put("differ_range_6m", "");
                    data.put("differ_range_y", "");
                    String index_uni_code = dataMap.get("index_uni_code").toString();
                    Map paraMap = new HashMap();
                    paraMap.put("index_uni_code", index_uni_code);
                    List<IndexRangeChanModel> lists = indexRangeChanDao.find(paraMap);
                    if (lists != null) {
                        for (IndexRangeChanModel indexRangeChanModel : lists) {
                            long period = indexRangeChanModel.getPeriod();
                            if (period == 1524004001) {
                                data.put("differ_range_w", indexRangeChanModel.getDiffer_range());
                            } else if (period == 1524004002) {
                                data.put("differ_range_m", indexRangeChanModel.getDiffer_range());
                            } else if (period == 1524009) {
                                data.put("differ_range_6m", indexRangeChanModel.getDiffer_range());
                            } else if (period == 1524008) {
                                data.put("differ_range_y", indexRangeChanModel.getDiffer_range());
                            }
                        }
                    }
                    notionData.add(data);
                }
                result.put("items", notionData);
                result.put("total", total);
            }
        }
        return result;
    }

    //获取同概率个股
    public Map notionDetail(Map map) {
        Map result = new HashMap();
        List<Map> queryList = secBasicInfoDao.getNotionDetail(map);
        if (queryList != null) {
            long total = secBasicInfoDao.getNotionDetailCount(map);
            result.put("total", total);
            result.put("item", queryList);
        }
        return result;
    }

    //获取股东十大股东明细
    public Map partner_detail(Map map) {
        Map<String, Object> result = new LinkedHashMap<>();
        List resultList = new ArrayList();
        Map prarMap = new HashMap();
        Long code = 0l;
        if (map.containsKey("code")) {
            code = Long.parseLong(map.get("code").toString());
        }
        String reporttypes = map.getOrDefault("reporttype", "1,5").toString();
        SecBasicInfoModel stockInfo = secBasicInfoDao.selectBySecUniCodeHS(code);
        if (stockInfo != null) {
            prarMap.put("limit", map.get("limit") == null ? "10" : map.get("limit"));
            prarMap.put("offset", map.get("offset") == null ? "0" : map.get("offset"));
            prarMap.put("com_uni_code", stockInfo.getCom_uni_code().toString());
            Map pMap = new HashMap();
            pMap.put("com_uni_code", stockInfo.getCom_uni_code().toString());
            pMap.put("limit", 1);
            pMap.put("offset", 0);
            pMap.put("orderby", "order by end_date desc");
            String temp_endDate = "";
            List<Map> tempList = secMainHolderDao.find(pMap);
            if (tempList != null && tempList.size()>0) {
                temp_endDate = tempList.get(0).get("end_date").toString();
            }
            prarMap.put("reporttype", convertToSQL(reporttypes, temp_endDate));
            prarMap.put("groupby", " group by end_date");
            prarMap.put("orderby", map.get("orderby") == null ? " order by end_date desc" : " order by end_date " + map.get("orderby").toString());
            prarMap.put("start_time", map.get("start_time"));
            prarMap.put("end_time", map.get("end_time"));
            List<Map> querylists = secMainHolderDao.find(prarMap);
            if (querylists != null) {
                long toal = secMainHolderDao.getCount(prarMap);
                if (querylists != null) {
                    for (int i = 0; i < querylists.size(); i++) {
                        Map dMap = new HashMap();
                        Map<String, Object> dataMap = querylists.get(i);
                        Map tMap = new HashMap();
                        List<RCompanyPartner> partners = new ArrayList<RCompanyPartner>();
                        String qkey = covertTimePartner(dataMap.get("end_date").toString());
                        tMap.put("end_date", dataMap.get("end_date").toString());
                        tMap.put("com_uni_code", stockInfo.getCom_uni_code().toString());
                        List<Map> dlists = secMainHolderDao.find(tMap);
                        for (Map<String, Object> daMap : dlists) {
                            JSONObject json = (JSONObject) JSONObject.toJSON(daMap);
                            RCompanyPartner rcompanyPartner = JSONObject.toJavaObject(json, RCompanyPartner.class);
                            rcompanyPartner.setHold_type(json.getOrDefault("hold_type", "").toString());
                            rcompanyPartner.setHold_shr_change(json.getOrDefault("hold_shr_add", "").toString());
                            partners.add(rcompanyPartner);
                        }
                        dMap.put("key", qkey);
                        dMap.put("data", partners);
                        resultList.add(dMap);
                    }
                }
                result.put("total", toal);
                result.put("items", resultList);
            }
        }
        return result;
    }

    //获取股东十大流通股东
    public Map partner_circulate(Map map) {
        Map<String, Object> result = new LinkedHashMap<>();
        List resultList = new ArrayList();
        Map prarMap = new HashMap();
        Long code = 0l;
        if (map.containsKey("code")) {
            code = Long.parseLong(map.get("code").toString());
        }
        String reporttypes = map.getOrDefault("reporttype", "1,5").toString();
        SecBasicInfoModel stockInfo = secBasicInfoDao.selectBySecUniCodeHS(code);
        if (stockInfo != null) {
            prarMap.put("limit", map.get("limit") == null ? "10" : map.get("limit"));
            prarMap.put("offset", map.get("offset") == null ? "0" : map.get("offset"));
            prarMap.put("com_uni_code", stockInfo.getCom_uni_code().toString());
            Map pMap = new HashMap();
            pMap.put("com_uni_code", stockInfo.getCom_uni_code().toString());
            pMap.put("limit", 1);
            pMap.put("offset", 0);
            pMap.put("orderby", "order by end_date desc");
            String temp_endDate = "";
            List<Map> tempList = secMainCirHolderDao.find(pMap);
            if (tempList != null) {
                temp_endDate = tempList.get(0).get("end_date").toString();
            }
            prarMap.put("reporttype", convertToSQL(reporttypes, temp_endDate));
            prarMap.put("groupby", " group by end_date");
            prarMap.put("orderby", map.get("orderby") == null ? " order by end_date desc" : " order by end_date " + map.get("orderby").toString());

            prarMap.put("start_time", map.get("start_time"));
            prarMap.put("end_time", map.get("end_time"));
            List<Map> querylists = secMainCirHolderDao.find(prarMap);
            if (querylists != null) {
                long toal = secMainCirHolderDao.getCount(prarMap);
                if (querylists != null) {
                    for (int i = 0; i < querylists.size(); i++) {
                        Map dMap = new HashMap();
                        Map<String, Object> dataMap = querylists.get(i);
                        Map tMap = new HashMap();
                        List<RCompanyPartner> partners = new ArrayList<RCompanyPartner>();
                        String qkey = covertTimePartner(dataMap.get("end_date").toString());
                        tMap.put("end_date", dataMap.get("end_date").toString());
                        tMap.put("com_uni_code", stockInfo.getCom_uni_code().toString());
                        List<Map> dlists = secMainCirHolderDao.find(tMap);
                        for (Map<String, Object> daMap : dlists) {
                            JSONObject json = (JSONObject) JSONObject.toJSON(daMap);
                            RCompanyPartner rcompanyPartner = JSONObject.toJavaObject(json, RCompanyPartner.class);
                            rcompanyPartner.setHold_type(json.getOrDefault("hold_type", "").toString());
                            rcompanyPartner.setHold_shr_change(json.getOrDefault("hold_shr_add", "").toString());
                            rcompanyPartner.setShare_change_rate(json.getOrDefault("change_prop", "").toString());
                            partners.add(rcompanyPartner);
                        }
                        dMap.put("key", qkey);
                        dMap.put("data", partners);
                        resultList.add(dMap);
                    }
                }
                result.put("total", toal);
                result.put("items", resultList);
            }
        }
        return result;
    }

    //获取公司交易行情统计数据
    public Map markettotal(Map map) {
        Map result = new HashMap();
        List<Object> datalist = new ArrayList<>();
        String tabletype = "d";
        if (map.containsKey("tabletype")) {
            tabletype = map.get("tabletype").toString();
        }
        if (tabletype.equals("d")) {
            if (map.containsKey("orderby")) {
                map.put("orderby", "ORDER BY a.trade_date " + map.get("orderby").toString());
            } else {
                map.put("orderby", "ORDER BY a.trade_date desc");
            }
            datalist = secPriceDayDao.getDay(map);
            long total = 0l;
            if (datalist != null) {
                total = secPriceDayDao.getCount(map);
            }
            result.put("items", datalist);
            result.put("total", total);
        } else if (tabletype.equals("w")) {

        } else if (tabletype.equals("m")) {

        } else if (tabletype.equals("y")) {

        }
        return result;
    }

    //获取公司行业研报
    public SearchReportBO industryReport(ReportRequest request) {
        SearchReportBO result = new SearchReportBO();
        try {
            if (request.getSelected() != null && !request.getSelected().isEmpty()) {
                result = search2Service.buildReportSearchResult(request);
            } else {
                List<Map> queryList = secIndustryNewDao.getCodeIndu(request.getStock_code());
                if (queryList != null && queryList.size() > 0) {
                    String third_indu_name = queryList.get(0).get("third_indu_name").toString();
                    String selected = "industry_txt," + third_indu_name;
                    request.setSelected(selected);
                    result = search2Service.buildReportSearchResult(request);
                }
            }
        } catch (Exception e) {
            logger.info(e.toString());
            return result;
        }
        return result;
    }

    //获取公司财务摘要
    public Map summary(Map map){
        Map result=new HashMap();
        Long code = 0l;
        if (map.containsKey("code")) {
            code = Long.parseLong(map.get("code").toString());
        }
        String reporttypes = map.getOrDefault("reporttype", "1,5").toString();
        SecBasicInfoModel stockInfo = secBasicInfoDao.selectBySecUniCodeHS(code);
        long com_uni_code=stockInfo.getCom_uni_code();
        String temp_endDate = "";
        List<ComProfitModel>  clist= comProfitDao.getTopNRecords(com_uni_code,1);
        if (clist != null) {
            temp_endDate = clist.get(0).getEnd_date().toLocaleString().substring(0,9);
        }
        map.put("reporttype", convertToSQL(reporttypes, temp_endDate).replaceAll("end_date","a.end_date"));
        map.put("orderby",map.getOrDefault("orderby"," desc ").toString());
        map.put("limit",map.getOrDefault("limit","10").toString());
        map.put("offset",map.getOrDefault("offset","0").toString());
        List<Map<String,Object>> queryList=comProfitSheetYoyDao.financialSummary(map);
        Map basicMap=new HashMap();
        basicMap.put("com_uni_code",com_uni_code);
        String total_staff=null;
        List<CombasicinfoModel> combasicinfoModels=comBasicInfoDao.find(basicMap);
        if(combasicinfoModels.size()>0){
            total_staff=combasicinfoModels.get(0).getTotal_staff();
        }
        Map dataMap=new LinkedHashMap();
        for(Map<String,Object> s:queryList){
            String key=s.get("end_date").toString().substring(0,10).replace("-","");
            s.put("reporttype",covertTimePartner(s.get("end_date").toString()).replaceAll("\\d+",""));
            Map proMap=new HashMap();
            proMap.put("com_uni_code",com_uni_code);
            proMap.put("limit",1);
            proMap.put("offset",0);
            proMap.put("end_time",s.get("end_date").toString());
            proMap.put("orderby"," order by end_date desc ");
            List<ComrdExpenseModel> comrdExpenseModels=comrdExpenseDao.find(proMap);
            if(comrdExpenseModels.size()>0){
                s.put("rd_total",comrdExpenseModels.get(0).getRd_total());
            }else{
                s.put("rd_total",null);
            }
            s.put("total_staff",total_staff);
            Map norMap=new HashMap();
            norMap.put("sec_uni_code",code);
            norMap.put("end_time",s.get("end_date").toString());
            norMap.put("limit",1);
            norMap.put("offset",0);
            norMap.put("orderby"," order by account_date desc ");
            List<NormalValuationIndexModel> normalValuationIndexModelList=normalValuationIndexDao.find(norMap);
            if(normalValuationIndexModelList.size()>0){
                NormalValuationIndexModel normalValuationIndexMode=normalValuationIndexModelList.get(0);
                s.put("pettm",normalValuationIndexMode.getPettm());
                s.put("pelyr",normalValuationIndexMode.getPelyr());
                s.put("pbmrq",normalValuationIndexMode.getPbmrq());
                s.put("psttm",normalValuationIndexMode.getPsttm());
            }else{
                s.put("pettm",null);
                s.put("pelyr",null);
                s.put("pbmrq",null);
                s.put("psttm",null);
            }
            dataMap.put(key,s);
        }
        result.put("items",dataMap);
        result.put("tree",customNavigation.financialsummary());
        return result;
    }

    //公司资产负债
    public Map liabilities(Map map){
        Map result=new HashMap();
        List itemList=new ArrayList();
        Long code = 0l;
        if (map.containsKey("code")) {
            code = Long.parseLong(map.get("code").toString());
        }
        SecBasicInfoModel stockInfo = secBasicInfoDao.selectBySecUniCodeHS(code);
        long com_uni_code=stockInfo.getCom_uni_code();
        Map paMap=new HashMap();
        paMap.put("com_uni_code",com_uni_code);
        paMap.put("consolidation",1501002);
        paMap.put("orderby"," desc ");
        paMap.put("limit","1");
        paMap.put("offset","0");
        String reporttypes = map.getOrDefault("reporttype", "1,5").toString();
        List<ComBalanceModel>  comBalanceModelList= comBalanceDao.find(paMap);
        String temp_endDate = "";
        String report_format="";
        if (comBalanceModelList.size()>0) {
            temp_endDate = comBalanceModelList.get(0).getEnd_date().toLocaleString().substring(0,9);
            report_format=comBalanceModelList.get(0).getReport_format()+"";
        }
        map.put("com_uni_code",com_uni_code);
        map.put("consolidation",1501002);
        map.put("reporttype", convertToSQL(reporttypes, temp_endDate));
        map.put("report_format",report_format);
        map.put("limit",map.getOrDefault("limit","10").toString());
        map.put("offset",map.getOrDefault("offset","0").toString());
        map.put("orderby",map.getOrDefault("orderby","desc").toString());
        List<ComBalanceModel>  comBalanceModelLists= comBalanceDao.find(map);
        switch(report_format){
            case "1013025":
                comBalanceModelLists.stream().forEach(s->{
                    JSONObject json = (JSONObject) JSONObject.toJSON(s);
                    RCompanyGeneralDebt rompanyGeneralDebt = JSONObject.toJavaObject(json, RCompanyGeneralDebt.class);
                    itemList.add(rompanyGeneralDebt);
                });
                break;
            case "1013002":
                comBalanceModelLists.stream().forEach(s->{
                    JSONObject json = (JSONObject) JSONObject.toJSON(s);
                    RCompanyDebtBank rcompanyDebtBank = JSONObject.toJavaObject(json, RCompanyDebtBank.class);
                    itemList.add(rcompanyDebtBank);
                });
                break;
            case "1013006":
                comBalanceModelLists.stream().forEach(s->{
                    JSONObject json = (JSONObject) JSONObject.toJSON(s);
                    RCompanySecuritiesDebt rcompanySecuritiesDebt = JSONObject.toJavaObject(json, RCompanySecuritiesDebt.class);
                    itemList.add(rcompanySecuritiesDebt);
                });
                break;
            case "1013003":
                comBalanceModelLists.stream().forEach(s->{
                    JSONObject json = (JSONObject) JSONObject.toJSON(s);
                    RCompanyInsuranceDebt rcompanyInsuranceDebt = JSONObject.toJavaObject(json, RCompanyInsuranceDebt.class);
                    itemList.add(rcompanyInsuranceDebt);
                });
                break;
        }
        result.put("type",report_format);
        result.put("items", itemList);
        result.put("tree",null);
        return result;
    }

    //获取公司业绩预告
    public Map foreshow(Map map) {
        Map result=new HashMap();
        List<RCompanyForeshow> resultList = new ArrayList();
        Map prarMap = new HashMap();
        Long code = 0l;
        if (map.containsKey("code")) {
            code = Long.parseLong(map.get("code").toString());
        }
        String reporttypes = map.getOrDefault("reporttype", "1,5").toString();
        SecBasicInfoModel stockInfo = secBasicInfoDao.selectBySecUniCodeHS(code);
        if(stockInfo!=null){
            prarMap.put("limit", map.get("limit") == null ? "20" : map.get("limit"));
            prarMap.put("offset", map.get("offset") == null ? "0" : map.get("offset"));
            prarMap.put("com_uni_code", stockInfo.getCom_uni_code().toString());
            Map pMap = new HashMap();
            pMap.put("com_uni_code", stockInfo.getCom_uni_code().toString());
            pMap.put("limit", 1);
            pMap.put("offset", 0);
            pMap.put("orderby", "order by end_date desc");
            String temp_endDate = "";
            List<ComForecastModel> tempList = comForecastDao.find(pMap);
            if (tempList != null) {
                temp_endDate = tempList.get(0).getEnd_date().toLocaleString();
            }
            prarMap.put("reporttypesql", convertToSQL(reporttypes, temp_endDate));
            prarMap.put("orderby", map.get("orderby") == null ? " order by end_date desc" : " order by end_date " + map.get("orderby").toString());
            prarMap.put("start_time", map.get("start_time"));
            prarMap.put("end_time", map.get("end_time"));
            List<ComForecastModel> querylists = comForecastDao.find(prarMap);
            querylists.stream().forEach(o->{
                JSONObject json = (JSONObject) JSONObject.toJSON(o);
                RCompanyForeshow rcompanyForeshow = JSONObject.toJavaObject(json, RCompanyForeshow.class);
                resultList.add(rcompanyForeshow);
            });
        }
        result.put("items",resultList);
        return result;
    }

    private String convertToSQL(String reporttypes, String temp_endDate) {
        StringBuffer reporttypesql = new StringBuffer();
        List<String> rtypelist = new ArrayList<>();
        List reporttypeList = Arrays.asList(reporttypes.split(","));
        if (reporttypeList.contains("1")) {
            rtypelist.add("'1231'");
        }
        if (reporttypeList.contains("2")) {
            rtypelist.add("'0930'");
        }
        if (reporttypeList.contains("3")) {
            rtypelist.add("'0630'");
        }
        if (reporttypeList.contains("4")) {
            rtypelist.add("'0331'");
        }
        if (rtypelist.size() > 0) {
            if (reporttypeList.contains("5")) {
                reporttypesql.append(" AND  ( DATE_FORMAT(end_date,'%m%d') IN  ( ").append(rtypelist.toString().replace("[", "").replace("]", "")).append(" )");
                reporttypesql.append(" OR end_date='" + temp_endDate + "' ) ");
            } else {
                reporttypesql.append(" AND DATE_FORMAT(end_date,'%m%d') IN  ( ").append(rtypelist.toString().replace("[", "").replace("]", "")).append(" )");
            }
        } else {
            if (reporttypeList.contains("5")) {
                reporttypesql.append(" AND end_date='" + temp_endDate + "'");
            }
        }
        return reporttypesql.toString();
    }

    private String covertTimePartner(String time) {
        String result = time;
        if (time.contains("12-31")) {
            result = time.substring(0, 4) + "年报";
        } else if (time.contains("06-30")) {
            result = time.substring(0, 4) + "中报";
        } else if (time.contains("03-31")) {
            result = time.substring(0, 4) + "一季报";
        } else if (time.contains("09-30")) {
            result = time.substring(0, 4) + "三季报";
        }
        return result;
    }

    private  long dateToStamp(String dateStr,String format){
        long result=0l;
        try {
            SimpleDateFormat sdf=new SimpleDateFormat(format);
            Date date = sdf.parse(dateStr);
            result=date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }
}
