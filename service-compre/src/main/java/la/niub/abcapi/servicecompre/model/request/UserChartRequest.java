package la.niub.abcapi.servicecompre.model.request;

public class UserChartRequest extends Request{

    private String keyword = "";

    private Integer limit = 20;

    private Integer offset = 0;

    private String title_include = "";

    private String title_uninclude = "";

    private String industry = "";

    private String author = "";

    private String publish = "";

    private String start_time = "";

    private String end_time = "";

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

    @Override
    public Integer getLimit() {
        return limit;
    }

    @Override
    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    @Override
    public Integer getOffset() {
        return offset;
    }

    @Override
    public void setOffset(Integer offset) {
        this.offset = offset;
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

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
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

    @Override
    public String getLanguage() {
        return language;
    }

    @Override
    public void setLanguage(String language) {
        this.language = language;
    }
}
