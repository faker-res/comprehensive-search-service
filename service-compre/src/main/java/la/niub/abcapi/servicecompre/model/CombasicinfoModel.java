package la.niub.abcapi.servicecompre.model;
import java.util.*;
/**
 *   @author adams
 */
public class CombasicinfoModel {
    //主键id
    private String com_uni_code;
    //公司全称
    private String com_name;
    //公司英文名称
    private String com_ename;
    //公司简称
    private String com_sname;
    //公司拼音简称
    private String com_spell;
    //公司英文简称
    private String com_espell;
    //公司类型
    private String com_type;
    //公司类别
    private String fin_repo_type;
    //公司类型(名称)
    private String com_type_name;
    //所属概念
    private String concept;
    //主营产品类型
    private String major_product_type;
    //主营产品名称
    private String major_product_name;
    //成立日期
    private Date build_time;
    //董事长
    private String board_chair_man;
    //法人代表
    private String legal_rp;
    //统一信用编码
    private String uni_credit;
    //工商登记号
    private String busi_number;
    //总经理
    private String gen_manager;
    //注册资本
    private String rg_capital;
    //货币类型
    private String currency_type;
    //主承销商
    private String main_saler;
    //会计师事务所
    private String acc_firm;
    //律师事务所
    private String law_firm;
    //法律顾问
    private String legal_adv;
    //所属行业代码
    private String industry_code;
    //主营业务
    private String busi_mainscope;
    //经营范围
    private String run_scope;
    //国家
    private String nati_name;
    //省
    private String prov_name;
    //市
    private String city_name;
    //注册地址
    private String regist_add;
    //办公地址
    private String office_add;
    //邮编
    private String office_postcode;
    //联系电话
    private String tel_number;
    //联系传真
    private String contact_fax;
    //联系邮箱
    private String contact_mail;
    //公司网址
    private String company_website;
    //董事会秘书
    private String secretary;
    //董秘电话
    private String secretary_tel;
    //董秘邮箱
    private String secretary_mail;
    //证券事务代表
    private String secretary_rep;
    //联系人
    private String contacts;
    //联系人电话
    private String contacts_tel;
    //联系人邮箱
    private String contacts_mail;
    //员工总人数
    private String total_staff;
    //是否境内上市
    private String domestic_listing;
    //公司简介
    private String com_intro;
    //创建时间
    private Date createtime;
    //更新时间
    private Date updatetime;
    //状态
    private String status;
    //注释
    private String remark;
    //创建人
    private String creator;
    //编辑人
    private String editor;
    //数据来源
    private String come_source;
    //同花顺机构id
    private String qhqm_id;

    public void setCom_uni_code(String com_uni_code){
        this.com_uni_code = com_uni_code;
    }

    public String getCom_uni_code(){
        return this.com_uni_code;
    }
    public void setCom_name(String com_name){
        this.com_name = com_name;
    }

    public String getCom_name(){
        return this.com_name;
    }
    public void setCom_ename(String com_ename){
        this.com_ename = com_ename;
    }

    public String getCom_ename(){
        return this.com_ename;
    }
    public void setCom_sname(String com_sname){
        this.com_sname = com_sname;
    }

    public String getCom_sname(){
        return this.com_sname;
    }
    public void setCom_spell(String com_spell){
        this.com_spell = com_spell;
    }

    public String getCom_spell(){
        return this.com_spell;
    }
    public void setCom_espell(String com_espell){
        this.com_espell = com_espell;
    }

    public String getCom_espell(){
        return this.com_espell;
    }
    public void setCom_type(String com_type){
        this.com_type = com_type;
    }

    public String getCom_type(){
        return this.com_type;
    }
    public void setFin_repo_type(String fin_repo_type){
        this.fin_repo_type = fin_repo_type;
    }

    public String getFin_repo_type(){
        return this.fin_repo_type;
    }
    public void setCom_type_name(String com_type_name){
        this.com_type_name = com_type_name;
    }

    public String getCom_type_name(){
        return this.com_type_name;
    }
    public void setConcept(String concept){
        this.concept = concept;
    }

    public String getConcept(){
        return this.concept;
    }
    public void setMajor_product_type(String major_product_type){
        this.major_product_type = major_product_type;
    }

    public String getMajor_product_type(){
        return this.major_product_type;
    }
    public void setMajor_product_name(String major_product_name){
        this.major_product_name = major_product_name;
    }

    public String getMajor_product_name(){
        return this.major_product_name;
    }
    public void setBuild_time(Date build_time){
        this.build_time = build_time;
    }

    public Date getBuild_time(){
        return this.build_time;
    }
    public void setBoard_chair_man(String board_chair_man){
        this.board_chair_man = board_chair_man;
    }

    public String getBoard_chair_man(){
        return this.board_chair_man;
    }
    public void setLegal_rp(String legal_rp){
        this.legal_rp = legal_rp;
    }

    public String getLegal_rp(){
        return this.legal_rp;
    }
    public void setUni_credit(String uni_credit){
        this.uni_credit = uni_credit;
    }

    public String getUni_credit(){
        return this.uni_credit;
    }
    public void setBusi_number(String busi_number){
        this.busi_number = busi_number;
    }

    public String getBusi_number(){
        return this.busi_number;
    }
    public void setGen_manager(String gen_manager){
        this.gen_manager = gen_manager;
    }

    public String getGen_manager(){
        return this.gen_manager;
    }
    public void setRg_capital(String rg_capital){
        this.rg_capital = rg_capital;
    }

    public String getRg_capital(){
        return this.rg_capital;
    }
    public void setCurrency_type(String currency_type){
        this.currency_type = currency_type;
    }

    public String getCurrency_type(){
        return this.currency_type;
    }
    public void setMain_saler(String main_saler){
        this.main_saler = main_saler;
    }

    public String getMain_saler(){
        return this.main_saler;
    }
    public void setAcc_firm(String acc_firm){
        this.acc_firm = acc_firm;
    }

    public String getAcc_firm(){
        return this.acc_firm;
    }
    public void setLaw_firm(String law_firm){
        this.law_firm = law_firm;
    }

    public String getLaw_firm(){
        return this.law_firm;
    }
    public void setLegal_adv(String legal_adv){
        this.legal_adv = legal_adv;
    }

    public String getLegal_adv(){
        return this.legal_adv;
    }
    public void setIndustry_code(String industry_code){
        this.industry_code = industry_code;
    }

    public String getIndustry_code(){
        return this.industry_code;
    }
    public void setBusi_mainscope(String busi_mainscope){
        this.busi_mainscope = busi_mainscope;
    }

    public String getBusi_mainscope(){
        return this.busi_mainscope;
    }
    public void setRun_scope(String run_scope){
        this.run_scope = run_scope;
    }

    public String getRun_scope(){
        return this.run_scope;
    }
    public void setNati_name(String nati_name){
        this.nati_name = nati_name;
    }

    public String getNati_name(){
        return this.nati_name;
    }
    public void setProv_name(String prov_name){
        this.prov_name = prov_name;
    }

    public String getProv_name(){
        return this.prov_name;
    }
    public void setCity_name(String city_name){
        this.city_name = city_name;
    }

    public String getCity_name(){
        return this.city_name;
    }
    public void setRegist_add(String regist_add){
        this.regist_add = regist_add;
    }

    public String getRegist_add(){
        return this.regist_add;
    }
    public void setOffice_add(String office_add){
        this.office_add = office_add;
    }

    public String getOffice_add(){
        return this.office_add;
    }
    public void setOffice_postcode(String office_postcode){
        this.office_postcode = office_postcode;
    }

    public String getOffice_postcode(){
        return this.office_postcode;
    }
    public void setTel_number(String tel_number){
        this.tel_number = tel_number;
    }

    public String getTel_number(){
        return this.tel_number;
    }
    public void setContact_fax(String contact_fax){
        this.contact_fax = contact_fax;
    }

    public String getContact_fax(){
        return this.contact_fax;
    }
    public void setContact_mail(String contact_mail){
        this.contact_mail = contact_mail;
    }

    public String getContact_mail(){
        return this.contact_mail;
    }
    public void setCompany_website(String company_website){
        this.company_website = company_website;
    }

    public String getCompany_website(){
        return this.company_website;
    }
    public void setSecretary(String secretary){
        this.secretary = secretary;
    }

    public String getSecretary(){
        return this.secretary;
    }
    public void setSecretary_tel(String secretary_tel){
        this.secretary_tel = secretary_tel;
    }

    public String getSecretary_tel(){
        return this.secretary_tel;
    }
    public void setSecretary_mail(String secretary_mail){
        this.secretary_mail = secretary_mail;
    }

    public String getSecretary_mail(){
        return this.secretary_mail;
    }
    public void setSecretary_rep(String secretary_rep){
        this.secretary_rep = secretary_rep;
    }

    public String getSecretary_rep(){
        return this.secretary_rep;
    }
    public void setContacts(String contacts){
        this.contacts = contacts;
    }

    public String getContacts(){
        return this.contacts;
    }
    public void setContacts_tel(String contacts_tel){
        this.contacts_tel = contacts_tel;
    }

    public String getContacts_tel(){
        return this.contacts_tel;
    }
    public void setContacts_mail(String contacts_mail){
        this.contacts_mail = contacts_mail;
    }

    public String getContacts_mail(){
        return this.contacts_mail;
    }
    public void setTotal_staff(String total_staff){
        this.total_staff = total_staff;
    }

    public String getTotal_staff(){
        return this.total_staff;
    }
    public void setDomestic_listing(String domestic_listing){
        this.domestic_listing = domestic_listing;
    }

    public String getDomestic_listing(){
        return this.domestic_listing;
    }
    public void setCom_intro(String com_intro){
        this.com_intro = com_intro;
    }

    public String getCom_intro(){
        return this.com_intro;
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
    public void setQhqm_id(String qhqm_id){
        this.qhqm_id = qhqm_id;
    }

    public String getQhqm_id(){
        return this.qhqm_id;
    }

     public CombasicinfoModel() {

     }

     public CombasicinfoModel(String com_uni_code , String com_name , String com_ename , String com_sname , String com_spell , String com_espell , String com_type , String fin_repo_type , String com_type_name , String concept , String major_product_type , String major_product_name , Date build_time , String board_chair_man , String legal_rp , String uni_credit , String busi_number , String gen_manager , String rg_capital , String currency_type , String main_saler , String acc_firm , String law_firm , String legal_adv , String industry_code , String busi_mainscope , String run_scope , String nati_name , String prov_name , String city_name , String regist_add , String office_add , String office_postcode , String tel_number , String contact_fax , String contact_mail , String company_website , String secretary , String secretary_tel , String secretary_mail , String secretary_rep , String contacts , String contacts_tel , String contacts_mail , String total_staff , String domestic_listing , String com_intro , Date createtime , Date updatetime , String status , String remark , String creator , String editor , String come_source , String qhqm_id ) {
            super();
            this.com_uni_code = com_uni_code;
            this.com_name = com_name;
            this.com_ename = com_ename;
            this.com_sname = com_sname;
            this.com_spell = com_spell;
            this.com_espell = com_espell;
            this.com_type = com_type;
            this.fin_repo_type = fin_repo_type;
            this.com_type_name = com_type_name;
            this.concept = concept;
            this.major_product_type = major_product_type;
            this.major_product_name = major_product_name;
            this.build_time = build_time;
            this.board_chair_man = board_chair_man;
            this.legal_rp = legal_rp;
            this.uni_credit = uni_credit;
            this.busi_number = busi_number;
            this.gen_manager = gen_manager;
            this.rg_capital = rg_capital;
            this.currency_type = currency_type;
            this.main_saler = main_saler;
            this.acc_firm = acc_firm;
            this.law_firm = law_firm;
            this.legal_adv = legal_adv;
            this.industry_code = industry_code;
            this.busi_mainscope = busi_mainscope;
            this.run_scope = run_scope;
            this.nati_name = nati_name;
            this.prov_name = prov_name;
            this.city_name = city_name;
            this.regist_add = regist_add;
            this.office_add = office_add;
            this.office_postcode = office_postcode;
            this.tel_number = tel_number;
            this.contact_fax = contact_fax;
            this.contact_mail = contact_mail;
            this.company_website = company_website;
            this.secretary = secretary;
            this.secretary_tel = secretary_tel;
            this.secretary_mail = secretary_mail;
            this.secretary_rep = secretary_rep;
            this.contacts = contacts;
            this.contacts_tel = contacts_tel;
            this.contacts_mail = contacts_mail;
            this.total_staff = total_staff;
            this.domestic_listing = domestic_listing;
            this.com_intro = com_intro;
            this.createtime = createtime;
            this.updatetime = updatetime;
            this.status = status;
            this.remark = remark;
            this.creator = creator;
            this.editor = editor;
            this.come_source = come_source;
            this.qhqm_id = qhqm_id;
     }
}