package la.niub.abcapi.servicecompre.model.bo;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReportItem implements Serializable {

    private String industry1;

    private String summary;

    private String summary_url;

    private String stockname;

    private List<String> industry_id;

    private List<String> author;

    private Integer full_match;

    private List<String> honor;

    private String rating;

    private String report_type;

    private String source;

    private String title;

    private Integer file_size;

    private String url;

    private Integer summarynum;

    private Integer score;

    private String file_type;

    private Integer id;

    private List<String> tag;

    private Integer time;

    private Integer create_at;

    private Integer file_pages;

    private List<String> category;

    private String uploaduser;

    private String stockcode;

    private String code;

    private String name;

    private String filetype;

    private List<ReportItem> data;

    private Integer match_num;

    private Integer count;

    private List<String> analyst_honor;

    private String is_new;

    public List<String> getAnalyst_honor() {
        return analyst_honor;
    }

    public void setAnalyst_honor(List<String> analyst_honor) {
        this.analyst_honor = analyst_honor;
    }

    public String getIs_new() {
        return is_new;
    }

    public void setIs_new(String is_new) {
        this.is_new = is_new;
    }

    public String getIndustry1() {
        return industry1;
    }

    public void setIndustry1(String industry1) {
        this.industry1 = industry1;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSummary_url() {
        return summary_url;
    }

    public void setSummary_url(String summary_url) {
        this.summary_url = summary_url;
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

    public List<String> getAuthor() {
        return author;
    }

    public void setAuthor(List<String> author) {
        this.author = author;
    }

    public Integer getFull_match() {
        return full_match;
    }

    public void setFull_match(Integer full_match) {
        this.full_match = full_match;
    }

    public List<String> getHonor() {
        return honor;
    }

    public void setHonor(List<String> honor) {
        this.honor = honor;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getFile_size() {
        return file_size;
    }

    public void setFile_size(Integer file_size) {
        this.file_size = file_size;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public String getFile_type() {
        return file_type;
    }

    public void setFile_type(String file_type) {
        this.file_type = file_type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<String> getTag() {
        return tag;
    }

    public void setTag(List<String> tag) {
        this.tag = tag;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Integer create_at) {
        this.create_at = create_at;
    }

    public Integer getFile_pages() {
        return file_pages;
    }

    public void setFile_pages(Integer file_pages) {
        this.file_pages = file_pages;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public String getUploaduser() {
        return uploaduser;
    }

    public void setUploaduser(String uploaduser) {
        this.uploaduser = uploaduser;
    }

    public String getStockcode() {
        return stockcode;
    }

    public void setStockcode(String stockcode) {
        this.stockcode = stockcode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ReportItem> getData() {
        return data;
    }

    public void setData(List<ReportItem> data) {
        this.data = data;
    }

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }

    public Integer getMatch_num() {
        return match_num;
    }

    public void setMatch_num(Integer match_num) {
        this.match_num = match_num;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "ReportItem{" +
                "industry1='" + industry1 + '\'' +
                ", summary='" + summary + '\'' +
                ", stockname='" + stockname + '\'' +
                ", industry_id=" + industry_id +
                ", author=" + author +
                ", full_match=" + full_match +
                ", honor=" + honor +
                ", rating='" + rating + '\'' +
                ", report_type='" + report_type + '\'' +
                ", source='" + source + '\'' +
                ", title='" + title + '\'' +
                ", file_size=" + file_size +
                ", url='" + url + '\'' +
                ", summarynum=" + summarynum +
                ", score=" + score +
                ", file_type='" + file_type + '\'' +
                ", id=" + id +
                ", tag=" + tag +
                ", time=" + time +
                ", create_at=" + create_at +
                ", file_pages=" + file_pages +
                ", category=" + category +
                ", uploaduser='" + uploaduser + '\'' +
                ", stockcode='" + stockcode + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", filetype='" + filetype + '\'' +
                ", data=" + data +
                ", match_num=" + match_num +
                ", count=" + count +
                '}';
    }
}
