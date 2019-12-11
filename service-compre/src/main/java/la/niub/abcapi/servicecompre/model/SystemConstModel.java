package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;
import java.util.Date;

public class SystemConstModel implements Serializable {
    private Long id;

    private Long system_uni_code;

    private String system_name;

    private Long fat_code;

    private String fat_name;

    private String system_disc;

    private Date createtime;

    private Date updatetime;

    private String status;

    private String remark;

    private String creator;

    private String editor;

    private String come_source;

    private static final long serialVersionUID = 1L;

    public SystemConstModel(Long id, Long system_uni_code, String system_name, Long fat_code, String fat_name, String system_disc, Date createtime, Date updatetime, String status, String remark, String creator, String editor, String come_source) {
        this.id = id;
        this.system_uni_code = system_uni_code;
        this.system_name = system_name;
        this.fat_code = fat_code;
        this.fat_name = fat_name;
        this.system_disc = system_disc;
        this.createtime = createtime;
        this.updatetime = updatetime;
        this.status = status;
        this.remark = remark;
        this.creator = creator;
        this.editor = editor;
        this.come_source = come_source;
    }

    public SystemConstModel() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSystem_uni_code() {
        return system_uni_code;
    }

    public void setSystem_uni_code(Long system_uni_code) {
        this.system_uni_code = system_uni_code;
    }

    public String getSystem_name() {
        return system_name;
    }

    public void setSystem_name(String system_name) {
        this.system_name = system_name == null ? null : system_name.trim();
    }

    public Long getFat_code() {
        return fat_code;
    }

    public void setFat_code(Long fat_code) {
        this.fat_code = fat_code;
    }

    public String getFat_name() {
        return fat_name;
    }

    public void setFat_name(String fat_name) {
        this.fat_name = fat_name == null ? null : fat_name.trim();
    }

    public String getSystem_disc() {
        return system_disc;
    }

    public void setSystem_disc(String system_disc) {
        this.system_disc = system_disc == null ? null : system_disc.trim();
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", system_uni_code=").append(system_uni_code);
        sb.append(", system_name=").append(system_name);
        sb.append(", fat_code=").append(fat_code);
        sb.append(", fat_name=").append(fat_name);
        sb.append(", system_disc=").append(system_disc);
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