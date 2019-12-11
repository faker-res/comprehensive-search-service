package la.niub.abcapi.servicecompre.model.request;

public class FundCompanyAchievementsInfoRequest {
    private Long com_uni_code;
    private String plate_code;
    private Integer page_num = 1;
    private Integer limit = 9;
    private String order_field;
    private String order_type;

    public Long getCom_uni_code() {
        return com_uni_code;
    }

    public void setCom_uni_code(Long com_uni_code) {
        this.com_uni_code = com_uni_code;
    }

    public String getPlate_code() {
        return plate_code;
    }

    public void setPlate_code(String plate_code) {
        this.plate_code = plate_code;
    }

    public Integer getPage_num() {
        return page_num;
    }

    public void setPage_num(Integer page_num) {
        this.page_num = page_num;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
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
