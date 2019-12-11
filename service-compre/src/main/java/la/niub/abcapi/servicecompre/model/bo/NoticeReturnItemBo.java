package la.niub.abcapi.servicecompre.model.bo;

import java.util.List;


public class NoticeReturnItemBo {
    private String summary;

    private List<String> paragraph;

    private String url;

    private String title;

    private String file_type;

    private String stockcode;

    private String stockname;

    private String src_id;

    private String industry_id;

    private String industry;

    private String category_id;

    private String category;

    private Integer perspective;

    private int file_size;

    private int publish_at;

    private int page_count;

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public List<String> getParagraph() {
        return paragraph;
    }

    public void setParagraph(List<String> paragraph) {
        this.paragraph = paragraph;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFile_type() {
        return file_type;
    }

    public void setFile_type(String file_type) {
        this.file_type = file_type;
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

    public String getSrc_id() {
        return src_id;
    }

    public void setSrc_id(String src_id) {
        this.src_id = src_id;
    }

    public String getIndustry_id() {
        return industry_id;
    }

    public void setIndustry_id(String industry_id) {
        this.industry_id = industry_id;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getFile_size() {
        return file_size;
    }

    public void setFile_size(int file_size) {
        this.file_size = file_size;
    }

    public int getPublish_at() {
        return publish_at;
    }

    public void setPublish_at(int publish_at) {
        this.publish_at = publish_at;
    }

    public int getPage_count() {
        return page_count;
    }

    public void setPage_count(int page_count) {
        this.page_count = page_count;
    }

    public Integer getPerspective() {
        return perspective;
    }

    public void setPerspective(Integer perspective) {
        this.perspective = perspective;
    }
}
