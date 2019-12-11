package la.niub.abcapi.servicecompre.model.response.fund;

import java.util.Date;

public class FundOtherOpinionResponse {

    private Date time;

    private Double num;

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Double getNum() {
        return num;
    }

    public void setNum(Double num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "FundOtherHeatBO{" +
                "time=" + time +
                ", num=" + num +
                '}';
    }
}
