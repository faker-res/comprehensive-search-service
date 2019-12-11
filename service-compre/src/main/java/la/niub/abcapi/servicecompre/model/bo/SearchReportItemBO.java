package la.niub.abcapi.servicecompre.model.bo;

import com.fasterxml.jackson.annotation.JsonInclude;
import la.niub.abcapi.servicecompre.model.response.client.report.ReportAanlystHonorData;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SearchReportItemBO {

    private String industry1;

    private String summary;

    private String stockname;

    private String industry_id;

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

    private Integer match_num;

    private String file_type;

    private Integer id;

    private List<String> tag;

    private Integer time;

    private Integer create_at;

    private Integer file_pages;

    private String category;

    private String uploaduser;

    private String stockcode;

    private String code;

    private String name;

    private Integer count;

    private String new_count_today;

    private Integer publish_at;

    private List<String> paragraph;

    private String category_id;

    private String industry;

    private String share_author;

    private String checkStock;

    private List<SearchReportItemBO> data;

    private List<ReportAanlystHonorData> analyst_honor;

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

    public String getStockname() {
        return stockname;
    }

    public void setStockname(String stockname) {
        this.stockname = stockname;
    }

    public String getIndustry_id() {
        return industry_id;
    }

    public void setIndustry_id(String industry_id) {
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

    public Integer getMatch_num() {
        return match_num;
    }

    public void setMatch_num(Integer match_num) {
        this.match_num = match_num;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getNew_count_today() {
        return new_count_today;
    }

    public void setNew_count_today(String new_count_today) {
        this.new_count_today = new_count_today;
    }

    public Integer getPublish_at() {
        return publish_at;
    }

    public void setPublish_at(Integer publish_at) {
        this.publish_at = publish_at;
    }

    public List<String> getParagraph() {
        return paragraph;
    }

    public void setParagraph(List<String> paragraph) {
        this.paragraph = paragraph;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getShare_author() {
        return share_author;
    }

    public void setShare_author(String share_author) {
        this.share_author = share_author;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public List<SearchReportItemBO> getData() {
        return data;
    }

    public void setData(List<SearchReportItemBO> data) {
        this.data = data;
    }

    public String getCheckStock() {
        return checkStock;
    }

    public void setCheckStock(String checkStock) {
        this.checkStock = checkStock;
    }

    public List<ReportAanlystHonorData> getAnalyst_honor() {
        return analyst_honor;
    }

    public void setAnalyst_honor(List<ReportAanlystHonorData> analyst_honor) {
        this.analyst_honor = analyst_honor;
    }

    @Override
    public String toString() {
        return "SearchReportItemBO{" +
                "industry1='" + industry1 + '\'' +
                ", summary='" + summary + '\'' +
                ", stockname='" + stockname + '\'' +
                ", industry_id='" + industry_id + '\'' +
                ", author='" + author + '\'' +
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
                ", id='" + id + '\'' +
                ", tag=" + tag +
                ", time=" + time +
                ", create_at=" + create_at +
                ", file_pages=" + file_pages +
                ", category='" + category + '\'' +
                ", uploaduser='" + uploaduser + '\'' +
                ", stockcode='" + stockcode + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", new_count_today='" + new_count_today + '\'' +
                ", publish_at=" + publish_at +
                ", paragraph=" + paragraph +
                ", category_id='" + category_id + '\'' +
                ", share_author='" + share_author + '\'' +
                ", industry='" + industry + '\'' +
                ", checkStock='" + checkStock + '\'' +
                ", data=" + data +
                '}';
    }
}
