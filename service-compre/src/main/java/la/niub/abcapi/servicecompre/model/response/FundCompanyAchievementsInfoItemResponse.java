package la.niub.abcapi.servicecompre.model.response;

import java.math.BigDecimal;

public class FundCompanyAchievementsInfoItemResponse {
    private Integer key;
    private Long com_uni_code;
    private String org_name;
    private Integer fund_quantity;
    private Integer fund_manager_quantity;
    private BigDecimal fund_nav;
    private String fund_quantity_ranking;
    private String fund_manager_quantity_ranking;
    private String fund_nav_ranking;

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public Long getCom_uni_code() {
        return com_uni_code;
    }

    public void setCom_uni_code(Long com_uni_code) {
        this.com_uni_code = com_uni_code;
    }

    public String getOrg_name() {
        return org_name;
    }

    public void setOrg_name(String org_name) {
        this.org_name = org_name;
    }

    public Integer getFund_quantity() {
        return fund_quantity;
    }

    public void setFund_quantity(Integer fund_quantity) {
        this.fund_quantity = fund_quantity;
    }

    public Integer getFund_manager_quantity() {
        return fund_manager_quantity;
    }

    public void setFund_manager_quantity(Integer fund_manager_quantity) {
        this.fund_manager_quantity = fund_manager_quantity;
    }

    public BigDecimal getFund_nav() {
        return fund_nav;
    }

    public void setFund_nav(BigDecimal fund_nav) {
        this.fund_nav = fund_nav;
    }

    public String getFund_quantity_ranking() {
        return fund_quantity_ranking;
    }

    public void setFund_quantity_ranking(String fund_quantity_ranking) {
        this.fund_quantity_ranking = fund_quantity_ranking;
    }

    public String getFund_manager_quantity_ranking() {
        return fund_manager_quantity_ranking;
    }

    public void setFund_manager_quantity_ranking(String fund_manager_quantity_ranking) {
        this.fund_manager_quantity_ranking = fund_manager_quantity_ranking;
    }

    public String getFund_nav_ranking() {
        return fund_nav_ranking;
    }

    public void setFund_nav_ranking(String fund_nav_ranking) {
        this.fund_nav_ranking = fund_nav_ranking;
    }
}
