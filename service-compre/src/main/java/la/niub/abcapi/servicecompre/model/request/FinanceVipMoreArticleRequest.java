package la.niub.abcapi.servicecompre.model.request;

public class FinanceVipMoreArticleRequest {
    private String fvIds;
    private String tags;
    private String title_keyword;
    private String order_field = "time";
    private String order_type = "desc";
    private Integer page_num = 1;
    private Integer limit = 15;
    private String sourceName;

    public FinanceVipMoreArticleRequest() {
        super();
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getFvIds() {
        return fvIds;
    }

    public void setFvIds(String fvIds) {
        this.fvIds = fvIds;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getTitle_keyword() {
        return title_keyword;
    }

    public void setTitle_keyword(String title_keyword) {
        this.title_keyword = title_keyword;
    }

    public String getOrder_field() {
        return order_field;
    }

    public void setOrder_field(String order_field) {
        this.order_field = order_field;
    }

    public String getOrder_type() {
        return order_type;
    }

    public void setOrder_type(String order_type) {
        this.order_type = order_type;
    }

    public Integer getPage_num() {
        return page_num;
    }

    public void setPage_num(Integer page_num) {
        this.page_num = page_num;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "FinanceVipMoreArticleRequest{" +
                "fvIds='" + fvIds + '\'' +
                ", tags='" + tags + '\'' +
                ", title_keyword='" + title_keyword + '\'' +
                ", order_field='" + order_field + '\'' +
                ", order_type='" + order_type + '\'' +
                ", page_num=" + page_num +
                ", limit=" + limit +
                ", sourceName='" + sourceName + '\'' +
                '}';
    }
}
