package la.niub.abcapi.servicecompre.model.request;

public class ReportRequest extends Request {

    private Boolean all_subscribed;//是否选择全部订阅

    private String subscribed_group;// 选择自选股分类（自选股分类名称）

    private String subscribed_keyword;//选择某一个关键字分类（关键字分类名称）

    private Integer sub_id = 0;// 自选股子分类或关键字子分类选择中某一个具体项目（具体项目的id）

    private String only_subscribed_stock;//选中我的自选股

    private String only_subscribed_keyword;//选中关键字筛选条件

    private Boolean ifUnderstand = true;// 是否分词

    private String report_type;//分类

    private String source;//来源

    private String industry_name;//行业

    private String rating;//评级

    private Integer min_file_pages = 0;

    private Integer max_file_pages = 0;

    private String start_time;

    private String end_time;

    private String order_by;//排序

    private String group_by;//分组

    private Integer offset = 0;

    private Integer limit = 20;

    private String author = "";//根据作者搜索

    private Boolean honored = false;//根据分析师是否获奖

    private String stockname = "";//根据股票名称查询

    private String stock_filter = "";//根据股票名查询时的股票代码

    private String keyword_filter = "";//查询框选择的股票名称

    private String stock_code = "";// 查询框选择的股票代码

    private String selected_stock_code = "";

    private String selected_industry_name = "";//按行业归类时查询的行业名

    private Integer selected_offset = 0;//归类时的分页起始行

    private Integer selected_limit = 3;//归类时的分页大小

    private String categories;//兼容老版本

    private String exception_ids;// 搜索中排除的id

    private Integer subject_id = 0;

    private String title_include;// 标题包含

    private String title_not_include;// 标题不包含

    private String content_include;// 正文包含

    private String conecpt;// 概念

    private String company_search;

    private String count_only;//只返回结果数

    private String first_category;// 第一级分类

    private String lang;// 语言 en 英文

    private Boolean isNavigation = false;//是否导航优先进行搜索

    private String industry_txt;

    public Boolean getAll_subscribed() {
        return all_subscribed;
    }

    public void setAll_subscribed(Boolean all_subscribed) {
        this.all_subscribed = all_subscribed;
    }

    public String getSubscribed_group() {
        return subscribed_group;
    }

    public void setSubscribed_group(String subscribed_group) {
        this.subscribed_group = subscribed_group;
    }

    public String getSubscribed_keyword() {
        return subscribed_keyword;
    }

    public void setSubscribed_keyword(String subscribed_keyword) {
        this.subscribed_keyword = subscribed_keyword;
    }

    public Integer getSub_id() {
        return sub_id;
    }

    public void setSub_id(Integer sub_id) {
        this.sub_id = sub_id;
    }

    public String getOnly_subscribed_stock() {
        return only_subscribed_stock;
    }

    public void setOnly_subscribed_stock(String only_subscribed_stock) {
        this.only_subscribed_stock = only_subscribed_stock;
    }

    public String getOnly_subscribed_keyword() {
        return only_subscribed_keyword;
    }

    public void setOnly_subscribed_keyword(String only_subscribed_keyword) {
        this.only_subscribed_keyword = only_subscribed_keyword;
    }

    public Boolean getIfUnderstand() {
        return ifUnderstand;
    }

    public void setIfUnderstand(Boolean ifUnderstand) {
        this.ifUnderstand = ifUnderstand;
    }

    public String getReport_type() {
        return report_type;
    }

    public void setReport_type(String report_type) {
        this.report_type = report_type;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getIndustry_name() {
        return industry_name;
    }

    public void setIndustry_name(String industry_name) {
        this.industry_name = industry_name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
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

    public String getOrder_by() {
        return order_by;
    }

    public void setOrder_by(String order_by) {
        this.order_by = order_by;
    }

    public String getGroup_by() {
        return group_by;
    }

    public void setGroup_by(String group_by) {
        this.group_by = group_by;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Boolean getHonored() {
        return honored;
    }

    public void setHonored(Boolean honored) {
        this.honored = honored;
    }

    public String getStockname() {
        return stockname;
    }

    public void setStockname(String stockname) {
        this.stockname = stockname;
    }

    public String getStock_filter() {
        return stock_filter;
    }

    public void setStock_filter(String stock_filter) {
        this.stock_filter = stock_filter;
    }

    public String getKeyword_filter() {
        return keyword_filter;
    }

    public void setKeyword_filter(String keyword_filter) {
        this.keyword_filter = keyword_filter;
    }

    public String getStock_code() {
        return stock_code;
    }

    public void setStock_code(String stock_code) {
        this.stock_code = stock_code;
    }

    public String getSelected_stock_code() {
        return selected_stock_code;
    }

    public void setSelected_stock_code(String selected_stock_code) {
        this.selected_stock_code = selected_stock_code;
    }

    public String getSelected_industry_name() {
        return selected_industry_name;
    }

    public void setSelected_industry_name(String selected_industry_name) {
        this.selected_industry_name = selected_industry_name;
    }

    public Integer getSelected_offset() {
        return selected_offset;
    }

    public void setSelected_offset(Integer selected_offset) {
        this.selected_offset = selected_offset;
    }

    public Integer getSelected_limit() {
        return selected_limit;
    }

    public void setSelected_limit(Integer selected_limit) {
        this.selected_limit = selected_limit;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getException_ids() {
        return exception_ids;
    }

    public void setException_ids(String exception_ids) {
        this.exception_ids = exception_ids;
    }

    public Integer getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(Integer subject_id) {
        this.subject_id = subject_id;
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

    public String getConecpt() {
        return conecpt;
    }

    public void setConecpt(String conecpt) {
        this.conecpt = conecpt;
    }

    public String getCompany_search() {
        return company_search;
    }

    public void setCompany_search(String company_search) {
        this.company_search = company_search;
    }

    public String getCount_only() {
        return count_only;
    }

    public void setCount_only(String count_only) {
        this.count_only = count_only;
    }

    public String getFirst_category() {
        return first_category;
    }

    public void setFirst_category(String first_category) {
        this.first_category = first_category;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public Boolean getIsNavigation() {
        return isNavigation;
    }

    public void setIsNavigation(Boolean isNavigation) {
        this.isNavigation = isNavigation;
    }

    public String getIndustry_txt() {
        return industry_txt;
    }

    public void setIndustry_txt(String industry_txt) {
        this.industry_txt = industry_txt;
    }

    @Override
    public String toString() {
        return "ReportRequest{" +
                ", all_subscribed=" + all_subscribed +
                ", subscribed_group='" + subscribed_group + '\'' +
                ", subscribed_keyword='" + subscribed_keyword + '\'' +
                ", sub_id=" + sub_id +
                ", only_subscribed_stock='" + only_subscribed_stock + '\'' +
                ", only_subscribed_keyword='" + only_subscribed_keyword + '\'' +
                ", ifUnderstand=" + ifUnderstand +
                ", report_type='" + report_type + '\'' +
                ", source='" + source + '\'' +
                ", industry_name='" + industry_name + '\'' +
                ", rating='" + rating + '\'' +
                ", min_file_pages=" + min_file_pages +
                ", max_file_pages=" + max_file_pages +
                ", start_time='" + start_time + '\'' +
                ", end_time='" + end_time + '\'' +
                ", order_by='" + order_by + '\'' +
                ", group_by='" + group_by + '\'' +
                ", offset=" + offset +
                ", limit=" + limit +
                ", author='" + author + '\'' +
                ", honored=" + honored +
                ", stockname='" + stockname + '\'' +
                ", stock_filter='" + stock_filter + '\'' +
                ", keyword_filter='" + keyword_filter + '\'' +
                ", stock_code='" + stock_code + '\'' +
                ", selected_stock_code='" + selected_stock_code + '\'' +
                ", selected_industry_name='" + selected_industry_name + '\'' +
                ", selected_offset=" + selected_offset +
                ", selected_limit=" + selected_limit +
                ", categories='" + categories + '\'' +
                ", exception_ids='" + exception_ids + '\'' +
                ", subject_id=" + subject_id +
                ", title_include='" + title_include + '\'' +
                ", title_not_include='" + title_not_include + '\'' +
                ", content_include='" + content_include + '\'' +
                ", conecpt='" + conecpt + '\'' +
                ", company_search='" + company_search + '\'' +
                ", count_only='" + count_only + '\'' +
                ", first_category='" + first_category + '\'' +
                ", lang='" + lang + '\'' +
                ", isNavigation=" + isNavigation +
                ", industry_txt='" + industry_txt + '\'' +
                '}';
    }
}
