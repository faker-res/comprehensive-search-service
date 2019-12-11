package la.niub.abcapi.servicecompre.model.bo.analyst;

public class AnalystSummaryBO {

    private String peo_uni_code;

    private String name;

    private String image;

    //所在公司名称
    private String organ;

    //主研方向
    private String direction;

    //平均达成天数
    private Integer average_days;

    //头部百分比
    private Double head_percent;

    //最近一篇文章
    private String recent_title;

    private String recent_type;

    private String recent_url;

    private String recent_id;

    public String getPeo_uni_code() {
        return peo_uni_code;
    }

    public void setPeo_uni_code(String peo_uni_code) {
        this.peo_uni_code = peo_uni_code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getOrgan() {
        return organ;
    }

    public void setOrgan(String organ) {
        this.organ = organ;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Integer getAverage_days() {
        return average_days;
    }

    public void setAverage_days(Integer average_days) {
        this.average_days = average_days;
    }

    public Double getHead_percent() {
        return head_percent;
    }

    public void setHead_percent(Double head_percent) {
        this.head_percent = head_percent;
    }

    public String getRecent_title() {
        return recent_title;
    }

    public void setRecent_title(String recent_title) {
        this.recent_title = recent_title;
    }

    public String getRecent_type() {
        return recent_type;
    }

    public void setRecent_type(String recent_type) {
        this.recent_type = recent_type;
    }

    public String getRecent_url() {
        return recent_url;
    }

    public void setRecent_url(String recent_url) {
        this.recent_url = recent_url;
    }

    public String getRecent_id() {
        return recent_id;
    }

    public void setRecent_id(String recent_id) {
        this.recent_id = recent_id;
    }

    @Override
    public String toString() {
        return "AnalystSummaryBO{" +
                "peo_uni_code='" + peo_uni_code + '\'' +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", organ='" + organ + '\'' +
                ", direction='" + direction + '\'' +
                ", average_days=" + average_days +
                ", head_percent=" + head_percent +
                ", recent_title='" + recent_title + '\'' +
                ", recent_type='" + recent_type + '\'' +
                ", recent_url='" + recent_url + '\'' +
                ", recent_id='" + recent_id + '\'' +
                '}';
    }
}
