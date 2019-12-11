package la.niub.abcapi.servicecompre.config.code;

public enum FinanceVipEnumCode implements ICodeConfig {

    EMPTY_FV_ID(412001, "empty fv id"),
    ERROR_FV_ID(412002, "error fv id"),

    ;


    private int code;

    private String message;

    private FinanceVipEnumCode(int code, String message) {
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
