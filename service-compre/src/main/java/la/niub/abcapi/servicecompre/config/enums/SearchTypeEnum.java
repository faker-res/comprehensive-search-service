package la.niub.abcapi.servicecompre.config.enums;

/**
 * 类型
 */
public enum SearchTypeEnum {

    //搜图
    C_SEARCH_CHART(SearchTypeEnum.CHART,"chart"),
    //搜表
    C_SEARCH_TABLE(SearchTypeEnum.TABLE,"table"),
    //搜公告
    C_SEARCH_NOTICE(SearchTypeEnum.NOTICE,"notice"),
    //搜研报
    C_SEARCH_REPORT(SearchTypeEnum.REPORT,"report"),
    //搜资讯
    C_SEARCH_NEWS(SearchTypeEnum.NEWS,"news"),
    ;

    public final static int CHART = 1;
    public final static int TABLE = 2;
    public final static int NOTICE = 3;
    public final static int REPORT = 4;
    public final static int NEWS = 5;

    private Integer type;

    private String name;

    SearchTypeEnum(Integer type, String name) {
        this.type = type;
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}
