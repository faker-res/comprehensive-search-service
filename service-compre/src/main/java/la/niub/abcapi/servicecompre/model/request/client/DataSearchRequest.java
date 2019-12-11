package la.niub.abcapi.servicecompre.model.request.client;

public class DataSearchRequest{

    private String keyword;

    private Integer offset = 0;

    private Integer limit = 10;

    // EDB创投整合
    private String data_type;

    private String type;

    //EDB
    private String ctype;

    private String csource;

    private String cfrequency;

    private String mul_address;

    //日志
    private String userId = "";

    private String request_id = "";

    private String device_info = "";

    private String input_from = "";

    private String page = "";

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getData_type() {
        return data_type;
    }

    public void setData_type(String data_type) {
        this.data_type = data_type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCtype() {
        return ctype;
    }

    public void setCtype(String ctype) {
        this.ctype = ctype;
    }

    public String getCsource() {
        return csource;
    }

    public void setCsource(String csource) {
        this.csource = csource;
    }

    public String getCfrequency() {
        return cfrequency;
    }

    public void setCfrequency(String cfrequency) {
        this.cfrequency = cfrequency;
    }

    public String getMul_address() {
        return mul_address;
    }

    public void setMul_address(String mul_address) {
        this.mul_address = mul_address;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public String getDevice_info() {
        return device_info;
    }

    public void setDevice_info(String device_info) {
        this.device_info = device_info;
    }

    public String getInput_from() {
        return input_from;
    }

    public void setInput_from(String input_from) {
        this.input_from = input_from;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }
}
