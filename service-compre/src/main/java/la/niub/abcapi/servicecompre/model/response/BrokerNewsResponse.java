package la.niub.abcapi.servicecompre.model.response;

import java.util.List;

public class BrokerNewsResponse {

    private List<BrokerNewsItemResponse> newest;

    private List<BrokerNewsItemResponse> news;

    private List<BrokerNewsItemResponse> notice;

    private List<BrokerNewsItemResponse> weibo;

    private List<BrokerNewsItemResponse> wechatPublic;

    public List<BrokerNewsItemResponse> getNewest() {
        return newest;
    }

    public void setNewest(List<BrokerNewsItemResponse> newest) {
        this.newest = newest;
    }

    public List<BrokerNewsItemResponse> getNews() {
        return news;
    }

    public void setNews(List<BrokerNewsItemResponse> news) {
        this.news = news;
    }

    public List<BrokerNewsItemResponse> getNotice() {
        return notice;
    }

    public void setNotice(List<BrokerNewsItemResponse> notice) {
        this.notice = notice;
    }

    public List<BrokerNewsItemResponse> getWeibo() {
        return weibo;
    }

    public void setWeibo(List<BrokerNewsItemResponse> weibo) {
        this.weibo = weibo;
    }

    public List<BrokerNewsItemResponse> getWechatPublic() {
        return wechatPublic;
    }

    public void setWechatPublic(List<BrokerNewsItemResponse> wechatPublic) {
        this.wechatPublic = wechatPublic;
    }
}
