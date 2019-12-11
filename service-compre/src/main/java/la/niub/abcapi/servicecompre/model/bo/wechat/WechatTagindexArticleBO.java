package la.niub.abcapi.servicecompre.model.bo.wechat;

public class WechatTagindexArticleBO {

    private int time;

    private String title;

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "WechatTagindexArticleBO{" +
                "time=" + time +
                ", title='" + title + '\'' +
                '}';
    }
}
