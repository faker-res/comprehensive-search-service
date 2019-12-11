package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class HiborModel implements Serializable {
    private Long added_id;

    private Long id;

    private String uploaduser;

    private String rating;

    private String typetitle;

    private String stockname;

    private String sharecontent;

    private String filetype;

    private Long file_size;

    private String shareurl;

    private String sharetitle;

    private Long publish_power;

    private String author;

    private String publish;

    private Long file_pages;

    private String stockcode;

    private String shareimg;

    private String title;

    private String file_url;

    private Date time;

    private String industryname;

    private String file_path;

    private Date create_at;

    private Date update_at;

    private String source;

    private Boolean push_flag;

    private Integer chart_version;

    private Integer table_version;

    private Integer content_version;

    private String content_related_version;

    private Byte status;

    private String analyst;

    private String category_id;

    private String industry_id;

    private String tag;

    private String honor;

    private List<String> honors;

    private static final long serialVersionUID = 1L;

    public HiborModel(Long added_id, Long id, String uploaduser, String rating, String typetitle, String stockname, String sharecontent, String filetype, Long file_size, String shareurl, String sharetitle, Long publish_power, String author, String publish, Long file_pages, String stockcode, String shareimg, String title, String file_url, Date time, String industryname, String file_path, Date create_at, Date update_at, String source, Boolean push_flag, Integer chart_version, Integer table_version, Integer content_version, String content_related_version, Byte status, String analyst, String category_id, String industry_id, String tag, String honor) {
        this.added_id = added_id;
        this.id = id;
        this.uploaduser = uploaduser;
        this.rating = rating;
        this.typetitle = typetitle;
        this.stockname = stockname;
        this.sharecontent = sharecontent;
        this.filetype = filetype;
        this.file_size = file_size;
        this.shareurl = shareurl;
        this.sharetitle = sharetitle;
        this.publish_power = publish_power;
        this.author = author;
        this.publish = publish;
        this.file_pages = file_pages;
        this.stockcode = stockcode;
        this.shareimg = shareimg;
        this.title = title;
        this.file_url = file_url;
        this.time = time;
        this.industryname = industryname;
        this.file_path = file_path;
        this.create_at = create_at;
        this.update_at = update_at;
        this.source = source;
        this.push_flag = push_flag;
        this.chart_version = chart_version;
        this.table_version = table_version;
        this.content_version = content_version;
        this.content_related_version = content_related_version;
        this.status = status;
        this.analyst = analyst;
        this.category_id = category_id;
        this.industry_id = industry_id;
        this.tag = tag;
        this.honor = honor;
    }

    public HiborModel() {
        super();
    }

    public Long getAdded_id() {
        return added_id;
    }

    public void setAdded_id(Long added_id) {
        this.added_id = added_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUploaduser() {
        return uploaduser;
    }

    public void setUploaduser(String uploaduser) {
        this.uploaduser = uploaduser == null ? null : uploaduser.trim();
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating == null ? null : rating.trim();
    }

    public String getTypetitle() {
        return typetitle;
    }

    public void setTypetitle(String typetitle) {
        this.typetitle = typetitle == null ? null : typetitle.trim();
    }

    public String getStockname() {
        return stockname;
    }

    public void setStockname(String stockname) {
        this.stockname = stockname == null ? null : stockname.trim();
    }

    public String getSharecontent() {
        return sharecontent;
    }

    public void setSharecontent(String sharecontent) {
        this.sharecontent = sharecontent == null ? null : sharecontent.trim();
    }

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype == null ? null : filetype.trim();
    }

    public Long getFile_size() {
        return file_size;
    }

    public void setFile_size(Long file_size) {
        this.file_size = file_size;
    }

    public String getShareurl() {
        return shareurl;
    }

    public void setShareurl(String shareurl) {
        this.shareurl = shareurl == null ? null : shareurl.trim();
    }

    public String getSharetitle() {
        return sharetitle;
    }

    public void setSharetitle(String sharetitle) {
        this.sharetitle = sharetitle == null ? null : sharetitle.trim();
    }

    public Long getPublish_power() {
        return publish_power;
    }

    public void setPublish_power(Long publish_power) {
        this.publish_power = publish_power;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish == null ? null : publish.trim();
    }

    public Long getFile_pages() {
        return file_pages;
    }

    public void setFile_pages(Long file_pages) {
        this.file_pages = file_pages;
    }

    public String getStockcode() {
        return stockcode;
    }

    public void setStockcode(String stockcode) {
        this.stockcode = stockcode == null ? null : stockcode.trim();
    }

    public String getShareimg() {
        return shareimg;
    }

    public void setShareimg(String shareimg) {
        this.shareimg = shareimg == null ? null : shareimg.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getFile_url() {
        return file_url;
    }

    public void setFile_url(String file_url) {
        this.file_url = file_url == null ? null : file_url.trim();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getIndustryname() {
        return industryname;
    }

    public void setIndustryname(String industryname) {
        this.industryname = industryname == null ? null : industryname.trim();
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path == null ? null : file_path.trim();
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    public Date getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(Date update_at) {
        this.update_at = update_at;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public Boolean getPush_flag() {
        return push_flag;
    }

    public void setPush_flag(Boolean push_flag) {
        this.push_flag = push_flag;
    }

    public Integer getChart_version() {
        return chart_version;
    }

    public void setChart_version(Integer chart_version) {
        this.chart_version = chart_version;
    }

    public Integer getTable_version() {
        return table_version;
    }

    public void setTable_version(Integer table_version) {
        this.table_version = table_version;
    }

    public Integer getContent_version() {
        return content_version;
    }

    public void setContent_version(Integer content_version) {
        this.content_version = content_version;
    }

    public String getContent_related_version() {
        return content_related_version;
    }

    public void setContent_related_version(String content_related_version) {
        this.content_related_version = content_related_version == null ? null : content_related_version.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getAnalyst() {
        return analyst;
    }

    public void setAnalyst(String analyst) {
        this.analyst = analyst == null ? null : analyst.trim();
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id == null ? null : category_id.trim();
    }

    public String getIndustry_id() {
        return industry_id;
    }

    public void setIndustry_id(String industry_id) {
        this.industry_id = industry_id == null ? null : industry_id.trim();
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }

    public String getHonor() {
        return honor;
    }

    public void setHonor(String honor) {
        this.honor = honor == null ? null : honor.trim();
    }

    public List<String> getHonors() {
        return honors;
    }

    public void setHonors(List<String> honors) {
        this.honors = honors;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", added_id=").append(added_id);
        sb.append(", id=").append(id);
        sb.append(", uploaduser=").append(uploaduser);
        sb.append(", rating=").append(rating);
        sb.append(", typetitle=").append(typetitle);
        sb.append(", stockname=").append(stockname);
        sb.append(", sharecontent=").append(sharecontent);
        sb.append(", filetype=").append(filetype);
        sb.append(", file_size=").append(file_size);
        sb.append(", shareurl=").append(shareurl);
        sb.append(", sharetitle=").append(sharetitle);
        sb.append(", publish_power=").append(publish_power);
        sb.append(", author=").append(author);
        sb.append(", publish=").append(publish);
        sb.append(", file_pages=").append(file_pages);
        sb.append(", stockcode=").append(stockcode);
        sb.append(", shareimg=").append(shareimg);
        sb.append(", title=").append(title);
        sb.append(", file_url=").append(file_url);
        sb.append(", time=").append(time);
        sb.append(", industryname=").append(industryname);
        sb.append(", file_path=").append(file_path);
        sb.append(", create_at=").append(create_at);
        sb.append(", update_at=").append(update_at);
        sb.append(", source=").append(source);
        sb.append(", push_flag=").append(push_flag);
        sb.append(", chart_version=").append(chart_version);
        sb.append(", table_version=").append(table_version);
        sb.append(", content_version=").append(content_version);
        sb.append(", content_related_version=").append(content_related_version);
        sb.append(", status=").append(status);
        sb.append(", analyst=").append(analyst);
        sb.append(", category_id=").append(category_id);
        sb.append(", industry_id=").append(industry_id);
        sb.append(", tag=").append(tag);
        sb.append(", honor=").append(honor);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}