package la.niub.abcapi.servicecompre.model.bo.fund;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class FundCompanyDetailBO {

    //公司名称
    private String company_name;

    //公司英文名称
    private String company_en_name;

    //公司logo
    private String image;

    //公司简介
    private String brif;

    //总经理名称
    private String boss_name;

    //公司性质
    private String company_nature;

    //管理金额规模
    private BigDecimal company_scale;

    //基金规模排名
    private Integer rank;

    //成立时间
    private Date establish_time;

    //官网
    private String url;

    //经理人数
    private Integer manager_num;

    //高层人员列表
    private List<FundManagerBO> boss_list;

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCompany_en_name() {
        return company_en_name;
    }

    public void setCompany_en_name(String company_en_name) {
        this.company_en_name = company_en_name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBrif() {
        return brif;
    }

    public void setBrif(String brif) {
        this.brif = brif;
    }

    public String getBoss_name() {
        return boss_name;
    }

    public void setBoss_name(String boss_name) {
        this.boss_name = boss_name;
    }

    public String getCompany_nature() {
        return company_nature;
    }

    public void setCompany_nature(String company_nature) {
        this.company_nature = company_nature;
    }

    public BigDecimal getCompany_scale() {
        return company_scale;
    }

    public void setCompany_scale(BigDecimal company_scale) {
        this.company_scale = company_scale;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Date getEstablish_time() {
        return establish_time;
    }

    public void setEstablish_time(Date establish_time) {
        this.establish_time = establish_time;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getManager_num() {
        return manager_num;
    }

    public void setManager_num(Integer manager_num) {
        this.manager_num = manager_num;
    }

    public List<FundManagerBO> getBoss_list() {
        return boss_list;
    }

    public void setBoss_list(List<FundManagerBO> boss_list) {
        this.boss_list = boss_list;
    }

    @Override
    public String toString() {
        return "FundCompanyDetailBO{" +
                "company_name='" + company_name + '\'' +
                ", company_en_name='" + company_en_name + '\'' +
                ", image='" + image + '\'' +
                ", brif='" + brif + '\'' +
                ", boss_name='" + boss_name + '\'' +
                ", company_nature='" + company_nature + '\'' +
                ", company_scale=" + company_scale +
                ", rank=" + rank +
                ", establish_time=" + establish_time +
                ", url='" + url + '\'' +
                ", manager_num=" + manager_num +
                ", boss_list=" + boss_list +
                '}';
    }
}
