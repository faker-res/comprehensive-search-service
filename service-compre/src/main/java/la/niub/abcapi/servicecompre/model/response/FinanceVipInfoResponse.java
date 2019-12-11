package la.niub.abcapi.servicecompre.model.response;

import java.util.List;

public class FinanceVipInfoResponse {
    private String userName;

    private String foreignName;

    private String nationality;

    private String nation;

    private String birthplace;

    private String birthdate;

    private String occupation;

    private String graduateSchool;

    private String nativePlace;

    private String achievement;

    private String avatar;

    private List<FinanceVipInfoNewsResponse> news;

    private List<FinanceVipInfoWechatPublicItemResponse> wechatPublic;

    private List<FinanceVipInfoWeiboItemResponse> weibo;

    private Integer readingNumber;

    private Integer fansNumber;

    private List<String> tag;

    public Integer getReadingNumber() {
        return readingNumber;
    }

    public void setReadingNumber(Integer readingNumber) {
        this.readingNumber = readingNumber;
    }

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getForeignName() {
        return foreignName;
    }

    public void setForeignName(String foreignName) {
        this.foreignName = foreignName;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getGraduateSchool() {
        return graduateSchool;
    }

    public void setGraduateSchool(String graduateSchool) {
        this.graduateSchool = graduateSchool;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public String getAchievement() {
        return achievement;
    }

    public void setAchievement(String achievement) {
        this.achievement = achievement;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public List<FinanceVipInfoNewsResponse> getNews() {
        return news;
    }

    public void setNews(List<FinanceVipInfoNewsResponse> news) {
        this.news = news;
    }

    public List<FinanceVipInfoWechatPublicItemResponse> getWechatPublic() {
        return wechatPublic;
    }

    public void setWechatPublic(List<FinanceVipInfoWechatPublicItemResponse> wechatPublic) {
        this.wechatPublic = wechatPublic;
    }

    public List<FinanceVipInfoWeiboItemResponse> getWeibo() {
        return weibo;
    }

    public void setWeibo(List<FinanceVipInfoWeiboItemResponse> weibo) {
        this.weibo = weibo;
    }


}
