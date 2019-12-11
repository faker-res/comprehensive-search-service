package la.niub.abcapi.servicecompre.model.response.fund;

import la.niub.abcapi.servicecompre.model.bo.fund.FundCompanyHolderBO;

import java.util.List;

public class FundCompanyHolderResponse {

    private List<FundCompanyHolderBO> holder;

    public List<FundCompanyHolderBO> getHolder() {
        return holder;
    }

    public void setHolder(List<FundCompanyHolderBO> holder) {
        this.holder = holder;
    }

    @Override
    public String toString() {
        return "FundCompanyHolderResponse{" +
                "holder=" + holder +
                '}';
    }
}
