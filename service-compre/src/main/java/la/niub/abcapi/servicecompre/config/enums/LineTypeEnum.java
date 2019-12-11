package la.niub.abcapi.servicecompre.config.enums;

/**
 * K线类型
 */
public enum LineTypeEnum {
    //1分钟线
    MIN1("1",1),
    //5分钟线
    MIN5("5",5),
    //15分钟线
    MIN15("15",15),
    //30分钟线
    MIN30("30",30),
    //60分钟线
    MIN60("60",60),
    //日线
    DAY("DAY",1440),
    //周线
    WEEK("WEEK",10080),
    //月线
    MONTH("MONTH",40320),
    //季度线
    QUARTER("QUARTER",120960),
    //半年线
    HALFYEAR("HALFYEAR",241920),
    //年线
    YEAR("YEAR",483840);

    private String tag;

    private Integer minuteInterval;

    LineTypeEnum(String tag, Integer minuteInterval) {
        this.tag = tag;
        this.minuteInterval = minuteInterval;
    }

    public String getTag() {
        return tag;
    }

    public Integer getMinuteInterval() {
        return minuteInterval;
    }
}
