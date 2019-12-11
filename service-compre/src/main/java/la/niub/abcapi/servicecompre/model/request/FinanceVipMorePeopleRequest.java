package la.niub.abcapi.servicecompre.model.request;


public class FinanceVipMorePeopleRequest {

    private String tags;
    private String name;
    private String name_py_prefix;
    private String order_field;
    private String order_type;
    private Integer page_num = 1;
    private Integer limit = 10;

    public FinanceVipMorePeopleRequest() {
        super();
    }

    public FinanceVipMorePeopleRequest(String tags, String name, String name_py_prefix, String order_field, String order_type, Integer page_num, Integer limit) {
        this.tags = tags;
        this.name = name;
        this.name_py_prefix = name_py_prefix;
        this.order_field = order_field;
        this.order_type = order_type;
        this.page_num = page_num;
        this.limit = limit;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName_py_prefix() {
        return name_py_prefix;
    }

    public void setName_py_prefix(String name_py_prefix) {
        this.name_py_prefix = name_py_prefix;
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
}
