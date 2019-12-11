package la.niub.abcapi.servicecompre.model.request;

public class FundManagerFundInfoRequest {
    private Long peo_uni_code;
    private String year;
    private String order_field;
    private String order_type = "desc";

    public Long getPeo_uni_code() {
        return peo_uni_code;
    }

    public void setPeo_uni_code(Long peo_uni_code) {
        this.peo_uni_code = peo_uni_code;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getOrder_field() {
        return order_field;
    }

    public void setOrder_field(String order_field) {
        this.order_field = order_field;
    }

    public String getOrder_type() {
        return order_type;
    }

    public void setOrder_type(String order_type) {
        this.order_type = order_type;
    }
}
