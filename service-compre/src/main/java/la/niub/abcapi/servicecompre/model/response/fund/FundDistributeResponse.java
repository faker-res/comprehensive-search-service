package la.niub.abcapi.servicecompre.model.response.fund;

import la.niub.abcapi.servicecompre.model.bo.fund.FundDistributeBO;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class FundDistributeResponse {

//    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date time;

    private List<FundDistributeBO> lastDistribute;

//    FundDistributeBO lastDistributeTotal;

    private Map<String,List<FundDistributeBO>> periodDistribute;

//    Map<String,FundDistributeBO> periodDistributeTotal;

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public List<FundDistributeBO> getLastDistribute() {
        return lastDistribute;
    }

    public void setLastDistribute(List<FundDistributeBO> lastDistribute) {
        this.lastDistribute = lastDistribute;
    }

//    public FundDistributeBO getLastDistributeTotal() {
//        return lastDistributeTotal;
//    }
//
//    public void setLastDistributeTotal(FundDistributeBO lastDistributeTotal) {
//        this.lastDistributeTotal = lastDistributeTotal;
//    }

    public Map<String, List<FundDistributeBO>> getPeriodDistribute() {
        return periodDistribute;
    }

    public void setPeriodDistribute(Map<String, List<FundDistributeBO>> periodDistribute) {
        this.periodDistribute = periodDistribute;
    }

//    public Map<String, FundDistributeBO> getPeriodDistributeTotal() {
//        return periodDistributeTotal;
//    }
//
//    public void setPeriodDistributeTotal(Map<String, FundDistributeBO> periodDistributeTotal) {
//        this.periodDistributeTotal = periodDistributeTotal;
//    }

    @Override
    public String toString() {
        return "FundDistributeResponse{" +
                "time='" + time + '\'' +
                ", lastDistribute=" + lastDistribute +
                ", periodDistribute=" + periodDistribute +
                '}';
    }
}
