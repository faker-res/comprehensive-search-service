package la.niub.abcapi.servicecompre.model.request.client;

import java.io.Serializable;

public class ClientNoticeSearch2Request implements Serializable {

    private String keyword = "ALL";

    private String category; // 分类

    private String market; // 市场

    private String company;  // 公司

    private String industry;  // 行业

    private String categoryIds;  // 公司ID

    private String industryIds;  // 行业ID

    private String self_selected_stock;  // 自选股

    private Integer toushi;  // 是否透视

    private String sort = "score";  // 排序

    private String time_area;

    private Integer offset = 0;

    private Integer limit = 20;

    private Integer start_time;

    private Integer end_time;

    private String ids; // 多个id参数

    private String file_type; // 文件格式

    private String page_count; // 文件页数

    // 匹配标题正文等
    private String title_include = ""; // 标题包含

    private String content_include = ""; // 正文包含

    private String title_content_include = ""; // 标题或者正文包含

    //日志
    private String user_id = "";

    private String request_id = "";

    private String input_from = "";

    private String page = "";

    private String device_info = "";

    public void setFilter(String filter_name,String filter_value){
        switch (filter_name){
            case "market":
                this.market = filter_value;break;
            case "category":
                this.category = filter_value;break;
            case "company":
                this.company = filter_value;break;
            case "industry":
                this.industry = filter_value;break;
            case "file_type":
                this.file_type = filter_value;break;
            case "page_count":
                this.page_count = filter_value;break;
            case "time_area":
                if(!filter_value.equals("自定义")){
                    this.time_area = filter_value;
                }
                break;
            case "toushi":
                this.toushi = 1 ;break;
            case "categoryIds":
                this.categoryIds = filter_value;break;
            case "industryIds":
                this.industryIds = filter_value;break;
            case "title_include":
                this.title_include = filter_value;break;
            case "content_include":
                this.content_include = filter_value;break;
            case "title_content_include":
                this.title_content_include = filter_value;break;
            case "selfSelectedStock" :
                this.self_selected_stock = filter_value;break;
        }
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getSelf_selected_stock() {
        return self_selected_stock;
    }

    public void setSelf_selected_stock(String self_selected_stock) {
        this.self_selected_stock = self_selected_stock;
    }

    public Integer getToushi() {
        return toushi;
    }

    public void setToushi(Integer toushi) {
        this.toushi = toushi;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
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

    public String getTime_area() {
        return time_area;
    }

    public void setTime_area(String time_area) {
        this.time_area = time_area;
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

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String getFile_type() {
        return file_type;
    }

    public void setFile_type(String file_type) {
        this.file_type = file_type;
    }

    public String getPage_count() {
        return page_count;
    }

    public void setPage_count(String page_count) {
        this.page_count = page_count;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }


    public String getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(String categoryIds) {
        this.categoryIds = categoryIds;
    }

    public String getIndustryIds() {
        return industryIds;
    }

    public void setIndustryIds(String industryIds) {
        this.industryIds = industryIds;
    }

    public String getTitle_include() {
        return title_include;
    }

    public void setTitle_include(String title_include) {
        this.title_include = title_include;
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

    @Override
    public String toString() {
        return "ClientNoticeSearch2Request{" +
                "keyword='" + keyword + '\'' +
                ", category='" + category + '\'' +
                ", market='" + market + '\'' +
                ", company='" + company + '\'' +
                ", industry='" + industry + '\'' +
                ", categoryIds='" + categoryIds + '\'' +
                ", industryIds='" + industryIds + '\'' +
                ", self_selected_stock='" + self_selected_stock + '\'' +
                ", toushi=" + toushi +
                ", sort='" + sort + '\'' +
                ", time_area='" + time_area + '\'' +
                ", offset=" + offset +
                ", limit=" + limit +
                ", start_time=" + start_time +
                ", end_time=" + end_time +
                ", ids='" + ids + '\'' +
                ", file_type='" + file_type + '\'' +
                ", page_count='" + page_count + '\'' +
                ", title_include='" + title_include + '\'' +
                ", content_include='" + content_include + '\'' +
                ", title_content_include='" + title_content_include + '\'' +
                ", user_id='" + user_id + '\'' +
                ", request_id='" + request_id + '\'' +
                ", input_from='" + input_from + '\'' +
                ", page='" + page + '\'' +
                ", device_info='" + device_info + '\'' +
                '}';
    }
}
