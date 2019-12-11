package la.niub.abcapi.servicecompre.model.bo.financevip;

import la.niub.abcapi.servicecompre.model.FinancialUserModel;

public class FinanceVipByTagBO {

    private Integer similarity;

    private FinancialUserModel financialUserModel;

    public Integer getSimilarity() {
        return similarity;
    }

    public void setSimilarity(Integer similarity) {
        this.similarity = similarity;
    }

    public FinancialUserModel getFinancialUserModel() {
        return financialUserModel;
    }

    public void setFinancialUserModel(FinancialUserModel financialUserModel) {
        this.financialUserModel = financialUserModel;
    }
}
