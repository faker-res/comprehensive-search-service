package la.niub.abcapi.servicecompre.model.response.client.notice;

import java.util.List;

public class NoticeItem {

    private String first_indu;

    private String summary;

    private String stockname;

    private List<String> industry_id;

    private String src_id;

    private Integer full_match;

    private String industry;

    private String title;

    private Integer publish_at;

    private Integer file_size;

    private String oss_path;

    private Integer summarynum;

    private Integer score;

    private List<String> data_id;

    private String file_type;

    private String crawler_type;

    private List<String> tag;

    private List<String> category;

    private Integer create_at;

    private Integer page_count;

    private String stockcode;

    public String getFirst_indu() {
        return first_indu;
    }

    public void setFirst_indu(String first_indu) {
        this.first_indu = first_indu;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getStockname() {
        return stockname;
    }

    public void setStockname(String stockname) {
        this.stockname = stockname;
    }

    public List<String> getIndustry_id() {
        return industry_id;
    }

    public void setIndustry_id(List<String> industry_id) {
        this.industry_id = industry_id;
    }

    public String getSrc_id() {
        return src_id;
    }

    public void setSrc_id(String src_id) {
        this.src_id = src_id;
    }

    public Integer getFull_match() {
        return full_match;
    }

    public void setFull_match(Integer full_match) {
        this.full_match = full_match;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPublish_at() {
        return publish_at;
    }

    public void setPublish_at(Integer publish_at) {
        this.publish_at = publish_at;
    }

    public Integer getFile_size() {
        return file_size;
    }

    public void setFile_size(Integer file_size) {
        this.file_size = file_size;
    }

    public String getOss_path() {
        return oss_path;
    }

    public void setOss_path(String oss_path) {
        this.oss_path = oss_path;
    }

    public Integer getSummarynum() {
        return summarynum;
    }

    public void setSummarynum(Integer summarynum) {
        this.summarynum = summarynum;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public List<String> getData_id() {
        return data_id;
    }

    public void setData_id(List<String> data_id) {
        this.data_id = data_id;
    }

    public String getFile_type() {
        return file_type;
    }

    public void setFile_type(String file_type) {
        this.file_type = file_type;
    }

    public String getCrawler_type() {
        return crawler_type;
    }

    public void setCrawler_type(String crawler_type) {
        this.crawler_type = crawler_type;
    }

    public List<String> getTag() {
        return tag;
    }

    public void setTag(List<String> tag) {
        this.tag = tag;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public Integer getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Integer create_at) {
        this.create_at = create_at;
    }

    public Integer getPage_count() {
        return page_count;
    }

    public void setPage_count(Integer page_count) {
        this.page_count = page_count;
    }

    public String getStockcode() {
        return stockcode;
    }

    public void setStockcode(String stockcode) {
        this.stockcode = stockcode;
    }

    @Override
    public String toString() {
        return "NoticeItem{" +
                "first_indu='" + first_indu + '\'' +
                ", summary='" + summary + '\'' +
                ", stockname='" + stockname + '\'' +
                ", industry_id=" + industry_id +
                ", src_id='" + src_id + '\'' +
                ", full_match=" + full_match +
                ", industry='" + industry + '\'' +
                ", title='" + title + '\'' +
                ", publish_at=" + publish_at +
                ", file_size=" + file_size +
                ", oss_path='" + oss_path + '\'' +
                ", summarynum=" + summarynum +
                ", score=" + score +
                ", data_id=" + data_id +
                ", file_type='" + file_type + '\'' +
                ", crawler_type='" + crawler_type + '\'' +
                ", tag=" + tag +
                ", category=" + category +
                ", create_at=" + create_at +
                ", page_count=" + page_count +
                ", stockcode='" + stockcode + '\'' +
                '}';
    }
}
