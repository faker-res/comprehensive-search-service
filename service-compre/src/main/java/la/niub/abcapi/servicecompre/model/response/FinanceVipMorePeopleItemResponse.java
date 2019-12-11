package la.niub.abcapi.servicecompre.model.response;

import java.util.List;

public class FinanceVipMorePeopleItemResponse {
    private String userId;

    private String userName;

    private String avatar;

    private List<String> tag;

    private Integer reading;

    private Integer follower;

    private FinanceVipInfoNewsResponse news;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public List<String> getTag() {
        return tag;
    }

    public void setTag(List<String> tag) {
        this.tag = tag;
    }

    public Integer getReading() {
        return reading;
    }

    public void setReading(Integer reading) {
        this.reading = reading;
    }

    public Integer getFollower() {
        return follower;
    }

    public void setFollower(Integer follower) {
        this.follower = follower;
    }

    public FinanceVipInfoNewsResponse getNews() {
        return news;
    }

    public void setNews(FinanceVipInfoNewsResponse news) {
        this.news = news;
    }
}
