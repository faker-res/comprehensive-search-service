package la.niub.abcapi.servicecompre.model.request;

public class SearchGeneralRequest {

    private String keyword = "人工智能";

    private String channel = "pc_web";

    private String version;

    private String language;

    private int limit_chart = 12;

    private int limit_table = 12;

    private int limit_report = 5;

    private int limit_notice = 5;

    private int limit_news = 10;

    private int limit_edb = 6;

    private int limit_stocks = 10;

    private String userId = "";

    private String token = "";

    private String request_id = "";

    private String device_info = "";

    private String input_from = "";

    private String page = "";

    private int offset_news = 0;

    private String type = "";

    private String code = "";

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public int getOffset_news() {
        return offset_news;
    }

    public void setOffset_news(int offset_news) {
        this.offset_news = offset_news;
    }

    public String getRequest_id() {
        return request_id;
    }

    public String getKeyword() {
        return keyword;
    }

    public String getUserId()
    {
        return userId;
    }


    public void setRequest_id(String request_id) { this.request_id = request_id;}

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void setUserId(String userId) { this.userId = userId;}

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getLimit_chart() {
        return limit_chart;
    }

    public void setLimit_chart(int limit_chart) {
        this.limit_chart = limit_chart;
    }

    public int getLimit_table() {
        return limit_table;
    }

    public void setLimit_table(int limit_table) {
        this.limit_table = limit_table;
    }

    public int getLimit_report() {
        return limit_report;
    }

    public void setLimit_report(int limit_report) {
        this.limit_report = limit_report;
    }

    public int getLimit_notice() {
        return limit_notice;
    }

    public void setLimit_notice(int limit_notice) {
        this.limit_notice = limit_notice;
    }

    public int getLimit_news() {
        return limit_news;
    }

    public void setLimit_news(int limit_news) {
        this.limit_news = limit_news;
    }

    public int getLimit_edb() {
        return limit_edb;
    }

    public void setLimit_edb(int limit_edb) {
        this.limit_edb = limit_edb;
    }

    public int getLimit_stocks() {
        return limit_stocks;
    }

    public void setLimit_stocks(int limit_stocks) {
        this.limit_stocks = limit_stocks;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
