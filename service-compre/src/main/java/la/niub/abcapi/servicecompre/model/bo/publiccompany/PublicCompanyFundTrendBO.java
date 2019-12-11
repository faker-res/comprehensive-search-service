package la.niub.abcapi.servicecompre.model.bo.publiccompany;

import java.math.BigDecimal;
import java.util.Date;

public class PublicCompanyFundTrendBO {

    private Date time;

    private BigDecimal amount;

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "PublicCompanyFundTrendBO{" +
                "time=" + time +
                ", amount=" + amount +
                '}';
    }
}
