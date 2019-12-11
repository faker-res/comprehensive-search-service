package la.niub.abcapi.servicecompre.model.request.fund;

public class FundCompanyDetailRequest {

    private String name;

    private Long com_uni_code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCom_uni_code() {
        return com_uni_code;
    }

    public void setCom_uni_code(Long com_uni_code) {
        this.com_uni_code = com_uni_code;
    }

    @Override
    public String toString() {
        return "FundCompanyDetailRequest{" +
                "name='" + name + '\'' +
                ", com_uni_code=" + com_uni_code +
                '}';
    }
}
