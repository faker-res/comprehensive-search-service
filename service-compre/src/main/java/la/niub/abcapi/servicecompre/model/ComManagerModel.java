package la.niub.abcapi.servicecompre.model;

import java.util.Date;

public class ComManagerModel {
    //流水号
    private String id;
    //公告日期
    private Date decl_date;
    //公司统一编码
    private String com_uni_code;
    //职位类型
    private String post_type;
    //职位代码
    private String post_code;
    //是否代理:1-是;0-否
    private String if_agent;
    //实际职位名称
    private String post_name;
    //人物统一编码
    private String peo_uni_code;
    //姓名
    private String led_name;
    //入职时董事会届次
    private String time_session;
    //任职所在届次
    private String in_session;
    //当前在职状态
    private String current_status;
    //任职起始日
    private Date in_date;
    //任职结束日
    private Date off_date;
    //离职原因
    private String leave_reason;
    //是否实际离职:1-是;0-否
    private String if_position;
    //备注
    private String remark;
    //状态
    private String status;
    //创建时间
    private Date createtime;
    //更新时间
    private Date updatetime;
    //数据来源
    private String come_source;
    //来源ID：关联来源表的ID
    private String source_id;
    //创建人
    private String creator;
    //编辑人
    private String editor;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDecl_date() {
        return decl_date;
    }

    public void setDecl_date(Date decl_date) {
        this.decl_date = decl_date;
    }

    public String getCom_uni_code() {
        return com_uni_code;
    }

    public void setCom_uni_code(String com_uni_code) {
        this.com_uni_code = com_uni_code;
    }

    public String getPost_type() {
        return post_type;
    }

    public void setPost_type(String post_type) {
        this.post_type = post_type;
    }

    public String getPost_code() {
        return post_code;
    }

    public void setPost_code(String post_code) {
        this.post_code = post_code;
    }

    public String getIf_agent() {
        return if_agent;
    }

    public void setIf_agent(String if_agent) {
        this.if_agent = if_agent;
    }

    public String getPost_name() {
        return post_name;
    }

    public void setPost_name(String post_name) {
        this.post_name = post_name;
    }

    public String getPeo_uni_code() {
        return peo_uni_code;
    }

    public void setPeo_uni_code(String peo_uni_code) {
        this.peo_uni_code = peo_uni_code;
    }

    public String getLed_name() {
        return led_name;
    }

    public void setLed_name(String led_name) {
        this.led_name = led_name;
    }

    public String getTime_session() {
        return time_session;
    }

    public void setTime_session(String time_session) {
        this.time_session = time_session;
    }

    public String getIn_session() {
        return in_session;
    }

    public void setIn_session(String in_session) {
        this.in_session = in_session;
    }

    public String getCurrent_status() {
        return current_status;
    }

    public void setCurrent_status(String current_status) {
        this.current_status = current_status;
    }

    public Date getIn_date() {
        return in_date;
    }

    public void setIn_date(Date in_date) {
        this.in_date = in_date;
    }

    public Date getOff_date() {
        return off_date;
    }

    public void setOff_date(Date off_date) {
        this.off_date = off_date;
    }

    public String getLeave_reason() {
        return leave_reason;
    }

    public void setLeave_reason(String leave_reason) {
        this.leave_reason = leave_reason;
    }

    public String getIf_position() {
        return if_position;
    }

    public void setIf_position(String if_position) {
        this.if_position = if_position;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getCome_source() {
        return come_source;
    }

    public void setCome_source(String come_source) {
        this.come_source = come_source;
    }

    public String getSource_id() {
        return source_id;
    }

    public void setSource_id(String source_id) {
        this.source_id = source_id;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }
}
