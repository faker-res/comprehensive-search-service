package la.niub.abcapi.servicecompre.model.response;

public class DataTableDetailResponse {

    private String table_id;

    private String table_url;

    private String table_title;

    private Integer table_ori;

    private String id;

    private String title;

    private String type;

    private String summary;

    private Integer time;

    private Integer table_rowCount;

    private Integer table_columnCount;

    private String full_match;

    private Float score;

    private String table_source;

    private String table_data;

    private String formula;

    private String hash;

    private String dupl;

    private String dupl_count;

    private String impfullscore;

    private String fullscore;

    private String termscore;

    private String company;

    private String publish;

    private String industry_name;

    private String type_id;

    private String src_id;

    public String getTable_id() {
        return table_id;
    }

    public void setTable_id(String table_id) {
        this.table_id = table_id;
    }

    public String getTable_url() {
        return table_url;
    }

    public void setTable_url(String table_url) {
        this.table_url = table_url;
    }

    public String getTable_title() {
        return table_title;
    }

    public void setTable_title(String table_title) {
        this.table_title = table_title;
    }

    public Integer getTable_ori() {
        return table_ori;
    }

    public void setTable_ori(Integer table_ori) {
        this.table_ori = table_ori;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getTable_rowCount() {
        return table_rowCount;
    }

    public void setTable_rowCount(Integer table_rowCount) {
        this.table_rowCount = table_rowCount;
    }

    public Integer getTable_columnCount() {
        return table_columnCount;
    }

    public void setTable_columnCount(Integer table_columnCount) {
        this.table_columnCount = table_columnCount;
    }

    public String getFull_match() {
        return full_match;
    }

    public void setFull_match(String full_match) {
        this.full_match = full_match;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public String getTable_source() {
        return table_source;
    }

    public void setTable_source(String table_source) {
        this.table_source = table_source;
    }

    public String getTable_data() {
        return table_data;
    }

    public void setTable_data(String table_data) {
        this.table_data = table_data;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getDupl() {
        return dupl;
    }

    public void setDupl(String dupl) {
        this.dupl = dupl;
    }

    public String getDupl_count() {
        return dupl_count;
    }

    public void setDupl_count(String dupl_count) {
        this.dupl_count = dupl_count;
    }

    public String getImpfullscore() {
        return impfullscore;
    }

    public void setImpfullscore(String impfullscore) {
        this.impfullscore = impfullscore;
    }

    public String getFullscore() {
        return fullscore;
    }

    public void setFullscore(String fullscore) {
        this.fullscore = fullscore;
    }

    public String getTermscore() {
        return termscore;
    }

    public void setTermscore(String termscore) {
        this.termscore = termscore;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public String getIndustry_name() {
        return industry_name;
    }

    public void setIndustry_name(String industry_name) {
        this.industry_name = industry_name;
    }

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

    public String getSrc_id() {
        return src_id;
    }

    public void setSrc_id(String src_id) {
        this.src_id = src_id;
    }

    @Override
    public String toString() {
        return "DataTableDetailResponse{" +
                "table_id='" + table_id + '\'' +
                ", table_url='" + table_url + '\'' +
                ", table_title='" + table_title + '\'' +
                ", table_ori=" + table_ori +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", summary='" + summary + '\'' +
                ", time=" + time +
                ", table_rowCount=" + table_rowCount +
                ", table_columnCount=" + table_columnCount +
                ", full_match='" + full_match + '\'' +
                ", score=" + score +
                ", table_source='" + table_source + '\'' +
                ", table_data='" + table_data + '\'' +
                ", formula='" + formula + '\'' +
                ", hash='" + hash + '\'' +
                ", dupl='" + dupl + '\'' +
                ", dupl_count='" + dupl_count + '\'' +
                ", impfullscore='" + impfullscore + '\'' +
                ", fullscore='" + fullscore + '\'' +
                ", termscore='" + termscore + '\'' +
                ", company='" + company + '\'' +
                ", publish='" + publish + '\'' +
                ", industry_name='" + industry_name + '\'' +
                ", type_id='" + type_id + '\'' +
                ", src_id='" + src_id + '\'' +
                '}';
    }
}
