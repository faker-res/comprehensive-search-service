package la.niub.abcapi.servicecompre.model.request.client;

public class ClientDataChartRequest {

    private String keyword = "ALL";

    private Integer offset;

    private Integer limit;

    private String id;

    private String title_uninclude = "";

    private String title_include = ""; // 标题包含

    private String content_include = ""; // 正文包含

    private String title_content_include = ""; // 标题或者正文包含

    private String industry;

    private String author;

    private Integer start_time = 0;

    private Integer end_time = 0;

    private String publish = "";

    private String type;

    private String company = "";

    private String user_id = "";

    private String request_id = "";

    private String device_info = "";

    private String input_from = "";

    private String page = "";

    // 搜索2.0
    private String company_s; // 公司

    private String industry_s; // 行业

    private String chart_type_cn; // 图表类型

    private String language; // 语言

    private String publish_s; // 发布机构

    private String time_area; // 时间区域

    private String stockcode; // 股票代码

    public void setFilter(String filter_name,String filter_value){
        switch (filter_name){
            case "company_s":
                this.company_s = filter_value;
                break;
            case "industry_s":
                this.industry_s = filter_value;
                break;
            case "chart_type_cn":
                this.chart_type_cn = filter_value;
                break;
            case "language":
                this.language = filter_value;
                break;
            case "publish_s":
                this.publish_s = filter_value;
                break;
            case "title_include":
                this.title_include = filter_value;
                break;
            case "content_include":
                this.content_include = filter_value;
                break;
            case "title_content_include":
                this.title_content_include = filter_value;
                break;
            case "selfSelectedStock":
                this.stockcode = filter_value;
                break;
            case "time_area":
                if(!filter_value.equals("自定义")){
                    this.time_area = filter_value;
                }
                break;
        }
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getDevice_info() {
        return device_info;
    }

    public String setDeviceInfo(String device_info) {
        return this.device_info = device_info;
    }

    public String getRequest_id() {

        return request_id;

    }

    public String getInput_from() {
        return input_from;
    }

    public void setInput_from(String input_from) {
        this.input_from = input_from;
    };


    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Integer getStart_time() {
        return start_time;
    }

    public void setStart_time(Integer start_time) {
        this.start_time = start_time;
    }

    public Integer getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Integer end_time) {
        this.end_time = end_time;
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


    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setDevice_info(String device_info) {
        this.device_info = device_info;
    }

    public String getCompany_s() {
        return company_s;
    }

    public void setCompany_s(String company_s) {
        this.company_s = company_s;
    }

    public String getIndustry_s() {
        return industry_s;
    }

    public void setIndustry_s(String industry_s) {
        this.industry_s = industry_s;
    }

    public String getChart_type_cn() {
        return chart_type_cn;
    }

    public void setChart_type_cn(String chart_type_cn) {
        this.chart_type_cn = chart_type_cn;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPublish_s() {
        return publish_s;
    }

    public void setPublish_s(String publish_s) {
        this.publish_s = publish_s;
    }

    public String getContent_include() {
        return content_include;
    }

    public void setContent_include(String content_include) {
        this.content_include = content_include;
    }

    public String getTitle_content_include() {
        return title_content_include;
    }

    public void setTitle_content_include(String title_content_include) {
        this.title_content_include = title_content_include;
    }

    public String getTime_area() {
        return time_area;
    }

    public void setTime_area(String time_area) {
        this.time_area = time_area;
    }

    public String getStockcode() {
        return stockcode;
    }

    public void setStockcode(String stockcode) {
        this.stockcode = stockcode;
    }

    @Override
    public String toString() {
        return "ClientDataChartRequest{" +
                "keyword='" + keyword + '\'' +
                ", offset=" + offset +
                ", limit=" + limit +
                ", id='" + id + '\'' +
                ", title_uninclude='" + title_uninclude + '\'' +
                ", title_include='" + title_include + '\'' +
                ", content_include='" + content_include + '\'' +
                ", title_content_include='" + title_content_include + '\'' +
                ", industry='" + industry + '\'' +
                ", author='" + author + '\'' +
                ", start_time=" + start_time +
                ", end_time=" + end_time +
                ", publish='" + publish + '\'' +
                ", type='" + type + '\'' +
                ", company='" + company + '\'' +
                ", user_id='" + user_id + '\'' +
                ", request_id='" + request_id + '\'' +
                ", device_info='" + device_info + '\'' +
                ", input_from='" + input_from + '\'' +
                ", page='" + page + '\'' +
                ", company_s='" + company_s + '\'' +
                ", industry_s='" + industry_s + '\'' +
                ", chart_type_cn='" + chart_type_cn + '\'' +
                ", language='" + language + '\'' +
                ", publish_s='" + publish_s + '\'' +
                ", time_area='" + time_area + '\'' +
                ", stockcode='" + stockcode + '\'' +
                '}';
    }
}
