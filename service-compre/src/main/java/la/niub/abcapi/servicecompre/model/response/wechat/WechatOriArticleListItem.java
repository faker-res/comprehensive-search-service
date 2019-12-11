package la.niub.abcapi.servicecompre.model.response.wechat;


public class WechatOriArticleListItem {
    private String id;

    private String title;

    private String url;

    private int time;

    private int read;


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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getRead() {
        return read;
    }

    public void setRead(int read) {
        this.read = read;
    }
}
