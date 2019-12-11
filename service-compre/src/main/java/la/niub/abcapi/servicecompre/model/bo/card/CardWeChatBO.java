package la.niub.abcapi.servicecompre.model.bo.card;

import java.util.List;

public class CardWeChatBO {
    private int id;

    private String name;

    private String account;

    private String image;

    private String summary;

    private String authentication;

    private String tags;

    private String qrCode;

    private int monthCount;

    private int source;

    private List<CardWeChatArticleBO> ArtilceList;

    private String classify;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getAuthentication() {
        return authentication;
    }

    public void setAuthentication(String authentication) {
        this.authentication = authentication;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public int getMonthCount() {
        return monthCount;
    }

    public void setMonthCount(int monthCount) {
        this.monthCount = monthCount;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public List<CardWeChatArticleBO> getArtilceList() {
        return ArtilceList;
    }

    public void setArtilceList(List<CardWeChatArticleBO> artilceList) {
        ArtilceList = artilceList;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }
}
