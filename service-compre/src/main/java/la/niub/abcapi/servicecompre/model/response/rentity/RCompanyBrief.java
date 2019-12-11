package la.niub.abcapi.servicecompre.model.response.rentity;

import java.util.Date;
import java.util.List;

//公司介绍
public class RCompanyBrief {
    //id
    private String id;
    //公司全称
    private String com_name;
    //公司曾用名
    private List begin_change;
    //公司英文名称
    private String com_ename;
    //公司简称
    private String com_sname;
    //公司实控人
    private String real_control;
    //法人代表
    private String legal_rp;
    //董事长
    private String board_chair_man;
    //总经理
    private String gen_manager;
    //董事会秘书，信息披露人
    private String secretary;
    //员工总人数
    private String total_staff;
    //成立日期
    private Date build_time;
    //统一信用编码
    private String uni_credit;
    //货币类型
    private String currency_type;
    //注册资本
    private String rg_capital;
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
    //会计师事务所
    private String acc_firm;
    //律师事务所
    private String law_firm;
    //经办会计师
    private String accountant;
    //经办律师
    private String lawyer;
    //经营范围
    private String run_scope;
    //公司简介
    private String com_intro;

    public String getCom_name() {
        return com_name;
    }

    public void setCom_name(String com_name) {
        this.com_name = com_name;
    }

    public List getBegin_change() {
        return begin_change;
    }

    public void setBegin_change(List begin_change) {
        this.begin_change = begin_change;
    }

    public String getCom_ename() {
        return com_ename;
    }

    public void setCom_ename(String com_ename) {
        this.com_ename = com_ename;
    }

    public String getCom_sname() {
        return com_sname;
    }

    public void setCom_sname(String com_sname) {
        this.com_sname = com_sname;
    }

    public String getReal_control() {
        return real_control;
    }

    public void setReal_control(String real_control) {
        this.real_control = real_control;
    }

    public String getLegal_rp() {
        return legal_rp;
    }

    public void setLegal_rp(String legal_rp) {
        this.legal_rp = legal_rp;
    }

    public String getBoard_chair_man() {
        return board_chair_man;
    }

    public void setBoard_chair_man(String board_chair_man) {
        this.board_chair_man = board_chair_man;
    }

    public String getGen_manager() {
        return gen_manager;
    }

    public void setGen_manager(String gen_manager) {
        this.gen_manager = gen_manager;
    }

    public String getSecretary() {
        return secretary;
    }

    public void setSecretary(String secretary) {
        this.secretary = secretary;
    }

    public String getTotal_staff() {
        return total_staff;
    }

    public void setTotal_staff(String total_staff) {
        this.total_staff = total_staff;
    }

    public Date getBuild_time() {
        return build_time;
    }

    public void setBuild_time(Date build_time) {
        this.build_time = build_time;
    }

    public String getUni_credit() {
        return uni_credit;
    }

    public void setUni_credit(String uni_credit) {
        this.uni_credit = uni_credit;
    }

    public String getCurrency_type() {
        return currency_type;
    }

    public void setCurrency_type(String currency_type) {
        this.currency_type = currency_type;
    }

    public String getRg_capital() {
        return rg_capital;
    }

    public void setRg_capital(String rg_capital) {
        this.rg_capital = rg_capital;
    }

    public String getNati_name() {
        return nati_name;
    }

    public void setNati_name(String nati_name) {
        this.nati_name = nati_name;
    }

    public String getProv_name() {
        return prov_name;
    }

    public void setProv_name(String prov_name) {
        this.prov_name = prov_name;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getRegist_add() {
        return regist_add;
    }

    public void setRegist_add(String regist_add) {
        this.regist_add = regist_add;
    }

    public String getOffice_add() {
        return office_add;
    }

    public void setOffice_add(String office_add) {
        this.office_add = office_add;
    }

    public String getOffice_postcode() {
        return office_postcode;
    }

    public void setOffice_postcode(String office_postcode) {
        this.office_postcode = office_postcode;
    }

    public String getTel_number() {
        return tel_number;
    }

    public void setTel_number(String tel_number) {
        this.tel_number = tel_number;
    }

    public String getContact_fax() {
        return contact_fax;
    }

    public void setContact_fax(String contact_fax) {
        this.contact_fax = contact_fax;
    }

    public String getContact_mail() {
        return contact_mail;
    }

    public void setContact_mail(String contact_mail) {
        this.contact_mail = contact_mail;
    }

    public String getCompany_website() {
        return company_website;
    }

    public void setCompany_website(String company_website) {
        this.company_website = company_website;
    }

    public String getAcc_firm() {
        return acc_firm;
    }

    public void setAcc_firm(String acc_firm) {
        this.acc_firm = acc_firm;
    }

    public String getLaw_firm() {
        return law_firm;
    }

    public void setLaw_firm(String law_firm) {
        this.law_firm = law_firm;
    }

    public String getAccountant() {
        return accountant;
    }

    public void setAccountant(String accountant) {
        this.accountant = accountant;
    }

    public String getLawyer() {
        return lawyer;
    }

    public void setLawyer(String lawyer) {
        this.lawyer = lawyer;
    }

    public String getRun_scope() {
        return run_scope;
    }

    public void setRun_scope(String run_scope) {
        this.run_scope = run_scope;
    }

    public String getCom_intro() {
        return com_intro;
    }

    public void setCom_intro(String com_intro) {
        this.com_intro = com_intro;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
