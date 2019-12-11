package la.niub.abcapi.servicecompre.service.impl;

import la.niub.abcapi.servicecompre.component.cache.KeyBuilder;
import la.niub.abcapi.servicecompre.component.util.RedisUtil;
import la.niub.abcapi.servicecompre.config.configuration.RedisMarketConfiguration;
import la.niub.abcapi.servicecompre.config.enums.IndustryStandardEnum;
import la.niub.abcapi.servicecompre.dao.reporter.*;
import la.niub.abcapi.servicecompre.model.*;
import la.niub.abcapi.servicecompre.model.bo.StockCardBO;
import la.niub.abcapi.servicecompre.model.bo.card.CardStockNewssetBO;
import la.niub.abcapi.servicecompre.model.bo.card.CardStockNewssetCompanyBO;
import la.niub.abcapi.servicecompre.model.bo.card.CardStockNewssetStockBO;
import la.niub.abcapi.servicecompre.model.bo.card.CardStockSharePriceChangeBO;
import la.niub.abcapi.servicecompre.model.bo.card.CardStockSharePriceChangeCompanyBO;
import la.niub.abcapi.servicecompre.model.bo.line.RealTimeBO;
import la.niub.abcapi.servicecompre.service.ICompanyManagerService;
import la.niub.abcapi.servicecompre.service.ILineService;
import la.niub.abcapi.servicecompre.service.IStockService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class CompanyManagerServiceImpl implements ICompanyManagerService {

    private static final Logger logger = LogManager.getLogger(CompanyManagerServiceImpl.class);

    @Autowired
    IStockService stockService;

    @Autowired
    IStockCategoryDao stockCategoryDao;

    @Autowired
    ITransacDao transacDao;

    @Autowired
    IComIndexAnaDao iomIndexAnaDao;

    @Autowired
    ISecIndustryNewDao secIndustryNewDao;

    @Autowired
    ISecStrucAlterDao secStrucAlterDao;

    @Autowired
    IComBalanceDao comBalanceDao;

    @Autowired
    IComProfitDao comProfitDao;

    @Autowired
    INormalValuationIndexDao normalValuationIndexDao;

    @Autowired
    ISecSuspendDao secSuspendDao;

    @Autowired
    ISecPriceDayDao secPriceDayDao;

    @Autowired
    IElementDao elementDao;

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    @Qualifier(RedisMarketConfiguration.NAME)
    private RedisTemplate<String, ?> redisTemplate;

    @Autowired
    ISecBasicInfoDao secBasicInfoDao;

    @Autowired
    IFinancialIndicatorDataNewDao financialIndicatorDataNewDao;

    @Autowired
    IFundNavCurDao fundNavCurDao;

    @Autowired
    IFundNavDao fundNavDao;

    @Autowired
    ILineService lineService;


    @Override
    public String getStockCategory(String secCode) {
        secCode = secCode.replaceAll("^(SH|SZ)","");
        StockCategoryModel record = stockCategoryDao.selectBySecCode(secCode);
        return record != null ? record.getStock_category() : "";
    }

    @Override
    public CardStockNewssetBO buildCompanyNewestStockInfo(String stockCode){
        if (StringUtils.isEmpty(stockCode)){
            stockCode = "{stock_code}";
        }
        SecBasicInfoModel comInfo = stockService.buildRecordByStockCode(stockCode);

        String industry = "货币金融服务";
        String stock_name = "平安银行";
        Long comUniCode = 1L;
        Long secUniCode = 1747L;
        String abcCode = "000001.SZ";
        Date realTime = null;
        if (comInfo != null){
            SecIndustryNewModel record = secIndustryNewDao.selectByCodeAndStandard(comInfo.getSec_uni_code(),IndustryStandardEnum.C1001014.getIndu_standard());
            industry = record != null ? record.getSecond_indu_name() : "";
            stock_name = comInfo.getSec_name();
            comUniCode = comInfo.getCom_uni_code();
            secUniCode = comInfo.getSec_uni_code();
            abcCode = comInfo.getAbc_code();
        }
        CardStockNewssetCompanyBO cardStockNewssetCompanyBO = new CardStockNewssetCompanyBO();
        cardStockNewssetCompanyBO.setStock_code(stockCode);
        cardStockNewssetCompanyBO.setAbc_code(abcCode);
        cardStockNewssetCompanyBO.setIndustry(industry);
        cardStockNewssetCompanyBO.setStock_name(stock_name);

        CardStockNewssetStockBO cardStockNewssetStockBO = new CardStockNewssetStockBO();
        RealTimeBO realTimeBO = lineService.getRealTime(stockCode);
        logger.info("Get realTimeBO for stockCode {} result {}",stockCode,realTimeBO);
        if (realTimeBO == null){
            logger.error("Empty redis data, stock code:{}", stockCode);
            cardStockNewssetStockBO.setCurrent_price(0f);
            cardStockNewssetStockBO.setZdf(0d);
            cardStockNewssetStockBO.setCjl(0d);
            cardStockNewssetStockBO.setCje(0d);
            cardStockNewssetStockBO.setJk(0d);
            cardStockNewssetStockBO.setZg(0d);
            cardStockNewssetStockBO.setZd(0d);
            cardStockNewssetStockBO.setZs(0d);
            cardStockNewssetStockBO.setStopSign(false);
        }else{
            realTime = realTimeBO.getTime();

            //最新价
            cardStockNewssetStockBO.setCurrent_price(realTimeBO.getNow() != null ? realTimeBO.getNow().floatValue(): 0);
            if (cardStockNewssetStockBO.getCurrent_price().equals(0) && realTimeBO.getSuspension().equals("Y")){
                cardStockNewssetStockBO.setCurrent_price(realTimeBO.getPre_close() != null ? realTimeBO.getPre_close().floatValue(): 0);
            }
            //涨跌幅
            cardStockNewssetStockBO.setZdf(realTimeBO.getDiffer_range());
            //成交量
            cardStockNewssetStockBO.setCjl(realTimeBO.getVolume() != null ? realTimeBO.getVolume().doubleValue() : 0);
            //成交额
            cardStockNewssetStockBO.setCje(realTimeBO.getAmount() != null ? realTimeBO.getAmount().doubleValue() : 0);
            //今日开盘价
            cardStockNewssetStockBO.setJk(realTimeBO.getOpen());
            //最高价
            cardStockNewssetStockBO.setZg(realTimeBO.getHigh());
            //最低价
            cardStockNewssetStockBO.setZd(realTimeBO.getLow());
            //昨收价
            cardStockNewssetStockBO.setZs(realTimeBO.getPre_close());

            //总市值
            cardStockNewssetStockBO.setZsz(realTimeBO.getMv() != null ? realTimeBO.getMv().doubleValue() : 0);
            //流通市值
            cardStockNewssetStockBO.setLtsz(realTimeBO.getLiqmv() != null ? realTimeBO.getLiqmv().doubleValue() : 0);

            //市盈率(动)
            cardStockNewssetStockBO.setSyl(realTimeBO.getPe());
            //市净率
            cardStockNewssetStockBO.setSjl(realTimeBO.getPb());
            //是否停牌
            cardStockNewssetStockBO.setStopSign(realTimeBO.getSuspension().equals("Y")?true:false);

            //换手率
            cardStockNewssetStockBO.setChange(realTimeBO.getChange());
            //振幅
            cardStockNewssetStockBO.setSwing(realTimeBO.getSwing());
            //总手
            cardStockNewssetStockBO.setTotalhand(realTimeBO.getVolume());
            //现手
            cardStockNewssetStockBO.setNowhand(realTimeBO.getRoundlot());
            //委比
            cardStockNewssetStockBO.setAppointment(realTimeBO.getCommissiondiff());
            //量比
            cardStockNewssetStockBO.setQuantity(realTimeBO.getVolumeratio());
            //涨跌
            cardStockNewssetStockBO.setChange_amount(realTimeBO.getDiffer());
        }

        SecStrucAlterModel stock = secStrucAlterDao.selectByComUniCode(comUniCode);
        logger.info("Get SecStrucAlterModel for comUniCode {} result {}",comUniCode,stock);
        ComBalanceModel balance = comBalanceDao.selectByComUniCode(comUniCode);
        logger.info("Get ComBalanceModel for comUniCode {} result {}",comUniCode,balance);

        if (stock != null){
            cardStockNewssetStockBO.setZgb(stock.getTotal_shares());
            if (cardStockNewssetStockBO.getZsz() == null
                    && cardStockNewssetStockBO.getCurrent_price() != null
                    && stock.getTotal_shares() != null){
                cardStockNewssetStockBO.setZsz(new Double(stock.getTotal_shares().intValue() * cardStockNewssetStockBO.getCurrent_price().intValue()));
            }
            if (cardStockNewssetStockBO.getLtsz() == null
                    && cardStockNewssetStockBO.getCurrent_price() != null
                    && stock.getPloat_share() != null){
                cardStockNewssetStockBO.setLtsz(new Double(stock.getPloat_share().intValue() * cardStockNewssetStockBO.getCurrent_price().intValue()));
            }
        }

        if (stock != null && balance != null){
            BigDecimal mgjzc = stock.getTotal_shares() == null || stock.getTotal_shares().doubleValue() == 0 ? new BigDecimal(0) : balance.getTotal_account_parent_equity().divide(stock.getTotal_shares(),4, BigDecimal.ROUND_HALF_UP);
            if (cardStockNewssetStockBO.getSjl() == null || cardStockNewssetStockBO.getSjl() == 0){
                cardStockNewssetStockBO.setSjl(mgjzc.doubleValue() == 0 ? 0 : cardStockNewssetStockBO.getCurrent_price()/mgjzc.doubleValue());
            }
        }

        List<ComProfitModel> profit = comProfitDao.getTopNRecords(comUniCode,5);
        logger.info("Get profit for comUniCode {} result count {}",comUniCode,profit.size());
        if (profit != null && stock != null && profit.size() > 4){
            BigDecimal total = new BigDecimal(0);
            ComProfitModel firstProfit = profit.get(0);
            if (firstProfit.getReport_type().equals(1015004)){
                total = firstProfit.getParent_netprofit();
            }else if (firstProfit.getReport_type().equals(1015003)){
                total = firstProfit.getParent_netprofit().add(profit.get(3).getParent_netprofit()).add(profit.get(4).getParent_netprofit());
            }else if (firstProfit.getReport_type().equals(1015002)){
                total = firstProfit.getParent_netprofit().add(profit.get(2).getParent_netprofit()).add(profit.get(4).getParent_netprofit());
            }else if (firstProfit.getReport_type().equals(1015001)){
                total = firstProfit.getParent_netprofit().add(profit.get(1).getParent_netprofit()).add(profit.get(4).getParent_netprofit());
            }

            BigDecimal gdmgyy = stock.getTotal_shares() == null || stock.getTotal_shares().doubleValue() == 0 ? new BigDecimal(0) : total.divide(stock.getTotal_shares());
            if (cardStockNewssetStockBO.getSyl() == null || cardStockNewssetStockBO.getSyl() == 0){
                cardStockNewssetStockBO.setSyl(gdmgyy.doubleValue() == 0 || cardStockNewssetStockBO.getCurrent_price() == 0 ? 0 : cardStockNewssetStockBO.getCurrent_price()/gdmgyy.doubleValue());
            }
            if (cardStockNewssetStockBO.getSxl() == null || cardStockNewssetStockBO.getSxl() == 0){
                cardStockNewssetStockBO.setSxl(total.doubleValue() == 0 ? 0 : cardStockNewssetStockBO.getZsz()/gdmgyy.doubleValue());
            }
        }

        //保持市值一致
        Date time = normalValuationIndexDao.getMaxData(secUniCode);
        NormalValuationIndexModel record = normalValuationIndexDao.getRecord(secUniCode,time);
        logger.info("Get record for secUniCode {} of time {} result {}",secUniCode,time,record);
//        cardStockNewssetStockBO.setZsz(0D);
        if (record != null){
//            logger.info("secUniCode {} normalValuationIndexDao find: {}",secUniCode,record);
            if (cardStockNewssetStockBO.getZgb() == null && record.getTotal_shares() != null){
                cardStockNewssetStockBO.setZgb(new BigDecimal(record.getTotal_shares()));
            }
//            cardStockNewssetStockBO.setZsz(record.getTotal_market_value() != null ? record.getTotal_market_value().doubleValue() : 0);
//            cardStockNewssetStockBO.setLtsz(record.getFreefloat_market_value() != null ? record.getFreefloat_market_value().doubleValue() : null);
            if (cardStockNewssetStockBO.getZsz() == null && record.getTotal_market_value() != null){
                cardStockNewssetStockBO.setZsz(record.getTotal_market_value().doubleValue());
            }
            if (cardStockNewssetStockBO.getLtsz() == null && record.getFreefloat_market_value() != null){
                cardStockNewssetStockBO.setLtsz(record.getFreefloat_market_value().doubleValue());
            }
            if (cardStockNewssetStockBO.getSyl() == null || cardStockNewssetStockBO.getSyl() == 0){
                cardStockNewssetStockBO.setSyl(record.getPettm() != null ? record.getPettm().doubleValue() : null);
            }
            if (cardStockNewssetStockBO.getSjl() == null || cardStockNewssetStockBO.getSjl() == 0){
                cardStockNewssetStockBO.setSjl(record.getPbmrq() != null ? record.getPbmrq().doubleValue() : null);
            }
            if (cardStockNewssetStockBO.getSxl() == null || cardStockNewssetStockBO.getSxl() == 0){
                cardStockNewssetStockBO.setSxl(record.getPsttm() != null ? record.getPsttm().doubleValue() : null);
            }
            //市盈率(静)
            if(record.getPelyr()!=null){
                cardStockNewssetStockBO.setPej(record.getPelyr());
            }
            //流通股
            if(record.getFloat_shares()!=null){
                cardStockNewssetStockBO.setCirculationstock(record.getFloat_shares());
            }
        }
        //EPS
        String com_uni_code =secBasicInfoDao.getStockComCode(stockCode);
        if(com_uni_code!=null && !com_uni_code.isEmpty()){
            List<ComIndexAnaModel> comProfitModels=iomIndexAnaDao.getTopNRecords(Long.parseLong(com_uni_code),1);
            if(comProfitModels!=null){
                cardStockNewssetStockBO.setEps(comProfitModels.get(0).getPerstock_incomeTTM());
            }
        }
        cardStockNewssetStockBO.setSuspend(false);
        SecSuspendModel suspend = secSuspendDao.getRecordBySecUniCode(secUniCode);
        logger.info("Get suspend for secUniCode {} result {}",secUniCode,suspend);
        if (suspend != null){
//            logger.info("secUniCode {} secSuspendDao find: {}",secUniCode,suspend);
            Date statDate = suspend.getStat_date();
            if (statDate == null || statDate.after(new Date())){
                logger.info("secUniCode {} suspend",secUniCode);
                cardStockNewssetStockBO.setSuspend(true);
                SecPriceDayModel zdRecord = secPriceDayDao.getNewestRecord(secUniCode);
                logger.info("secUniCode {} secSuspendDao setting Current_price: {}",secUniCode,zdRecord.getClose_price());
                cardStockNewssetStockBO.setCurrent_price(zdRecord.getClose_price().floatValue());
                cardStockNewssetStockBO.setZs(zdRecord.getClose_price().doubleValue());
            }
        }

        CardStockNewssetBO cardStockNewssetBO = new CardStockNewssetBO();
        cardStockNewssetBO.setTime(realTime);
        cardStockNewssetBO.setCompany_data(cardStockNewssetCompanyBO);
        cardStockNewssetBO.setStock_data(cardStockNewssetStockBO);

        return cardStockNewssetBO;
    }

    @Override
    public List<String> getStockRealTimePrice(String stockCode){
//        stockCode = stockCode.replaceAll("\\.(SH|SZ)$","");
//        stockCode = stockCode.replaceAll("^(SH|SZ)","");

        String cacheKey = KeyBuilder.getKeyStockRealtimePrice(stockCode);
        logger.info("redis getting key: "+cacheKey);
        String currentSharePriceInfo = redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                byte[] value = connection.get(serializer.serialize(cacheKey));
                return serializer.deserialize(value);
            }
        });
//        currentSharePriceInfo = "2018-07-17 15:01:03,13.4400,25537,-0.0015,39561937.0000,527310184.6400,0.7551,-0.2442,13.4300,13.4200,13.4100,13.4000,13.3900,502.0000,280.0000,749.0000,923.8500,329.0000,13.4400,13.4500,13.4600,13.4700,13.4800,31.0100,1452.0000,940.0000,884.0000,1561.0000,13.5500,13.1800,13.3900,13.4600,-0.0200,N,-1911.1600,,,,,,78580301184.00000000,125126933568.00000000,281.2170,1.5849";

        List<String> currentSharePriceInfoList = new LinkedList<>();
        if(currentSharePriceInfo == null || currentSharePriceInfo.isEmpty()){
            return currentSharePriceInfoList;
        }
        for (String item : currentSharePriceInfo.split(",",-1)){
            currentSharePriceInfoList.add(item.equals("None") ? "" : item);
        }

        return currentSharePriceInfoList;
    }

    @Override
    public CardStockSharePriceChangeBO buildSharePriceChange(String stockCode, Date startTime, String graphType) {
        SecBasicInfoModel comInfo = stockService.buildRecordByStockCode(stockCode);
        Date ipoDate = comInfo.getIpo_date();//获取上市时间

        if (ipoDate.after(startTime)){
            startTime = ipoDate;
        }
        String tableName = "";
        List<Map<String,Object>> records = null;
        switch (graphType.toLowerCase()){
            case "t"://分时
                tableName = "company_1_min_price";
                records = stockService.getRecordsBySecUniCode(comInfo.getSec_code(),startTime,graphType);
                break;
            case "5"://5分钟线
                tableName = "company_5_min_price";
                records = stockService.getRecordsBySecUniCode(comInfo.getSec_code(),startTime,graphType);
                break;
            case "30"://30分钟线
                tableName = "company_30_min_price";
                records = stockService.getRecordsBySecUniCode(comInfo.getSec_code(),startTime,graphType);
                break;
            case "d"://日线
                tableName = "sec_price_day";
                records = stockService.getRecordsBySecUniCode(comInfo.getSec_uni_code().toString(),startTime,graphType);
                break;
            case "m"://月线
                tableName = "sec_price_month";
                records = stockService.getRecordsBySecUniCode(comInfo.getSec_uni_code().toString(),startTime,graphType);
                break;
            default:
                tableName = "sec_price_day";
                records = stockService.getRecordsBySecUniCode(comInfo.getSec_uni_code().toString(),startTime,graphType);
                break;
        }

//        List<String> fields = new ArrayList<>(records.get(0).keySet());
//        List<ElementModel> units = elementDao.getRecordsBySheetNameAndFileds(tableName,fields);

        Map<String,CardStockSharePriceChangeCompanyBO> company = new TreeMap<>();
        for (Map<String,Object> item : records){
            CardStockSharePriceChangeCompanyBO cardStockSharePriceChangeCompanyBO = new CardStockSharePriceChangeCompanyBO();
            Date time = null;
            if (tableName.equals("sec_price_day")){
                time = (Date)item.get("trade_date");
                cardStockSharePriceChangeCompanyBO.setTrade_date(time);
                cardStockSharePriceChangeCompanyBO.setClose_price((BigDecimal)item.get("close_price"));
                cardStockSharePriceChangeCompanyBO.setDiffer_range((BigDecimal)item.get("differ_range"));
                cardStockSharePriceChangeCompanyBO.setDiffer((BigDecimal)item.get("differ"));
                cardStockSharePriceChangeCompanyBO.setOpen((BigDecimal)item.get("open_price"));
                cardStockSharePriceChangeCompanyBO.setHigh((BigDecimal)item.get("high_price"));
                cardStockSharePriceChangeCompanyBO.setLow((BigDecimal)item.get("low_price"));
                cardStockSharePriceChangeCompanyBO.setVolume((BigDecimal)item.getOrDefault("volume",new BigDecimal(0)));
                cardStockSharePriceChangeCompanyBO.setAmount((BigDecimal)item.getOrDefault("amount",new BigDecimal(0)));
                cardStockSharePriceChangeCompanyBO.setTurn((BigDecimal)item.getOrDefault("turn",new BigDecimal(0)));
            }else{
                time = (Date)item.get("time");
                cardStockSharePriceChangeCompanyBO.setTrade_date(time);
                cardStockSharePriceChangeCompanyBO.setClose_price((BigDecimal)item.get("close_price"));
                cardStockSharePriceChangeCompanyBO.setDiffer_range((BigDecimal)item.get("differ_range"));
                cardStockSharePriceChangeCompanyBO.setDiffer((BigDecimal)item.get("differ"));
                cardStockSharePriceChangeCompanyBO.setOpen((BigDecimal)item.get("open"));
                cardStockSharePriceChangeCompanyBO.setHigh((BigDecimal)item.get("high"));
                cardStockSharePriceChangeCompanyBO.setLow((BigDecimal)item.get("low"));
                cardStockSharePriceChangeCompanyBO.setVolume((BigDecimal)item.getOrDefault("volume",new BigDecimal(0)));
                cardStockSharePriceChangeCompanyBO.setAmount((BigDecimal)item.getOrDefault("amount",new BigDecimal(0)));
                cardStockSharePriceChangeCompanyBO.setTurn((BigDecimal)item.getOrDefault("turnover_rate",new BigDecimal(0)));
            }

            cardStockSharePriceChangeCompanyBO.setAmount_unit("元");
//            cardStockSharePriceChangeCompanyBO.setAvg_price_unit("元");
            cardStockSharePriceChangeCompanyBO.setClose_price_unit("元");
            cardStockSharePriceChangeCompanyBO.setDiffer_unit("元");
            cardStockSharePriceChangeCompanyBO.setHigh_unit("元");
            cardStockSharePriceChangeCompanyBO.setLow_unit("元");
            cardStockSharePriceChangeCompanyBO.setOpen_unit("元");
            cardStockSharePriceChangeCompanyBO.setVolume_unit("股");

            String key = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss").format(time);
            company.put(key,cardStockSharePriceChangeCompanyBO);
        }

        CardStockSharePriceChangeBO cardStockSharePriceChangeBO = new CardStockSharePriceChangeBO();
        cardStockSharePriceChangeBO.setCompany(company);
        cardStockSharePriceChangeBO.setUpdate_time(new Date());
        return cardStockSharePriceChangeBO;
    }

    /**
     * 股票|基金小卡片
     * @param abc_code
     * @return
     */
    @Override
    public Object getStockCard(String abc_code) {
        SecBasicInfoModel secBasic = secBasicInfoDao.selectByAbcCode(abc_code);
        if (secBasic == null){
            return null;
        }
        StockCardBO stockCardBO = new StockCardBO();
        stockCardBO.setAbc_code(abc_code);
        stockCardBO.setSec_name(secBasic.getSec_name());
        stockCardBO.setSec_uni_code(secBasic.getSec_uni_code().toString());
        stockCardBO.setCom_uni_code(secBasic.getCom_uni_code().toString());

        if (secBasic.getSec_type() == 1004003){
            stockCardBO.setStock_type("fund");
            FundNavModel fundNav = fundNavDao.selectBySecUniCode(secBasic.getSec_uni_code());
            if (fundNav != null){
                stockCardBO.setUnit_nav(fundNav.getUnit_nav() != null ? fundNav.getUnit_nav().doubleValue() : null);
                stockCardBO.setCumu_unit_nav(fundNav.getCumu_unit_nav() != null ? fundNav.getCumu_unit_nav().doubleValue() : null);
                stockCardBO.setRise_rate(fundNav.getRise_rate() != null ? fundNav.getRise_rate().doubleValue() : null);
                stockCardBO.setEnd_date(fundNav.getEnd_date());
                stockCardBO.setDiffer(fundNav.getDiffer() != null ? fundNav.getDiffer().doubleValue() : null);
                stockCardBO.setIs_money(0);
                return stockCardBO;
            }

            FundNavCurModel fundNavCur = fundNavCurDao.selectBySecUniCode(secBasic.getSec_uni_code());
            if (fundNavCur != null){
                stockCardBO.setIncome(fundNavCur.getTen_thousand_returns() != null ? fundNavCur.getTen_thousand_returns().doubleValue() : null);
                stockCardBO.setSeven_day_yield(fundNavCur.getSeven_day_yield() != null ? fundNavCur.getSeven_day_yield().doubleValue() : null);
                stockCardBO.setEnd_date(fundNavCur.getEnd_date());
                stockCardBO.setIs_money(1);
                return stockCardBO;
            }

        }

        if (secBasic.getSec_type() == 1004001){
            stockCardBO.setSuspend(false);
            SecSuspendModel suspend = secSuspendDao.getRecordBySecUniCode(secBasic.getSec_uni_code());
            if (suspend != null){
                Date statDate = suspend.getStat_date();
                if (statDate == null || statDate.after(new Date())){
                    stockCardBO.setSuspend(true);
                }
            }

            NormalValuationIndexModel record = normalValuationIndexDao.getValuationBySecUniCode(secBasic.getSec_uni_code());
            stockCardBO.setPe(record.getPettm() != null ? record.getPettm().doubleValue() : null);
            stockCardBO.setTotal_market_value(record.getTotal_market_value() != null ? record.getTotal_market_value().doubleValue() : 0);

            stockCardBO.setStock_type("HS");
        }


        String stock_price_icon = financialIndicatorDataNewDao.getIndicatorValue(secBasic.getSec_code()+"_sec_price_day_close_price_day_6");
        if (stock_price_icon != null){
            HashMap<String, String> amap = new HashMap();
            amap.put("indicator_value", stock_price_icon);
            stockCardBO.setStock_price_icon(amap);
        }

        List<String> currentSharePriceInfoList = getStockRealTimePrice(abc_code);
        if (currentSharePriceInfoList == null || currentSharePriceInfoList.size() < 32){
            logger.error("Empty redis data, stock code:{}", abc_code);
            stockCardBO.setCurrent_price("");
            stockCardBO.setZdf("");
            stockCardBO.setZg("");
            stockCardBO.setZd("");
            stockCardBO.setJk("");
            stockCardBO.setZs("");
        }else{
            stockCardBO.setCurrent_price(currentSharePriceInfoList.get(1));
            stockCardBO.setZdf(currentSharePriceInfoList.get(3));
            stockCardBO.setJk(currentSharePriceInfoList.get(30));
            stockCardBO.setZg(currentSharePriceInfoList.get(28));
            stockCardBO.setZd(currentSharePriceInfoList.get(29));
            stockCardBO.setZs(currentSharePriceInfoList.get(31));
        }

        return stockCardBO;
    }
}
