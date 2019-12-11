package la.niub.abcapi.servicecompre.model.request.client;

public class WechatArticleRequest {
    private boolean single;

    private String core_name = "core_news";

    private String source_name;

    private int limit = 5;

    private int offset = 0;


    public String getSource_name() {
        return source_name;
    }

    public void setSource_name(String source_name) {
        this.source_name = source_name;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
}
