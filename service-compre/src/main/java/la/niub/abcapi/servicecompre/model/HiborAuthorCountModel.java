package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;

public class HiborAuthorCountModel implements Serializable {
    private Integer id;

    private String publish;

    private String author;

    private Integer rid;

    private Integer r_year;

    private String p_au;

    private String peo_uni_code;

    private Integer r_time;

    private Integer report_num;

    private static final long serialVersionUID = 1L;

    public HiborAuthorCountModel(Integer id, String publish, String author, Integer rid, Integer r_year, String p_au, String peo_uni_code, Integer r_time) {
        this.id = id;
        this.publish = publish;
        this.author = author;
        this.rid = rid;
        this.r_year = r_year;
        this.p_au = p_au;
        this.peo_uni_code = peo_uni_code;
        this.r_time = r_time;
    }

    public HiborAuthorCountModel() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish == null ? null : publish.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Integer getR_year() {
        return r_year;
    }

    public void setR_year(Integer r_year) {
        this.r_year = r_year;
    }

    public String getP_au() {
        return p_au;
    }

    public void setP_au(String p_au) {
        this.p_au = p_au == null ? null : p_au.trim();
    }

    public String getPeo_uni_code() {
        return peo_uni_code;
    }

    public void setPeo_uni_code(String peo_uni_code) {
        this.peo_uni_code = peo_uni_code == null ? null : peo_uni_code.trim();
    }

    public Integer getR_time() {
        return r_time;
    }

    public void setR_time(Integer r_time) {
        this.r_time = r_time;
    }

    public Integer getReport_num() {
        return report_num;
    }

    public void setReport_num(Integer report_num) {
        this.report_num = report_num;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", publish=").append(publish);
        sb.append(", author=").append(author);
        sb.append(", rid=").append(rid);
        sb.append(", r_year=").append(r_year);
        sb.append(", p_au=").append(p_au);
        sb.append(", peo_uni_code=").append(peo_uni_code);
        sb.append(", r_time=").append(r_time);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}