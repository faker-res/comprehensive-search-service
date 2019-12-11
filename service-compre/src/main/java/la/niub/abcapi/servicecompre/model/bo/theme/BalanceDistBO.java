package la.niub.abcapi.servicecompre.model.bo.theme;

import java.math.BigDecimal;
import java.util.Date;

public class BalanceDistBO {

    Long com_uni_code;

    BigDecimal roe_weigh;

    BigDecimal debt_assets;

    Date end_date;

    public BalanceDistBO() {
    }

    public BalanceDistBO(Long com_uni_code, BigDecimal roe_weigh, BigDecimal debt_assets, Date end_date) {
        this.com_uni_code = com_uni_code;
        this.roe_weigh = roe_weigh;
        this.debt_assets = debt_assets;
        this.end_date = end_date;
    }

    public Long getCom_uni_code() {
        return com_uni_code;
    }

    public void setCom_uni_code(Long com_uni_code) {
        this.com_uni_code = com_uni_code;
    }

    public BigDecimal getRoe_weigh() {
        return roe_weigh;
    }

    public void setRoe_weigh(BigDecimal roe_weigh) {
        this.roe_weigh = roe_weigh;
    }

    public BigDecimal getDebt_assets() {
        return debt_assets;
    }

    public void setDebt_assets(BigDecimal debt_assets) {
        this.debt_assets = debt_assets;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }
}
