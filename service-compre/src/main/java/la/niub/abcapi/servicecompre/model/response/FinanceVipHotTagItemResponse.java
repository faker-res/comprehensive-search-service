package la.niub.abcapi.servicecompre.model.response;

import java.util.List;

public class FinanceVipHotTagItemResponse {
    private String fvId;

    private String fvName;

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

    public String getFvName() {
        return fvName;
    }

    public void setFvName(String fvName) {
        this.fvName = fvName;
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
