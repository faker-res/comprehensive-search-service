package la.niub.abcapi.servicecompre.model.response;

import java.util.List;

public class FinanceVipSimilarItemResponse {
    private String fvId;

    private String userName;

    private List<FinanceVipInfoNewsResponse> news;

    private String avatar;

    private Integer fansNumber;

    private List<String> tag;

    public Integer getFansNumber() {
        return fansNumber;
    }

    public void setFansNumber(Integer fansNumber) {
        this.fansNumber = fansNumber;
    }

    public List<String> getTag() {
        return tag;
    }

    public void setTag(List<String> tag) {
        this.tag = tag;
    }

    public String getFvId() {
        return fvId;
    }

    public void setFvId(String fvId) {
        this.fvId = fvId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<FinanceVipInfoNewsResponse> getNews() {
        return news;
    }

    public void setNews(List<FinanceVipInfoNewsResponse> news) {
        this.news = news;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
