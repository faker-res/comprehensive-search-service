package la.niub.abcapi.servicecompre.model.bo.fund;

import java.util.List;

public class FundLastDistributeBO {

    private List<FundDistributeBO> distributes;

    private FundDistributeBO distributeTotal;

    public List<FundDistributeBO> getDistributes() {
        return distributes;
    }

    public void setDistributes(List<FundDistributeBO> distributes) {
        this.distributes = distributes;
    }

    public FundDistributeBO getDistributeTotal() {
        return distributeTotal;
    }

    public void setDistributeTotal(FundDistributeBO distributeTotal) {
        this.distributeTotal = distributeTotal;
    }

    @Override
    public String toString() {
        return "FundLastDistributeBO{" +
                "distributes=" + distributes +
                ", distributeTotal=" + distributeTotal +
                '}';
    }
}
