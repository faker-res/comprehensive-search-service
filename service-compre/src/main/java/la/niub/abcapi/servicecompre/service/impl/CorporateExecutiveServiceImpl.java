package la.niub.abcapi.servicecompre.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import la.niub.abcapi.servicecompre.component.util.HttpUtil;
import la.niub.abcapi.servicecompre.component.util.NumberUtil;
import la.niub.abcapi.servicecompre.component.util.StringUtil;
import la.niub.abcapi.servicecompre.dao.IWeiboWaveNewDao;
import la.niub.abcapi.servicecompre.dao.notice.IHiborDao;
import la.niub.abcapi.servicecompre.dao.notice.INoticeDao;
import la.niub.abcapi.servicecompre.dao.reporter.*;
import la.niub.abcapi.servicecompre.model.*;
import la.niub.abcapi.servicecompre.model.nosql.WeiboWaveNewModel;
import la.niub.abcapi.servicecompre.service.ICorporateExecutiveService;
import la.niub.abcapi.servicecompre.service.IIndustryService;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CorporateExecutiveServiceImpl implements ICorporateExecutiveService{

    @Autowired
    private ICorporateExecutiveDao iCorporateExecutiveDao;

    @Autowired
    IPeoLeaderDao iPeoLeaderDao;

    @Autowired
    IComBasicInfoDao iComBasicInfoDao;

    @Autowired
    IPeoBasicInfoDao iPeoBasicInfoDao;

    @Value("${oss.file_server_host}")
    private String oss_path_prefix;

    @Value("${feign.client.apiNews.url}")
    private String apiNewsUrl;

    @Autowired
    IComLedRewardStatDao iComLedRewardStatDao;

    @Autowired
    ISecBasicInfoDao iSecBasicInfoDao;

    @Autowired
    IOvsecHKShareSalaryDao iOvsecHKShareSalaryDao;

    @Autowired
    IOvsecUSShareSalaryDao iOvsecUSShareSalaryDao;

    @Autowired
    ISecPriceDayDao iSecPriceDayDao;

    @Autowired
    IOvsecHKPriceDayDao iOvsecHKPriceDayDao;

    @Autowired
    IOvsecUSPriceDayDao iOvsecUSPriceDayDao;

    @Autowired
    ISecMainHolderDao iSecMainHolderDao;

    @Autowired
    IOvsecMainHoldDao iOvsecMainHoldDao;

    @Autowired
    ILedAchievementRiskDao iLedAchievementRiskDao;

    @Autowired
    IComIpoInfoNewDao iComIpoInfoNewDao;

    @Autowired
    IIndustryService iIndustryService;

    @Autowired
    IHiborDao iHiborDao;

    @Autowired
    IUserWeiboInfoDao iUserWeiboInfoDao;

    @Autowired
    INoticeDao iNoticeDao;

    private static Pattern NUMBER_PATTERN = Pattern.compile("营业收入(\\d)+亿元");

    @Autowired
    IFundUserNewsDao iFundUserNewsDao;

    @Autowired
    ISearchIndexDao iSearchIndexDao;

    @Autowired
    ISearchIndexDataDao iSearchIndexDataDao;

    @Autowired
    IWeiboWaveNewDao iWeiboWaveNewDao;

    @Autowired
    INtfEntityComSubDao iNtfEntityComSubDao;

    @Autowired
    IOvsecIpoInfoDao iOvsecIpoInfoDao;

    @Override
    public CorporateExecutiveBasicInfoModel selectCorporateExecutiveBasicInfoByPeoUniCode(Long peo_uni_code) throws Exception {

        List<CorporateExecutiveBasicInfoModel> corporateExecutiveBasicInfoModelList = iCorporateExecutiveDao.selectCorporateExecutiveBasicInfoByPeoUniCode(peo_uni_code);
        if (corporateExecutiveBasicInfoModelList.size() == 0) {
            return null;
        }

        CorporateExecutiveBasicInfoModel corporateExecutiveBasicInfoModel = corporateExecutiveBasicInfoModelList.get(0);
        String oss_path = corporateExecutiveBasicInfoModel.getOss_path();
        if (!StringUtil.isEmpty(oss_path)) {
            corporateExecutiveBasicInfoModel.setOss_path(oss_path_prefix + "/" + oss_path);
        }

        String name = corporateExecutiveBasicInfoModel.getLed_name();
        String bdbk_url = "https://baike.baidu.com/item/" + name;
//        corporateExecutiveBasicInfoModel.setBdbk_url("https://baike.baidu.com/item/" + name);

        HttpURLConnection conn = (HttpURLConnection)new URL(bdbk_url).openConnection();
        int code = conn.getResponseCode();
        String connStr = conn.toString();
        Boolean isWrong = connStr.endsWith("https://baike.baidu.com/error.html");
        if (!isWrong) {
            corporateExecutiveBasicInfoModel.setBdbk_url(bdbk_url);
        }

        String birthDay = corporateExecutiveBasicInfoModel.getBirth_day();
        if (NumberUtils.isNumber(birthDay)) {
            Integer length = birthDay.length();
            if (length == 4) {
                birthDay = birthDay + "年";
            } else if (length > 4 && length < 7) {
                birthDay = birthDay.substring(0, 4) + "年" + birthDay.substring(4) + "月";
            } else if (length > 7) {
                birthDay = birthDay.substring(0, 4) + "年" + birthDay.substring(4, 6) + "月" + birthDay.substring(6) + "日";
            }
        }
        corporateExecutiveBasicInfoModel.setBirth_day(birthDay);

        return corporateExecutiveBasicInfoModel;
    }

    @Override
    public List<CorporateExecutiveSameComModel> selectCorporateExecutiveSameComList(Long peo_uni_code) throws Exception {
        List<CorporateExecutiveSameComModel> corporateExecutiveSameComModelList = iCorporateExecutiveDao.selectCorporateExecutiveSameComByPeoUniCode(peo_uni_code);

        if (StringUtil.isEmpty(corporateExecutiveSameComModelList)) {
            corporateExecutiveSameComModelList = iCorporateExecutiveDao.getSameComWhenComIsInUk(peo_uni_code);
        }

        if (StringUtil.isEmpty(corporateExecutiveSameComModelList)) {
            corporateExecutiveSameComModelList = iCorporateExecutiveDao.getSameComWhenComIsInUS(peo_uni_code);
        }

        List<CorporateExecutiveSameComModel> resultList = new ArrayList<>();

        OUT:
        for (CorporateExecutiveSameComModel corporateExecutiveSameComModel : corporateExecutiveSameComModelList) {
            for (int i = 0; i < resultList.size(); i++) {
                CorporateExecutiveSameComModel corporateExecutiveSameComModelItem = resultList.get(i);
                if (corporateExecutiveSameComModelItem.getPeo_uni_code().equals(corporateExecutiveSameComModel.getPeo_uni_code())) {
                    String post_name = corporateExecutiveSameComModel.getPost_name();
                    corporateExecutiveSameComModelItem.setPost_name(corporateExecutiveSameComModelItem.getPost_name() + (StringUtil.isEmpty(post_name) ? "" : " " + post_name));
                    continue OUT;
                }
            }

            String oss_path = corporateExecutiveSameComModel.getOss_path();
            if (!StringUtil.isEmpty(oss_path)) {
                corporateExecutiveSameComModel.setOss_path(oss_path_prefix + "/" + oss_path);
            }

            resultList.add(corporateExecutiveSameComModel);
        }

        return resultList;
    }

    @Override
    public List<Map<String, Object>> getPeoComList(Long peo_uni_code) throws Exception {
        return iPeoLeaderDao.getPeoComNameByPeoUniCode(peo_uni_code);
    }

    @Override
    public List<Map<String, Object>> getPeoNews(Long peo_uni_code, Integer limit) throws Exception {
        if (StringUtil.isEmpty(limit)) {
            limit = 5;
        }

        String peoName = iPeoBasicInfoDao.selectNameByPeoUniCode(peo_uni_code);
        if (StringUtil.isEmpty(peoName)) {
            return null;
        }

        List<String> comSnameList  = new ArrayList<>();
        List<Long> com_uni_code_list = iPeoLeaderDao.getComUniCodeByPeoUniCode(peo_uni_code);
        if (!StringUtil.isEmpty(com_uni_code_list)) {
            comSnameList = iComBasicInfoDao.selectComSnameByComUniCodeList(com_uni_code_list);
        }

        StringBuffer keyword = new StringBuffer("$content:(\"" + peoName + "\"");

        if (comSnameList.isEmpty()) {
            keyword.append(")");
        } else {
            for (int i = 0; i < comSnameList.size(); i++) {
                String comSname = comSnameList.get(i);
                if (i == 0) {
                    keyword.append(" AND (\"" + comSname + "\"");
                } else {
                    keyword.append(" OR \"" + comSname + "\"");
                }
            }

            keyword.append(")");
        }

        keyword.append(")");

        Map<String, String> newsRequest = new HashMap<>();
        newsRequest.put("keyword", keyword.toString());
        newsRequest.put("offset", "0");
        newsRequest.put("limit", limit + "");
        newsRequest.put("prior", "time");
        newsRequest.put("channel", "");
        newsRequest.put("core_name", "core_news");
        newsRequest.put("single", "false");

        String result = HttpUtil.post(apiNewsUrl, newsRequest, null);

        List<Map<String, Object>> peoNewsList = new ArrayList<>();

        JSONObject resultObj = JSONObject.parseObject(result);
        JSONObject data = resultObj.getJSONObject("data");
        if (!StringUtil.isEmpty(data)) {
            JSONArray items = data.getJSONArray("item");
            if (!StringUtil.isEmpty(items)) {
                for (Object itemObj : items) {
                    Map<String, Object> peoNews = new HashMap<>();
                    JSONObject item = JSONObject.parseObject(itemObj.toString());
                    if (!StringUtil.isEmpty(item)) {
                        String title = item.getString("title");
                        if (!StringUtil.isEmpty(title)) {
                            for (String comSname :comSnameList) {
                                String replaceWord = "<font color='red'>" + comSname + "</font>";
                                title = title.replace(replaceWord, comSname);
                            }
                        }
                        peoNews.put("title", title);

                        String url = item.getString("url");
                        peoNews.put("url", url);

                        String image = item.getString("first_image_oss");
                        peoNews.put("image", image);

                        String brief = item.getString("brief");
                        if (StringUtil.isEmpty(brief)) {
                            String content = StringUtil.stripHtml(item.getString("content"));
                            brief = content.length() > 100 ? content.substring(0, 100) + "......" : content;
                        }

                        for (String comSname :comSnameList) {
                            String replaceWord = "<font color='red'>" + comSname + "</font>";
                            brief = brief.replace(replaceWord, comSname);
                        }
                        peoNews.put("brief", brief);

                        Long time = item.getLong("time");
                        peoNews.put("time", time);

                        String sourceName = item.getString("source_name");
                        peoNews.put("sourceName", sourceName);

                        String id = item.getString("id");
                        peoNews.put("id", id);

                        peoNewsList.add(peoNews);
                    }
                }
            }
        }

        return peoNewsList;
    }

    @Override
    public List<Map<String, Object>> getEmolumentInfo(Long com_uni_code) throws Exception {
        Long secType = iSecBasicInfoDao.getSecTypeByComUniCode(com_uni_code);
        Long sec_uni_code = iSecBasicInfoDao.getSecUniCodeByComUniCode(com_uni_code);
        List<Long> peo_uni_code_list = iPeoLeaderDao.getPeoUniCodeListByComUniCode(com_uni_code);
        if (StringUtil.isEmpty(peo_uni_code_list)) {
            return null;
        }
        List<Map<String, Object>> emolumentInfoList = new ArrayList<>();
        for (Long peo_uni_code : peo_uni_code_list) {
            List<Map<String, Object>> stockHolderInfoList = new ArrayList<>();
            List<Date> latestFourDateList = new ArrayList<>();
            BigDecimal price = new BigDecimal(0);
            String unit = "";
            switch (secType + "") {
                case "1004001":
                    latestFourDateList = iComLedRewardStatDao.getLatestFourDateByComUniCode(com_uni_code, peo_uni_code);
                    if (StringUtil.isEmpty(latestFourDateList)) {
                        continue;
                    }

                    stockHolderInfoList = iComLedRewardStatDao.getStockHolderInfo(com_uni_code, peo_uni_code, latestFourDateList);
                    if (StringUtil.isEmpty(stockHolderInfoList)) {
                        continue;
                    }

                    price = iSecPriceDayDao.getClosePriceBySecUniCode(sec_uni_code);
                    unit = "元";
                    break;
                case "1004017":
                    latestFourDateList = iOvsecHKShareSalaryDao.getLatestFourDateByComUniCode(com_uni_code, peo_uni_code);
                    if (StringUtil.isEmpty(latestFourDateList)) {
                        continue;
                    }

                    stockHolderInfoList = iOvsecHKShareSalaryDao.getStockHolderInfo(com_uni_code, peo_uni_code, latestFourDateList);
                    if (StringUtil.isEmpty(stockHolderInfoList)) {
                        continue;
                    }

                    price = iOvsecHKPriceDayDao.getClosePriceBySecUniCode(sec_uni_code);
                    unit = "港元";
                    break;
                case "1004018":
                    latestFourDateList = iOvsecUSShareSalaryDao.getLatestFourDateByComUniCode(com_uni_code, peo_uni_code);
                    if (StringUtil.isEmpty(latestFourDateList)) {
                        continue;
                    }

                    stockHolderInfoList = iOvsecUSShareSalaryDao.getStockHolderInfo(com_uni_code, peo_uni_code, latestFourDateList);
                    if (StringUtil.isEmpty(stockHolderInfoList)) {
                        continue;
                    }

                    price = iOvsecUSPriceDayDao.getClosePriceBySecUniCode(sec_uni_code);
                    unit = "美元";
                    break;
                default:
            }

            if (StringUtil.isEmpty(stockHolderInfoList)) {
                return null;
            }

            Map<String, Object> emolumentInfoItem = new HashMap<>();

            emolumentInfoItem.put("peo_uni_code", stockHolderInfoList.get(0).get("peo_uni_code"));
            emolumentInfoItem.put("led_name", stockHolderInfoList.get(0).get("led_name"));
            emolumentInfoItem.put("unit", unit);
            emolumentInfoItem.put("post_name", stockHolderInfoList.get(0).get("post_name"));

//            List<Map<String, Object>> emolumentInfoList = new ArrayList<>();
            List<Map<String, Object>> salaryAndStockData = new ArrayList<>();
            for (Map<String, Object> stockHolderInfo : stockHolderInfoList) {
                Map<String, Object> emolumentInfo = new HashMap<>();


                emolumentInfo.put("end_date", stockHolderInfo.get("end_date"));

                Double salary = Double.parseDouble(StringUtil.isEmpty(stockHolderInfo.get("salary")) ? "0" : stockHolderInfo.get("salary").toString());
                emolumentInfo.put("salary", salary);


                Double stockCount = Double.parseDouble(StringUtil.isEmpty(stockHolderInfo.get("stockCount")) ? "0" : stockHolderInfo.get("stockCount").toString());
                Double stockValue = stockCount * price.doubleValue();
                emolumentInfo.put("stockValue", stockValue);
                Double totalValue = salary + stockValue;
                emolumentInfo.put("totalValue", totalValue);

                salaryAndStockData.add(emolumentInfo);
            }
            emolumentInfoItem.put("salaryAndStockData", salaryAndStockData);
            emolumentInfoList.add(emolumentInfoItem);
        }


        Collections.sort(emolumentInfoList, new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                return (int)((double)((List<Map>)o2.get("salaryAndStockData")).get(0).get("totalValue") - (double)((List<Map>)o1.get("salaryAndStockData")).get(0).get("totalValue"));
            }
        });

        return emolumentInfoList;
    }

    @Override
    public List<Map<String, Object>> getStockHoldInfo(Long com_uni_code) throws Exception {
        Long secType = iSecBasicInfoDao.getSecTypeByComUniCode(com_uni_code);
        List<Map<String, Object>> mainHolderInfoList = new ArrayList<>();
        List<Date> latestFiveDateByComUniCode = new ArrayList<>();
        List<Map<String, Object>> stockHoldInfoList = new ArrayList<>();
        List<Long> org_uni_code_list = new ArrayList<>();
        List<Long> peo_uni_code_list = new ArrayList<>();
        List<Map<String, Object>> mainHolderCodeList = new ArrayList<>();
        List<Map<String, Object>> mainOrgHolderInfoList = new ArrayList<>();
        List<Map<String, Object>> mainPeoHolderInfoList = new ArrayList<>();
        switch (secType + "") {
            case "1004001":
                latestFiveDateByComUniCode = iSecMainHolderDao.getLatestFiveDateByComUniCode(com_uni_code);
                if (StringUtil.isEmpty(latestFiveDateByComUniCode)) {
                    break;
                }

                mainHolderCodeList = iSecMainHolderDao.getMainHolderCode(com_uni_code, latestFiveDateByComUniCode.get(0));
                if (StringUtil.isEmpty(mainHolderCodeList)) {
                    break;
                }

                for (Map<String, Object> mainHolderCode : mainHolderCodeList) {
                    Object org_uni_code = mainHolderCode.get("org_uni_code");
                    if (!StringUtil.isEmpty(org_uni_code) && NumberUtils.isNumber(org_uni_code.toString()) && Long.parseLong(org_uni_code.toString()) != 0) {
                        org_uni_code_list.add(Long.parseLong(org_uni_code.toString()));
                    }

                    Object peo_uni_code = mainHolderCode.get("peo_uni_code");
                    if (!StringUtil.isEmpty(peo_uni_code) && NumberUtils.isNumber(peo_uni_code.toString()) && Long.parseLong(peo_uni_code.toString()) != 0) {
                        peo_uni_code_list.add(Long.parseLong(peo_uni_code.toString()));
                    }
                }

                for (Long org_uni_code : org_uni_code_list) {
                    mainOrgHolderInfoList = iSecMainHolderDao.getMainHolderInfo(com_uni_code, org_uni_code, null, latestFiveDateByComUniCode);
                    if (StringUtil.isEmpty(mainOrgHolderInfoList)) {
                        continue;
                    }

                    Map<String, Object> stockHoldInfo = getStockHolderInfoItem(mainOrgHolderInfoList);
                    stockHoldInfoList.add(stockHoldInfo);
                }

                for (Long peo_uni_code : peo_uni_code_list) {
                    mainPeoHolderInfoList = iSecMainHolderDao.getMainHolderInfo(com_uni_code, null, peo_uni_code, latestFiveDateByComUniCode);
                    if (StringUtil.isEmpty(mainPeoHolderInfoList)) {
                        continue;
                    }

                    Map<String, Object> stockHoldInfo = getStockHolderInfoItem(mainPeoHolderInfoList);
                    stockHoldInfoList.add(stockHoldInfo);
                }

                break;
            case "1004017":
            case "1004018":
                latestFiveDateByComUniCode = iOvsecMainHoldDao.getLatestFiveDateByComUniCode(com_uni_code);
                if (StringUtil.isEmpty(latestFiveDateByComUniCode)) {
                    break;
                }

                mainHolderCodeList = iOvsecMainHoldDao.getMainHolderCode(com_uni_code, latestFiveDateByComUniCode.get(0));
                if (StringUtil.isEmpty(mainHolderCodeList)) {
                    break;
                }

                for (Map<String, Object> mainHolderCode : mainHolderCodeList) {
                    Object org_uni_code = mainHolderCode.get("org_uni_code");
                    if (!StringUtil.isEmpty(org_uni_code) && NumberUtils.isNumber(org_uni_code.toString()) && Long.parseLong(org_uni_code.toString()) != 0) {
                        org_uni_code_list.add(Long.parseLong(org_uni_code.toString()));
                    }

                    Object peo_uni_code = mainHolderCode.get("peo_uni_code");
                    if (!StringUtil.isEmpty(peo_uni_code) && NumberUtils.isNumber(peo_uni_code.toString()) && Long.parseLong(peo_uni_code.toString()) != 0) {
                        peo_uni_code_list.add(Long.parseLong(peo_uni_code.toString()));
                    }
                }

                for (Long org_uni_code : org_uni_code_list) {
                    mainOrgHolderInfoList = iOvsecMainHoldDao.getMainHolderInfo(com_uni_code, org_uni_code, null, latestFiveDateByComUniCode);
                    if (StringUtil.isEmpty(mainOrgHolderInfoList)) {
                        continue;
                    }

                    Map<String, Object> stockHoldInfo = getStockHolderInfoItem(mainOrgHolderInfoList);
                    stockHoldInfoList.add(stockHoldInfo);
                }

                for (Long peo_uni_code : peo_uni_code_list) {
                    mainPeoHolderInfoList = iOvsecMainHoldDao.getMainHolderInfo(com_uni_code, null, peo_uni_code, latestFiveDateByComUniCode);
                    if (StringUtil.isEmpty(mainPeoHolderInfoList)) {
                        continue;
                    }

                    Map<String, Object> stockHoldInfo = getStockHolderInfoItem(mainPeoHolderInfoList);
                    stockHoldInfoList.add(stockHoldInfo);
                }
        }

        Collections.sort(stockHoldInfoList, new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                return (int)((double)((List<Map>)o2.get("holdChangeData")).get(0).get("stockCount") - (double)((List<Map>)o1.get("holdChangeData")).get(0).get("stockCount"));
            }
        });

        return stockHoldInfoList;
    }

    private Map<String, Object> getStockHolderInfoItem(List<Map<String, Object>> mainPeoHolderInfoList) {
        Map<String, Object> stockHoldInfo = new HashMap<>();
        stockHoldInfo.put("com_uni_code", mainPeoHolderInfoList.get(0).get("com_uni_code"));
        stockHoldInfo.put("holderName", mainPeoHolderInfoList.get(0).get("holderName"));

        List<Map<String, Object>> holdChangeData = new ArrayList<>();
        for (Map<String, Object> mainHolderInfo : mainPeoHolderInfoList) {
            Map<String, Object> holdChangeItem = new HashMap<>();
            holdChangeItem.put("end_date", mainHolderInfo.get("end_date"));

            Double stockCount = 0.0;
            if (!StringUtil.isEmpty(mainHolderInfo.get("stockCount"))) {
                stockCount = ((BigDecimal) mainHolderInfo.get("stockCount")).doubleValue();
            }
            holdChangeItem.put("stockCount", stockCount);
            holdChangeItem.put("proportion", mainHolderInfo.get("proportion"));

            //这个数据可能是string类型，也有可能是数字
            String holdChangeCount = StringUtil.isEmpty(mainHolderInfo.get("holdChangeCount"))? "0" : mainHolderInfo.get("holdChangeCount").toString();
            Double holdChangeCountNum = 0.0;
            switch (holdChangeCount) {
                case "不变":
                    break;
                case "新进":
                    holdChangeCountNum = stockCount;
                    break;
                default:
                    holdChangeCountNum = new BigDecimal(holdChangeCount).doubleValue();
            }
            holdChangeItem.put("holdChangeCount", holdChangeCountNum);
            holdChangeData.add(holdChangeItem);
        }
        stockHoldInfo.put("holdChangeData", holdChangeData);

        return stockHoldInfo;
    }

    @Override
    public Map<String, Object> getAchieveAndRiskData(Long peo_uni_code, Long com_uni_code) throws Exception {
        Map<String, Object> achieveAndRiskData = new HashMap<>();
        List<String> achievementList = new ArrayList<>();
        List<Map<String, Object>> postInfoList = iLedAchievementRiskDao.getPostInfoByPeoUniCode(peo_uni_code);
        StringBuffer postInfoStr = new StringBuffer();
        if (!StringUtil.isEmpty(postInfoList)) {
            postInfoStr.append("共担任");
            for (Map<String, Object> postInfo : postInfoList) {
                Integer comCount = Integer.parseInt(postInfo.get("comCount").toString());
                String post_name = postInfo.get("post_name").toString();
                postInfoStr.append("<i style=\" font-style: normal;color: #5480D0;\">" + comCount + "</i>" + "家公司" + post_name + ",");
            }
            achievementList.add(postInfoStr.toString().substring(0, postInfoStr.length() > 0 ? postInfoStr.length() - 1 : 0));
        }


        Integer listedComCount = iLedAchievementRiskDao.getListedComCountByPeoUniCode(peo_uni_code);
        List<Long> com_uni_code_list = iLedAchievementRiskDao.getComUniCodeListByPeoUniCode(peo_uni_code);
        if (!StringUtil.isEmpty(com_uni_code_list)) {
            Object financeAmountObj = iComIpoInfoNewDao.getFinanceByComUniCodeList(com_uni_code_list);
            Double financeAmount = financeAmountObj == null ? 0.0 : new BigDecimal(financeAmountObj.toString()).doubleValue();
            List<Map<String, Object>> financeAndStockCodeList = iOvsecIpoInfoDao.getFinanceAndStockCodeByComUniCodeList(com_uni_code_list);
            Double financeAmountHK = 0.0;
            Double financeAmountUS = 0.0;
            for (Map<String, Object> item : financeAndStockCodeList) {
                String stockCode = item.get("stock_code").toString();
                Double financeCount = new BigDecimal(item.get("financeAmount").toString()).setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue();
                if (stockCode.length() == 5 && NumberUtils.isNumber(stockCode)) {
                    financeAmountHK += financeCount;
                } else {
                    financeAmountUS += financeCount;
                }
            }

            StringBuffer money = new StringBuffer();
            if (financeAmount != 0) {
                money.append("<i style=\" font-style: normal;color: #5480D0;\">" + new BigDecimal(financeAmount / 10000).setScale(2, BigDecimal.ROUND_HALF_UP) + "</i>亿元");
            }

            if (financeAmount != 0 && financeAmountUS != 0) {
                money.append("+<i style=\" font-style: normal;color: #5480D0;\">" + new BigDecimal(financeAmountUS / 10000).setScale(2, BigDecimal.ROUND_HALF_UP) + "</i>亿美元");
            } else if (financeAmountUS == 0){
            } else {
                money.append("<i style=\" font-style: normal;color: #5480D0;\">" + new BigDecimal(financeAmountUS / 10000).setScale(2, BigDecimal.ROUND_HALF_UP) + "</i>亿美元");
            }

            if (financeAmount == 0 && financeAmountUS == 0 && financeAmountHK != 0) {
                money.append("<i style=\" font-style: normal;color: #5480D0;\">" + new BigDecimal(financeAmountHK / 10000).setScale(2, BigDecimal.ROUND_HALF_UP) + "</i>亿港元");
            } else if (financeAmountHK == 0) {
            } else {
                money.append("+<i style=\" font-style: normal;color: #5480D0;\">" + new BigDecimal(financeAmountHK / 10000).setScale(2, BigDecimal.ROUND_HALF_UP) + "</i>亿港元");
            }

            String finance = "";
            if (StringUtil.isEmpty(money)) {
                finance = "<i style=\" font-style: normal;color: #5480D0;\">0</i>亿元";
            } else {
                finance = money.toString();
            }

            String comFianceInfo = "任职期间<i style=\" font-style: normal;color: #5480D0;\">" + listedComCount + "</i>家公司成功上市" +
                    (StringUtil.isEmpty(finance) ? "" : "，" + listedComCount + "家公司融资共" + finance);

            achievementList.add(comFianceInfo);
        }

        Map<String, Object> incomeAndRankAndValueInfo = iLedAchievementRiskDao.getIncomeAndIncomeYoyByComUniCode(peo_uni_code, com_uni_code);
        if (!StringUtil.isEmpty(incomeAndRankAndValueInfo)) {
            Object comIncome = incomeAndRankAndValueInfo.get("main_income");
            Object comIncomeYoyObj = incomeAndRankAndValueInfo.get("incomeYoy");
            if (!StringUtil.isEmpty(comIncome)) {
                String incomeAndYoyInfo = "";
                String comIncomeInfo = comIncome.toString();
                Matcher matcher = NUMBER_PATTERN.matcher(comIncomeInfo);
                if (matcher.find()) {
                    Integer address = matcher.start();
                    String num = matcher.group().replace("营业收入", "").replace("亿元", "");
                    incomeAndYoyInfo = "公司" + comIncomeInfo.substring(0, address + 4) + "<i style=\" font-style: normal;color: #5480D0;\">" + num + "</i>亿元";
                }

                if (!StringUtil.isEmpty(comIncomeYoyObj)) {
                    BigDecimal comIncomeYoy = new BigDecimal(incomeAndRankAndValueInfo.get("incomeYoy").toString()).multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP);
                    if (comIncomeYoy.compareTo(BigDecimal.ZERO) > 0) {
                        incomeAndYoyInfo = new StringBuffer(incomeAndYoyInfo).append(",营收增长率为<i style=\" font-style: normal;color: #5480D0;\">" + comIncomeYoy + "%</i>").toString();
                    }
                }

                if (!StringUtil.isEmpty(incomeAndYoyInfo)) {
                    achievementList.add(incomeAndYoyInfo);
                }
            }

            Integer hurunRank = Integer.parseInt(incomeAndRankAndValueInfo.get("hurun_rank") == null ? "0" : incomeAndRankAndValueInfo.get("hurun_rank").toString());
            if (!StringUtil.isEmpty(hurunRank) && hurunRank != 0) {
                String hurunRankInfo = "胡润排行榜最高<i style=\" font-style: normal;color: #5480D0;\">" + hurunRank + "</i>位";
                achievementList.add(hurunRankInfo);
            }
        }

        achieveAndRiskData.put("achievement", achievementList);

        List<String> riskList = new ArrayList<>();
        Integer peoLawsuitNum = iLedAchievementRiskDao.getTotalLawsuitNumByPeoUniCode(peo_uni_code);
        if (!StringUtil.isEmpty(peoLawsuitNum) && peoLawsuitNum != 0) {
            String peoLawsuitInfo = "个人有<i style=\" font-style: normal;color: #D36957;\">" + peoLawsuitNum + "</i>起未履行义务的法律事件";
            riskList.add(peoLawsuitInfo);
        }

        if (!StringUtil.isEmpty(incomeAndRankAndValueInfo)) {
            Object comLawsuit = incomeAndRankAndValueInfo.get("lawsuit_num");
            if (!StringUtil.isEmpty(comLawsuit)) {
                Integer comLawsuitNum = Integer.parseInt(comLawsuit.toString());
                if (comLawsuitNum > 0) {
                    String comLawsuitInfo = "所在公司卷入<i style=\" font-style: normal;color: #D36957;\">" + comLawsuitNum + "</i>件法律事件";
                    riskList.add(comLawsuitInfo);
                }
            }
        }

        BigDecimal minGrowth = iLedAchievementRiskDao.getGrowthByComUniCode(com_uni_code);
        if (!StringUtil.isEmpty(minGrowth)) {
            if (minGrowth.compareTo(new BigDecimal(0)) <= 0) {
                String growthInfo = "公司市值在2018年下降了<i style=\" font-style: normal;color: #D36957;\">" + minGrowth.multiply(new BigDecimal(-1)).setScale(2, BigDecimal.ROUND_HALF_UP) + "%</i>";
                riskList.add(growthInfo);
            }
        }

        achieveAndRiskData.put("risk", riskList);

        return achieveAndRiskData;
    }

    @Override
    public Object getHeatIndex(Long peo_uni_code, Long com_uni_code, Integer days) throws Exception {
        Calendar calendar = Calendar.getInstance();
        switch (days) {
            case 30:
                calendar.add(Calendar.MONTH, -1);
                break;
            case 90:
                calendar.add(Calendar.MONTH, -3);
                break;
            case 180:
                calendar.add(Calendar.MONTH, -6);
                break;
            default:
                calendar.add(Calendar.DAY_OF_YEAR, -days);
        }

        Date startDate = new Date(calendar.getTime().getTime() / (24 * 3600 * 1000) * 24 * 3600 * 1000 - TimeZone.getDefault().getRawOffset());;

        String name = iPeoLeaderDao.getLedNameByPeoUniCode(peo_uni_code);
        Map<String, Object> indexObj = new HashMap<>();

        //baidu
        SearchIndexModel stockBaiduInfo = iSearchIndexDao.selectByKeyword(name);
        Map<String, Object> baiduIndex = new LinkedHashMap<>();
        if (stockBaiduInfo != null){
            List<SearchIndexDataModel> indexDataModelList = iSearchIndexDataDao.selectByKeywordIdAndStartDate(stockBaiduInfo.getKeyword_id(), startDate);
            for (SearchIndexDataModel item : indexDataModelList){
                String date = FastDateFormat.getInstance("yyyy-MM-dd").format(item.getData_time());
                baiduIndex.put(date,item.getSearch_index());
            }
        }
        indexObj.put("baidu", baiduIndex);

        //weibo
        Map<String, Object> weiboIndex = new LinkedHashMap<>();
        List<WeiboWaveNewModel> weiboRecords = iWeiboWaveNewDao.getByKeyAndDate(name, startDate);
        if (weiboRecords != null || !weiboRecords.isEmpty()){
            for (WeiboWaveNewModel item : weiboRecords){
                weiboIndex.put(item.getDate(),item.getValue());
            }
        }
        indexObj.put("weibo", weiboIndex);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        //新闻总条数
        Map<String, Object> newsCount = new LinkedHashMap<>();
        List<Map<String, Object>> newCountList = iFundUserNewsDao.getNewCountByPeoUniCodeAndStartDate(peo_uni_code, startDate.getTime() / 1000);
        for (Map<String, Object> item : newCountList) {
            Object date = item.get("date");
            Object count = item.get("news_num");
            if (!StringUtil.isEmpty(date) && !StringUtil.isEmpty(count) && Integer.parseInt(count.toString()) != 0) {
                String dateFormate = simpleDateFormat.format(new Date(Long.parseLong(date.toString()) * 1000));
                newsCount.put(dateFormate, Integer.parseInt(count == null? "0" : count.toString()));
            }
        }
        indexObj.put("news", newsCount);


        List<String> abcCodeList = iSecBasicInfoDao.getAbcCodeByComUniCode(com_uni_code);
        if (StringUtil.isEmpty(abcCodeList)) {
            return indexObj;
        }

        //研报和研报数量
        Map<String, Object> report = new LinkedHashMap<>();
        String time = simpleDateFormat.format(System.currentTimeMillis());
        Map<String, Object> reportCount = new HashMap<>();
        List<Map<String, Object>> reportInfoList = iHiborDao.getReportBasicInfoByStockCodeListAndTime(abcCodeList, startDate);
        OUT:
        for (Map<String, Object> reportItem : reportInfoList) {
            Long reportId = (long)reportItem.get("id");
            String title = reportItem.get("title") == null ? "" : reportItem.get("title").toString();
            Date date = (Date) reportItem.get("time");

            Map<String, Object> titleAndId = new HashMap<>();
            time = simpleDateFormat.format(date);
            for (Map.Entry reportEntry : report.entrySet()) {
                Integer count = 1;
                String key = reportEntry.getKey().toString();
                List<Map<String, Object>> value = (List<Map<String, Object>>)((Map<String, Object>)reportEntry.getValue()).get("reportInfo");
                count = (Integer) ((Map<String, Object>)reportEntry.getValue()).get("count");
                if (time.equals(key)) {
                    titleAndId.put("id", reportId);
                    titleAndId.put("title", title);
                    value.add(titleAndId);
                    ((Map<String, Object>) reportEntry.getValue()).put("count", count + 1);
                    continue OUT;
                }
            }

            titleAndId.put("id", reportId);
            titleAndId.put("title", title);
            List<Map<String, Object>> titleAndIdList = new ArrayList<>();
            titleAndIdList.add(titleAndId);
            Map<String, Object> valueObj = new HashMap<>();
            valueObj.put("count", 1);
            valueObj.put("reportInfo", titleAndIdList);
            report.put(time, valueObj);
        }
        indexObj.put("report", report);

        //公告数量
        Map<String, Object> notice = new LinkedHashMap<>();
        List<Map<String, Object>> noticeList = iNoticeDao.getNoticeCountByStockCodeListAndStartDate(abcCodeList, startDate);
        for (Map<String, Object> noticeItem : noticeList) {
            Date publishDate = (Date) noticeItem.get("publish_at");
            Long count = (Long) noticeItem.get("noticeCount");
            time = simpleDateFormat.format(publishDate);
            JSONObject noticeCount = new JSONObject();
            noticeCount.put("count", count);
            notice.put(time, noticeCount);
        }
        indexObj.put("notice", notice);

        return indexObj;
    }

    @Override
    public List<Map<String, Object>> getPeoWeiboList(Long peo_uni_code) throws Exception {
        List<Map<String, Object>> peoWeiboList = new ArrayList<>();
        List<UserWeiboInfoModel> userWeiboInfoModelList = iUserWeiboInfoDao.getUserWeiboInfoByUserId(peo_uni_code);
        for (UserWeiboInfoModel userWeiboInfoModel : userWeiboInfoModelList) {
            Map<String, Object> peoWeibo = new HashMap<>();
            peoWeibo.put("name", userWeiboInfoModel.getAccount_name());
            peoWeibo.put("peo_uni_code", userWeiboInfoModel.getPeo_uni_code());
            peoWeibo.put("avatar", userWeiboInfoModel.getHead());
            peoWeibo.put("time", userWeiboInfoModel.getPublish_time() * 1000);
            peoWeibo.put("url", userWeiboInfoModel.getUrl());
            String img = userWeiboInfoModel.getImg().replace("[", "").replace("]", "").replace("\"", "");
            String[] img_list = new String[]{};
            if (!StringUtil.isEmpty(img)) {
                if (img.contains(",")) {
                    img_list = img.split(",");
                } else {
                    img_list = new String[] {img};
                }
            }
            peoWeibo.put("img_list", img_list);

            peoWeibo.put("weibo_id", userWeiboInfoModel.getWeibo_article_id());
            peoWeibo.put("speech", userWeiboInfoModel.getContent());
            peoWeibo.put("verify", userWeiboInfoModel.getVerify());
            peoWeibo.put("member_ranking", userWeiboInfoModel.getMember_ranking());

            peoWeiboList.add(peoWeibo);
        }

        return peoWeiboList;
    }

    @Override
    public Map<String, Object> getInvestInfo(Long peo_uni_code, Integer page_num, Integer limit, String order_field, String order_type) throws Exception {
        Integer count = iNtfEntityComSubDao.getInvestInfoCountByPeoUniCode(peo_uni_code);
        if (count == null || count == 0) {
            return null;
        }

        StringBuilder order = new StringBuilder();
        if (!StringUtil.isEmpty(order_field) && !StringUtil.isEmpty(order_field)) {
            boolean isValidField = ("estiblish_time".equals(order_field) || "ownership_stake".equals(order_field))
                    && ("desc".equals(order_type) || "asc".equals(order_type));
            if (isValidField) {
                if ("estiblish_time".equals(order_field)) {
                    order_field = "estibilish_time";
                }
                order.append(order_field).append(" ").append(order_type).append(",");
            }
        }
        order.append("id desc");

        Integer index = (page_num - 1) * limit;
        List<Map<String, Object>> investInfoList = iNtfEntityComSubDao.getInvestInfoListByPeoUniCode(peo_uni_code, index, limit, order.toString());

        if (investInfoList == null || investInfoList.isEmpty()) {
            return null;
        }

        Map<String, Object> result = new HashMap<>();

        Integer total = count % limit == 0 ? count / limit : (count / limit) + 1;

        result.put("item", investInfoList);
        result.put("total", total);

        return result;
    }

    @Override
    public Map<String, Object> getBusinessRole(Long peo_uni_code, Integer page_num, Integer limit, String order_field, String order_type) throws Exception {
        Integer count = iNtfEntityComSubDao.getBusinessRoleCountByPeoUniCode(peo_uni_code);
        if (count == null || count == 0) {
            return null;
        }

        StringBuilder order = new StringBuilder();
        if (!StringUtil.isEmpty(order_field) && !StringUtil.isEmpty(order_field)) {
            boolean isValidField = ("estiblish_time".equals(order_field)) && ("desc".equals(order_type) || "asc".equals(order_type));
            if (isValidField) {
                if ("estiblish_time".equals(order_field)) {
                    order_field = "estibilish_time";
                }
                order.append(order_field).append(" ").append(order_type).append(",");
            }
        }
        order.append("id desc");

        Integer index = (page_num - 1) * limit;
        List<Map<String, Object>> businessRoleList = iNtfEntityComSubDao.getBusinessRoleListByPeoUniCode(peo_uni_code, index, limit, order.toString());

        if (businessRoleList == null || businessRoleList.isEmpty()) {
            return null;
        }

        Map<String, Object> result = new HashMap<>();

        Integer total = count % limit == 0 ? count / limit : (count / limit) + 1;

        result.put("item", businessRoleList);
        result.put("total", total);

        return result;
    }
}
