package la.niub.abcapi.servicecompre.model.request.client;

public class ClientUserCenterChartRequest {

    private String keyword = "";

    private Integer offset = 0;

    private Integer limit = 20;

    private String title_include = "";

    private String title_uninclude = "";

    private String industry = "";

    private String author = "";

    private Integer start_time = 0;

    private Integer end_time = 0;

    private String publish = "";

    private String honor = "";

    private String owner_id = "";

    private String source_type = "";

    private String source_name = "";

    private String chart_type = "";

    private String language = "";

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

    public String getHonor() {
        return honor;
    }

    public void setHonor(String honor) {
        this.honor = honor;
    }

    public String getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(String owner_id) {
        this.owner_id = owner_id;
    }

    public String getSource_type() {
        return source_type;
    }

    public void setSource_type(String source_type) {
        this.source_type = source_type;
    }

    public String getSource_name() {
        return source_name;
    }

    public void setSource_name(String source_name) {
        this.source_name = source_name;
    }

    public String getChart_type() {
        return chart_type;
    }

    public void setChart_type(String chart_type) {
        this.chart_type = chart_type;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "ClientUserCenterChartRequest{" +
                "keyword='" + keyword + '\'' +
                ", offset=" + offset +
                ", limit=" + limit +
                ", title_include='" + title_include + '\'' +
                ", title_uninclude='" + title_uninclude + '\'' +
                ", industry='" + industry + '\'' +
                ", author='" + author + '\'' +
                ", start_time=" + start_time +
                ", end_time=" + end_time +
                ", publish='" + publish + '\'' +
                ", honor='" + honor + '\'' +
                ", owner_id='" + owner_id + '\'' +
                ", source_type='" + source_type + '\'' +
                ", source_name='" + source_name + '\'' +
                ", chart_type='" + chart_type + '\'' +
                ", language='" + language + '\'' +
                '}';
    }

    public String getJson() {
        String jsonString = "{";
        jsonString += "keyword='" + keyword+"'";
        jsonString += offset == 0 ? "" : ", offset=" + offset;
        jsonString += limit == 0 ? "" : ", limit=" + limit;
        jsonString += start_time == 0 ? "" : ", start_time=" + start_time;
        jsonString += end_time == 0 ? "" : ", end_time=" + end_time;
        jsonString += title_include.isEmpty() ? "" : ", title_include='" + title_include+"'";
        jsonString += title_uninclude.isEmpty() ? "" : ", title_uninclude='" + title_uninclude+"'";
        jsonString += industry.isEmpty() ? "" : ", industry='" + industry+"'";
        jsonString += author.isEmpty() ? "" : ", author='" + author+"'";
        jsonString += publish.isEmpty() ? "" : ", publish='" + publish+"'";
        jsonString += honor.isEmpty() ? "" : ", honor='" + honor+"'";
        jsonString += owner_id.isEmpty() ? "" : ", owner_id='" + owner_id+"'";
        jsonString += source_type.isEmpty() ? "" : ", source_type='" + source_type+"'";
        jsonString += source_name.isEmpty() ? "" : ", source_name='" + source_name+"'";
        jsonString += chart_type.isEmpty() ? "" : ", chart_type='" + chart_type+"'";
        jsonString += language.isEmpty() ? "" : ", language='" + language+"'";
        jsonString += "}";
        return jsonString ;
    }
}
