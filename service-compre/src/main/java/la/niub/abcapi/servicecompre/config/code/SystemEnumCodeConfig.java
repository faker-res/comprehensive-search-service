package la.niub.abcapi.servicecompre.config.code;

public enum SystemEnumCodeConfig implements ICodeConfig {
    SUCCESS(200, "success"),
    ERROR_SERVER(50000, "server error"),
    ERROR_REQUEST_URL(40001, "error request url"),
    ERROR_METHOD_NOT_SUPPORTED(40002, "method not supported"),

    SOLR_REQUEST_FAIL(1001,"后端服务请求出错"),

    INCLUDE_EQUALS_NOT_INCLUDE(1101,"标题包含与标题不包含相同"),
    NO_KEYWORD_IN_GROUP(2002,"No keyword in this group"),
    NO_STOCK_IN_GROUP(2003,"No stock in this group"),
    NO_STOCK(2004,"No stock"),
    NO_KEYWORD(2005,"No keyword"),
    NO_SUBSCRIBE(2006,"无订阅"),



    NOT_SUPPORT_INDUSTRY_LEVEL(3102,"Do not support industry level 4!"),
    UNSUPPORTED_TYPE(3103,"Unsupported type"),
    UNSUPPORTED_KEYWORD(3101,"Keyword is Empty"),
    ;
    private int code;

    private String message;

    private SystemEnumCodeConfig(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
