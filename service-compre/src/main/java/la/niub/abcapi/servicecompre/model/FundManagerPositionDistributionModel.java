package la.niub.abcapi.servicecompre.model;

import java.math.BigDecimal;

public class FundManagerPositionDistributionModel {

    private Long peo_uni_code;

    private String fund_manager_name;

    private Long sec_uni_code;

    private Long sec_code;

    private String sec_name;

    private BigDecimal tot_fund_nav;

//    private List<FundManagerStockModel> fundManagerStockModelList;

    public FundManagerPositionDistributionModel() {
    }

    public FundManagerPositionDistributionModel(Long peo_uni_code, String fund_manager_name, Long sec_uni_code, Long sec_code, String sec_name, BigDecimal tot_fund_nav) {
        this.peo_uni_code = peo_uni_code;
        this.fund_manager_name = fund_manager_name;
        this.sec_uni_code = sec_uni_code;
        this.sec_code = sec_code;
        this.sec_name = sec_name;
        this.tot_fund_nav = tot_fund_nav;
    }

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

    public Long getSec_uni_code() {
        return sec_uni_code;
    }

    public void setSec_uni_code(Long sec_uni_code) {
        this.sec_uni_code = sec_uni_code;
    }

    public Long getSec_code() {
        return sec_code;
    }

    public void setSec_code(Long sec_code) {
        this.sec_code = sec_code;
    }

    public String getSec_name() {
        return sec_name;
    }

    public void setSec_name(String sec_name) {
        this.sec_name = sec_name;
    }

    public BigDecimal getTot_fund_nav() {
        return tot_fund_nav;
    }

    public void setTot_fund_nav(BigDecimal tot_fund_nav) {
        this.tot_fund_nav = tot_fund_nav;
    }

//    public List<FundManagerStockModel> getFundManagerStockModelList() {
//        return fundManagerStockModelList;
//    }
//
//    public void setFundManagerStockModelList(List<FundManagerStockModel> fundManagerStockModelList) {
//        this.fundManagerStockModelList = fundManagerStockModelList;
//    }

    @Override
    public String toString() {
        return "FundManagerPositionDistributionModel{" +
                "peo_uni_code=" + peo_uni_code +
                ", fund_manager_name='" + fund_manager_name + '\'' +
                ", sec_uni_code=" + sec_uni_code +
                ", sec_code=" + sec_code +
                ", sec_name='" + sec_name + '\'' +
                ", tot_fund_nav=" + tot_fund_nav +
//                ", fundManagerStockModelList=" + fundManagerStockModelList +
                '}';
    }
}
