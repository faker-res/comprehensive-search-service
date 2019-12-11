package la.niub.abcapi.servicecompre.model.response;

public class FundCompanyKpiItemResponse {
    private int managerCount;

    private int fundCount;

    private float fundMoney;

    public int getManagerCount() {
        return managerCount;
    }

    public void setManagerCount(int managerCount) {
        this.managerCount = managerCount;
    }

    public int getFundCount() {
        return fundCount;
    }

    public void setFundCount(int fundCount) {
        this.fundCount = fundCount;
    }

    public float getFundMoney() {
        return fundMoney;
    }

    public void setFundMoney(float fundMoney) {
        this.fundMoney = fundMoney;
    }
}
