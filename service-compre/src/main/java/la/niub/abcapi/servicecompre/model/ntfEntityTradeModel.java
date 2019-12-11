package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;
import java.util.Date;

public class ntfEntityTradeModel implements Serializable {
    private Integer id;

    private Long com_id;

    private Long com_uni_code;

    private String com_name;

    private Date stat_date;

    private String trade_pic;

    private String trade_name;

    private String reg_no;

    private String trade_status;

    private String come_source;

    private Date createtime;

    private Date updatetime;

    private String editor;

    private String remark;

    private String status;

    private String trade_type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getCom_id() {
        return com_id;
    }

    public void setCom_id(Long com_id) {
        this.com_id = com_id;
    }

    public Long getCom_uni_code() {
        return com_uni_code;
    }

    public void setCom_uni_code(Long com_uni_code) {
        this.com_uni_code = com_uni_code;
    }

    public String getCom_name() {
        return com_name;
    }

    public void setCom_name(String com_name) {
        this.com_name = com_name == null ? null : com_name.trim();
    }

    public Date getStat_date() {
        return stat_date;
    }

    public void setStat_date(Date stat_date) {
        this.stat_date = stat_date;
    }

    public String getTrade_pic() {
        return trade_pic;
    }

    public void setTrade_pic(String trade_pic) {
        this.trade_pic = trade_pic == null ? null : trade_pic.trim();
    }

    public String getTrade_name() {
        return trade_name;
    }

    public void setTrade_name(String trade_name) {
        this.trade_name = trade_name == null ? null : trade_name.trim();
    }

    public String getReg_no() {
        return reg_no;
    }

    public void setReg_no(String reg_no) {
        this.reg_no = reg_no == null ? null : reg_no.trim();
    }

    public String getTrade_status() {
        return trade_status;
    }

    public void setTrade_status(String trade_status) {
        this.trade_status = trade_status == null ? null : trade_status.trim();
    }

    public String getCome_source() {
        return come_source;
    }

    public void setCome_source(String come_source) {
        this.come_source = come_source == null ? null : come_source.trim();
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

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor == null ? null : editor.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type == null ? null : trade_type.trim();
    }

    @Override
    public String toString() {
        return "ntfEntityTradeModel{" +
                "id=" + id +
                ", com_id=" + com_id +
                ", com_uni_code=" + com_uni_code +
                ", com_name='" + com_name + '\'' +
                ", stat_date=" + stat_date +
                ", trade_pic='" + trade_pic + '\'' +
                ", trade_name='" + trade_name + '\'' +
                ", reg_no='" + reg_no + '\'' +
                ", trade_status='" + trade_status + '\'' +
                ", come_source='" + come_source + '\'' +
                ", createtime=" + createtime +
                ", updatetime=" + updatetime +
                ", editor='" + editor + '\'' +
                ", remark='" + remark + '\'' +
                ", status='" + status + '\'' +
                ", trade_type='" + trade_type + '\'' +
                '}';
    }
}