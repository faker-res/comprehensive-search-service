package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;
import java.util.Date;

public class AbcIndustryModel implements Serializable {
    private Long indu_uni_code;

    private Long indu_standard;

    private String indu_code;

    private String index_code;

    private String indu_name;

    private String parent_id;

    private String indu_enname;

    private String isvalid;

    private Date createtime;

    private Date updatetime;

    private Byte status;

    private String remark;

    private String creator;

    private String editor;

    private Byte indu_level;

    private static final long serialVersionUID = 1L;

    public AbcIndustryModel(Long indu_uni_code, Long indu_standard, String indu_code, String index_code, String indu_name, String parent_id, String indu_enname, String isvalid, Date createtime, Date updatetime, Byte status, String remark, String creator, String editor, Byte indu_level) {
        this.indu_uni_code = indu_uni_code;
        this.indu_standard = indu_standard;
        this.indu_code = indu_code;
        this.index_code = index_code;
        this.indu_name = indu_name;
        this.parent_id = parent_id;
        this.indu_enname = indu_enname;
        this.isvalid = isvalid;
        this.createtime = createtime;
        this.updatetime = updatetime;
        this.status = status;
        this.remark = remark;
        this.creator = creator;
        this.editor = editor;
        this.indu_level = indu_level;
    }

    public AbcIndustryModel() {
        super();
    }

    public Long getIndu_uni_code() {
        return indu_uni_code;
    }

    public void setIndu_uni_code(Long indu_uni_code) {
        this.indu_uni_code = indu_uni_code;
    }

    public Long getIndu_standard() {
        return indu_standard;
    }

    public void setIndu_standard(Long indu_standard) {
        this.indu_standard = indu_standard;
    }

    public String getIndu_code() {
        return indu_code;
    }

    public void setIndu_code(String indu_code) {
        this.indu_code = indu_code == null ? null : indu_code.trim();
    }

    public String getIndex_code() {
        return index_code;
    }

    public void setIndex_code(String index_code) {
        this.index_code = index_code == null ? null : index_code.trim();
    }

    public String getIndu_name() {
        return indu_name;
    }

    public void setIndu_name(String indu_name) {
        this.indu_name = indu_name == null ? null : indu_name.trim();
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id == null ? null : parent_id.trim();
    }

    public String getIndu_enname() {
        return indu_enname;
    }

    public void setIndu_enname(String indu_enname) {
        this.indu_enname = indu_enname == null ? null : indu_enname.trim();
    }

    public String getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(String isvalid) {
        this.isvalid = isvalid == null ? null : isvalid.trim();
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

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
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

    public Byte getIndu_level() {
        return indu_level;
    }

    public void setIndu_level(Byte indu_level) {
        this.indu_level = indu_level;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", indu_uni_code=").append(indu_uni_code);
        sb.append(", indu_standard=").append(indu_standard);
        sb.append(", indu_code=").append(indu_code);
        sb.append(", index_code=").append(index_code);
        sb.append(", indu_name=").append(indu_name);
        sb.append(", parent_id=").append(parent_id);
        sb.append(", indu_enname=").append(indu_enname);
        sb.append(", isvalid=").append(isvalid);
        sb.append(", createtime=").append(createtime);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append(", creator=").append(creator);
        sb.append(", editor=").append(editor);
        sb.append(", indu_level=").append(indu_level);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}