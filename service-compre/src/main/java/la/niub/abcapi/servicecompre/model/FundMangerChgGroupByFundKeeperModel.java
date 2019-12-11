package la.niub.abcapi.servicecompre.model;

import java.util.Date;

public class FundMangerChgGroupByFundKeeperModel {

    private Long fund_keeper_code;

    private String plate_name;

    private int fund_manager_quantity;

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

    public int getFund_manager_quantity() {
        return fund_manager_quantity;
    }

    public void setFund_manager_quantity(int fund_manager_quantity) {
        this.fund_manager_quantity = fund_manager_quantity;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    //    fund_keeper_code, plate_name, fund_manager_quantity, max(end_date) as end_date
}
