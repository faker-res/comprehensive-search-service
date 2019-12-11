package la.niub.abcapi.servicecompre.model;

import java.math.BigDecimal;
import java.util.Date;

public class FundKeeperScaleGroupByFundKeeperModel {

    private Long fund_keeper_code;

    private String plate_name;

    private int fund_quantity;

    private BigDecimal fund_share;

    private Date end_date;

    public Long getFund_keeper_code() {
        return fund_keeper_code;
    }

    public void setFund_keeper_code(Long fund_keeper_code) {
        this.fund_keeper_code = fund_keeper_code;
    }

    public String getPlate_name() {
        return plate_name;
    }

    public void setPlate_name(String plate_name) {
        this.plate_name = plate_name;
    }

    public int getFund_quantity() {
        return fund_quantity;
    }

    public void setFund_quantity(int fund_quantity) {
        this.fund_quantity = fund_quantity;
    }

    public BigDecimal getFund_share() {
        return fund_share;
    }

    public void setFund_share(BigDecimal fund_share) {
        this.fund_share = fund_share;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }
}
