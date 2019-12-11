package la.niub.abcapi.servicecompre.service.impl;

import la.niub.abcapi.servicecompre.component.cache.KeyBuilder;
import la.niub.abcapi.servicecompre.component.util.ListUtil;
import la.niub.abcapi.servicecompre.component.util.TimeUtil;
import la.niub.abcapi.servicecompre.config.configuration.RedisIndustryRealTimeConfiguration;
import la.niub.abcapi.servicecompre.config.configuration.RedisIndustryTimeConfiguration;
import la.niub.abcapi.servicecompre.config.configuration.RedisMarketConfiguration;
import la.niub.abcapi.servicecompre.config.enums.LineTypeEnum;
import la.niub.abcapi.servicecompre.dao.market.IIndexCaptitalFlowTimeDao;
import la.niub.abcapi.servicecompre.dao.reporter.IIndexBasicInfoDao;
import la.niub.abcapi.servicecompre.model.IndexBasicInfoModel;
import la.niub.abcapi.servicecompre.model.bo.line.KlineCompanyBO;
import la.niub.abcapi.servicecompre.model.bo.line.PublicCompanyTimeBO;
import la.niub.abcapi.servicecompre.model.bo.line.RealTimeBO;
import la.niub.abcapi.servicecompre.model.bo.line.TimelineCompanyBO;
import la.niub.abcapi.servicecompre.service.ICompanyManagerService;
import la.niub.abcapi.servicecompre.service.ILineService;
import la.niub.abcapi.servicecompre.service.IStockService;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class LineServiceImpl implements ILineService {

    private static final Logger logger = LogManager.getLogger(LineServiceImpl.class);

    @Autowired
    @Qualifier(RedisMarketConfiguration.NAME)
    private RedisTemplate<String, ?> marketRedisTemplate;

    @Autowired
    @Qualifier(RedisIndustryTimeConfiguration.NAME)
    private RedisTemplate<String, ?> industryTimeRedisTemplate;

    @Autowired
    @Qualifier(RedisIndustryRealTimeConfiguration.NAME)
    private RedisTemplate<String, ?> industryRealTimeRedisTemplate;

    @Autowired
    IIndexCaptitalFlowTimeDao indexCaptitalFlowTimeDao;

    @Autowired
    IIndexBasicInfoDao indexBasicInfoDao;

    @Autowired
    ICompanyManagerService companyManagerService;

    @Autowired
    IStockService stockService;

    @Override
    public List<TimelineCompanyBO> getTimeline(Date startTime, String stockCode) {
        String cacheKey = KeyBuilder.getKeyStockTimeline(stockCode);
        HashOperations<String, String, String> hash = marketRedisTemplate.opsForHash();
        Map<String, String> cacheResult = hash.entries(cacheKey);
        Map<String, String> dataResult = new TreeMap(cacheResult);

        //股票代码，分钟时间，收盘价，平均价，涨跌，涨跌幅，成交量，成交金额，转手率
        String time = TimeUtil.toString(startTime,"HHmm");
        List<TimelineCompanyBO> list = new LinkedList<>();
        for (Map.Entry<String, String> entry : dataResult.entrySet()){
            if (time.compareTo(entry.getKey()) > 0){
                continue;
            }
            List<String> values = Arrays.asList(entry.getValue().split(",",-1));
            if (values.size() < 9){
                continue;
            }
            if (StringUtils.isEmpty(values.get(1))){
                continue;
            }

            TimelineCompanyBO timelineCompanyBO = new TimelineCompanyBO();
            timelineCompanyBO.setTrade_date(TimeUtil.parseDateStr(values.get(1),"yyyy-MM-dd HH:mm:ss"));
            timelineCompanyBO.setOpen(StringUtils.isNotEmpty(values.get(2)) ? new BigDecimal(values.get(2)) : null);
            timelineCompanyBO.setAvg_price(StringUtils.isNotEmpty(values.get(3)) ? new BigDecimal(values.get(3)) : null);
            timelineCompanyBO.setDiffer(StringUtils.isNotEmpty(values.get(4)) ? new BigDecimal(values.get(4)) : null);
            timelineCompanyBO.setDiffer_range(StringUtils.isNotEmpty(values.get(5)) ? new BigDecimal(values.get(5)) : null);
            timelineCompanyBO.setVolume(StringUtils.isNotEmpty(values.get(6)) ? new BigDecimal(values.get(6)) : null);
            timelineCompanyBO.setAmount(StringUtils.isNotEmpty(values.get(7)) ? new BigDecimal(values.get(7)) : null);
            timelineCompanyBO.setTurn(StringUtils.isNotEmpty(values.get(8)) ? new BigDecimal(values.get(8)) : null);
            timelineCompanyBO.setAmount_unit("元");
            timelineCompanyBO.setAvg_price_unit("元");
            timelineCompanyBO.setDiffer_unit("元");
            timelineCompanyBO.setOpen_unit("元");
            timelineCompanyBO.setVolume_unit("股");
            
            list.add(timelineCompanyBO);
        }

        return list;
    }

    @Override
    public List<KlineCompanyBO> getKline(Date startTime, String stockCode, LineTypeEnum lineTypeEnum) {
        String keyPattern = null;
        List<String> fields = null;
        switch (lineTypeEnum){
            case DAY:
                keyPattern = "yyyyMMdd";
                fields = KeyBuilder.getKeyStockKlineField(startTime,stockCode,lineTypeEnum);
                break;
            case WEEK:
                keyPattern = "yyyyMMdd";
                fields = KeyBuilder.getKeyStockKlineField(startTime,stockCode,lineTypeEnum);
                break;
            case MONTH:
                keyPattern = "yyyyMM";
                fields = KeyBuilder.getKeyStockKlineField(startTime,stockCode,lineTypeEnum);
                break;
            case QUARTER:
                keyPattern = "yyyyMM";
                fields = KeyBuilder.getKeyStockKlineField(startTime,stockCode,lineTypeEnum);
                break;
            case HALFYEAR:
                keyPattern = "yyyyMM";
                fields = KeyBuilder.getKeyStockKlineField(startTime,stockCode,lineTypeEnum);
                break;
            case YEAR:
                keyPattern = "yyyy";
                fields = KeyBuilder.getKeyStockKlineField(startTime,stockCode,lineTypeEnum);
                break;
            case MIN1:
            case MIN5:
            case MIN15:
            case MIN30:
            case MIN60:
                keyPattern = "yyyyMMddHHmm";
                fields = KeyBuilder.getKeyStockKlineFieldMin(startTime,stockCode,lineTypeEnum);
                break;
            default:
                return new ArrayList<>();
        }

        Map<String, KlineCompanyBO> listMap = new TreeMap<>();

        /**
         * 获取K线历史数据
         */
        List<KlineCompanyBO> klineHistory = stockService.getKlineHistory(startTime,stockCode,lineTypeEnum);
        for (KlineCompanyBO item : klineHistory){
            listMap.put(TimeUtil.toString(item.getTrade_date(),keyPattern),item);
        }

        /**
         * 从缓存获取K线实时动态数据
         */
        String cacheKey = KeyBuilder.getKeyStockKline(lineTypeEnum,false);
        String yesterdayCacheKey = null;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 15);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        if (startTime.before(calendar.getTime())){
            yesterdayCacheKey = KeyBuilder.getKeyStockKline(lineTypeEnum,true);
        }
        logger.info("redis getting hash key: {} fields: {}...",cacheKey,fields.subList(0,Math.min(fields.size(),10)));
        HashOperations<String, String, String> hash = marketRedisTemplate.opsForHash();
        List<String> cacheResult = hash.multiGet(cacheKey,fields);
        if (yesterdayCacheKey != null){
            logger.info("redis getting hash key: {} fields: {}...",yesterdayCacheKey,fields.subList(0,Math.min(fields.size(),10)));
            List<String> yesterdayCacheResult = hash.multiGet(yesterdayCacheKey,fields);
            cacheResult.addAll(yesterdayCacheResult);
        }

        //0股票,1时间,2收盘价,3开盘价,4最高价,5最低价,6成交量,7成交金额,8涨跌,9涨跌幅,10换手率,11振幅，12证券类型，13证券统一代码
        cacheResult = (List<String>) ListUtil.removeEmpty(cacheResult);
        if (!cacheResult.isEmpty()){
            for (String item : cacheResult){
                List<String> values = Arrays.asList(item.split(",",-1));
                if (StringUtils.isEmpty(values.get(1))){
                    continue;
                }
                if (values.size() < 14){
                    continue;
                }

                Date date = TimeUtil.parseDateStr(values.get(1),"yyyy-MM-dd HH:mm:ss");

                KlineCompanyBO klineCompanyBO = new KlineCompanyBO();
                klineCompanyBO.setTrade_date(date);
                klineCompanyBO.setClose_price(StringUtils.isNotEmpty(values.get(2)) ? new BigDecimal(values.get(2)) : null);
                klineCompanyBO.setOpen(StringUtils.isNotEmpty(values.get(3)) ? new BigDecimal(values.get(3)) : null);
                klineCompanyBO.setHigh(StringUtils.isNotEmpty(values.get(4)) ? new BigDecimal(values.get(4)) : null);
                klineCompanyBO.setLow(StringUtils.isNotEmpty(values.get(5)) ? new BigDecimal(values.get(5)) : null);
                klineCompanyBO.setVolume(StringUtils.isNotEmpty(values.get(6)) ? new BigDecimal(values.get(6)) : null);
                klineCompanyBO.setAmount(StringUtils.isNotEmpty(values.get(7)) ? new BigDecimal(values.get(7)) : null);
                klineCompanyBO.setDiffer(StringUtils.isNotEmpty(values.get(8)) ? new BigDecimal(values.get(8)) : null);
                klineCompanyBO.setDiffer_range(StringUtils.isNotEmpty(values.get(9)) ? new BigDecimal(values.get(9)) : null);
                klineCompanyBO.setTurn(StringUtils.isNotEmpty(values.get(10)) ? new BigDecimal(values.get(10)) : null);
                klineCompanyBO.setAmplitude(StringUtils.isNotEmpty(values.get(11)) ? new BigDecimal(values.get(11)) : null);
                listMap.put(TimeUtil.toString(date,keyPattern),klineCompanyBO);
            }
        }

        //整理成list返回
        List<KlineCompanyBO> list = new LinkedList<>();
        for (Map.Entry<String, KlineCompanyBO> entry : listMap.entrySet()){
            list.add(entry.getValue());
        }

        /**
         * 把最后一个动态数据的时间设置成对应时间类型后一个区间的时间
         * 比如15分钟线最后一个动态数据时间需要是最接近的未来15分钟，如6点15、6点30、6点45、7点
         */
        switch (lineTypeEnum){
            case MIN5:
            case MIN15:
            case MIN30:
            case MIN60:
                KlineCompanyBO lastKlineCompanyBO = ((LinkedList<KlineCompanyBO>) list).getLast();
                Calendar lastCalendar = Calendar.getInstance();
                lastCalendar.setTime(lastKlineCompanyBO.getTrade_date());
                int minute = lastCalendar.get(Calendar.MINUTE);

                int minuteInterval = lineTypeEnum.getMinuteInterval();
                if (minute%minuteInterval != 0){
                    lastCalendar.add(Calendar.MINUTE,-minute%minuteInterval);
                    lastCalendar.add(Calendar.MINUTE,minuteInterval);
                }
                lastKlineCompanyBO.setTrade_date(lastCalendar.getTime());
                break;
            default:
                ;
        }

        return list;
    }

    @Override
    public RealTimeBO getRealTime(String stockCode) {
        String cacheKey = KeyBuilder.getKeyStockRealtimePrice(stockCode);
        logger.info("redis getting key: "+cacheKey);
        String result = marketRedisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = marketRedisTemplate.getStringSerializer();
                byte[] value = connection.get(serializer.serialize(cacheKey));
                return serializer.deserialize(value);
            }
        });
//        result = "2018-07-11 15:01:03,8.7800,31806,-0.0223,85129670.0000,744765824.8900,0.8791,-0.3819,8.7700,8.7600,8.7500,8.7400,8.7300,1237.0000,2603.9000,1821.0000,1621.0100,1096.0000,8.7800,8.7900,8.8000,8.8100,8.8200,1274.0000,4621.0000,9245.0000,2178.0000,1730.0000,8.8300,8.6800,8.7600,8.9800,-0.2000,N,-10528.0900,,,,,,150754766904.00000000,150756212092.00000000,5.7148,0.6749";
        if (StringUtils.isEmpty(result)){
            return null;
        }

        List<String> values = Arrays.asList(result.split(",",-1));
        if (StringUtils.isEmpty(values.get(0))){
            return null;
        }
        if (values.size() < 44){
            return null;
        }

        RealTimeBO realTimeBO = new RealTimeBO();
        Date time = TimeUtil.parseDateStr(values.get(0),"yyyy-MM-dd HH:mm:ss");
        realTimeBO.setTime(time);
        realTimeBO.setTrade_date(time);

        realTimeBO.setNow(StringUtils.isNotEmpty(values.get(1)) ? Double.valueOf(values.get(1)) : null);
        realTimeBO.setRoundlot(StringUtils.isNotEmpty(values.get(2)) ? Double.valueOf(values.get(2)) : null);
        realTimeBO.setDiffer_range(StringUtils.isNotEmpty(values.get(3)) ? Double.valueOf(values.get(3)) : null);
        realTimeBO.setVolume(StringUtils.isNotEmpty(values.get(4)) ? new BigDecimal(values.get(4)) : null);
        realTimeBO.setAmount(StringUtils.isNotEmpty(values.get(5)) ? new BigDecimal(values.get(5)) : null);
        realTimeBO.setVolumeratio(StringUtils.isNotEmpty(values.get(6)) ? Double.valueOf(values.get(6)) : 0);
        realTimeBO.setCommissiondiff(StringUtils.isNotEmpty(values.get(7)) ? Double.valueOf(values.get(7)) : 0);
        realTimeBO.setBuyprice1(StringUtils.isNotEmpty(values.get(8)) ? Double.valueOf(values.get(8)) : null);
        realTimeBO.setBuyprice2(StringUtils.isNotEmpty(values.get(9)) ? Double.valueOf(values.get(9)) : null);
        realTimeBO.setBuyprice3(StringUtils.isNotEmpty(values.get(10)) ? Double.valueOf(values.get(10)) : null);
        realTimeBO.setBuyprice4(StringUtils.isNotEmpty(values.get(11)) ? Double.valueOf(values.get(11)) : null);
        realTimeBO.setBuyprice5(StringUtils.isNotEmpty(values.get(12)) ? Double.valueOf(values.get(12)) : null);
        realTimeBO.setBuyvolume1(StringUtils.isNotEmpty(values.get(13)) ? Double.valueOf(values.get(13)) : null);
        realTimeBO.setBuyvolume2(StringUtils.isNotEmpty(values.get(14)) ? Double.valueOf(values.get(14)) : null);
        realTimeBO.setBuyvolume3(StringUtils.isNotEmpty(values.get(15)) ? Double.valueOf(values.get(15)) : null);
        realTimeBO.setBuyvolume4(StringUtils.isNotEmpty(values.get(16)) ? Double.valueOf(values.get(16)) : null);
        realTimeBO.setBuyvolume5(StringUtils.isNotEmpty(values.get(17)) ? Double.valueOf(values.get(17)) : null);
        realTimeBO.setSellprice1(StringUtils.isNotEmpty(values.get(18)) ? Double.valueOf(values.get(18)) : null);
        realTimeBO.setSellprice2(StringUtils.isNotEmpty(values.get(19)) ? Double.valueOf(values.get(19)) : null);
        realTimeBO.setSellprice3(StringUtils.isNotEmpty(values.get(20)) ? Double.valueOf(values.get(20)) : null);
        realTimeBO.setSellprice4(StringUtils.isNotEmpty(values.get(21)) ? Double.valueOf(values.get(21)) : null);
        realTimeBO.setSellprice5(StringUtils.isNotEmpty(values.get(22)) ? Double.valueOf(values.get(22)) : null);
        realTimeBO.setSellvolume1(StringUtils.isNotEmpty(values.get(23)) ? Double.valueOf(values.get(23)) : null);
        realTimeBO.setSellvolume2(StringUtils.isNotEmpty(values.get(24)) ? Double.valueOf(values.get(24)) : null);
        realTimeBO.setSellvolume3(StringUtils.isNotEmpty(values.get(25)) ? Double.valueOf(values.get(25)) : null);
        realTimeBO.setSellvolume4(StringUtils.isNotEmpty(values.get(26)) ? Double.valueOf(values.get(26)) : null);
        realTimeBO.setSellvolume5(StringUtils.isNotEmpty(values.get(27)) ? Double.valueOf(values.get(27)) : null);
        realTimeBO.setHigh(StringUtils.isNotEmpty(values.get(28)) ? Double.valueOf(values.get(28)) : null);
        realTimeBO.setLow(StringUtils.isNotEmpty(values.get(29)) ? Double.valueOf(values.get(29)) : null);
        realTimeBO.setOpen(StringUtils.isNotEmpty(values.get(30)) ? Double.valueOf(values.get(30)) : null);
        realTimeBO.setPre_close(StringUtils.isNotEmpty(values.get(31)) ? Double.valueOf(values.get(31)) : null);
        realTimeBO.setDiffer(StringUtils.isNotEmpty(values.get(32)) ? Double.valueOf(values.get(32)) : null);
        realTimeBO.setSuspension(StringUtils.isNotEmpty(values.get(33)) ? values.get(33) : null);
        realTimeBO.setCommission(StringUtils.isNotEmpty(values.get(34)) ? Double.valueOf(values.get(34)) : null);
        realTimeBO.setOutvolume(StringUtils.isNotEmpty(values.get(35)) ? Double.valueOf(values.get(35)) : null);
        realTimeBO.setInvolume(StringUtils.isNotEmpty(values.get(36)) ? Double.valueOf(values.get(36)) : null);
        realTimeBO.setRise_num(StringUtils.isNotEmpty(values.get(37)) ? Integer.valueOf(values.get(37)) : null);
        realTimeBO.setFall_num(StringUtils.isNotEmpty(values.get(38)) ? Integer.valueOf(values.get(38)) : null);
        realTimeBO.setFair_num(StringUtils.isNotEmpty(values.get(39)) ? Integer.valueOf(values.get(39)) : null);
        realTimeBO.setLiqmv(StringUtils.isNotEmpty(values.get(40)) ? new BigDecimal(values.get(40)) : null);
        realTimeBO.setMv(StringUtils.isNotEmpty(values.get(41)) ? new BigDecimal(values.get(41)) : null);
        realTimeBO.setPe(StringUtils.isNotEmpty(values.get(42)) ? Double.valueOf(values.get(42)) : null);
        realTimeBO.setPb(StringUtils.isNotEmpty(values.get(43)) ? Double.valueOf(values.get(43)) : null);
        realTimeBO.setSwing(StringUtils.isNotEmpty(values.get(44)) ? Double.valueOf(values.get(44)) :0);
        realTimeBO.setChange(StringUtils.isNotEmpty(values.get(45)) ? Double.valueOf(values.get(45)) : 0);
        return realTimeBO;
    }

    @Override
    public PublicCompanyTimeBO getPublicCompanyRealTime(Long indexUniCode) {
        IndexBasicInfoModel indexBasicInfoModel = indexBasicInfoDao.selectByPrimaryKey(indexUniCode);
        if (indexBasicInfoModel == null || StringUtils.isEmpty(indexBasicInfoModel.getAbc_index_code())){
            return null;
        }

        //指数代码,交易时间,最新价,分钟成交股数,总成交量,分钟成交额,总成交额,涨跌,涨跌幅,上涨家数,下跌家数,持平家数,流通市值,总市值,市盈率（动）,市净率,市盈率TTM
        String cacheKey = KeyBuilder.getKeyIndustryTime(indexBasicInfoModel.getAbc_index_code());
        logger.info("redis getting key: "+cacheKey);
        HashOperations<String, String, String> hash = industryTimeRedisTemplate.opsForHash();
        Map<String, String> cacheResult = hash.entries(cacheKey);
        if (cacheResult == null || cacheResult.isEmpty()){
            return null;
        }
        String result = (new TreeMap<>(cacheResult)).lastEntry().getValue();
//        String result = "801020.SWI,2018-07-13 14:34:00,2588.897949,1668667,476837157,11312645.0,3319477831.0,-11.031982,-0.004243,3,12,2,1765542027854.3000,2042209886709.8500,19.2622,0.9450";

        List<String> values = Arrays.asList(result.split(",",-1));
        if (StringUtils.isEmpty(values.get(1))){
            return null;
        }
        if (values.size() < 16){
            return null;
        }

        PublicCompanyTimeBO publicCompanyRealTimeBO = new PublicCompanyTimeBO();
        Date time = TimeUtil.parseDateStr(values.get(1),"yyyy-MM-dd HH:mm:ss");
        publicCompanyRealTimeBO.setTime(time);
        publicCompanyRealTimeBO.setTrade_date(time);

        publicCompanyRealTimeBO.setNow(StringUtils.isNotEmpty(values.get(2)) ? Double.valueOf(values.get(2)) : null);
        publicCompanyRealTimeBO.setMin_volume(StringUtils.isNotEmpty(values.get(3)) ? new BigDecimal(values.get(3)) : null);
        publicCompanyRealTimeBO.setVolume(StringUtils.isNotEmpty(values.get(4)) ? new BigDecimal(values.get(4)) : null);
        publicCompanyRealTimeBO.setMin_amount(StringUtils.isNotEmpty(values.get(5)) ? new BigDecimal(values.get(5)) : null);
        publicCompanyRealTimeBO.setAmount(StringUtils.isNotEmpty(values.get(6)) ? new BigDecimal(values.get(6)) : null);
        publicCompanyRealTimeBO.setDiffer(StringUtils.isNotEmpty(values.get(7)) ? Double.valueOf(values.get(7)) : null);
        publicCompanyRealTimeBO.setDiffer_range(StringUtils.isNotEmpty(values.get(8)) ? Double.valueOf(values.get(8)) : null);
        publicCompanyRealTimeBO.setRise_num(StringUtils.isNotEmpty(values.get(9)) ? Integer.valueOf(values.get(9)) : null);
        publicCompanyRealTimeBO.setFall_num(StringUtils.isNotEmpty(values.get(10)) ? Integer.valueOf(values.get(10)) : null);
        publicCompanyRealTimeBO.setFair_num(StringUtils.isNotEmpty(values.get(11)) ? Integer.valueOf(values.get(11)) : null);
        publicCompanyRealTimeBO.setLiqmv(StringUtils.isNotEmpty(values.get(12)) ? new BigDecimal(values.get(12)) : null);
        publicCompanyRealTimeBO.setMv(StringUtils.isNotEmpty(values.get(13)) ? new BigDecimal(values.get(13)) : null);
        publicCompanyRealTimeBO.setPe(StringUtils.isNotEmpty(values.get(14)) ? Double.valueOf(values.get(14)) : null);
        publicCompanyRealTimeBO.setPb(StringUtils.isNotEmpty(values.get(15)) ? Double.valueOf(values.get(15)) : null);

        return publicCompanyRealTimeBO;
    }
//    public PublicCompanyRealTimeBO getPublicCompanyRealTime(Long indexUniCode) {
//        IndexBasicInfoModel indexBasicInfoModel = indexBasicInfoDao.selectByPrimaryKey(indexUniCode);
//        if (indexBasicInfoModel == null || StringUtils.isEmpty(indexBasicInfoModel.getAbc_index_code())){
//            return null;
//        }
//
//        //交易时间,最新价,最高价,最低价,开盘价,昨收价,现手,成交量（单位:股）,成交金额（单位:元）,涨跌,涨跌幅（无单位,调用*100）,涨速
//        String cacheKey = KeyBuilder.getKeyIndustryRealTime(indexBasicInfoModel.getAbc_index_code());
//        logger.info("redis getting key: "+cacheKey);
//        String result = industryRealTimeRedisTemplate.execute(new RedisCallback<String>() {
//            @Override
//            public String doInRedis(RedisConnection connection) throws DataAccessException {
//                RedisSerializer<String> serializer = industryRealTimeRedisTemplate.getStringSerializer();
//                byte[] value = connection.get(serializer.serialize(cacheKey));
//                return serializer.deserialize(value);
//            }
//        });
////        result = "2018-07-16 15:15:00,2598.179932,2605.789063,2583.695068,2584.133057,2584.639893,0,699232539,4724872596.0,13.540039,0.005239,0.0";
//
//        List<String> values = Arrays.asList(result.split(",",-1));
//        if (StringUtils.isEmpty(values.get(0))){
//            return null;
//        }
//        if (values.size() < 12){
//            return null;
//        }
//
//        PublicCompanyRealTimeBO publicCompanyRealTimeBO = new PublicCompanyRealTimeBO();
//        Date time = TimeUtil.parseDateStr(values.get(0),"yyyy-MM-dd HH:mm:ss");
//        publicCompanyRealTimeBO.setTime(time);
//        publicCompanyRealTimeBO.setTrade_date(time);
//
//        publicCompanyRealTimeBO.setNow(StringUtils.isNotEmpty(values.get(1)) ? Double.valueOf(values.get(1)) : null);
//        publicCompanyRealTimeBO.setHigh(StringUtils.isNotEmpty(values.get(2)) ? Double.valueOf(values.get(2)) : null);
//        publicCompanyRealTimeBO.setLow(StringUtils.isNotEmpty(values.get(3)) ? Double.valueOf(values.get(3)) : null);
//        publicCompanyRealTimeBO.setOpen(StringUtils.isNotEmpty(values.get(4)) ? Double.valueOf(values.get(4)) : null);
//        publicCompanyRealTimeBO.setPre_close(StringUtils.isNotEmpty(values.get(5)) ? Double.valueOf(values.get(5)) : null);
//        publicCompanyRealTimeBO.setRoundlot(StringUtils.isNotEmpty(values.get(6)) ? Double.valueOf(values.get(6)) : null);
//        publicCompanyRealTimeBO.setVolume(StringUtils.isNotEmpty(values.get(7)) ? new BigDecimal(values.get(7)) : null);
//        publicCompanyRealTimeBO.setAmount(StringUtils.isNotEmpty(values.get(8)) ? new BigDecimal(values.get(8)) : null);
//        publicCompanyRealTimeBO.setDiffer(StringUtils.isNotEmpty(values.get(9)) ? Double.valueOf(values.get(9)) : null);
//        publicCompanyRealTimeBO.setDiffer_range(StringUtils.isNotEmpty(values.get(10)) ? Double.valueOf(values.get(10)) : null);
//        publicCompanyRealTimeBO.setDiffer_speed(StringUtils.isNotEmpty(values.get(11)) ? Double.valueOf(values.get(11)) : null);
//
//        return publicCompanyRealTimeBO;
//    }
}
