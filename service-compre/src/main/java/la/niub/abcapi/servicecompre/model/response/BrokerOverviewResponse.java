package la.niub.abcapi.servicecompre.model.response;

public class BrokerOverviewResponse {

    private String orgName;

    private String orgLogo;

    private int orgAnalystCount;

    private int orgReportCount;

    private int orgDirectionCount;

    private int orgHonorCount;

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgLogo() {
        return orgLogo;
    }

    public void setOrgLogo(String orgLogo) {
        this.orgLogo = orgLogo;
    }


    public int getOrgAnalystCount() {
        return orgAnalystCount;
    }

    public void setOrgAnalystCount(int orgAnalystCount) {
        this.orgAnalystCount = orgAnalystCount;
    }

    public int getOrgReportCount() {
        return orgReportCount;
    }

    public void setOrgReportCount(int orgReportCount) {
        this.orgReportCount = orgReportCount;
    }

    public int getOrgDirectionCount() {
        return orgDirectionCount;
    }

    public void setOrgDirectionCount(int orgDirectionCount) {
        this.orgDirectionCount = orgDirectionCount;
    }

    public int getOrgHonorCount() {
        return orgHonorCount;
    }

    public void setOrgHonorCount(int orgHonorCount) {
        this.orgHonorCount = orgHonorCount;
    }


}
