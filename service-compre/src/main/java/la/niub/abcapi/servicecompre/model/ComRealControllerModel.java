package la.niub.abcapi.servicecompre.model;

import java.util.Date;

public class ComRealControllerModel {

    //主键id
    private String id;
    //公司统一编码
    private String com_uni_code;
    //公告日期
    private Date decl_date;
    //截止日期
    private Date end_date;
    //控制人类型
    private String control_type;
    //实际控制人
    private String real_control;
    //控制人类型编码
    private String control_type_code;
    //实际控制人类别
    private String control_category;
    //实际控制人id
    private String real_control_id;
    //控股比例
    private String hold_ratio;
    //控制人简介
    private String control_desc;
    //创建时间
    private Date createtime;
    //更新时间
    private Date updatetime;
    //状态
    private String status;
    //备注
    private String remark;
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
    public void setCom_uni_code(String com_uni_code){
        this.com_uni_code = com_uni_code;
    }

    public String getCom_uni_code(){
        return this.com_uni_code;
    }
    public void setDecl_date(Date decl_date){
        this.decl_date = decl_date;
    }

    public Date getDecl_date(){
        return this.decl_date;
    }
    public void setEnd_date(Date end_date){
        this.end_date = end_date;
    }

    public Date getEnd_date(){
        return this.end_date;
    }
    public void setControl_type(String control_type){
        this.control_type = control_type;
    }

    public String getControl_type(){
        return this.control_type;
    }
    public void setReal_control(String real_control){
        this.real_control = real_control;
    }

    public String getReal_control(){
        return this.real_control;
    }
    public void setControl_type_code(String control_type_code){
        this.control_type_code = control_type_code;
    }

    public String getControl_type_code(){
        return this.control_type_code;
    }
    public void setControl_category(String control_category){
        this.control_category = control_category;
    }

    public String getControl_category(){
        return this.control_category;
    }
    public void setReal_control_id(String real_control_id){
        this.real_control_id = real_control_id;
    }

    public String getReal_control_id(){
        return this.real_control_id;
    }
    public void setHold_ratio(String hold_ratio){
        this.hold_ratio = hold_ratio;
    }

    public String getHold_ratio(){
        return this.hold_ratio;
    }
    public void setControl_desc(String control_desc){
        this.control_desc = control_desc;
    }

    public String getControl_desc(){
        return this.control_desc;
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
    public void setRemark(String remark){
        this.remark = remark;
    }

    public String getRemark(){
        return this.remark;
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

    public ComRealControllerModel() {

    }

    public ComRealControllerModel(String id , String com_uni_code , Date decl_date , Date end_date , String control_type , String real_control , String control_type_code , String control_category , String real_control_id , String hold_ratio , String control_desc , Date createtime , Date updatetime , String status , String remark , String creator , String editor , String come_source ) {
        super();
        this.id = id;
        this.com_uni_code = com_uni_code;
        this.decl_date = decl_date;
        this.end_date = end_date;
        this.control_type = control_type;
        this.real_control = real_control;
        this.control_type_code = control_type_code;
        this.control_category = control_category;
        this.real_control_id = real_control_id;
        this.hold_ratio = hold_ratio;
        this.control_desc = control_desc;
        this.createtime = createtime;
        this.updatetime = updatetime;
        this.status = status;
        this.remark = remark;
        this.creator = creator;
        this.editor = editor;
        this.come_source = come_source;
    }
}
