package la.niub.abcapi.servicecompre.config.code;

public enum FundCompanyEnumCode implements ICodeConfig {
    EMPTY_COM_ID(45001, "empty org id"),
    ERROR_COM_ID(45002, "error org id"),

    ;


    private int code;

    private String message;

    private FundCompanyEnumCode(int code, String message) {
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
