package la.niub.abcapi.servicecompre.model.response;

public class DataChartDetailResponse {

    private String image_id;

    private String image_url;

    private String image_title;

    private String id;

    private String title;

    private String type;

    private String summary;

    private Integer time;

    private String publish;

    private String industry_name;

    private String company;

    private String author;

    private String chart_type;

    private String chart_data;

    private String source_url;

    private String thumbnailLink;

    private String image_title_move;

    private Float confidence;

    private String file_url;

    private String owner_id;

    private String tags;

    private Float score;

    private String index_time;

    private Integer file_id;

    private String image_legends;

    private String owner_type;

    private String honor;

    public String getImage_id() {
        return image_id;
    }

    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getImage_title() {
        return image_title;
    }

    public void setImage_title(String image_title) {
        this.image_title = image_title;
    }

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public String getIndustry_name() {
        return industry_name;
    }

    public void setIndustry_name(String industry_name) {
        this.industry_name = industry_name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getChart_type() {
        return chart_type;
    }

    public void setChart_type(String chart_type) {
        this.chart_type = chart_type;
    }

    public String getChart_data() {
        return chart_data;
    }

    public void setChart_data(String chart_data) {
        this.chart_data = chart_data;
    }

    public String getSource_url() {
        return source_url;
    }

    public void setSource_url(String source_url) {
        this.source_url = source_url;
    }

    public String getThumbnailLink() {
        return thumbnailLink;
    }

    public void setThumbnailLink(String thumbnailLink) {
        this.thumbnailLink = thumbnailLink;
    }

    public String getImage_title_move() {
        return image_title_move;
    }

    public void setImage_title_move(String image_title_move) {
        this.image_title_move = image_title_move;
    }

    public Float getConfidence() {
        return confidence;
    }

    public void setConfidence(Float confidence) {
        this.confidence = confidence;
    }

    public String getFile_url() {
        return file_url;
    }

    public void setFile_url(String file_url) {
        this.file_url = file_url;
    }

    public String getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(String owner_id) {
        this.owner_id = owner_id;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public String getIndex_time() {
        return index_time;
    }

    public void setIndex_time(String index_time) {
        this.index_time = index_time;
    }

    public Integer getFile_id() {
        return file_id;
    }

    public void setFile_id(Integer file_id) {
        this.file_id = file_id;
    }

    public String getImage_legends() {
        return image_legends;
    }

    public void setImage_legends(String image_legends) {
        this.image_legends = image_legends;
    }

    public String getOwner_type() {
        return owner_type;
    }

    public void setOwner_type(String owner_type) {
        this.owner_type = owner_type;
    }

    public String getHonor() {
        return honor;
    }

    public void setHonor(String honor) {
        this.honor = honor;
    }

    @Override
    public String toString() {
        return "DataChartDetailResponse{" +
                "image_id='" + image_id + '\'' +
                ", image_url='" + image_url + '\'' +
                ", image_title='" + image_title + '\'' +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", summary='" + summary + '\'' +
                ", time=" + time +
                ", publish='" + publish + '\'' +
                ", industry_name='" + industry_name + '\'' +
                ", company='" + company + '\'' +
                ", author='" + author + '\'' +
                ", chart_type='" + chart_type + '\'' +
                ", chart_data='" + chart_data + '\'' +
                ", source_url='" + source_url + '\'' +
                ", thumbnailLink='" + thumbnailLink + '\'' +
                ", image_title_move='" + image_title_move + '\'' +
                ", confidence=" + confidence +
                ", file_url='" + file_url + '\'' +
                ", owner_id='" + owner_id + '\'' +
                ", tags='" + tags + '\'' +
                ", score=" + score +
                ", index_time='" + index_time + '\'' +
                ", file_id=" + file_id +
                ", image_legends='" + image_legends + '\'' +
                ", owner_type='" + owner_type + '\'' +
                ", honor='" + honor + '\'' +
                '}';
    }
}
