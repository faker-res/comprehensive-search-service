package la.niub.abcapi.servicecompre.model;

public class ChartConfigIMAGEModel {

    private String img_Title;

    private String img_Url;

    private String img_Source;

    private String source_Url;

    private String img_Category;

    private String recommendation;

    private String isTop;

    private String img_Width;

    private String img_Height;

    private String update_Time;

    private String id;

    public String getImg_Title() { return img_Title; }

    public void setImg_Title(String img_Title) { this.img_Title=img_Title; }

    public String getImg_Url() { return img_Url; }

    public void setImg_Url(String img_Url) { this.img_Url=img_Url; }

    public String getImg_Source() { return img_Source; }

    public void setImg_Source(String img_Source) { this.img_Source=img_Source; }

    public String getSource_Url() { return source_Url; }

    public void setSource_Url(String source_Url) { this.source_Url=source_Url; }

    public String getImg_Category() { return img_Category; }

    public void setImg_Category(String img_Category) { this.img_Category=img_Category; }

    public String getRecommendation() { return recommendation; }

    public void setRecommendation(String recommendation) { this.recommendation=recommendation; }

    public String getIsTop() { return isTop; }

    public void setIsTop(String isTop) { this.isTop=isTop; }

    public String getImg_Width() { return img_Width; }

    public void setImg_Width(String img_Width) { this.img_Width=img_Width; }

    public String getImg_Height() { return img_Height; }

    public void setImg_Height(String img_Height) { this.img_Height=img_Height; }

    public String getUpdate_Time() { return update_Time; }

    public void setUpdate_Time(String update_Time) { this.update_Time=update_Time; }

    public String getId() { return id; }

    public void setId(String id) { this.id=id; }

    @Override
    public String toString() {
        return "ChartConfigIMAGEModel{" +
                "id=" + id + '\'' +
                "img_Title=" + img_Title + '\'' +
                "img_Url=" + img_Url + '\'' +
                "img_Source=" + img_Source + '\'' +
                "source_Url=" + source_Url + '\'' +
                "img_Category=" + img_Category +
                '}';
    }
}
