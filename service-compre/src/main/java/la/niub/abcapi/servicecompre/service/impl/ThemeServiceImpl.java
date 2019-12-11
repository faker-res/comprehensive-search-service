package la.niub.abcapi.servicecompre.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import la.niub.abcapi.servicecompre.component.cache.KeyBuilder;
import la.niub.abcapi.servicecompre.component.util.HttpUtil;
import la.niub.abcapi.servicecompre.component.util.NumberUtil;
import la.niub.abcapi.servicecompre.component.util.ObjectUtil;
import la.niub.abcapi.servicecompre.component.util.StringUtil;
import la.niub.abcapi.servicecompre.config.configuration.RedisMarketConfiguration;
import la.niub.abcapi.servicecompre.config.enums.FundPeriodEnum;
import la.niub.abcapi.servicecompre.dao.market.*;
import la.niub.abcapi.servicecompre.dao.market.IIndexCaptitalFlowDao;
import la.niub.abcapi.servicecompre.dao.market.IIndexPriceDayDao;
import la.niub.abcapi.servicecompre.dao.market.ISecCaptitalFlowMonthDao;
import la.niub.abcapi.servicecompre.dao.market.ISecCaptitalFlowTimeDao;
import la.niub.abcapi.servicecompre.dao.market.ISecCaptitalFlowWeekDao;
import la.niub.abcapi.servicecompre.dao.market.ISecCaptitalFlowYearDao;
import la.niub.abcapi.servicecompre.dao.market.ISecPriceDayDao;
import la.niub.abcapi.servicecompre.dao.market.ISecPriceMonthDao;
import la.niub.abcapi.servicecompre.dao.market.ISecPriceWeekDao;
import la.niub.abcapi.servicecompre.dao.market.ISecPriceYearDao;
import la.niub.abcapi.servicecompre.dao.notice.IHiborDao;
import la.niub.abcapi.servicecompre.dao.reporter.*;
import la.niub.abcapi.servicecompre.model.*;
import la.niub.abcapi.servicecompre.model.bo.line.RealTimeBO;
import la.niub.abcapi.servicecompre.dao.reporter.IComBasicInfoDao;
import la.niub.abcapi.servicecompre.dao.reporter.IComIndexAnaDao;
import la.niub.abcapi.servicecompre.dao.reporter.IComLedPositionDao;
import la.niub.abcapi.servicecompre.dao.reporter.ICorporateExecutiveDao;
import la.niub.abcapi.servicecompre.dao.reporter.IIndexBasicInfoDao;
import la.niub.abcapi.servicecompre.dao.reporter.IIndexIngreStockDao;
import la.niub.abcapi.servicecompre.dao.reporter.IIndexPlateCorDao;
import la.niub.abcapi.servicecompre.dao.reporter.IIndexRangeChanDao;
import la.niub.abcapi.servicecompre.dao.reporter.IIndexValuationAnaDao;
import la.niub.abcapi.servicecompre.dao.reporter.INormalValuationIndexDao;
import la.niub.abcapi.servicecompre.dao.reporter.INtfJzComInfoDao;
import la.niub.abcapi.servicecompre.dao.reporter.INtfJzComTagDao;
import la.niub.abcapi.servicecompre.dao.reporter.INtfJzInvesteventDetailDao;
import la.niub.abcapi.servicecompre.dao.reporter.IPlateChainStockDao;
import la.niub.abcapi.servicecompre.dao.reporter.IPlateInduChainDao;
import la.niub.abcapi.servicecompre.dao.reporter.IRegionDao;
import la.niub.abcapi.servicecompre.dao.reporter.ISecBasicInfoDao;
import la.niub.abcapi.servicecompre.dao.reporter.ISecIndustryNewDao;
import la.niub.abcapi.servicecompre.dao.reporter.IThemeMappingDao;
import la.niub.abcapi.servicecompre.dao.reporter.IWechatPublicDao;
import la.niub.abcapi.servicecompre.model.ComLedPositionModel;
import la.niub.abcapi.servicecompre.model.IndexBasicInfoModel;
import la.niub.abcapi.servicecompre.model.IndexCaptitalFlowModel;
import la.niub.abcapi.servicecompre.model.IndexPriceDayModel;
import la.niub.abcapi.servicecompre.model.IndexRangeChanModel;
import la.niub.abcapi.servicecompre.model.IndexValuationAnaModel;
import la.niub.abcapi.servicecompre.model.ThemeMappingModel;
import la.niub.abcapi.servicecompre.model.WechatPublicModel;
import la.niub.abcapi.servicecompre.model.bo.publiccompany.PublicCompanyStockHeatBO;
import la.niub.abcapi.servicecompre.model.bo.theme.BalanceDistBO;
import la.niub.abcapi.servicecompre.model.bo.theme.ComCountInIpoSectorBO;
import la.niub.abcapi.servicecompre.model.bo.theme.ComCountOfCityBO;
import la.niub.abcapi.servicecompre.model.bo.theme.FinancialStatisticsBO;
import la.niub.abcapi.servicecompre.model.bo.theme.IndexPriceDayBO;
import la.niub.abcapi.servicecompre.model.bo.theme.ListedPlateDistBO;
import la.niub.abcapi.servicecompre.model.bo.theme.MarketStatisticsBO;
import la.niub.abcapi.servicecompre.model.bo.theme.PepbDistBO;
import la.niub.abcapi.servicecompre.model.bo.theme.PlateDistBO;
import la.niub.abcapi.servicecompre.model.bo.theme.RegionDistBO;
import la.niub.abcapi.servicecompre.model.bo.theme.ThemeCardBO;
import la.niub.abcapi.servicecompre.model.request.theme.ThemeWechatPublicRequest;
import la.niub.abcapi.servicecompre.model.response.ApiNewsDataItemResponse;
import la.niub.abcapi.servicecompre.model.response.client.ApiNewsResponse;
import la.niub.abcapi.servicecompre.model.response.client.WechatArticleResponse;
import la.niub.abcapi.servicecompre.model.response.client.wechat.WechatArticleDataItemResponse;
import la.niub.abcapi.servicecompre.model.response.theme.ThemeWechatPublicItemResponse;
import la.niub.abcapi.servicecompre.service.IHeatService;
import la.niub.abcapi.servicecompre.service.ILineService;
import la.niub.abcapi.servicecompre.service.IStockService;
import la.niub.abcapi.servicecompre.service.IThemeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ThemeServiceImpl implements IThemeService {

    private static final Logger logger = LoggerFactory.getLogger(ThemeServiceImpl.class);

    @Autowired
    IIndexBasicInfoDao indexBasicInfoDao;

    @Autowired
    IIndexPriceDayDao indexPriceDayDao;

    @Autowired
    IIndexValuationAnaDao indexValuationAnaDao;

    @Autowired
    IIndexCaptitalFlowDao indexCaptitalFlowDao;

    @Autowired
    IIndexRangeChanDao indexRangeChanDao;

    @Autowired
    IIndexIngreStockDao iIndexIngreStockDao;

    @Autowired
    ISecBasicInfoDao iSecBasicInfoDao;

    @Autowired
    IComBasicInfoDao iComBasicInfoDao;

    @Autowired
    IRegionDao iRegionDao;

    @Autowired
    private INtfJzComTagDao ntfJzComTagDao;

    @Autowired
    private INtfJzComInfoDao ntfJzComInfoDao;

    @Autowired
    private INtfJzInvesteventDetailDao ntfJzInvesteventDetailDao;

    @Autowired
    private IIndexPlateCorDao indexPlateCorDao;

    @Autowired
    private IPlateInduChainDao plateInduChainDao;

    @Autowired
    private IPlateChainStockDao plateChainStockDao;

    @Autowired
    @Qualifier(RedisMarketConfiguration.NAME)
    private RedisTemplate<String, ?> redisTemplate;

    @Value("${oss.file_server_host}")
    private String oSSFileServerHost;

    @Autowired
    INormalValuationIndexDao iNormalValuationIndexDao;

    @Autowired
    IComIndexAnaDao iComIndexAnaDao;

    @Autowired
    IIndexPriceDayDao iIndexPriceDayDao;

    @Autowired
    IHiborDao iHiborDao;

    @Autowired
    ISecCaptitalFlowTimeDao iSecCaptitalFlowTimeDao;

    @Autowired
    ISecCaptitalFlowMonthDao iSecCaptitalFlowMonthDao;

    @Autowired
    ISecCaptitalFlowWeekDao iSecCaptitalFlowWeekDao;

    @Autowired
    ISecCaptitalFlowYearDao iSecCaptitalFlowYearDao;

    @Autowired
    ISecPriceDayDao iSecPriceDayDao;

    @Autowired
    ILineService iLineService;

//    @Autowired
//    ISecPriceDayDao secPriceDayDao;

    @Autowired
    IStockService stockService;

    @Autowired
    ISecPriceWeekDao secPriceWeekDao;

    @Autowired
    ISecPriceMonthDao secPriceMonthDao;

    @Autowired
    ISecPriceYearDao secPriceYearDao;

    @Autowired
    private INormalValuationIndexDao normalValuationIndexDao;

    @Autowired
    private ISecIndustryNewDao secIndustryNewDao;

    @Autowired
    private IComLedPositionDao comLedPositionDao;

    @Autowired
    private ICorporateExecutiveDao corporateExecutiveDao;

    @Value("${feign.client.apiNews.url}")
    private String apiNewsUrl;

    @Autowired
    IThemeMappingDao themeMappingDao;

    @Autowired
    IWechatPublicDao wechatPublicDao;

    @Value("${feign.client.wechatArticle.url}")
    private String wechatArticleUrl;

    @Autowired
    IHeatService heatService;

    @Override
    public ThemeCardBO buildThemeCard(String indexCode) {
        IndexBasicInfoModel indexInfo = indexBasicInfoDao.selectByIndexCode(indexCode);
        if(indexInfo == null){
            return null;
        }
        ThemeCardBO basicInfo = new ThemeCardBO();
        basicInfo.setIndex_code(indexInfo.getIndex_code());
        basicInfo.setIndex_name(indexInfo.getIndex_name());
        // 获取主题基本信息
        IndexPriceDayModel indexPrice = indexPriceDayDao.getNewestRecord(indexInfo.getIndex_uni_code());
        if(indexPrice != null){
            basicInfo.setDiffer(indexPrice.getDiffer());
            basicInfo.setDiffer_range(indexPrice.getDiffer_range());
            basicInfo.setTrade_date(indexPrice.getTrade_date());
            basicInfo.setFair_num(indexPrice.getFair_num());
            basicInfo.setFall_num(indexPrice.getFall_num());
            basicInfo.setRise_num(indexPrice.getRise_num());
            basicInfo.setAmount(indexPrice.getAmount());
            basicInfo.setVolume(indexPrice.getVolume());
            basicInfo.setClose(indexPrice.getClose());
            basicInfo.setHigh(indexPrice.getHigh());
            basicInfo.setLow(indexPrice.getLow());
            basicInfo.setOpen(indexPrice.getOpen());
            basicInfo.setTurnover_rate(indexPrice.getTurnover_rate());

            // 获取市盈率和市净率
            IndexValuationAnaModel  valuationAnaModel = indexValuationAnaDao.getRecordsByDate(indexInfo.getIndex_uni_code(), indexPrice.getTrade_date());
            if(valuationAnaModel != null){
                basicInfo.setPe(valuationAnaModel.getPelyr());
                basicInfo.setPb(valuationAnaModel.getPbmrq());
            }

            // 净资金流入
            IndexCaptitalFlowModel captitalFlowModel = indexCaptitalFlowDao.getRecordsByDate(indexInfo.getIndex_uni_code(),indexPrice.getTrade_date());
            if (captitalFlowModel != null){
                basicInfo.setMain_netin_flow(captitalFlowModel.getMain_netin_flow());
            }

            // 5日和20日
            List<Integer> periods = new ArrayList<>(Arrays.asList(1524004001,1524004003));
            List<IndexRangeChanModel> indexRangeChanModels= indexRangeChanDao.getRecordsByDate(indexInfo.getIndex_uni_code(),indexPrice.getTrade_date(),periods);
            if(indexRangeChanModels != null){
                for(IndexRangeChanModel item: indexRangeChanModels){
                    if(item.getPeriod() == 1524004001){
                        basicInfo.setDiffer_range_5(item.getDiffer_range());
                    }
                    if(item.getPeriod() == 1524004003){
                        basicInfo.setDiffer_range_20(item.getDiffer_range());
                    }
                }
            }
        }

        // 获取主题近3个月行情图
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
//        calendar.add(Calendar.DAY_OF_MONTH, -3);
        calendar.add(Calendar.YEAR, -1);
        Date startDate = calendar.getTime();
        List<IndexPriceDayModel> records = indexPriceDayDao.getRecordsByDates(indexInfo.getIndex_uni_code(), startDate,null);
        List<IndexPriceDayBO> items = new ArrayList<>();
        if(records != null){
            for(IndexPriceDayModel item: records){
                IndexPriceDayBO itemBO = ObjectUtil.copy(item, IndexPriceDayBO.class);
                items.add(itemBO);
            }
        }
        basicInfo.setGraph(items);
        return basicInfo;
    }

    @Override
    public Object buildThemeSide(String index_code) {
        IndexBasicInfoModel indexInfo = indexBasicInfoDao.selectByIndexCode(index_code);
        String introduction = indexInfo != null ? indexInfo.getIndex_memo() : null;
        Map<String, Object> ret = new HashMap<>();
        ret.put("Introduction", introduction);
        return ret;
    }

    @Override
    public PlateDistBO getInduAnalysisPalteDist(String index_code) throws Exception {
        PlateDistBO plateDistBO = new PlateDistBO();

        IndexBasicInfoModel indexBasicInfoModel = indexBasicInfoDao.selectByIndexCode(index_code);
        if (StringUtil.isEmpty(indexBasicInfoModel)) {
            return null;
        }

        plateDistBO.setIndex_code(indexBasicInfoModel.getIndex_code());
        String index_name = indexBasicInfoModel.getIndex_name();
        plateDistBO.setIndex_name(index_name);

        RegionDistBO regionDistBO = new RegionDistBO();
        Long index_id = indexBasicInfoModel.getIndex_id();
        if (StringUtil.isEmpty(index_id)) {
            return null;
        }

        List<String> sec_code_list = iIndexIngreStockDao.selectSecCodeByIndexId(indexBasicInfoModel.getIndex_id());
        if (StringUtil.isEmpty(sec_code_list)) {
            return null;
        }

        List<Long> com_uni_code_list = iSecBasicInfoDao.getComUniCodeBySecCodeList(sec_code_list);

        JSONObject prov_dist = new JSONObject();
        List<String> prov_list = iComBasicInfoDao.selectProvNameListByComCountOfCity(com_uni_code_list);
        Map<String, Integer> cityComCountMap = new HashMap<>();
        for (String prov_code : prov_list) {
            if (StringUtil.isEmpty(prov_code)) {
                continue;
            }

            String prov_name = iRegionDao.selectNameByCode(prov_code);
            List<ComCountOfCityBO> comCountOfCityBOList = iComBasicInfoDao.selectComCountOfCityAndProvCode(com_uni_code_list, prov_code);
            JSONObject city_dist = new JSONObject();
            switch (prov_code) {
                case "120100":
                case "500100":
                case "110100":
                case "310100":
                    int totalCount = 0;
                    for (ComCountOfCityBO comCountOfCityBO : comCountOfCityBOList) {
                        totalCount += comCountOfCityBO.getCount();
                    }
                    city_dist.put(prov_name, totalCount);
                    cityComCountMap.put(prov_name, totalCount);
                    break;
                default:
                    for (ComCountOfCityBO comCountOfCityBO : comCountOfCityBOList) {
                        String city_code = comCountOfCityBO.getCity_code();
                        if (StringUtil.isEmpty(city_code)) {
                            continue;
                        }

                        String city_name = iRegionDao.selectNameByCode(city_code);
                        city_dist.put(city_name, comCountOfCityBO.getCount());
                        cityComCountMap.put(city_name, comCountOfCityBO.getCount());
                    }
            }

            prov_dist.put(prov_name, city_dist);
        }

//        Integer totalCount = iComBasicInfoDao.selectComTotalCount(com_uni_code_list);
//
//        ComCountOfCityBO comCountOfCityBO = iComBasicInfoDao.selectMaxCountInCity(com_uni_code_list);
//        String city_code = comCountOfCityBO.getCity_code();
//        String city_name = iRegionDao.selectNameByCode(city_code);
        Integer totalCount = com_uni_code_list.size();
        Integer max = 0;
        String maxName = "";
        for (Map.Entry item : cityComCountMap.entrySet()) {
            String key = item.getKey().toString();
            Integer value = Integer.parseInt(item.getValue().toString());

//            totalCount += value;
            if (value > max) {
                max = value;
                maxName = key;
            }
        }

        String tip = "截止目前，" + index_name + "行业共有上市公司" + totalCount + "家（含新三板）， " +
                "其中拥有<i style=\" font-style: normal;color: #5480D0;\">上市公司最多</i>的城市为<i style=\" font-style: normal;color: #5480D0;\">" +
                maxName + "市</i>，共有<i style=\" font-style: normal;color: #5480D0;\">" + max + "</i>家";

        regionDistBO.setCountOfProv(prov_dist);
        regionDistBO.setTip(tip);
        plateDistBO.setRegionDist(regionDistBO);

        ListedPlateDistBO listedPlateDistBO = new ListedPlateDistBO();
        JSONObject countInPlate = new JSONObject();
        Integer totalCountInPlate = 0;
        StringBuffer plateTip = new StringBuffer("其中拥有");
        Integer newFlag = 0;
        List<ComCountInIpoSectorBO> comCountInIpoSectorBOlist = iSecBasicInfoDao.selectComCountInIpoSectorBySecCodeList(sec_code_list);
        for (int i = 0; i < comCountInIpoSectorBOlist.size(); i++) {
            ComCountInIpoSectorBO comCountInIpoSectorBO = comCountInIpoSectorBOlist.get(i);
            Integer count = comCountInIpoSectorBO.getCount();
            String ipoSector = comCountInIpoSectorBO.getIpo_sector();
            countInPlate.put(ipoSector, count);
            totalCountInPlate += count;

            if (ipoSector.equals("新三板")) {
                plateTip.append((newFlag == 0 ? "" : ",") + "<i style=\" font-style: normal;color: #5480D0;\">" + ipoSector + "</i>挂牌" + count + "家");
            } else {
                plateTip.append((newFlag == 0 ? "" : ",") + "<i style=\" font-style: normal;color: #5480D0;\">" + ipoSector + "</i>上市" + count + "家");
            }

            newFlag = 1;
        }

        listedPlateDistBO.setCountInPlate(countInPlate);
        plateTip.insert(0, index_name + "行业中共有上市公司" + totalCountInPlate + "家，");
        listedPlateDistBO.setTip(plateTip.toString());

        plateDistBO.setListedPlateDist(listedPlateDistBO);
        return plateDistBO;
    }

    @Override
    public FinancialStatisticsBO getInduAnalysisFinancialStatistics(String index_code) throws Exception {
        FinancialStatisticsBO financialStatisticsBO = new FinancialStatisticsBO();
        IndexBasicInfoModel indexBasicInfoModel = indexBasicInfoDao.selectByIndexCode(index_code);
        if (StringUtil.isEmpty(indexBasicInfoModel)) {
            return null;
        }

        financialStatisticsBO.setIndex_code(indexBasicInfoModel.getIndex_code());
        String index_name = indexBasicInfoModel.getIndex_name();
        financialStatisticsBO.setIndex_name(index_name);

        Long index_id = indexBasicInfoModel.getIndex_id();
        if (StringUtil.isEmpty(index_id)) {
            return null;
        }

        List<String> sec_code_list = iIndexIngreStockDao.selectSecCodeByIndexId(index_id);
        if (StringUtil.isEmpty(sec_code_list)) {
            return null;
        }

        List<Long> sec_uni_code_list = iSecBasicInfoDao.selectSecUniCodeBySecTypeAndSecCodeList(sec_code_list);
        List<PepbDistBO> pepbDistBOList = new ArrayList<>();
        for (Long sec_uni_code : sec_uni_code_list) {
            Date account_date = iNormalValuationIndexDao.selectAccountDateBySecUniCode(sec_uni_code);
            PepbDistBO pepbDistBO = iNormalValuationIndexDao.selectPepbDistBySecUniCodeAndAccountDate(sec_uni_code, account_date);
            pepbDistBOList.add(pepbDistBO);
        }
        financialStatisticsBO.setPEPB_dist_list(pepbDistBOList);

        List<Long> com_uni_code_list = iSecBasicInfoDao.getComUniCodeBySecCodeList(sec_code_list);
        List<BalanceDistBO> balanceDistBOList = new ArrayList<>();
        for (Long com_uni_code : com_uni_code_list) {
            Date end_date = iComIndexAnaDao.selectMaxEndDateByComUniCode(com_uni_code);
            BalanceDistBO balanceDistBO = iComIndexAnaDao.selectBalanceDistByEndDateAndComUniCode(com_uni_code, end_date);
            balanceDistBOList.add(balanceDistBO);
        }
        financialStatisticsBO.setBalance_dist_list(balanceDistBOList);

        return financialStatisticsBO;
    }

    @Override
    public MarketStatisticsBO getInduAnalysisMarketStatistics(String index_code) throws Exception {
        MarketStatisticsBO marketStatisticsBO = new MarketStatisticsBO();
        IndexBasicInfoModel indexBasicInfoModel = indexBasicInfoDao.selectByIndexCode(index_code);
        if (StringUtil.isEmpty(indexBasicInfoModel)) {
            return null;
        }

        marketStatisticsBO.setIndex_code(index_code);
        String index_name = indexBasicInfoModel.getIndex_name();
        marketStatisticsBO.setIndex_name(index_name);

        Long index_uni_code = indexBasicInfoModel.getIndex_uni_code();
        List index_uni_code_list = new ArrayList();
        index_uni_code_list.add(index_uni_code);
        index_uni_code_list.add(422);
        index_uni_code_list.add(249);
        index_uni_code_list.add(570);
        index_uni_code_list.add(1119);
        index_uni_code_list.add(1120);

//        Date nowDate = new Date();
//        Long dateBeforeOneYear = nowDate.getTime() - 365 * 24 * 60 * 60 * 1000;
//        Date startDate = new Date(dateBeforeOneYear);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -1);
        Date startDate = calendar.getTime();

        List<IndexPriceDayModel> indexPriceDayModelList = iIndexPriceDayDao.selectByIndexUniCodeListAndStartDate(index_uni_code_list, startDate);

        JSONObject themeIndex = new JSONObject();
        JSONObject hs300Index = new JSONObject();
        JSONObject shzqIndex = new JSONObject();
        JSONObject szzqIndex = new JSONObject();
        JSONObject zxbIndex = new JSONObject();
        JSONObject cybIndex = new JSONObject();
        for (IndexPriceDayModel indexPriceDayModel : indexPriceDayModelList) {
            Long code = indexPriceDayModel.getIndex_uni_code();
            String trade_date = indexPriceDayModel.getTrade_date().getTime() + "";
            BigDecimal close = indexPriceDayModel.getClose();
            switch (code.toString()) {
                case 422 + "" :
                    hs300Index.put(trade_date, close);
                    break;
                case 249 + "" :
                    shzqIndex.put(trade_date, close);
                    break;
                case 570 + "" :
                    szzqIndex.put(trade_date, close);
                    break;
                case 1119 + "" :
                    zxbIndex.put(trade_date, close);
                    break;
                case 1120 + "":
                    cybIndex.put(trade_date, close);
                    break;
                default:
                    themeIndex.put(trade_date, close);
            }
        }

        JSONObject indu_index = new JSONObject();
        indu_index.put(index_name, themeIndex);
        indu_index.put("沪深300", hs300Index);
        indu_index.put("上证指数", shzqIndex);
        indu_index.put("深证成指", szzqIndex);
        indu_index.put("中小板指数", zxbIndex);
        indu_index.put("创业板指数", cybIndex);

        marketStatisticsBO.setIndu_index(indu_index);

        Long index_id = indexBasicInfoModel.getIndex_id();
        if (StringUtil.isEmpty(index_id)) {
            return null;
        }

        List<String> sec_code_list = iIndexIngreStockDao.selectSecCodeByIndexId(index_id);
        if (StringUtil.isEmpty(sec_code_list)) {
            return null;
        }

//        List<BigDecimal> marketValueList = new ArrayList<>();
        List<Long> sec_uni_code_list = iSecBasicInfoDao.selectSecUniCodeBySecTypeAndSecCodeList(sec_code_list);
//        BigDecimal max = new BigDecimal(0);
//        BigDecimal min = new BigDecimal(0);
//        int flag = 0;
        List<BigDecimal> numberList = new ArrayList<>();
        for (Long sec_uni_code : sec_uni_code_list) {
            Date account_date = iNormalValuationIndexDao.selectAccountDateBySecUniCode(sec_uni_code);
            BigDecimal marketValue = iNormalValuationIndexDao.selectMarketValueBySecUniCodeAndAccountDate(sec_uni_code, account_date);
            numberList.add(marketValue);
        }

        JSONObject value_scale_dist = new JSONObject();
        JSONObject number_dist = NumberUtil.getNumberPatition(numberList);
        value_scale_dist.put("data", number_dist.getJSONObject("data"));
        String unit = number_dist.getString("unit");
        value_scale_dist.put("unit", unit);
        String maxInterval = number_dist.getString("maxInterval");
        Integer maxCount = number_dist.getInteger("maxCount");
        Integer totalCount = numberList.size();

        value_scale_dist.put("tip", indexBasicInfoModel.getIndex_name() + "行业共有上市公司" + totalCount +
                "家， 其中上市公式规模<i style=\" font-style: normal;color: #5480D0;\">集中于" +
                maxInterval + unit + "</i>,共有<i style=\" font-style: normal;color: #5480D0;\">" +
                maxCount + "</i>家");

        marketStatisticsBO.setValue_scale_dist(value_scale_dist);
        return marketStatisticsBO;
    }

    @Override
    public List<ThemeWechatPublicItemResponse> wechatPublic(ThemeWechatPublicRequest themeWechatPublicRequest) {
        IndexBasicInfoModel indexInfo = indexBasicInfoDao.selectByIndexCode(themeWechatPublicRequest.getCode());
        if(indexInfo == null){
            return null;
        }
        List<ThemeWechatPublicItemResponse> result = new ArrayList<>();
        List<ThemeMappingModel> accountList = themeMappingDao.selectByThemeNameAndLimit(indexInfo.getIndex_name()/*, 6*/);

        if (accountList != null && !accountList.isEmpty()) {
            for (ThemeMappingModel aItem : accountList) {

                WechatPublicModel accountInfo = wechatPublicDao.getByAccount(aItem.getPublicAccount());
                if (accountInfo != null) {
                    ThemeWechatPublicItemResponse resultItem = new ThemeWechatPublicItemResponse();
//                    resultItem.setId(accountInfo.getId());
//                    resultItem.setName(accountInfo.getPublicName());
//                    resultItem.setAvatar(accountInfo.getPublicImage());
//                    String tag = "";
//                    String publicTags = accountInfo.getPublicTags();
                    resultItem.setId(accountInfo.getId().intValue());
                    resultItem.setName(accountInfo.getPublic_name());
                    resultItem.setAvatar(accountInfo.getPublic_image());

                    //加了阅读量数据（bug）
                    resultItem.setReads(accountInfo.getReading());
                    String tag = "";
                    String publicTags = accountInfo.getPublic_tags();
                    if (publicTags.length() >  0) {
                        String[] spilts = publicTags.split(",");
                        tag = spilts[0];
                    }
                    resultItem.setTag(tag);
                    Map<String, String> params = new HashMap<>();
                    params.put("single", "true");
                    params.put("core_name", "core_news");
                    try {
                        params.put("keyword", URLEncoder.encode("$source_name_s:\"" + accountInfo.getPublic_name() + "\"","UTF-8"));
                    } catch (UnsupportedEncodingException e) {

                    }
                    params.put("limit", "1");
                    params.put("offset", "0");
                    params.put("prior","time");

                    String wechatArtilceRet = HttpUtil.get(wechatArticleUrl, params, null);

                    if (wechatArtilceRet != null && !wechatArtilceRet.isEmpty()) {
                        WechatArticleResponse wechatArticleResponse = JSON.parseObject(wechatArtilceRet, WechatArticleResponse.class);
                        if (wechatArticleResponse.getData() != null) {
                            if ( wechatArticleResponse.getData().getItem() != null && !wechatArticleResponse.getData().getItem().isEmpty()) {

                                for (WechatArticleDataItemResponse item : wechatArticleResponse.getData().getItem()){
                                    resultItem.setNewestNewsId(item.getId());
                                    resultItem.setNewestNewsTitle(item.getTitle());
                                    resultItem.setNewestNewsUrl(item.getUrl());
                                    break;
                                }

                            }
                        }
                    }
                    result.add(resultItem);
                }
            }
        }

        return result;
    }


    @Override
    public List<Map<String, Object>> getHotPeoList(String theme_code) throws Exception {
        IndexBasicInfoModel indexBasicInfoModel = indexBasicInfoDao.selectByIndexCode(theme_code);
        if (indexBasicInfoModel == null || StringUtil.isEmpty(indexBasicInfoModel.getIndex_id())) {
            return null;
        }

        List<String> secCodeList = iIndexIngreStockDao.selectSecCodeByIndexId(indexBasicInfoModel.getIndex_id());
        if (StringUtil.isEmpty(secCodeList)) {
            return null;
        }

        List<Map<String, Object>> secBasicInfoList = iSecBasicInfoDao.getListBySecCodeList(secCodeList);
        if (StringUtil.isEmpty(secBasicInfoList)) {
            return null;
        }

        List<BigInteger> secUniCodeList = new ArrayList<>();
        for (Map<String, Object> secBasicInfoMap : secBasicInfoList) {
            if (!StringUtil.isEmpty(secBasicInfoMap.get("sec_uni_code"))) {
                secUniCodeList.add((BigInteger) secBasicInfoMap.get("sec_uni_code"));
            }
        }

        if (StringUtil.isEmpty(secUniCodeList)) {
            return null;
        }

        List<BigInteger> secUniCodeListForTop6 = normalValuationIndexDao.getSecUniCodeForTopNBySecUniCodeList(secUniCodeList, 6);
        if (StringUtil.isEmpty(secUniCodeListForTop6)) {
            return null;
        }

        List<Map<String, Object>> result = new ArrayList<>();

        for (BigInteger secUniCode : secUniCodeListForTop6) {
            Map<String, Object> map = new HashMap<>();
            for (Map<String, Object> secBasicInfoMap : secBasicInfoList) {
                if (secUniCode.compareTo((BigInteger) secBasicInfoMap.get("sec_uni_code")) == 0) {
                    map.put("companyName", secBasicInfoMap.get("sec_name"));

                    String thirdInduName = secIndustryNewDao.getThirdInduNameBySecUniCode(secUniCode);
                    map.put("industryName", StringUtil.isEmpty(thirdInduName) ? null : thirdInduName);

                    if (secBasicInfoMap.get("com_uni_code") != null) {
                        ComLedPositionModel comLedPositionModel = comLedPositionDao.getRecordByComUniCode((BigInteger)secBasicInfoMap.get("com_uni_code"));
                        if (comLedPositionModel != null) {
                            map.put("userName", comLedPositionModel.getLed_name());
                            map.put("userId", comLedPositionModel.getPeo_uni_code());
                            map.put("postName", comLedPositionModel.getPost_name());

                            String avatar = corporateExecutiveDao.getImageByPeoUniCode(comLedPositionModel.getPeo_uni_code());
                            map.put("avatar", StringUtil.isEmpty(avatar) ? null : oSSFileServerHost + "/" + avatar);

                            Map<String, Object> news = new HashMap<>();
                            Map<String, String> newsRequest = new HashMap<>();
                            String keyword = comLedPositionModel.getLed_name() + " " + secBasicInfoMap.get("sec_name") + " "
                                    + indexBasicInfoModel.getIndex_name();
                            newsRequest.put("keyword", keyword);
                            newsRequest.put("offset", "0");
                            newsRequest.put("limit", "1");
                            //newsRequest.put("prior", "time");
                            newsRequest.put("channel", "");
                            newsRequest.put("core_name", "core_news");
                            newsRequest.put("single", "false");


                            String apiNewsRet = HttpUtil.post(apiNewsUrl, newsRequest, null);
                            if (apiNewsRet != null && !apiNewsRet.isEmpty()) {
                                ApiNewsResponse apiNewsRetObject = JSON.parseObject(apiNewsRet, ApiNewsResponse.class);
                                if (apiNewsRetObject != null && apiNewsRetObject.getErr_code() == 0) {
                                    List<ApiNewsDataItemResponse> newsList = apiNewsRetObject.getData().getItem();
                                    if (newsList != null && newsList.size() > 0) {
                                        ApiNewsDataItemResponse apiNewsDataItemResponse = newsList.get(0);
                                        if (apiNewsDataItemResponse != null ) {
                                            String title = apiNewsDataItemResponse.getTitle();
                                            if (!StringUtil.isEmpty(title)) {
                                                title = title.replace("<font color='red'>", "")
                                                        .replace("</font>", "");
                                            }
                                            news.put("id", apiNewsDataItemResponse.getId());
                                            news.put("title", title);
                                            news.put("url", apiNewsDataItemResponse.getUrl());
                                        }
                                    }
                                }
                            }

                            map.put("news", news);
                        }
                    }
                    break;
                }
            }

            result.add(map);
        }

        return result;
    }

    @Override
    public Map<String, Object> getIndustryChainMap(String theme_code) throws Exception {
        Map<String, Object> result = new HashMap<>();

        IndexBasicInfoModel indexInfo = indexBasicInfoDao.selectByIndexCode(theme_code);
        if(indexInfo == null){
            return null;
        }

        IndexPriceDayModel indexPrice = indexPriceDayDao.getNewestRecord(indexInfo.getIndex_uni_code());
        result.put("name", indexInfo.getIndex_name());
        if (indexPrice.getDiffer_range() != null) {
            result.put("differRange", getDifferRange(indexPrice.getDiffer_range(), false));
        } else {
            result.put("differRange", "");
        }

        if (indexPrice.getDiffer() != null) {
            result.put("differ", getDiffer(indexPrice.getDiffer()));
        } else {
            result.put("differ", "");
        }

        List<Object> categoryList = new ArrayList<>();

        String plateCode = indexPlateCorDao.getPlateCodeByIndexCode(new BigInteger(indexInfo.getIndex_code()));
        if (!StringUtil.isEmpty(plateCode)) {
            List<Map<String, Object>> induChainList = plateInduChainDao.getInduChainCodeAndNameByPlateCode(plateCode);
            if (induChainList != null && induChainList.size() > 0) {
                for (Map<String, Object> map : induChainList) {
                    Map<String, Object> category = new HashMap<>();
                    if (map != null && !StringUtil.isEmpty(map.get("induChainName"))) {
                        category.put("name", map.get("induChainName"));
                    }

                    List<Object> companyList = new ArrayList<>();
                    if (map != null && map.get("induChainCode") != null) {
                        List<Map<String, String>> plateChainStockList = plateChainStockDao.getPlateChainStockByInduChainCode((Long)map.get("induChainCode"));
                        if (plateChainStockList != null && plateChainStockList.size() > 0) {
                            for (Map<String, String> companyMap : plateChainStockList) {
                                Map<String, Object> company = new HashMap<>();
                                if (companyMap != null && !StringUtil.isEmpty(companyMap.get("sec_name"))) {
                                    company.put("name", companyMap.get("sec_name"));
                                }

                                if (companyMap != null && !StringUtil.isEmpty(companyMap.get("abc_code"))) {
                                    String cacheKey = KeyBuilder.getKeyStockRealtimePrice(companyMap.get("abc_code").toString());
                                    String str = redisTemplate.execute(new RedisCallback<String>() {
                                        @Override
                                        public String doInRedis(RedisConnection connection) throws DataAccessException {
                                            RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                                            byte[] value = connection.get(serializer.serialize(cacheKey));
                                            return serializer.deserialize(value);
                                        }
                                    });
                                    // str = "2018-07-11 15:01:03,8.7800,31806,-0.0223,85129670.0000,744765824.8900,0.8791,-0.3819,8.7700,8.7600,8.7500,8.7400,8.7300,1237.0000,2603.9000,1821.0000,1621.0100,1096.0000,8.7800,8.7900,8.8000,8.8100,8.8200,1274.0000,4621.0000,9245.0000,2178.0000,1730.0000,8.8300,8.6800,8.7600,8.9800,-0.2000,N,-10528.0900,,,,,,150754766904.00000000,150756212092.00000000,5.7148,0.6749";

                                    logger.info("redis getting key: " + cacheKey + ", value: " + str);
                                    String differRange = null;
                                    String differ = null;
                                    if (!StringUtil.isEmpty(str)) {
                                        List<String> values = Arrays.asList(str.split(",",-1));
                                        if (values.size() >= 44) {
                                            differRange = values.get(3);
                                            differ = values.get(32);
                                        }
                                    }
                                    if (!StringUtil.isEmpty(differRange)) {
                                        company.put("differRange", getDifferRange(new BigDecimal(differRange), true));
                                    } else {
                                        company.put("differRange", "");
                                    }

                                    if (!StringUtil.isEmpty(differ)) {
                                        company.put("differ", getDiffer(new BigDecimal(differ)));
                                    } else {
                                        company.put("differ", "");
                                    }
                                }

                                companyList.add(company);
                            }
                        }
                    }
                    category.put("company", companyList);

                    categoryList.add(category);
                }
            }
        }

        result.put("category", categoryList);

        return result;
    }

    private String getDifferRange(BigDecimal differRange, Boolean isMultiply100) {
        StringBuilder sb = new StringBuilder();
        if (BigDecimal.ZERO.compareTo(differRange) < 0) {
            sb.append("+");
        }

        if (isMultiply100) {
            differRange = differRange.multiply(new BigDecimal("100")).setScale(2, BigDecimal.ROUND_HALF_UP);
        } else {
            differRange = differRange.setScale(2, BigDecimal.ROUND_HALF_UP);
        }


        sb.append(differRange).append("%");

        return sb.toString();
    }

    private String getDiffer(BigDecimal differ) {
        StringBuilder sb = new StringBuilder();
        if (BigDecimal.ZERO.compareTo(differ) < 0) {
            sb.append("+");
        }

        differ = differ.setScale(2, BigDecimal.ROUND_HALF_UP);
        sb.append(differ);

        return sb.toString();
    }

    @Override
    public Map<String, Object> getPrimaryMarket(String theme_code, Integer currentPage, Integer limit, Integer financingAmountSort) throws Exception {
        String themeName = indexBasicInfoDao.getThemeNameByCode(theme_code);
        if (StringUtil.isEmpty(themeName)) {
            return null;
        }

        List<Integer> comIds = ntfJzComTagDao.getComIdListByTagName(themeName);
        if (comIds == null || comIds.size() == 0) {
            return null;
        }

        Map<String, Object> result = new HashMap<>();

        Integer count = ntfJzComInfoDao.getComInfoCountByComIds(comIds);
        Integer total = count % limit == 0 ? count / limit : (count / limit) + 1;

        if (total == 0) {
            result.put("total", total);
            result.put("item", new ArrayList<>());

            return result;
        }

        Integer index = (currentPage - 1) * limit;
        List<Map<String, Object>> comInfoList = ntfJzComInfoDao.getComInfoListByComIds(comIds, index, limit, financingAmountSort);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        for (Map<String, Object> map : comInfoList) {
            if (map != null) {
                String comFundStatusName = StringUtil.isEmpty(map.get("comFundStatusName").toString()) ? "不明确" : map.get("comFundStatusName").toString();
                map.put("comFundStatusName", comFundStatusName);

                boolean isGetFinancing = !"尚未获投".equals(comFundStatusName) && !"已被收购".equals(comFundStatusName) && !"不明确".equals(comFundStatusName);

                if (!StringUtil.isEmpty(map.get("comLogo"))) {
                    map.put("comLogo", oSSFileServerHost + "/" + map.get("comLogo"));
                }

                if (map.get("invstDate") != null) {
                    map.put("invstDate", ((Date)map.get("invstDate")).getTime());
                } else {
                    map.put("invstDate", isGetFinancing ? "未透露" : "无");
                }

                if (!StringUtil.isEmpty(map.get("comBornDate"))) {
                    map.put("comBornDate", sdf.parse(map.get("comBornDate").toString()).getTime());
                }

                if (!StringUtil.isEmpty(map.get("invstDetailMoney")) && !"未透露".equals(map.get("invstDetailMoney").toString())
                        && !StringUtil.isEmpty(map.get("invstCurrencyName"))) {
                    map.put("invstDetailMoney", map.get("invstDetailMoney").toString() + map.get("invstCurrencyName").toString());
                } else {
                    if (StringUtil.isEmpty(map.get("invstDetailMoney"))) {
                        if (isGetFinancing) {
                            map.put("invstDetailMoney", "未透露");
                        } else {
                            map.put("invstDetailMoney", "无");
                        }
                    }
                }

                if (map.containsKey("invstCurrencyName")) {
                    map.remove("invstCurrencyName");
                }

                map.put("comTag", themeName);

                if (map.get("eventId") == null) {
                    List<String> invstNameList = new ArrayList<>();
                    if (isGetFinancing) {
                        invstNameList.add("投资方未透露");
                    } else {
                        invstNameList.add("无");
                    }
                    map.put("invstName", invstNameList);
                } else {
                    List<String> invstNameList = ntfJzInvesteventDetailDao.getInvstNameListByEventId((int)map.get("eventId"));
                    if (invstNameList != null && invstNameList.size() > 0) {
                        map.put("invstName", invstNameList);
                    } else {
                        if (invstNameList == null) {
                            invstNameList = new ArrayList<>();
                        }
                        if (isGetFinancing) {
                            invstNameList.add("投资方未透露");
                        } else {
                            invstNameList.add("无");
                        }
                        map.put("invstName", invstNameList);
                    }

                    if (map.containsKey("eventId")) {
                        map.remove("eventId");
                    }
                }
            }
        }

        result.put("total", total);
        result.put("item", comInfoList);

        return result;
    }

    @Override
    public List<PublicCompanyStockHeatBO> getThermodynamicChart(String index_code, FundPeriodEnum period, Integer limit) throws Exception {
        if (StringUtil.isEmpty(period)) {
            period = FundPeriodEnum.D1;
        }

        if (StringUtil.isEmpty(limit)) {
            limit = 100000;
        }

        IndexBasicInfoModel indexBasicInfoModel = indexBasicInfoDao.selectByIndexCode(index_code);
        if (StringUtil.isEmpty(indexBasicInfoModel)) {
            return new ArrayList<>();
        }

        Long index_id = indexBasicInfoModel.getIndex_id();
        if (StringUtil.isEmpty(index_id)) {
            return new ArrayList<>();
        }

        List<String> sec_code_list = iIndexIngreStockDao.selectSecCodeByIndexId(index_id);
        if (StringUtil.isEmpty(sec_code_list)) {
            return new ArrayList<>();
        }

        List<Long> secUniCodesOfALL = iSecBasicInfoDao.selectSecUniCodeBySecTypeAndSecCodeList(sec_code_list);

        return heatService.handleStockHeat(secUniCodesOfALL,period,limit);
    }


}
