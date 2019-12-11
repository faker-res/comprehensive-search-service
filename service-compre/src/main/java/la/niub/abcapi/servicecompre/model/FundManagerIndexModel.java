package la.niub.abcapi.servicecompre.model;

import java.math.BigDecimal;
import java.util.Date;

public class FundManagerIndexModel {

    private Long peo_uni_code;

    private String fund_manager_name;

    private Date end_date;

    private BigDecimal tenure_avg_annual_yield_index;

    private BigDecimal hs_300_index_yield;

    private BigDecimal csi_universal_index_yield;

    public FundManagerIndexModel() {
    }

    public FundManagerIndexModel(Long peo_uni_code, String fund_manager_name, Date end_date, BigDecimal tenure_avg_annual_yield_index, BigDecimal hs_300_index_yield, BigDecimal csi_universal_index_yield) {
        this.peo_uni_code = peo_uni_code;
        this.fund_manager_name = fund_manager_name;
        this.end_date = end_date;
        this.tenure_avg_annual_yield_index = tenure_avg_annual_yield_index;
        this.hs_300_index_yield = hs_300_index_yield;
        this.csi_universal_index_yield = csi_universal_index_yield;
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

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public BigDecimal getTenure_avg_annual_yield_index() {
        return tenure_avg_annual_yield_index;
    }

    public void setTenure_avg_annual_yield_index(BigDecimal tenure_avg_annual_yield_index) {
        this.tenure_avg_annual_yield_index = tenure_avg_annual_yield_index;
    }

    public BigDecimal getHs_300_index_yield() {
        return hs_300_index_yield;
    }

    public void setHs_300_index_yield(BigDecimal hs_300_index_yield) {
        this.hs_300_index_yield = hs_300_index_yield;
    }

    public BigDecimal getCsi_universal_index_yield() {
        return csi_universal_index_yield;
    }

    public void setCsi_universal_index_yield(BigDecimal csi_universal_index_yield) {
        this.csi_universal_index_yield = csi_universal_index_yield;
    }

    @Override
    public String toString() {
        return "FundManagerIndexModel{" +
                "peo_uni_code=" + peo_uni_code +
                ", fund_manager_name='" + fund_manager_name + '\'' +
                ", end_date=" + end_date +
                ", tenure_avg_annual_yield_index=" + tenure_avg_annual_yield_index +
                ", hs_300_index_yield=" + hs_300_index_yield +
                ", csi_universal_index_yield=" + csi_universal_index_yield +
                '}';
    }
}
