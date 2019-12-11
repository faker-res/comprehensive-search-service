package la.niub.abcapi.servicecompre.service.impl;

import la.niub.abcapi.servicecompre.component.cache.KeyBuilder;
import la.niub.abcapi.servicecompre.component.util.ObjectUtil;
import la.niub.abcapi.servicecompre.component.util.TimeUtil;
import la.niub.abcapi.servicecompre.config.enums.LineTypeEnum;
import la.niub.abcapi.servicecompre.dao.market.ISecPriceYearDao;
import la.niub.abcapi.servicecompre.dao.marketprice.ISecPrice15MinDao;
import la.niub.abcapi.servicecompre.dao.marketprice.ISecPrice1MinDao;
import la.niub.abcapi.servicecompre.dao.marketprice.ISecPrice30MinDao;
import la.niub.abcapi.servicecompre.dao.marketprice.ISecPrice5MinDao;
import la.niub.abcapi.servicecompre.dao.marketprice.ISecPrice60MinDao;
import la.niub.abcapi.servicecompre.dao.market.ISecPriceDayDao;
import la.niub.abcapi.servicecompre.dao.market.ISecPriceMonthDao;
import la.niub.abcapi.servicecompre.dao.market.ISecPriceWeekDao;
import la.niub.abcapi.servicecompre.dao.reporter.ICompany1MinPriceDao;
import la.niub.abcapi.servicecompre.dao.reporter.ICompany30MinPriceDao;
import la.niub.abcapi.servicecompre.dao.reporter.ICompany5MinPriceDao;
import la.niub.abcapi.servicecompre.dao.reporter.ISecBasicInfoDao;
import la.niub.abcapi.servicecompre.dao.reporter.IStockMarket1MinPriceDao;
import la.niub.abcapi.servicecompre.dao.reporter.IStockMarket30MinPriceDao;
import la.niub.abcapi.servicecompre.dao.reporter.IStockMarket5MinPriceDao;
import la.niub.abcapi.servicecompre.dao.reporter.IStockMarketMonthPriceDao;
import la.niub.abcapi.servicecompre.dao.reporter.IStockMarketSharePriceDao;
import la.niub.abcapi.servicecompre.dao.reporter.IStockMarketWeekPriceDao;
import la.niub.abcapi.servicecompre.model.*;
import la.niub.abcapi.servicecompre.model.bo.line.KlineCompanyBO;
import la.niub.abcapi.servicecompre.service.IStockService;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class StockServiceImpl implements IStockService {

    private static final Logger logger = LogManager.getLogger(StockServiceImpl.class);

    @Autowired
    ISecBasicInfoDao secBasicInfoDao;

    @Autowired
    ICompany1MinPriceDao company1MinPriceDao;

    @Autowired
    ICompany5MinPriceDao company5MinPriceDao;

    @Autowired
    ICompany30MinPriceDao company30MinPriceDao;

    @Autowired
    ISecPriceYearDao secPriceYearDao;

    @Autowired
    @Qualifier("MarketISecPriceDayDao")
    ISecPriceDayDao secPriceDayDao;

    @Autowired
    @Qualifier("MarketISecPriceWeekDao")
    ISecPriceWeekDao secPriceWeekDao;

    @Autowired
    @Qualifier("MarketISecPriceMonthDao")
    ISecPriceMonthDao secPriceMonthDao;

    @Autowired
    IStockMarket1MinPriceDao stockMarket1MinPriceDao;

    @Autowired
    IStockMarket5MinPriceDao stockMarket5MinPriceDao;

    @Autowired
    IStockMarket30MinPriceDao stockMarket30MinPriceDao;

    @Autowired
    IStockMarketSharePriceDao stockMarketSharePriceDao;

    @Autowired
    IStockMarketWeekPriceDao stockMarketWeekPriceDao;

    @Autowired
    IStockMarketMonthPriceDao stockMarketMonthPriceDao;

    @Autowired
    ISecPrice1MinDao secPrice1MinDao;

    @Autowired
    ISecPrice5MinDao secPrice5MinDao;

    @Autowired
    ISecPrice15MinDao secPrice15MinDao;

    @Autowired
    ISecPrice30MinDao secPrice30MinDao;

    @Autowired
    ISecPrice60MinDao secPrice60MinDao;

    @Override
    @Cacheable(cacheNames = KeyBuilder.KEY_STOCK)
    public SecBasicInfoModel buildRecordByStockCode(String stockCode) {
        if (StringUtils.isEmpty(stockCode)){
            return null;
        }
        if (!stockCode.contains(".")){
            return secBasicInfoDao.selectBySecCode2(stockCode);
        }else{
            return secBasicInfoDao.selectByAbcCode(stockCode.toUpperCase());
        }
    }

    @Override
    public List<Map<String,Object>> getRecordsBySecUniCode(String code, Date startTime, String graphType) {
        List<Map<String,Object>> records = new ArrayList<>();
        switch (graphType.toLowerCase()){
            case "t"://分时
                List<Company1MinPriceModel> company1MinPriceModelList = company1MinPriceDao.getRecords(code,startTime,null);
                for (Company1MinPriceModel item : company1MinPriceModelList){
                    records.add(ObjectUtil.bean2Map(item,false));
                }
                break;
            case "5"://5分钟线
                List<Company5MinPriceModel> company5MinPriceModelList = company5MinPriceDao.getRecords(code,startTime,null);
                for (Company5MinPriceModel item : company5MinPriceModelList){
                    records.add(ObjectUtil.bean2Map(item,false));
                }
                break;
            case "30"://30分钟线
                List<Company30MinPriceModel> company30MinPriceModelList = company30MinPriceDao.getRecords(code,startTime,null);
                for (Company30MinPriceModel item : company30MinPriceModelList){
                    records.add(ObjectUtil.bean2Map(item,false));
                }
                break;
            case "d"://日线
                List<SecPriceDayModel> secPriceDayModelList = secPriceDayDao.getRecordsBySecUniCode(Long.valueOf(code),startTime,null);
                for (SecPriceDayModel item : secPriceDayModelList){
                    records.add(ObjectUtil.bean2Map(item,false));
                }
                break;
            case "m"://月线
                List<SecPriceMonthModel> secPriceMonthModelList = secPriceMonthDao.getRecordsBySecUniCode(Long.valueOf(code),startTime,null);
                for (SecPriceMonthModel item : secPriceMonthModelList){
                    records.add(ObjectUtil.bean2Map(item,false));
                }
                break;
            default:
                List<SecPriceDayModel> secPriceDayModelList1 = secPriceDayDao.getRecordsBySecUniCode(Long.valueOf(code),startTime,null);
                for (SecPriceDayModel item : secPriceDayModelList1){
                    records.add(ObjectUtil.bean2Map(item,false));
                }
                break;
        }
        return records;
    }

    @Override
    public List<KlineCompanyBO> getKlineHistory(Date startTime, String stockCode, LineTypeEnum lineTypeEnum) {
        stockCode = stockCode.toUpperCase();
        SecBasicInfoModel secBasicInfoModel = buildRecordByStockCode(stockCode);
        if (secBasicInfoModel == null){
            return new ArrayList<>();
        }
        Date ipoDate = secBasicInfoModel.getIpo_date();//获取上市时间
        if (ipoDate != null && ipoDate.after(startTime)){
            startTime = ipoDate;
            logger.info("ipoDate {} is after startTime",ipoDate);
        }

        Map<String,KlineCompanyBO> records = new TreeMap<>();
        switch (lineTypeEnum){
            case MIN5://5分钟线
                List<SecPrice5MinModel> secPrice5MinModelList = secPrice5MinDao.getRecords(secBasicInfoModel.getAbc_code(),startTime);
                for (SecPrice5MinModel item : secPrice5MinModelList){
                    if (item.getTrade_date() == null){
                        continue;
                    }
                    KlineCompanyBO klineCompanyBO = new KlineCompanyBO();
                    klineCompanyBO.setTrade_date(item.getTrade_date());
                    klineCompanyBO.setClose_price(item.getClose_price());
                    klineCompanyBO.setOpen(item.getOpen());
                    klineCompanyBO.setHigh(item.getHigh());
                    klineCompanyBO.setLow(item.getLow());
                    klineCompanyBO.setVolume(item.getVolume());
                    klineCompanyBO.setAmount(item.getAmount());
                    klineCompanyBO.setDiffer(item.getDiffer());
                    klineCompanyBO.setDiffer_range(item.getDiffer_range());
                    klineCompanyBO.setTurn(item.getTurn());
                    klineCompanyBO.setAmplitude(item.getAmplitude());
                    records.put(TimeUtil.toString(item.getTrade_date(),"yyyyMMddHHmmss"),klineCompanyBO);
                }
                break;
            case MIN15://15分钟线
                List<SecPrice15MinModel> secPrice15MinModelList = secPrice15MinDao.getRecords(secBasicInfoModel.getAbc_code(),startTime);
                for (SecPrice15MinModel item : secPrice15MinModelList){
                    if (item.getTrade_date() == null){
                        continue;
                    }
                    KlineCompanyBO klineCompanyBO = new KlineCompanyBO();
                    klineCompanyBO.setTrade_date(item.getTrade_date());
                    klineCompanyBO.setClose_price(item.getClose_price());
                    klineCompanyBO.setOpen(item.getOpen());
                    klineCompanyBO.setHigh(item.getHigh());
                    klineCompanyBO.setLow(item.getLow());
                    klineCompanyBO.setVolume(item.getVolume());
                    klineCompanyBO.setAmount(item.getAmount());
                    klineCompanyBO.setDiffer(item.getDiffer());
                    klineCompanyBO.setDiffer_range(item.getDiffer_range());
                    klineCompanyBO.setTurn(item.getTurn());
                    klineCompanyBO.setAmplitude(item.getAmplitude());
                    records.put(TimeUtil.toString(item.getTrade_date(),"yyyyMMddHHmmss"),klineCompanyBO);
                }
                break;
            case MIN30://30分钟线
                List<SecPrice30MinModel> secPrice30MinModelList = secPrice30MinDao.getRecords(secBasicInfoModel.getAbc_code(),startTime);
                for (SecPrice30MinModel item : secPrice30MinModelList){
                    if (item.getTrade_date() == null){
                        continue;
                    }
                    KlineCompanyBO klineCompanyBO = new KlineCompanyBO();
                    klineCompanyBO.setTrade_date(item.getTrade_date());
                    klineCompanyBO.setClose_price(item.getClose_price());
                    klineCompanyBO.setOpen(item.getOpen());
                    klineCompanyBO.setHigh(item.getHigh());
                    klineCompanyBO.setLow(item.getLow());
                    klineCompanyBO.setVolume(item.getVolume());
                    klineCompanyBO.setAmount(item.getAmount());
                    klineCompanyBO.setDiffer(item.getDiffer());
                    klineCompanyBO.setDiffer_range(item.getDiffer_range());
                    klineCompanyBO.setTurn(item.getTurn());
                    klineCompanyBO.setAmplitude(item.getAmplitude());
                    records.put(TimeUtil.toString(item.getTrade_date(),"yyyyMMddHHmmss"),klineCompanyBO);
                }
                break;
            case MIN60://60分钟线
                List<SecPrice60MinModel> secPrice60MinModelList = secPrice60MinDao.getRecords(secBasicInfoModel.getAbc_code(),startTime);
                for (SecPrice60MinModel item : secPrice60MinModelList){
                    if (item.getTrade_date() == null){
                        continue;
                    }
                    KlineCompanyBO klineCompanyBO = new KlineCompanyBO();
                    klineCompanyBO.setTrade_date(item.getTrade_date());
                    klineCompanyBO.setClose_price(item.getClose_price());
                    klineCompanyBO.setOpen(item.getOpen());
                    klineCompanyBO.setHigh(item.getHigh());
                    klineCompanyBO.setLow(item.getLow());
                    klineCompanyBO.setVolume(item.getVolume());
                    klineCompanyBO.setAmount(item.getAmount());
                    klineCompanyBO.setDiffer(item.getDiffer());
                    klineCompanyBO.setDiffer_range(item.getDiffer_range());
                    klineCompanyBO.setTurn(item.getTurn());
                    klineCompanyBO.setAmplitude(item.getAmplitude());
                    records.put(TimeUtil.toString(item.getTrade_date(),"yyyyMMddHHmmss"),klineCompanyBO);
                }
                break;
            case DAY://日线
                List<SecPriceDayModel> secPriceDayModelList = secPriceDayDao.getRecords(secBasicInfoModel.getSec_uni_code(),startTime);
                for (SecPriceDayModel item : secPriceDayModelList){
                    if (item.getTrade_date() == null){
                        continue;
                    }
                    KlineCompanyBO klineCompanyBO = new KlineCompanyBO();
                    klineCompanyBO.setTrade_date(item.getTrade_date());
                    klineCompanyBO.setClose_price(item.getClose_price());
                    klineCompanyBO.setOpen(item.getOpen_price());
                    klineCompanyBO.setHigh(item.getHigh_price());
                    klineCompanyBO.setLow(item.getLow_price());
                    klineCompanyBO.setVolume(item.getVolume());
                    klineCompanyBO.setAmount(item.getAmount());
                    klineCompanyBO.setDiffer(item.getDiffer());
                    klineCompanyBO.setDiffer_range(item.getDiffer_range());
                    klineCompanyBO.setTurn(item.getTurn());
                    klineCompanyBO.setAmplitude(item.getAmplitude());
                    records.put(TimeUtil.toString(item.getTrade_date(),"yyyyMMddHHmmss"),klineCompanyBO);
                }
                break;
            case WEEK://周线
                List<SecPriceWeekModel> secPriceWeekModelList = secPriceWeekDao.getRecords(secBasicInfoModel.getSec_uni_code(),startTime);
                for (SecPriceWeekModel item : secPriceWeekModelList){
                    if (item.getEnd_date() == null){
                        continue;
                    }
                    KlineCompanyBO klineCompanyBO = new KlineCompanyBO();
                    klineCompanyBO.setTrade_date(item.getEnd_date());
                    klineCompanyBO.setClose_price(item.getClose_price());
                    klineCompanyBO.setOpen(item.getOpen());
                    klineCompanyBO.setHigh(item.getHigh());
                    klineCompanyBO.setLow(item.getLow());
                    klineCompanyBO.setVolume(item.getVolume());
                    klineCompanyBO.setAmount(item.getAmount());
                    klineCompanyBO.setDiffer(item.getDiffer());
                    klineCompanyBO.setDiffer_range(item.getDiffer_range());
                    klineCompanyBO.setTurn(item.getTurn());
                    klineCompanyBO.setAmplitude(item.getAmplitude());
                    records.put(TimeUtil.toString(item.getEnd_date(),"yyyyMMddHHmmss"),klineCompanyBO);
                }
                break;
            case MONTH://月线
                List<SecPriceMonthModel> secPriceMonthModelList = secPriceMonthDao.getRecords(secBasicInfoModel.getSec_uni_code(),startTime);
                for (SecPriceMonthModel item : secPriceMonthModelList){
                    if (item.getEnd_date() == null){
                        continue;
                    }
                    KlineCompanyBO klineCompanyBO = new KlineCompanyBO();
                    klineCompanyBO.setTrade_date(item.getEnd_date());
                    klineCompanyBO.setClose_price(item.getClose_price());
                    klineCompanyBO.setOpen(item.getOpen());
                    klineCompanyBO.setHigh(item.getHigh());
                    klineCompanyBO.setLow(item.getLow());
                    klineCompanyBO.setVolume(item.getVolume());
                    klineCompanyBO.setAmount(item.getAmount());
                    klineCompanyBO.setDiffer(item.getDiffer());
                    klineCompanyBO.setDiffer_range(item.getDiffer_range());
                    klineCompanyBO.setTurn(item.getTurn());
                    klineCompanyBO.setAmplitude(item.getAmplitude());
                    records.put(TimeUtil.toString(item.getEnd_date(),"yyyyMMddHHmmss"),klineCompanyBO);
                }
                break;
            case YEAR:

                List<SecPriceYearModel> secPriceYearModelList = secPriceYearDao.getRecords(secBasicInfoModel.getSec_uni_code(),startTime);
                for (SecPriceYearModel item : secPriceYearModelList){
                    if (item.getEnd_date() == null){
                        continue;
                    }
                    KlineCompanyBO klineCompanyBO = new KlineCompanyBO();
                    klineCompanyBO.setTrade_date(item.getEnd_date());
                    klineCompanyBO.setClose_price(item.getClose_price());
                    klineCompanyBO.setOpen(item.getOpen());
                    klineCompanyBO.setHigh(item.getHigh());
                    klineCompanyBO.setLow(item.getLow());
                    klineCompanyBO.setVolume(item.getVolume());
                    klineCompanyBO.setAmount(item.getAmount());
                    klineCompanyBO.setDiffer(item.getDiffer());
                    klineCompanyBO.setDiffer_range(item.getDiffer_range());
                    klineCompanyBO.setTurn(item.getTurn());
                    klineCompanyBO.setAmplitude(item.getAmplitude());
                    records.put(TimeUtil.toString(item.getEnd_date(),"yyyyMMddHHmmss"),klineCompanyBO);
                }
                break;
            default:
                return new ArrayList<>();
        }

        List<KlineCompanyBO> list = new LinkedList<>();
        for (Map.Entry<String, KlineCompanyBO> entry : records.entrySet()){
            list.add(entry.getValue());
        }
        return list;
    }

    @Override
    public List<SecBasicInfoModel> getRecordsBySecUniCodes(List<Long> secUniCodes) {
        return secBasicInfoDao.getListBySecUniCodesNullEndDate(secUniCodes);
    }

    @Override
    public List<SecBasicInfoModel> getRecordsBySecCodes(List<String> secCodes) {
        return secBasicInfoDao.getListByAbcCodes(secCodes);
    }
}
