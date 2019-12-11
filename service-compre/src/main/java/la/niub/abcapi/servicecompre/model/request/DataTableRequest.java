package la.niub.abcapi.servicecompre.model.request;

public class DataTableRequest extends Request{

    private String keyword = "";

    private String selected; // 选中

    private Integer limit = 20;

    private Integer offset = 0;

    private Boolean suggest = false;

    private String title_include = "";

    private String title_uninclude = "";

    private String industry = "";

    private String author = "";

    private String start_time = "";

    private String end_time = "";

    private String id = "";

    private String publish = "";

    private String type = "";

    private String company = "";

    private String userId = "";

    private String request_id = "";

    private String device_info = "";

    private String input_from = "";

    private String page = "";

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getInput_from()
    {
        return  input_from;
    }

    public String setInputFrom(String input_from) { return this.input_from = input_from;};

    public String setDeviceInfo(String device_info) {
        return this.device_info = device_info;
    }

    public String getDevice_info() {
        return device_info;
    }

    public String getUserId()
    {
        return userId;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) { this.request_id = request_id;}

    public void setUserId(String userId) { this.userId = userId;}


    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Boolean getSuggest() {
        return suggest;
    }

    public void setSuggest(Boolean suggest) {
        this.suggest = suggest;
    }

    public String getTitle_include() {
        return title_include;
    }

    public void setTitle_include(String title_include) {
        this.title_include = title_include;
    }

    public String getTitle_uninclude() {
        return title_uninclude;
    }

    public void setTitle_uninclude(String title_uninclude) {
        this.title_uninclude = title_uninclude;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setDevice_info(String device_info) {
        this.device_info = device_info;
    }

    public void setInput_from(String input_from) {
        this.input_from = input_from;
    }

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }

    @Override
    public String toString() {
        return "DataTableRequest{" +
                "keyword='" + keyword + '\'' +
                ", limit=" + limit +
                ", offset=" + offset +
                ", suggest=" + suggest +
                ", title_include='" + title_include + '\'' +
                ", title_uninclude='" + title_uninclude + '\'' +
                ", industry='" + industry + '\'' +
                ", author='" + author + '\'' +
                ", start_time='" + start_time + '\'' +
                ", end_time='" + end_time + '\'' +
                ", id='" + id + '\'' +
                ", publish='" + publish + '\'' +
                ", type='" + type + '\'' +
                ", company='" + company + '\'' +
                ", userId='" + userId + '\'' +
                ", request_id='" + request_id + '\'' +
                ", device_info='" + device_info + '\'' +
                ", input_from='" + input_from + '\'' +
                ", page='" + page + '\'' +
                '}';
    }
}
