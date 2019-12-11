package la.niub.abcapi.servicecompre.model.response;

public class FundCompanyLawsuitListItemResponse {
    private String judgeTime;

    private String title;

    private String url;

    private String caseNo;

    private String info;

    private String court;

    private String caseType;

    public String getJudgeTime() {
        return judgeTime;
    }

    public void setJudgeTime(String judgeTime) {
        this.judgeTime = judgeTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getCourt() {
        return court;
    }

    public void setCourt(String court) {
        this.court = court;
    }

    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }
}
