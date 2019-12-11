package la.niub.abcapi.servicecompre.model.bo;

public class NoticeReadingBo {

    private String type = "";
    private String subscribed_keyword = ""; //选择某一个 关键字分类
    private String subscribed_group = "";  // 选择某一个个股分类
//    private group_type = ""; //

    private String only_subscribed_stock = "";
    private String only_subscribed_keyword = "";
    private String all_subscribed = "";
    private int  ifUnderstand = 1; // 是否分词
    private int sub_id = 0; // 订阅分钟中某一个具体的id

    private String industry_name = "";
    private String market = "";
    private String categories= ""; // 逗号分割的 category id

    private String order_by = "";
    private String group_by = ""; // 分组
    private String start_time = "";
    private String end_time = "";

    private String stock_filter = "";
    private String keyword_filter = "ALL";
    private String stock_code = ""; // 选择的股票
    private int delimiter = 1; // 1 \n 2 。

    private int offset = 0;
    private int limit = 10;
    private String company_search;
    private String title_include;
    private String title_not_include;
    private String content_include;
    private String selected_stock_code = "";
    private String selected_industry_name= "";
    private int selected_offset = 0;
    private String stockname = "";
    private int selected_limit = 3;
    private String count_only = "";//只返回结果数
    private String first_category; // 第一级分类
    private int perspective = 0; // 有无透视
    private boolean isUserSearch = false; //是否是用户数据

    private boolean isNavigation = false;//搜索时是否导航优先
    private String  tag = ""; // tag标签

    private String user_id = "";//user_id
    private String filter = ""; // 使用高级语法


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubscribed_keyword() {
        return subscribed_keyword;
    }

    public void setSubscribed_keyword(String subscribed_keyword) {
        this.subscribed_keyword = subscribed_keyword;
    }

    public String getSubscribed_group() {
        return subscribed_group;
    }

    public void setSubscribed_group(String subscribed_group) {
        this.subscribed_group = subscribed_group;
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

    public String getAll_subscribed() {
        return all_subscribed;
    }

    public void setAll_subscribed(String all_subscribed) {
        this.all_subscribed = all_subscribed;
    }

    public int getIfUnderstand() {
        return ifUnderstand;
    }

    public void setIfUnderstand(int ifUnderstand) {
        this.ifUnderstand = ifUnderstand;
    }

    public int getSub_id() {
        return sub_id;
    }

    public void setSub_id(int sub_id) {
        this.sub_id = sub_id;
    }

    public String getIndustry_name() {
        return industry_name;
    }

    public void setIndustry_name(String industry_name) {
        this.industry_name = industry_name;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
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

    public int getDelimiter() {
        return delimiter;
    }

    public void setDelimiter(int delimiter) {
        this.delimiter = delimiter;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getCompany_search() {
        return company_search;
    }

    public void setCompany_search(String company_search) {
        this.company_search = company_search;
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

    public int getSelected_offset() {
        return selected_offset;
    }

    public void setSelected_offset(int selected_offset) {
        this.selected_offset = selected_offset;
    }

    public String getStockname() {
        return stockname;
    }

    public void setStockname(String stockname) {
        this.stockname = stockname;
    }

    public int getSelected_limit() {
        return selected_limit;
    }

    public void setSelected_limit(int selected_limit) {
        this.selected_limit = selected_limit;
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

    public int getPerspective() {
        return perspective;
    }

    public void setPerspective(int perspective) {
        this.perspective = perspective;
    }

    public boolean isUserSearch() {
        return isUserSearch;
    }

    public void setUserSearch(boolean userSearch) {
        isUserSearch = userSearch;
    }

    public boolean isNavigation() {
        return isNavigation;
    }

    public void setNavigation(boolean navigation) {
        isNavigation = navigation;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }
}
