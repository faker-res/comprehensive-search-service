package la.niub.abcapi.servicecompre.model;

import java.util.Date;

public class OrgbasicinfochgModel {
    //自增ID
    private String id;
    //机构统一编码
    private String org_uni_code;
    //公告日期
    private Date announcement_date;
    //变动类型
    private String change_type;
    //变动起始日
    private Date begin_date;
    //变动截止日
    private Date end_date;
    //变动前内容
    private String begin_change;
    //变动后内容
    private String after_change;
    //变动原因
    private String change_reason;
    //创建时间
    private Date createtime;
    //更新时间
    private Date updatetime;
    //状态
    private String status;
    //创建人
    private String creator;
    //编辑人
    private String editor;
    //数据来源
    private String come_source;

    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return this.id;
    }
    public void setOrg_uni_code(String org_uni_code){
        this.org_uni_code = org_uni_code;
    }

    public String getOrg_uni_code(){
        return this.org_uni_code;
    }
    public void setAnnouncement_date(Date announcement_date){
        this.announcement_date = announcement_date;
    }

    public Date getAnnouncement_date(){
        return this.announcement_date;
    }
    public void setChange_type(String change_type){
        this.change_type = change_type;
    }

    public String getChange_type(){
        return this.change_type;
    }
    public void setBegin_date(Date begin_date){
        this.begin_date = begin_date;
    }

    public Date getBegin_date(){
        return this.begin_date;
    }
    public void setEnd_date(Date end_date){
        this.end_date = end_date;
    }

    public Date getEnd_date(){
        return this.end_date;
    }
    public void setBegin_change(String begin_change){
        this.begin_change = begin_change;
    }

    public String getBegin_change(){
        return this.begin_change;
    }
    public void setAfter_change(String after_change){
        this.after_change = after_change;
    }

    public String getAfter_change(){
        return this.after_change;
    }
    public void setChange_reason(String change_reason){
        this.change_reason = change_reason;
    }

    public String getChange_reason(){
        return this.change_reason;
    }
    public void setCreatetime(Date createtime){
        this.createtime = createtime;
    }

    public Date getCreatetime(){
        return this.createtime;
    }
    public void setUpdatetime(Date updatetime){
        this.updatetime = updatetime;
    }

    public Date getUpdatetime(){
        return this.updatetime;
    }
    public void setStatus(String status){
        this.status = status;
    }

    public String getStatus(){
        return this.status;
    }
    public void setCreator(String creator){
        this.creator = creator;
    }

    public String getCreator(){
        return this.creator;
    }
    public void setEditor(String editor){
        this.editor = editor;
    }

    public String getEditor(){
        return this.editor;
    }
    public void setCome_source(String come_source){
        this.come_source = come_source;
    }

    public String getCome_source(){
        return this.come_source;
    }

    public OrgbasicinfochgModel() {

    }

    public OrgbasicinfochgModel(String id , String org_uni_code , Date announcement_date , String change_type , Date begin_date , Date end_date , String begin_change , String after_change , String change_reason , Date createtime , Date updatetime , String status , String creator , String editor , String come_source ) {
        super();
        this.id = id;
        this.org_uni_code = org_uni_code;
        this.announcement_date = announcement_date;
        this.change_type = change_type;
        this.begin_date = begin_date;
        this.end_date = end_date;
        this.begin_change = begin_change;
        this.after_change = after_change;
        this.change_reason = change_reason;
        this.createtime = createtime;
        this.updatetime = updatetime;
        this.status = status;
        this.creator = creator;
        this.editor = editor;
        this.come_source = come_source;
    }
}
