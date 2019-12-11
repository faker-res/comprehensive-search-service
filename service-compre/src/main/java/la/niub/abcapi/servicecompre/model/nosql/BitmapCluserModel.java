package la.niub.abcapi.servicecompre.model.nosql;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "bitmap_cluster_status")
public class BitmapCluserModel {

    private String _id;

    private Long head_savepoint;

    private Date _t;

    private Date head_update;

    private Integer bitmap_ver;

    private Date first_update;

    private Date tail_update;

    private Long first_savepoint;

    private Long tail_savepoint;

    public void set_id(String _id) {
        this._id = _id;
    }

    public String get_id() {
        return _id;
    }

    public void setHead_savepoint(Long head_savepoint) {
        this.head_savepoint = head_savepoint;
    }

    public Long getHead_savepoint() {
        return head_savepoint;
    }

    public void set_t(Date _t) {
        this._t = _t;
    }

    public Date get_t() {
        return _t;
    }

    public void setHead_update(Date head_update) {
        this.head_update = head_update;
    }

    public Date getHead_update() {
        return head_update;
    }

    public void setBitmap_ver(Integer bitmap_ver) {
        this.bitmap_ver = bitmap_ver;
    }

    public Integer getBitmap_ver() {
        return bitmap_ver;
    }

    public void setFirst_update(Date first_update) {
        this.first_update = first_update;
    }

    public Date getFirst_update() {
        return first_update;
    }

    public void setTail_update(Date tail_update) {
        this.tail_update = tail_update;
    }

    public Date getTail_update() {
        return tail_update;
    }

    public void setTail_savepoint(Long tail_savepoint) {
        this.tail_savepoint = tail_savepoint;
    }

    public Long getTail_savepoint() {
        return tail_savepoint;
    }

    public Long getFirst_savepoint() {
        return first_savepoint;
    }

    public void setFirst_savepoint(Long first_savepoint) {
        this.first_savepoint = first_savepoint;
    }
}
