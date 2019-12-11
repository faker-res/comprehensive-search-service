package la.niub.abcapi.servicecompre.model.response.client.notice;

import java.util.List;

public class NoticeSearch2Item {

    private String id;

    private String title;

    private Long publish_at;

    private List<String> industry;

    private List<String> category;

    private String stockcode;

    private String stockname;

    private String file_type;

    private Integer file_size;

    private Integer page_count;

    private String crawler_type;

    private String oss_path;

    private Long create_at;

    private List<String> data_id;

    private String content;

    private Integer toushi;

    private List<String> tag;

    private String src_id;

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

    public Long getPublish_at() {
        return publish_at;
    }

    public void setPublish_at(Long publish_at) {
        this.publish_at = publish_at;
    }

    public List<String> getIndustry() {
        return industry;
    }

    public void setIndustry(List<String> industry) {
        this.industry = industry;
    }

    public String getStockcode() {
        return stockcode;
    }

    public void setStockcode(String stockcode) {
        this.stockcode = stockcode;
    }

    public String getStockname() {
        return stockname;
    }

    public void setStockname(String stockname) {
        this.stockname = stockname;
    }

    public String getFile_type() {
        return file_type;
    }

    public void setFile_type(String file_type) {
        this.file_type = file_type;
    }

    public Integer getFile_size() {
        return file_size;
    }

    public void setFile_size(Integer file_size) {
        this.file_size = file_size;
    }

    public Integer getPage_count() {
        return page_count;
    }

    public void setPage_count(Integer page_count) {
        this.page_count = page_count;
    }

    public String getCrawler_type() {
        return crawler_type;
    }

    public void setCrawler_type(String crawler_type) {
        this.crawler_type = crawler_type;
    }

    public String getOss_path() {
        return oss_path;
    }

    public void setOss_path(String oss_path) {
        this.oss_path = oss_path;
    }

    public Long getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Long create_at) {
        this.create_at = create_at;
    }

    public List<String> getData_id() {
        return data_id;
    }

    public void setData_id(List<String> data_id) {
        this.data_id = data_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getToushi() {
        return toushi;
    }

    public void setToushi(Integer toushi) {
        this.toushi = toushi;
    }

    public List<String> getTag() {
        return tag;
    }

    public void setTag(List<String> tag) {
        this.tag = tag;
    }

    public String getSrc_id() {
        return src_id;
    }

    public void setSrc_id(String src_id) {
        this.src_id = src_id;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "NoticeSearch2Item{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", publish_at=" + publish_at +
                ", industry=" + industry +
                ", stockcode='" + stockcode + '\'' +
                ", stockname='" + stockname + '\'' +
                ", file_type='" + file_type + '\'' +
                ", file_size=" + file_size +
                ", page_count=" + page_count +
                ", crawler_type='" + crawler_type + '\'' +
                ", oss_path='" + oss_path + '\'' +
                ", create_at=" + create_at +
                ", data_id=" + data_id +
                ", content='" + content + '\'' +
                ", toushi=" + toushi +
                ", tag=" + tag +
                ", src_id='" + src_id + '\'' +
                '}';
    }
}
