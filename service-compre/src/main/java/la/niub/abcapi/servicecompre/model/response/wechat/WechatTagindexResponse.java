package la.niub.abcapi.servicecompre.model.response.wechat;

import la.niub.abcapi.servicecompre.model.bo.wechat.WechatTagindexArticleBO;
import la.niub.abcapi.servicecompre.model.bo.wechat.WechatTagindexIndexBO;

import java.util.List;

public class WechatTagindexResponse {

    private List<WechatTagindexIndexBO> index;

    private List<WechatTagindexArticleBO> article;

    public List<WechatTagindexIndexBO> getIndex() {
        return index;
    }

    public void setIndex(List<WechatTagindexIndexBO> index) {
        this.index = index;
    }

    public List<WechatTagindexArticleBO> getArticle() {
        return article;
    }

    public void setArticle(List<WechatTagindexArticleBO> article) {
        this.article = article;
    }

    @Override
    public String toString() {
        return "WechatTagindexResponse{" +
                "index=" + index +
                ", article=" + article +
                '}';
    }
}
