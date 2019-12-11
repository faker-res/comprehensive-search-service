package la.niub.abcapi.servicecompre.model;

import java.math.BigDecimal;

public class FundManagerStarModel {

    private Long peo_uni_code;

    private String fund_manager_name;

    private String oss_path;

    private String org_sname;

    private BigDecimal fund_manage_nav;

//    private String fund_news;

    public Long getPeo_uni_code() {
        return peo_uni_code;
    }

    public void setPeo_uni_code(Long peo_uni_code) {
        this.peo_uni_code = peo_uni_code;
    }

    public String getFund_manager_name() {
        return fund_manager_name;
    }

    public void setFund_manager_name(String fund_manager_name) {
        this.fund_manager_name = fund_manager_name;
    }

    public String getOss_path() {
        return oss_path;
    }

    public void setOss_path(String oss_path) {
        this.oss_path = oss_path;
    }

    public String getOrg_sname() {
        return org_sname;
    }

    public void setOrg_sname(String org_sname) {
        this.org_sname = org_sname;
    }

    public BigDecimal getFund_manage_nav() {
        return fund_manage_nav;
    }

    public void setFund_manage_nav(BigDecimal fund_manage_nav) {
        this.fund_manage_nav = fund_manage_nav;
    }

//    public String getFund_news() {
//        return fund_news;
//    }
//
//    public void setFund_news(String fund_news) {
//        this.fund_news = fund_news;
//    }

    public FundManagerStarModel(Long peo_uni_code, String fund_manager_name, String oss_path, String org_sname, BigDecimal fund_manage_nav/*, String fund_news*/) {
        this.peo_uni_code = peo_uni_code;
        this.fund_manager_name = fund_manager_name;
        this.oss_path = oss_path;
        this.org_sname = org_sname;
        this.fund_manage_nav = fund_manage_nav;
//        this.fund_news = fund_news;
    }

    public FundManagerStarModel() {
    }

    @Override
    public String toString() {
        return "FundManagerStarModel{" +
                "peo_uni_code='" + peo_uni_code + '\'' +
                ", fund_manager_name='" + fund_manager_name + '\'' +
                ", oss_path='" + oss_path + '\'' +
                ", org_sname='" + org_sname + '\'' +
                ", fund_manage_nav=" + fund_manage_nav + '\'' +
//                ", fund_news=" + fund_news +
                '}';
    }
}
