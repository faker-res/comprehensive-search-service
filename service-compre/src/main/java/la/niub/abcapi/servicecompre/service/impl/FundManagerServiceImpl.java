package la.niub.abcapi.servicecompre.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import la.niub.abcapi.servicecompre.component.cache.KeyBuilder;
import la.niub.abcapi.servicecompre.component.util.HttpUtil;
import la.niub.abcapi.servicecompre.component.util.StringUtil;
import la.niub.abcapi.servicecompre.component.util.TimeUtil;
import la.niub.abcapi.servicecompre.config.configuration.RedisMarketConfiguration;
import la.niub.abcapi.servicecompre.dao.reporter.*;
import la.niub.abcapi.servicecompre.model.*;
import la.niub.abcapi.servicecompre.model.request.FundManagerFundChartRequest;
import la.niub.abcapi.servicecompre.model.request.FundManagerFundInfoRequest;
import la.niub.abcapi.servicecompre.model.response.*;
import la.niub.abcapi.servicecompre.service.IFundManagerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class FundManagerServiceImpl implements IFundManagerService{

    @Autowired
    private IFundManagerDao iFundManagerDao;

    @Value("${feign.client.apiNews.url}")
    private String apiNewsUrl;

    @Value("${oss.file_server_host}")
    private String oss_path_prefix;

    @Autowired
    @Qualifier(RedisMarketConfiguration.NAME)
    private RedisTemplate<String, ?> redisTemplate;

    @Autowired
    private IFundManagerBasicInfoDao fundManagerBasicInfoDao;

    @Autowired
    private ISecBasicInfoDao secBasicInfoDao;

    @Autowired
    private ISecPlateInfoDao secPlateInfoDao;

    @Autowired
    private IFundAssetAllocationDao fundAssetAllocationDao;

    @Autowired
    private IFundManagerPerfChgDao fundManagerPerfChgDao;

    @Autowired
    private IFundNavDao fundNavDao;

    @Override
    public FundManagerInfoModel selectFundManagerInfoByPeoUniCode(Long peo_uni_code) throws Exception {
        FundManagerInfoModel fundManagerInfoModel = iFundManagerDao.selectFundManagerInfoByPeoUniCode(peo_uni_code);
        if (fundManagerInfoModel == null) {
            return null;
        }

        String oss_path = fundManagerInfoModel.getOss_path();
        if (!StringUtil.isEmpty(oss_path)) {
            fundManagerInfoModel.setOss_path(oss_path_prefix + "/" + oss_path);
        }

        BigDecimal fund_manage_nav = fundManagerInfoModel.getFund_manage_nav();
        if (!StringUtil.isEmpty(fund_manage_nav)) {
            fund_manage_nav = fund_manage_nav.setScale(2, BigDecimal.ROUND_HALF_UP);
            fundManagerInfoModel.setFund_manage_nav(fund_manage_nav);
        }

//        Long Fund_manager_tot_days = fundManagerInfoModel.getFund_manager_tot_days();
//        if ()

        return fundManagerInfoModel;
    }

    @Override
    public List<HotPeoListResponse> selectFundManagerStarList() throws Exception {
        List<FundManagerStarModel> fundManagerStarModelList = iFundManagerDao.selectFundManagerStarList();
        List<HotPeoListResponse> hotPeoListResponseList = new ArrayList<HotPeoListResponse>();
        for (FundManagerStarModel fundManagerStarModel : fundManagerStarModelList) {
            HotPeoListResponse hotPeoListResponse = new HotPeoListResponse();
            hotPeoListResponse.setId(fundManagerStarModel.getPeo_uni_code().toString());
            hotPeoListResponse.setName(fundManagerStarModel.getFund_manager_name());
            String oss_path = fundManagerStarModel.getOss_path();
            if (!StringUtil.isEmpty(oss_path)) {
                hotPeoListResponse.setAvatar(oss_path_prefix + "/" + oss_path);
            }

            List<String> stringList = new ArrayList<String>();
            stringList.add(fundManagerStarModel.getOrg_sname());
            BigDecimal fund_manage_nav = fundManagerStarModel.getFund_manage_nav();
            if (!StringUtil.isEmpty(fund_manage_nav)) {
                fund_manage_nav = fund_manage_nav.setScale(2, BigDecimal.ROUND_HALF_UP);
                stringList.add("资产管理规模：" + fund_manage_nav + " 亿元");
            }
            hotPeoListResponse.setDesc(stringList);

            //基金资讯
            Map<String, String> newsRequest = new HashMap<>();
            newsRequest.put("keyword", fundManagerStarModel.getOrg_sname());
            newsRequest.put("offset", "0");
            newsRequest.put("limit", "1");
            newsRequest.put("prior", "time");
            newsRequest.put("channel", "");
            newsRequest.put("core_name", "core_news");
            newsRequest.put("single", "false");

            String result = HttpUtil.post(apiNewsUrl, newsRequest, null);
            JSONObject tip = new JSONObject();
            JSONObject resultObj = JSONObject.parseObject(result);
            JSONObject data = resultObj.getJSONObject("data");
            if (!StringUtil.isEmpty(data)) {
                JSONArray items = data.getJSONArray("item");
                if (!StringUtil.isEmpty(items)) {
                    JSONObject item = items.getJSONObject(0);
                    if (!StringUtil.isEmpty(item)) {
                        String title = item.getString("title");
                        if (!StringUtil.isEmpty(title)) {
                            title = title.replace("<font color='red'>", "")
                                    .replace("</font>", "");
                        }
                        tip.put("title", title);

                        String url = item.getString("url");
                        tip.put("url", url);
                    }
                }
            }

            hotPeoListResponse.setTip(tip);
            hotPeoListResponseList.add(hotPeoListResponse);
        }

        return hotPeoListResponseList;
    }

    @Override
    public FundManagerIndexResponse selectFundManagerIndexByPeoUniCode(Long peo_uni_code) throws Exception {
        List<FundManagerIndexModel> fundManagerIndexModelList = iFundManagerDao.selectFundManagerIndexByPeoUniCode(peo_uni_code);
        if (StringUtil.isEmpty(fundManagerIndexModelList)) {
            return null;
        }

        FundManagerIndexModel fundManagerIndexModel = fundManagerIndexModelList.get(0);
        FundManagerIndexResponse fundManagerIndexResponse = new FundManagerIndexResponse();
        fundManagerIndexResponse.setPeo_uni_code(peo_uni_code);
        fundManagerIndexResponse.setFund_manager_name(fundManagerIndexModel.getFund_manager_name());
        Map<Date, List<BigDecimal>> index = new HashMap<Date, List<BigDecimal>>();
        for (FundManagerIndexModel fundManagerIndexModelItem : fundManagerIndexModelList) {
            List<BigDecimal> list = new ArrayList<BigDecimal>();
            list.add(fundManagerIndexModelItem.getTenure_avg_annual_yield_index());
            list.add(fundManagerIndexModelItem.getHs_300_index_yield());
            list.add(fundManagerIndexModelItem.getCsi_universal_index_yield());
            index.put(fundManagerIndexModelItem.getEnd_date(), list);
        }
        fundManagerIndexResponse.setIndex(index);

        return fundManagerIndexResponse;
    }

    @Override
    public FundManagerPositionDistributionResponse selectFundManagerPositionDistributionByPeoUniCode(Long peo_uni_code) throws Exception {
        List<FundManagerPositionDistributionModel> fundManagerPositionDistributionModelList = iFundManagerDao.selectFundManagerPositionDistributionByPeoUniCode(peo_uni_code);
        if (StringUtil.isEmpty(fundManagerPositionDistributionModelList)) {
            return null;
        }

        FundManagerPositionDistributionModel fundManagerPositionDistributionModel = fundManagerPositionDistributionModelList.get(0);
        FundManagerPositionDistributionResponse fundManagerPositionDistributionResponse = new FundManagerPositionDistributionResponse();
        fundManagerPositionDistributionResponse.setPeo_uni_code(fundManagerPositionDistributionModel.getPeo_uni_code());
        fundManagerPositionDistributionResponse.setFund_manager_name(fundManagerPositionDistributionModel.getFund_manager_name());

        JSONArray fund_com = new JSONArray();
        for (FundManagerPositionDistributionModel fundManagerPositionDistributionModelItem : fundManagerPositionDistributionModelList) {
            JSONObject fund_com_item = new JSONObject();
            fund_com_item.put("sec_uni_code", fundManagerPositionDistributionModelItem.getSec_uni_code());
            fund_com_item.put("sec_code", fundManagerPositionDistributionModelItem.getSec_code());
            fund_com_item.put("sec_name", fundManagerPositionDistributionModelItem.getSec_name());
            fund_com_item.put("tot_fund_nav", fundManagerPositionDistributionModelItem.getTot_fund_nav());
            fund_com.add(fund_com_item);
        }
        fundManagerPositionDistributionResponse.setFund_com(fund_com);

        return fundManagerPositionDistributionResponse;
    }

    @Override
    public List<FundManagerStockModel> selectFundManagerStockBySecUniCode(Long sec_uni_code) throws Exception {
        List<FundManagerStockModel> fundManagerStockModelList = iFundManagerDao.selectFundManagerStockBySecUniCode(sec_uni_code);
        for (FundManagerStockModel fundManagerStockModel : fundManagerStockModelList) {
            Long stock_uni_code = fundManagerStockModel.getStock_uni_code();
            SecBasicInfoModel secBasicInfoModel = iFundManagerDao.selectStockCodeBySecUniCode(stock_uni_code);
            String stockCode = secBasicInfoModel.getAbc_code();
            if (!StringUtil.isEmpty(stockCode)) {
                fundManagerStockModel.setStock_code(secBasicInfoModel.getSec_code());
                String cacheKey = KeyBuilder.getKeyStockTimeline(stockCode);
                HashOperations<String, String, String> hash = redisTemplate.opsForHash();
                Map<String, String> cacheResult = hash.entries(cacheKey);
                Map<String, String> dataResult = new TreeMap(cacheResult);

                Date nowTime = new Date();
                //股票代码，分钟时间，收盘价，平均价，涨跌，涨跌幅，成交量，成交金额，转手率
                String time = TimeUtil.toString(nowTime,"HHmm");
                if (time.compareTo("1500") > 0 || time.compareTo("0930") < 0) {
                    time = "1500";
                }

                if (time.compareTo("1130") > 0 && time.compareTo("1300") < 0) {
                    time = "1130";
                }

                for (Map.Entry<String, String> entry : dataResult.entrySet()){
                    if (time.equals(entry.getKey())){
                        List<String> values = Arrays.asList(entry.getValue().split(",",-1));
                        if (values.size() == 9){
                            if (!StringUtils.isEmpty(values.get(1))){
                                fundManagerStockModel.setNow(StringUtils.isNotEmpty(values.get(2)) ? new BigDecimal(values.get(2)) : null);
                                fundManagerStockModel.setDiffer_range(StringUtils.isNotEmpty(values.get(2)) ? new BigDecimal(values.get(5))  : null);
                                break;
                            }
                        }
                    }
                }
            }
        }

        return fundManagerStockModelList;
    }

    @Override
    public List<FundManagerFundInfoResponse> getFundInfo(FundManagerFundInfoRequest request) throws Exception {
        int year;
        if (StringUtil.isEmpty(request.getYear())) {
            Calendar calendar = Calendar.getInstance();
            year = calendar.get(Calendar.YEAR);
        } else {
            year = Integer.valueOf(request.getYear());
        }

        Date startTime = getYearFirst(year);
        Date endTime = getYearFirst(year + 1);

        List<FundManagerBasicInfoModel> list = fundManagerBasicInfoDao.getFundManagerInfoByPeoUniCodeAndDate(request.getPeo_uni_code(), startTime, endTime);

        if (list == null || list.isEmpty()) {
            return null;
        }

        List<FundManagerFundInfoResponse> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            FundManagerBasicInfoModel model = list.get(i);
            if (model == null) {
                continue;
            }

            FundManagerFundInfoResponse item = new FundManagerFundInfoResponse();
            item.setKey((i + 1) + "");
            item.setSec_uni_code(model.getSec_uni_code());
            item.setSec_name(model.getSec_name());
            item.setBegin_date(model.getBegin_date() == null ? null : model.getBegin_date().getTime());
            item.setEnd_date(model.getEnd_date() == null ? null : model.getEnd_date().getTime());

            SecBasicInfoModel secBasicInfoModel = secBasicInfoDao.selectBySecUniCode(model.getSec_uni_code());
            if (secBasicInfoModel != null) {
                item.setSec_code(secBasicInfoModel.getSec_code());
                item.setAbc_code(secBasicInfoModel.getAbc_code());
            }

            String fundManagerName = fundManagerBasicInfoDao.getFundManagerInfoBySecUniCodeAndDateAndPeoUniCode(request.getPeo_uni_code(), model.getSec_uni_code(), model.getBegin_date(), model.getEnd_date());
            if (!StringUtil.isEmpty(fundManagerName)) {
                item.setFund_manager_name(fundManagerName);
            }

            String plateName = secPlateInfoDao.getPlateNameBySecUniCode(model.getSec_uni_code());
            if (!StringUtil.isEmpty(plateName)) {
                item.setPlate_name(plateName);
            }

            Date endDate = model.getEnd_date() == null ? new Date() : model.getEnd_date();

            BigDecimal totFundNav = fundAssetAllocationDao.getTotFundNavBySecUniCodeAndDate(model.getSec_uni_code(), endDate);
            if (totFundNav != null) {
                totFundNav = totFundNav.divide(new BigDecimal("100000000"), 2, BigDecimal.ROUND_HALF_UP);
            }
            item.setTot_fund_nav(totFundNav);

            FundManagerPerfChgModel fundManagerPerfChgModel = fundManagerPerfChgDao.getByPeoUniCodeAndSecUniCodeAndDate(request.getPeo_uni_code(), model.getSec_uni_code(), endDate);
            if (fundManagerPerfChgModel != null) {
                item.setAnnual_yield(fundManagerPerfChgModel.getAnnual_yield());
                item.setBenchmark_annual_yield(fundManagerPerfChgModel.getBenchmark_annual_yield());
                item.setAlpha(fundManagerPerfChgModel.getAlpha());
                item.setIr(fundManagerPerfChgModel.getIr());
                item.setIr_pct(fundManagerPerfChgModel.getIr_pct());
            }

            result.add(item);
        }

        String order_field = request.getOrder_field();
        boolean isValidOrderField = !StringUtil.isEmpty(order_field) && ("tot_fund_nav".equals(order_field) || "begin_date".equals(order_field)
                || "end_date".equals(order_field) || "annual_yield".equals(order_field) || "benchmark_annual_yield".equals(order_field)
                || "alpha".equals(order_field) || "ir".equals(order_field) || "ir_pct".equals(order_field));
        if (!isValidOrderField) {
            order_field = "tot_fund_nav";
        }

        String order_type = request.getOrder_type();
        boolean isValidOrderType = !StringUtil.isEmpty(order_type) && ("desc".equals(order_type) || "asc".equals(order_type));
        if (!isValidOrderType) {
            order_type = "desc";
        }

        sort(order_type, order_field, result);

        return result;
    }

    private void sort(String order_type, String order_field, List<FundManagerFundInfoResponse> result) throws Exception{
        switch (order_field) {
            case "begin_date":
                Collections.sort(result, (o1, o2) -> {
                    if (o2.getBegin_date() == null && o1.getBegin_date() == null) {
                        return o2.getSec_uni_code().compareTo(o1.getSec_uni_code());
                    } else {
                        if (o2.getBegin_date() != null && o1.getBegin_date() != null) {
                            if (o2.getBegin_date().compareTo(o1.getBegin_date()) == 0) {
                                return o2.getSec_uni_code().compareTo(o1.getSec_uni_code());
                            }
                            return o2.getBegin_date().compareTo(o1.getBegin_date());
                        } else {
                            if (o1.getBegin_date() == null) {
                                return 1;
                            } else {
                                return -1;
                            }
                        }
                    }
                });
                break;
            case "end_date":
                Collections.sort(result, (o1, o2) -> {
                    if (o2.getEnd_date() == null && o1.getEnd_date() == null) {
                        return o2.getSec_uni_code().compareTo(o1.getSec_uni_code());
                    } else {
                        if (o2.getEnd_date() != null && o1.getEnd_date() != null) {
                            if (o2.getEnd_date().compareTo(o1.getEnd_date()) == 0) {
                                return o2.getSec_uni_code().compareTo(o1.getSec_uni_code());
                            }
                            return o2.getEnd_date().compareTo(o1.getEnd_date());
                        } else {
                            if (o1.getEnd_date() == null) {
                                return 1;
                            } else {
                                return -1;
                            }
                        }
                    }
                });
                break;
            case "annual_yield":
                Collections.sort(result, (o1, o2) -> {
                    if (o2.getAnnual_yield() == null && o1.getAnnual_yield() == null) {
                        return o2.getSec_uni_code().compareTo(o1.getSec_uni_code());
                    } else {
                        if (o2.getAnnual_yield() != null && o1.getAnnual_yield() != null) {
                            if (o2.getAnnual_yield().compareTo(o1.getAnnual_yield()) == 0) {
                                return o2.getSec_uni_code().compareTo(o1.getSec_uni_code());
                            }
                            return o2.getAnnual_yield().compareTo(o1.getAnnual_yield());
                        } else {
                            if (o1.getAnnual_yield() == null) {
                                return 1;
                            } else {
                                return -1;
                            }
                        }
                    }
                });
                break;
            case "benchmark_annual_yield":
                Collections.sort(result, (o1, o2) -> {
                    if (o2.getBenchmark_annual_yield() == null && o1.getBenchmark_annual_yield() == null) {
                        return o2.getSec_uni_code().compareTo(o1.getSec_uni_code());
                    } else {
                        if (o2.getBenchmark_annual_yield() != null && o1.getBenchmark_annual_yield() != null) {
                            if (o2.getBenchmark_annual_yield().compareTo(o1.getBenchmark_annual_yield()) == 0) {
                                return o2.getSec_uni_code().compareTo(o1.getSec_uni_code());
                            }
                            return o2.getBenchmark_annual_yield().compareTo(o1.getBenchmark_annual_yield());
                        } else {
                            if (o1.getBenchmark_annual_yield() == null) {
                                return 1;
                            } else {
                                return -1;
                            }
                        }
                    }
                });
                break;
            case "alpha":
                Collections.sort(result, (o1, o2) -> {
                    if (o2.getAlpha() == null && o1.getAlpha() == null) {
                        return o2.getSec_uni_code().compareTo(o1.getSec_uni_code());
                    } else {
                        if (o2.getAlpha() != null && o1.getAlpha() != null) {
                            if (o2.getAlpha().compareTo(o1.getAlpha()) == 0) {
                                return o2.getSec_uni_code().compareTo(o1.getSec_uni_code());
                            }
                            return o2.getAlpha().compareTo(o1.getAlpha());
                        } else {
                            if (o1.getAlpha() == null) {
                                return 1;
                            } else {
                                return -1;
                            }
                        }
                    }
                });
                break;
            case "ir":
                Collections.sort(result, (o1, o2) -> {
                    if (o2.getIr() == null && o1.getIr() == null) {
                        return o2.getSec_uni_code().compareTo(o1.getSec_uni_code());
                    } else {
                        if (o2.getIr() != null && o1.getIr() != null) {
                            if (o2.getIr().compareTo(o1.getIr()) == 0) {
                                return o2.getSec_uni_code().compareTo(o1.getSec_uni_code());
                            }
                            return o2.getIr().compareTo(o1.getIr());
                        } else {
                            if (o1.getIr() == null) {
                                return 1;
                            } else {
                                return -1;
                            }
                        }
                    }
                });
                break;
            case "ir_pct":
                Collections.sort(result, (o1, o2) -> {
                    if (o2.getIr_pct() == null && o1.getIr_pct() == null) {
                        return o2.getSec_uni_code().compareTo(o1.getSec_uni_code());
                    } else {
                        if (o2.getIr_pct() != null && o1.getIr_pct() != null) {
                            if (o2.getIr_pct().compareTo(o1.getIr_pct()) == 0) {
                                return o2.getSec_uni_code().compareTo(o1.getSec_uni_code());
                            }
                            return o2.getIr_pct().compareTo(o1.getIr_pct());
                        } else {
                            if (o1.getIr_pct() == null) {
                                return 1;
                            } else {
                                return -1;
                            }
                        }
                    }
                });
                break;
            case "tot_fund_nav":
            default:
                Collections.sort(result, (o1, o2) -> {
                    if (o2.getTot_fund_nav() == null && o1.getTot_fund_nav() == null) {
                        return o2.getSec_uni_code().compareTo(o1.getSec_uni_code());
                    } else {
                        if (o2.getTot_fund_nav() != null && o1.getTot_fund_nav() != null) {
                            if (o2.getTot_fund_nav().compareTo(o1.getTot_fund_nav()) == 0) {
                                return o2.getSec_uni_code().compareTo(o1.getSec_uni_code());
                            }
                            return o2.getTot_fund_nav().compareTo(o1.getTot_fund_nav());
                        } else {
                            if (o1.getTot_fund_nav() == null) {
                                return 1;
                            } else {
                                return -1;
                            }
                        }
                    }
                });
                break;
        }

        if ("asc".equals(order_type)) {
            Collections.reverse(result);
        }
    }

    private Date getYearFirst(int year){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        return calendar.getTime();
    }

    @Override
    public Map<String, Object> getFundChart(FundManagerFundChartRequest request) throws Exception {
        int year;
        if (StringUtil.isEmpty(request.getYear())) {
            Calendar calendar = Calendar.getInstance();
            year = calendar.get(Calendar.YEAR);
        } else {
            year = Integer.valueOf(request.getYear());
        }

        Date startTime = getYearFirst(year);

        String time = StringUtil.isEmpty(request.getTime()) ? "1y" : request.getTime();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startTime);
        switch (time) {
            case "7d":
                calendar.add(Calendar.DAY_OF_YEAR, 7);
                break;
            case "1m":
                calendar.add(Calendar.MONTH, 1);
                break;
            case "3m":
                calendar.add(Calendar.MONTH, 3);
                break;
            case "6m":
                calendar.add(Calendar.MONTH, 6);
                break;
            case "1y":
            default:
                calendar.add(Calendar.YEAR, 1);
                break;
        }
        Date endTime = calendar.getTime();

        List<String> secUniCodeList = Arrays.asList(request.getSec_uni_codes().split(","));

        Map<String, Object> result = new LinkedHashMap<>();

        for (String secUniCodeStr : secUniCodeList) {
            Long sec_uni_code = Long.valueOf(secUniCodeStr);
            SecBasicInfoModel secBasicInfoModel = secBasicInfoDao.selectBySecUniCode(sec_uni_code);
            if (secBasicInfoModel == null) {
                continue;
            }

            Map<String, Object> item = new HashMap<>();

            List<FundNavModel> fundNavModelList = fundNavDao.getRiseRateBySecUniCodeAndDate(sec_uni_code, startTime, endTime);
            if (fundNavModelList == null ||fundNavModelList.isEmpty()) {
                item.put("chartsData", null);
                item.put("endTime", null);
                result.put(secBasicInfoModel.getSec_name(), item);
            } else {
                Map<String, Object> chartData = new LinkedHashMap<>();

                List<Long> endTimeList = new ArrayList<>();
                List<FundManagerBasicInfoModel> fundManagerBasicInfoModelList = fundManagerBasicInfoDao.getEndDateByPeoUniCodeAndSecUniCodeAndDate(request.getPeo_uni_code(), sec_uni_code, startTime, endTime);
                if (fundManagerBasicInfoModelList == null || fundManagerBasicInfoModelList.isEmpty()) {
                    for (FundNavModel fundNavModel : fundNavModelList) {
                        if (fundNavModel == null || fundNavModel.getEnd_date() == null) {
                            continue;
                        }

                        chartData.put(fundNavModel.getEnd_date().getTime() + "", fundNavModel.getRise_rate());
                        endTimeList.add(fundNavModel.getEnd_date().getTime());
                    }
                } else {
                    for (FundNavModel fundNavModel : fundNavModelList) {
                        if (fundNavModel == null || fundNavModel.getEnd_date() == null) {
                            continue;
                        }

                        Long date = fundNavModel.getEnd_date().getTime();
                        chartData.put(date.toString(), fundNavModel.getRise_rate());

                        for (FundManagerBasicInfoModel fundManagerBasicInfoModel : fundManagerBasicInfoModelList) {
                            Long startDate = fundManagerBasicInfoModel.getBegin_date() == null ? null : fundManagerBasicInfoModel.getBegin_date().getTime();
                            Long endDate = fundManagerBasicInfoModel.getEnd_date() == null ? null : fundManagerBasicInfoModel.getEnd_date().getTime();

                            if (startDate == null) {
                                endTimeList.add(date);
                            } else {
                                if (endDate == null) {
                                    if (date < startDate) {
                                        endTimeList.add(date);
                                    }
                                } else {
                                    if (date < startDate || date > endDate) {
                                        endTimeList.add(date);
                                    }
                                }
                            }
                        }
                    }
                }

                item.put("chartsData", chartData);
                item.put("endTime", endTimeList);
                result.put(secBasicInfoModel.getSec_name(), item);
            }
        }

        return result;
    }

    @Override
    public FundManagerCompetitiveStrengthAnalysisResponse getCompetitiveStrengthAnalysis(Long peo_uni_code) throws Exception {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);

        FundManagerCompetitiveStrengthAnalysisResponse result = new FundManagerCompetitiveStrengthAnalysisResponse();
        Map<Long, Map<Integer, List<FundManagerCompetitiveStrengthAnalysisItemResponse>>> infoMap = new HashMap<>();
        for (int i = 0; i < 4; i++) {
            Date startTime = getYearFirst(year);
            Date endTime = getYearFirst(year + 1);

            List<Map<String, Object>> infoList = fundManagerPerfChgDao.getInfoByDate(startTime, endTime);
            for (Map<String, Object> info : infoList) {
                FundManagerCompetitiveStrengthAnalysisItemResponse fmcsair = new FundManagerCompetitiveStrengthAnalysisItemResponse();
                Long secUniCode = Long.parseLong(info.get("sec_uni_code").toString());
                Map<String, Object> secInfo = secBasicInfoDao.getSecNameAndSecCodeBySecUniCode(secUniCode);
                Long peoUniCode = info.get("peo_uni_code") == null ? null : Long.parseLong(info.get("peo_uni_code").toString());
                fmcsair.setSec_code(secInfo.get("sec_code").toString());
                fmcsair.setAlpha(info.get("alpha") == null ? null : new BigDecimal(info.get("alpha").toString()));
                fmcsair.setAnnual_yield(info.get("annual_yield") == null ? null : new BigDecimal(info.get("annual_yield").toString()));
                fmcsair.setIr(info.get("ir") == null ? null : new BigDecimal(info.get("ir").toString()));
                fmcsair.setJenson_ratio(info.get("jenson_ratio") == null ? null : new BigDecimal(info.get("jenson_ratio").toString()));
                fmcsair.setSharpe_ratio(info.get("sharpe_ratio") == null ? null : new BigDecimal(info.get("sharpe_ratio").toString()));
                fmcsair.setTreynor_ratio(info.get("treynor_ratio") == null ? null : new BigDecimal(info.get("treynor_ratio").toString()));
                Date date = (Date)info.get("trade_date");
                fmcsair.setDate(date.getTime());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
                Integer yearNum = Integer.parseInt(simpleDateFormat.format(date));

                Map<Integer, List<FundManagerCompetitiveStrengthAnalysisItemResponse>> infoItem = new HashMap<>();
                if (infoMap.containsKey(secUniCode)) {
                    Map<Integer, List<FundManagerCompetitiveStrengthAnalysisItemResponse>> items = infoMap.get(secUniCode);
                    if (items.containsKey(yearNum)) {
                        List<FundManagerCompetitiveStrengthAnalysisItemResponse> valueList = items.get(yearNum);
                        valueList.add(fmcsair);
                        continue;
                    } else {
                        List<FundManagerCompetitiveStrengthAnalysisItemResponse> list = new ArrayList<>();
                        list.add(fmcsair);
                        items.put(yearNum, list);
                        continue;
                    }
                }

                List<FundManagerCompetitiveStrengthAnalysisItemResponse> list = new ArrayList<>();
                list.add(fmcsair);
                infoItem.put(yearNum, list);
                infoMap.put(peoUniCode, infoItem);
            }
            year--;
        }

        result.setInfo(infoMap);
        result.setPeo_uni_code(peo_uni_code);

        FundManagerInfoModel fundManagerInfoModel = iFundManagerDao.selectFundManagerInfoByPeoUniCode(peo_uni_code);
        result.setFund_manager_name(fundManagerInfoModel == null ? null : fundManagerInfoModel.getFund_manager_name());

        return result;
    }
}
