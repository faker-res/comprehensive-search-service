package la.niub.abcapi.servicecompre.component.cache;

import la.niub.abcapi.servicecompre.component.util.TimeUtil;
import la.niub.abcapi.servicecompre.config.enums.LineTypeEnum;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class KeyBuilder {

    private static final String KEY_NOTICE_NEW_COUNT = "notice_new_count";

    private static final String KEY_FLAT_NOTICE_CATEGORIES = "flat_notice_categories";

    private static final String KEY_INDUSTRY_FLAT = "sec_industry_new_falt";

    //研报分类
    private static final String KEY_REPORT_CATEGORY = "report_categories";

    //展开的研报分类
    private static final String KEY_REPORT_CATEGORY_FLAT = "report_categories_flat";

    //实时数据
    private static final String KEY_STOCK_REALTIME_PRICE = "price_";

    //分时线
    private static final String KEY_STOCK_TIMELINE = "price_fs_";

    //K线
    private static final String KEY_STOCK_KLINE = "KX_";
    private static final String KEY_STOCK_KLINE_MIN = "KX_MIN_";

    // 5分钟取值范围(48个)
    public static final int[] SCOPE_MIN_5 = {935, 940, 945, 950, 955, 1000, 1005, 1010, 1015, 1020, 1025, 1030, 1035, 1040, 1045, 1050, 1055, 1100, 1105, 1110, 1115, 1120, 1125, 1130, 1305, 1310, 1315, 1320, 1325, 1330, 1335, 1340, 1345, 1350, 1355, 1400, 1405, 1410, 1415, 1420, 1425, 1430, 1435, 1440, 1445, 1450, 1455, 1500};

    // 15分钟取值范围(16个)
    public static final int[] SCOPE_MIN_15 = {945, 1000, 1015, 1030, 1045, 1100, 1115, 1130, 1315, 1330, 1345, 1400, 1415, 1430, 1445, 1500};

    // 30分钟取值范围(8个)
    public static final int[] SCOPE_MIN_30 = {1000, 1030, 1100, 1130, 1330, 1400, 1430, 1500};

    // 60分钟取值范围(4个)
    public static final int[] SCOPE_MIN_60 = {1030, 1130, 1400, 1500};

    //行业分时数据
    private static final String KEY_INDUSTRY_TIME = "price_fs_";

    //行业实时数据
    private static final String KEY_INDUSTRY_REALTIME = "price_";

    //个股
    public static final String KEY_STOCK = "STOCK";

    //公司
    public static final String KEY_ORG = "ORG";

    // 分析师同领域研报数量统计
    private static final String ANALYST_DIRECTION_REPORT = "analyst_report_";

    // 分析师基本信息
    private static final String ANALYST_BASIC = "analyst_basic_";


    private static final String FUND_MANAGER_CHG_BY_YEAR_GROUP_BY_FUND_KEEPER = "fund_manager_chg_by_year_group_by_fund_keeper";

    private static final String FUND_KEEPER_SCALE_BY_YEAR_GROUP_BY_KEEPER = "fund_keeper_scale_by_year_group_by_keeper";

    public static final String getNoticeNewCountKey() {
        return KEY_NOTICE_NEW_COUNT;
    }

    public static final String getFlatNoticeCategoriesKey() {
        return KEY_FLAT_NOTICE_CATEGORIES;
    }

    public static final String getIndustryNameKey() {
        return KEY_INDUSTRY_FLAT;
    }

    //研报分类
    public static String getKeyReportCategory() {
        return KEY_REPORT_CATEGORY;
    }

    //展开的研报分类
    public static String getKeyReportCategoryFlat() {
        return KEY_REPORT_CATEGORY_FLAT;
    }

    //实时数据
    public static String getKeyStockRealtimePrice(String stockCode) {
        StringBuilder builder = new StringBuilder(KEY_STOCK_REALTIME_PRICE);
        builder.append(stockCode.toUpperCase());
        return builder.toString();
    }

    //分时线
    public static String getKeyStockTimeline(String stockCode) {
        StringBuilder builder = new StringBuilder(KEY_STOCK_TIMELINE);
        builder.append(stockCode.toUpperCase());
        return builder.toString();
    }

    //K线
    public static String getKeyStockKline(LineTypeEnum lineTypeEnum,Boolean yesterday) {
        Date date = new Date();
        if (yesterday){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DAY_OF_YEAR, -1);
            date = calendar.getTime();
        }
        StringBuilder builder;
        switch (lineTypeEnum){
            case DAY:
                builder = new StringBuilder(KEY_STOCK_KLINE);
                builder.append(lineTypeEnum.getTag());
                builder.append(TimeUtil.toString(date,"_yyyyMMdd"));
                break;
            case WEEK:
                builder = new StringBuilder(KEY_STOCK_KLINE);
                builder.append(lineTypeEnum.getTag());
                builder.append(TimeUtil.toString(date,"_yyyy_w"));
                break;
            case MONTH:
                builder = new StringBuilder(KEY_STOCK_KLINE);
                builder.append(lineTypeEnum.getTag());
                builder.append(TimeUtil.toString(date,"_yyyyMM"));
                break;
            case QUARTER:
                builder = new StringBuilder(KEY_STOCK_KLINE);
                builder.append(lineTypeEnum.getTag());
                builder.append(TimeUtil.toString(date,"_yyyy_"));
                builder.append(date.getMonth()/3+1);
                break;
            case HALFYEAR:
                builder = new StringBuilder(KEY_STOCK_KLINE);
                builder.append(lineTypeEnum.getTag());
                builder.append(TimeUtil.toString(date,"_yyyy_"));
                builder.append(date.getMonth()/6+1);
                break;
            case YEAR:
                builder = new StringBuilder(KEY_STOCK_KLINE);
                builder.append(lineTypeEnum.getTag());
                builder.append(TimeUtil.toString(date,"_yyyy"));
                break;
            case MIN1:
            case MIN5:
            case MIN15:
            case MIN30:
            case MIN60:
                builder = new StringBuilder(KEY_STOCK_KLINE_MIN);
                builder.append(lineTypeEnum.getTag());
                builder.append(TimeUtil.toString(date,"_yyyyMMdd"));
                break;
            default:
                return null;
        }
        return builder.toString();
    }

    //K线分钟线
    public static List<String> getKeyStockKlineFieldMin(Date startTime, String stockCode, LineTypeEnum lineTypeEnum) {
        Set<String> fields = new HashSet<>();
        stockCode = stockCode.toUpperCase();

        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        int[] SCOPE_MIN;
        switch (lineTypeEnum){
            case MIN5:
                SCOPE_MIN = SCOPE_MIN_5;
                break;
            case MIN15:N:
                SCOPE_MIN = SCOPE_MIN_15;
                break;
            case MIN30:
                SCOPE_MIN = SCOPE_MIN_30;
                break;
            case MIN60:
                SCOPE_MIN = SCOPE_MIN_60;
                break;
            default:
                return new ArrayList<>();
        }

        while (true){
            for (int scope : SCOPE_MIN){
                calendar.set(Calendar.HOUR_OF_DAY,scope/100);
                calendar.set(Calendar.MINUTE,scope%100);
                if (calendar.getTime().after(startTime)){
                    fields.add(stockCode+"_"+TimeUtil.toString(calendar.getTime(),"yyyyMMddHHmm"));
                }
            }

            calendar.add(Calendar.DAY_OF_YEAR, -1);
            if (calendar.getTime().before(startTime)){
                break;
            }
        }

        return new ArrayList<>(fields);
    }

    //K线日月线
    public static List<String> getKeyStockKlineField(Date startTime, String stockCode, LineTypeEnum lineTypeEnum) {
        Set<String> fields = new HashSet<>();
        stockCode = stockCode.toUpperCase();

        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        String dateFormat;
        int dateUnit;
        switch (lineTypeEnum){
            case DAY://日
                dateFormat = "yyyyMMdd";
                dateUnit = Calendar.DAY_OF_YEAR;
                break;
            case WEEK://周
                dateFormat = "yyyyMMdd_w";
                dateUnit = Calendar.WEEK_OF_YEAR;
                break;
            case MONTH://月
                dateFormat = "yyyyMM";
                dateUnit = Calendar.MONTH;
                break;
            case YEAR://年
                dateFormat = "yyyy";
                dateUnit = Calendar.YEAR;
                break;
            default:
                return new ArrayList<>();
        }
//        fields.add(stockCode+"_"+TimeUtil.toString(new Date(),dateFormat));

        while (true){
            if (calendar.getTime().after(startTime)){
                fields.add(stockCode+"_"+TimeUtil.toString(calendar.getTime(),dateFormat));
            }

            calendar.add(dateUnit, -1);
            if (calendar.getTime().before(startTime)){
                break;
            }
        }

        return new ArrayList<>(fields);
    }

    //行业分时数据
    public static String getKeyIndustryTime(String abcIndexCode) {
        StringBuilder builder = new StringBuilder(KEY_INDUSTRY_TIME);
        builder.append(abcIndexCode.toUpperCase());
        return builder.toString();
    }

    //行业实时数据
    public static String getKeyIndustryRealTime(String abcIndexCode) {
        StringBuilder builder = new StringBuilder(KEY_INDUSTRY_REALTIME);
        builder.append(abcIndexCode.toUpperCase());
        return builder.toString();
    }

    // 分析师缓存
    public static String getKeyAnalyst(String keyCode, String type) {
        StringBuilder builder = new StringBuilder(type.equals("report") ? ANALYST_DIRECTION_REPORT : ANALYST_BASIC);
        builder.append(keyCode);
        return builder.toString();
    }


    public static String getFundManagerChgByYearGroupByFundKeeperKey(String begintime, String endTime) {
        StringBuilder builder = new StringBuilder(FUND_MANAGER_CHG_BY_YEAR_GROUP_BY_FUND_KEEPER);
        builder.append("_").append(begintime).append("_").append(endTime);
        return builder.toString();
    }


    public static String getFundManagerScaleByYearGroupByFundKeeperKey(String begintime, String endTime) {
        StringBuilder builder = new StringBuilder(FUND_KEEPER_SCALE_BY_YEAR_GROUP_BY_KEEPER);
        builder.append("_").append(begintime).append("_").append(endTime);
        return builder.toString();
    }


}
