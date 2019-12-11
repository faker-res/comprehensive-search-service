package la.niub.abcapi.servicecompre.model.request.publiccompany;

public class PublicCompanyFundFlowRequest {
    private long code;

    private int type;

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
