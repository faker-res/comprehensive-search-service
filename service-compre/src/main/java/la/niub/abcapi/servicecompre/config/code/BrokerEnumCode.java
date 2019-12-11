package la.niub.abcapi.servicecompre.config.code;

public enum BrokerEnumCode implements ICodeConfig {

    EMPTY_ORG_ID(43001, "empty org id"),
    ERROR_ORG_ID(43002, "error org id"),
    ERROR_HEATMAP_TYPE(43011, "error heatMap type"),
    ERROR_GET_BEGIN_TRADE_DATE(43012, "error get begin trade date"),
    ERROR_GET_END_TRADE_DATE(43013, "error get end trade date")

    ;


    private int code;

    private String message;

    private BrokerEnumCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
