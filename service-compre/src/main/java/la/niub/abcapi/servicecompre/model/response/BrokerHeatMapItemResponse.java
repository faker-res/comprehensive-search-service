package la.niub.abcapi.servicecompre.model.response;

public class BrokerHeatMapItemResponse {
    private String industryCode;

    private String industryName;

    private int reportCount;

    private float industryPerformance;

    private float moneyFlow;

    public String getIndustryCode() {
        return industryCode;
    }

    public void setIndustryCode(String industryCode) {
        this.industryCode = industryCode;
    }

    public String getIndustryName() {
        return industryName;
    }

    public void setIndustryName(String industryName) {
        this.industryName = industryName;
    }

    public int getReportCount() {
        return reportCount;
    }

    public void setReportCount(int reportCount) {
        this.reportCount = reportCount;
    }

    public float getIndustryPerformance() {
        return industryPerformance;
    }

    public void setIndustryPerformance(float industryPerformance) {
        this.industryPerformance = industryPerformance;
    }

    public float getMoneyFlow() {
        return moneyFlow;
    }

    public void setMoneyFlow(float moneyFlow) {
        this.moneyFlow = moneyFlow;
    }
}
