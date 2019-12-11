package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;
import java.util.Date;

public class SecPlateInfoModel implements Serializable {
    private Long id;

    private Long sec_uni_code;

    private Long org_uni_code;

    private Date begin_date;

    private Date end_date;

    private String plate_code;

    private String plate_name;

    private Byte is_valid;

    private Date createtime;

    private Date updatetime;

    private Byte status;

    private String creator;

    private String editor;

    private Byte push_search;

    private Byte push_product;

    private static final long serialVersionUID = 1L;

    public SecPlateInfoModel(Long id, Long sec_uni_code, Long org_uni_code, Date begin_date, Date end_date, String plate_code, String plate_name, Byte is_valid, Date createtime, Date updatetime, Byte status, String creator, String editor, Byte push_search, Byte push_product) {
        this.id = id;
        this.sec_uni_code = sec_uni_code;
        this.org_uni_code = org_uni_code;
        this.begin_date = begin_date;
        this.end_date = end_date;
        this.plate_code = plate_code;
        this.plate_name = plate_name;
        this.is_valid = is_valid;
        this.createtime = createtime;
        this.updatetime = updatetime;
        this.status = status;
        this.creator = creator;
        this.editor = editor;
        this.push_search = push_search;
        this.push_product = push_product;
    }

    public SecPlateInfoModel() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSec_uni_code() {
        return sec_uni_code;
    }

    public void setSec_uni_code(Long sec_uni_code) {
        this.sec_uni_code = sec_uni_code;
    }

    public Long getOrg_uni_code() {
        return org_uni_code;
    }

    public void setOrg_uni_code(Long org_uni_code) {
        this.org_uni_code = org_uni_code;
    }

    public Date getBegin_date() {
        return begin_date;
    }

    public void setBegin_date(Date begin_date) {
        this.begin_date = begin_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public String getPlate_code() {
        return plate_code;
    }

    public void setPlate_code(String plate_code) {
        this.plate_code = plate_code == null ? null : plate_code.trim();
    }

    public String getPlate_name() {
        return plate_name;
    }

    public void setPlate_name(String plate_name) {
        this.plate_name = plate_name == null ? null : plate_name.trim();
    }

    public Byte getIs_valid() {
        return is_valid;
    }

    public void setIs_valid(Byte is_valid) {
        this.is_valid = is_valid;
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

    public Byte getPush_search() {
        return push_search;
    }

    public void setPush_search(Byte push_search) {
        this.push_search = push_search;
    }

    public Byte getPush_product() {
        return push_product;
    }

    public void setPush_product(Byte push_product) {
        this.push_product = push_product;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", sec_uni_code=").append(sec_uni_code);
        sb.append(", org_uni_code=").append(org_uni_code);
        sb.append(", begin_date=").append(begin_date);
        sb.append(", end_date=").append(end_date);
        sb.append(", plate_code=").append(plate_code);
        sb.append(", plate_name=").append(plate_name);
        sb.append(", is_valid=").append(is_valid);
        sb.append(", createtime=").append(createtime);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", status=").append(status);
        sb.append(", creator=").append(creator);
        sb.append(", editor=").append(editor);
        sb.append(", push_search=").append(push_search);
        sb.append(", push_product=").append(push_product);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}