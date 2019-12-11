package la.niub.abcapi.servicecompre.model.response.fund;

import la.niub.abcapi.servicecompre.model.bo.fund.FundTopInvestBO;

import java.util.List;

public class FundTopInvestResponse {

    private List<FundTopInvestBO> list;

    public List<FundTopInvestBO> getList() {
        return list;
    }

    public void setList(List<FundTopInvestBO> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "FundTopInvestResponse{" +
                "list=" + list +
                '}';
    }
}
