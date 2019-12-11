package la.niub.abcapi.servicecompre.model.response.fund;

import java.math.BigDecimal;
import java.util.Date;

public class FundWorthResponse {

    private Date time;

    private BigDecimal num;

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public BigDecimal getNum() {
        return num;
    }

    public void setNum(BigDecimal num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "FundWorthResponse{" +
                "time=" + time +
                ", num=" + num +
                '}';
    }
}
