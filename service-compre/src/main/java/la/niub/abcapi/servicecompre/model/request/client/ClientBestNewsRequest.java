package la.niub.abcapi.servicecompre.model.request.client;

public class ClientBestNewsRequest {

    private Integer pageNo;

    private Integer pageSize;

    private Integer channel_id;

    private Integer sub_channel_id;

    private Integer article_id;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(Integer channel_id) {
        this.channel_id = channel_id;
    }

    public Integer getSub_channel_id() {
        return sub_channel_id;
    }

    public void setSub_channel_id(Integer sub_channel_id) {
        this.sub_channel_id = sub_channel_id;
    }

    public Integer getArticle_id() {
        return article_id;
    }

    public void setArticle_id(Integer article_id) {
        this.article_id = article_id;
    }

    @Override
    public String toString() {
        return "ClientBestNewsRequest{" +
                "pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", channel_id=" + channel_id +
                ", sub_channel_id=" + sub_channel_id +
                ", article_id=" + article_id +
                '}';
    }
}
