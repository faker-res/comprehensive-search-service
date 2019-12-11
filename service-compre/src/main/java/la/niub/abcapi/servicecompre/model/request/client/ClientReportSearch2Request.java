package la.niub.abcapi.servicecompre.model.request.client;

import java.io.Serializable;

public class ClientReportSearch2Request implements Serializable {

    private String keyword = "ALL";

    private String source; // 所属机构

    private Integer start_time; // 开始时间

    private Integer end_time; // 结束时间

    private String rating; // 评级


    /**
     * 页数范围
     */
    private String page_area;

    /**
     * 订阅的关键字
     */
    private String subscribed_keyword;

    /**
     * 订阅的股票
     */
    private String subscribed_stock_list;

    /**
     * 订阅的行业
     */
    private String subscribed_industry_list;

    /**
     * 分类id
     */
    private String report_type;

    /**
     * 行业id
     */
    private String industry_name;

    /**
     * 最小文档页数
     */
    private Integer min_file_pages;

    /**
     * 最大文档页数
     */
    private Integer max_file_pages;

    /**
     * 文档类型
     */
    private String file_type;

    /**
     * 排序字段
     */
    private String order_by;

    /**
     * 排序方式 ?
     */
    private String order;

    /**
     * 标题包含
     */
    private String title_include;

    /**
     * 标题不包含
     */
    private String title_not_include;

    /**
     * content_include
     */
    private String content_include;

    /**
     * 标题或者正文包含
     */
    private String title_content_include = "";

    /**
     * 只包含获奖研报
     */
    private Boolean honored;

    /**
     * 第一大类
     */
    private String first_category;

    /**
     * 股票代码
     */
    private String stock_code;

    /**
     * 市场名称 ?
     */
    private String market;

    private String theme;  // 主题

    private String stockname; //公司

    private String author; // 分析师

    private String industry_txt; // 行业

    private String category_name; // 分类


    private String honor; // 获奖


    private String time_area; // 时间区域

    private Integer offset = 0;

    private Integer limit = 20;

    //日志
    private String user_id = "";

    private String request_id = "";

    private String input_from = "";

    private String page = "";

    private String device_info = "";

    public void setFilter(String filter_name,String filter_value){
        switch (filter_name){
            case "theme":
                this.theme = filter_value;break;
            case "stockname":
                this.stockname = filter_value;break;
            case "author":
                this.author = filter_value;break;
            case "industry_txt":
                this.industry_txt = filter_value;break;
            case "category_name":
                this.category_name = filter_value;break;
            case "source":
                this.source = filter_value;break;
            case "honor":
                this.honor = filter_value;break;
            case "rating":
                this.rating = filter_value;break;
            case "file_type":
                this.file_type = filter_value;break;
            case "page_area":
                this.page_area = filter_value;break;
            case "time_area":
                if(!filter_value.equals("自定义")){
                    this.time_area = filter_value;
                }
                break;
            case "title_include":
                this.title_include = filter_value;break;
            case "content_include":
                this.content_include = filter_value;break;
            case "title_content_include":
                this.title_content_include = filter_value;break;
            case "selfSelectedStock":
                this.subscribed_stock_list = filter_value;break;
        }
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getStockname() {
        return stockname;
    }

    public void setStockname(String stockname) {
        this.stockname = stockname;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIndustry_txt() {
        return industry_txt;
    }

    public void setIndustry_txt(String industry_txt) {
        this.industry_txt = industry_txt;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getHonor() {
        return honor;
    }

    public void setHonor(String honor) {
        this.honor = honor;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getTime_area() {
        return time_area;
    }

    public void setTime_area(String time_area) {
        this.time_area = time_area;
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

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getOrder_by() {
        return order_by;
    }

    public void setOrder_by(String order_by) {
        this.order_by = order_by;
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

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
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

    public String getDevice_info() {
        return device_info;
    }

    public void setDevice_info(String device_info) {
        this.device_info = device_info;
    }

    public String getSubscribed_keyword() {
        return subscribed_keyword;
    }

    public void setSubscribed_keyword(String subscribed_keyword) {
        this.subscribed_keyword = subscribed_keyword;
    }

    public String getSubscribed_stock_list() {
        return subscribed_stock_list;
    }

    public void setSubscribed_stock_list(String subscribed_stock_list) {
        this.subscribed_stock_list = subscribed_stock_list;
    }

    public String getSubscribed_industry_list() {
        return subscribed_industry_list;
    }

    public void setSubscribed_industry_list(String subscribed_industry_list) {
        this.subscribed_industry_list = subscribed_industry_list;
    }

    public String getReport_type() {
        return report_type;
    }

    public void setReport_type(String report_type) {
        this.report_type = report_type;
    }

    public String getIndustry_name() {
        return industry_name;
    }

    public void setIndustry_name(String industry_name) {
        this.industry_name = industry_name;
    }

    public Integer getMin_file_pages() {
        return min_file_pages;
    }

    public void setMin_file_pages(Integer min_file_pages) {
        this.min_file_pages = min_file_pages;
    }

    public Integer getMax_file_pages() {
        return max_file_pages;
    }

    public void setMax_file_pages(Integer max_file_pages) {
        this.max_file_pages = max_file_pages;
    }

    public String getFile_type() {
        return file_type;
    }

    public void setFile_type(String file_type) {
        this.file_type = file_type;
    }

    public String getTitle_include() {
        return title_include;
    }

    public void setTitle_include(String title_include) {
        this.title_include = title_include;
    }

    public String getTitle_not_include() {
        return title_not_include;
    }

    public void setTitle_not_include(String title_not_include) {
        this.title_not_include = title_not_include;
    }

    public String getContent_include() {
        return content_include;
    }

    public void setContent_include(String content_include) {
        this.content_include = content_include;
    }

    public Boolean getHonored() {
        return honored;
    }

    public void setHonored(Boolean honored) {
        this.honored = honored;
    }

    public String getFirst_category() {
        return first_category;
    }

    public void setFirst_category(String first_category) {
        this.first_category = first_category;
    }

    public String getStock_code() {
        return stock_code;
    }

    public void setStock_code(String stock_code) {
        this.stock_code = stock_code;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getPage_area() {
        return page_area;
    }

    public void setPage_area(String page_area) {
        this.page_area = page_area;
    }

    public String getTitle_content_include() {
        return title_content_include;
    }

    public void setTitle_content_include(String title_content_include) {
        this.title_content_include = title_content_include;
    }

    @Override
    public String toString() {
        return "ClientReportSearch2Request{" +
                "keyword='" + keyword + '\'' +
                ", theme='" + theme + '\'' +
                ", stockname='" + stockname + '\'' +
                ", author='" + author + '\'' +
                ", industry_txt='" + industry_txt + '\'' +
                ", category_name='" + category_name + '\'' +
                ", source='" + source + '\'' +
                ", honor='" + honor + '\'' +
                ", rating='" + rating + '\'' +
                ", time_area='" + time_area + '\'' +
                ", start_time=" + start_time +
                ", end_time=" + end_time +
                ", order='" + order + '\'' +
                ", order_by='" + order_by + '\'' +
                ", offset=" + offset +
                ", limit=" + limit +
                '}';
    }
}
