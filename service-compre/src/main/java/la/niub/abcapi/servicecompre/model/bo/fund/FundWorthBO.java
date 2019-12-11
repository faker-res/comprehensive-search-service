package la.niub.abcapi.servicecompre.model.bo.fund;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class FundWorthBO {

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date date;

    private Date time;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

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
        return "FundWorthBO{" +
                "date=" + date +
                ", time=" + time +
                ", num=" + num +
                '}';
    }
}
