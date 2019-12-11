package la.niub.abcapi.servicecompre.model.response.message;

import java.util.List;

public class ArticleResponse {

    private List<ArticleListItemResponse> list;

    private int total;

    private int pageCount;

    public List<ArticleListItemResponse> getList() {
        return list;
    }

    public void setList(List<ArticleListItemResponse> list) {
        this.list = list;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
}
