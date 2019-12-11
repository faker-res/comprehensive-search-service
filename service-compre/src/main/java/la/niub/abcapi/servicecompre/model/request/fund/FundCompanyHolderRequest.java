package la.niub.abcapi.servicecompre.model.request.fund;

public class FundCompanyHolderRequest {

    private String name;

    private Long com_uni_code;

    private Integer limit = 100;

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

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "FundCompanyHolderRequest{" +
                "name='" + name + '\'' +
                ", com_uni_code=" + com_uni_code +
                ", limit=" + limit +
                '}';
    }
}
