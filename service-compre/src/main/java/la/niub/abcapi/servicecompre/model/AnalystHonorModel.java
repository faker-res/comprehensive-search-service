package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;
import java.util.Date;

public class AnalystHonorModel implements Serializable {
    private Long id;

    private String peo_uni_code;

    private String sac;

    private String honor;

    private Date time;

    private String direction;

    private Integer ranking;

    private String organ;

    private String analyst;

    private String session;

    private Date createtime;

    private Date updatetime;

    private String status;

    private String remark;

    private String creator;

    private String editor;

    private String come_source;

    private Integer honor_total;

    private static final long serialVersionUID = 1L;

    public AnalystHonorModel(Long id, String peo_uni_code, String sac, String honor, Date time, String direction, Integer ranking, String organ, String analyst, String session, Date createtime, Date updatetime, String status, String remark, String creator, String editor, String come_source) {
        this.id = id;
        this.peo_uni_code = peo_uni_code;
        this.sac = sac;
        this.honor = honor;
        this.time = time;
        this.direction = direction;
        this.ranking = ranking;
        this.organ = organ;
        this.analyst = analyst;
        this.session = session;
        this.createtime = createtime;
        this.updatetime = updatetime;
        this.status = status;
        this.remark = remark;
        this.creator = creator;
        this.editor = editor;
        this.come_source = come_source;
    }

    public AnalystHonorModel() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPeo_uni_code() {
        return peo_uni_code;
    }

    public void setPeo_uni_code(String peo_uni_code) {
        this.peo_uni_code = peo_uni_code == null ? null : peo_uni_code.trim();
    }

    public String getSac() {
        return sac;
    }

    public void setSac(String sac) {
        this.sac = sac == null ? null : sac.trim();
    }

    public String getHonor() {
        return honor;
    }

    public void setHonor(String honor) {
        this.honor = honor == null ? null : honor.trim();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction == null ? null : direction.trim();
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public String getOrgan() {
        return organ;
    }

    public void setOrgan(String organ) {
        this.organ = organ == null ? null : organ.trim();
    }

    public String getAnalyst() {
        return analyst;
    }

    public void setAnalyst(String analyst) {
        this.analyst = analyst == null ? null : analyst.trim();
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session == null ? null : session.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor == null ? null : editor.trim();
    }

    public String getCome_source() {
        return come_source;
    }

    public void setCome_source(String come_source) {
        this.come_source = come_source == null ? null : come_source.trim();
    }
    public Integer getHonor_total() {
        return honor_total;
    }

    public void setHonor_total(Integer honor_total) {
        this.honor_total = honor_total;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", peo_uni_code=").append(peo_uni_code);
        sb.append(", sac=").append(sac);
        sb.append(", honor=").append(honor);
        sb.append(", time=").append(time);
        sb.append(", direction=").append(direction);
        sb.append(", ranking=").append(ranking);
        sb.append(", organ=").append(organ);
        sb.append(", analyst=").append(analyst);
        sb.append(", session=").append(session);
        sb.append(", createtime=").append(createtime);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append(", creator=").append(creator);
        sb.append(", editor=").append(editor);
        sb.append(", come_source=").append(come_source);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}