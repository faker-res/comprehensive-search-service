package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;
import java.util.Date;

public class PeoImageModel implements Serializable {
    private Long id;

    private Long peo_uni_code;

    private String name;

    private String image_size;

    private String oss_path;

    private String come_source;

    private String source_id;

    private Date createtime;

    private Date updatetime;

    private Byte status;

    private String creator;

    private String editor;

    private static final long serialVersionUID = 1L;

    public PeoImageModel(Long id, Long peo_uni_code, String name, String image_size, String oss_path, String come_source, String source_id, Date createtime, Date updatetime, Byte status, String creator, String editor) {
        this.id = id;
        this.peo_uni_code = peo_uni_code;
        this.name = name;
        this.image_size = image_size;
        this.oss_path = oss_path;
        this.come_source = come_source;
        this.source_id = source_id;
        this.createtime = createtime;
        this.updatetime = updatetime;
        this.status = status;
        this.creator = creator;
        this.editor = editor;
    }

    public PeoImageModel() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPeo_uni_code() {
        return peo_uni_code;
    }

    public void setPeo_uni_code(Long peo_uni_code) {
        this.peo_uni_code = peo_uni_code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getImage_size() {
        return image_size;
    }

    public void setImage_size(String image_size) {
        this.image_size = image_size == null ? null : image_size.trim();
    }

    public String getOss_path() {
        return oss_path;
    }

    public void setOss_path(String oss_path) {
        this.oss_path = oss_path == null ? null : oss_path.trim();
    }

    public String getCome_source() {
        return come_source;
    }

    public void setCome_source(String come_source) {
        this.come_source = come_source == null ? null : come_source.trim();
    }

    public String getSource_id() {
        return source_id;
    }

    public void setSource_id(String source_id) {
        this.source_id = source_id == null ? null : source_id.trim();
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", peo_uni_code=").append(peo_uni_code);
        sb.append(", name=").append(name);
        sb.append(", image_size=").append(image_size);
        sb.append(", oss_path=").append(oss_path);
        sb.append(", come_source=").append(come_source);
        sb.append(", source_id=").append(source_id);
        sb.append(", createtime=").append(createtime);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", status=").append(status);
        sb.append(", creator=").append(creator);
        sb.append(", editor=").append(editor);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}