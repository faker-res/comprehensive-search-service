package la.niub.abcapi.servicecompre.model.nosql;

import org.springframework.data.mongodb.core.mapping.Document;

//@Document(collection = "weibo_wave_new")
@Document(collection = "weibo_wave")
public class WeiboWaveNewModel {

    private String _id;

    private String group;

    private String keys;

    private Long value;

    private Integer mobile_value;

    private Integer pc_value;

    private String date;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getKeys() {
        return keys;
    }

    public void setKeys(String keys) {
        this.keys = keys;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public Integer getMobile_value() {
        return mobile_value;
    }

    public void setMobile_value(Integer mobile_value) {
        this.mobile_value = mobile_value;
    }

    public Integer getPc_value() {
        return pc_value;
    }

    public void setPc_value(Integer pc_value) {
        this.pc_value = pc_value;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "WeiboWaveNewModel{" +
                "_id='" + _id + '\'' +
                ", group='" + group + '\'' +
                ", keys='" + keys + '\'' +
                ", value=" + value +
                ", mobile_value=" + mobile_value +
                ", pc_value=" + pc_value +
                ", date='" + date + '\'' +
                '}';
    }
}
