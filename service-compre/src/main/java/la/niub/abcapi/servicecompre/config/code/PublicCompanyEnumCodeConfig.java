package la.niub.abcapi.servicecompre.config.code;

public enum PublicCompanyEnumCodeConfig implements ICodeConfig {


    EMPTY_PUBLIC_COMPANY_CODE(408001, "empty public company code"),
    ERROR_PUBLIC_COMPANY_FUND_FLOW_TYPE(408002, "error public company fund flow type"),

    ;


    private int code;

    private String message;

    private PublicCompanyEnumCodeConfig(int code, String message) {
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
