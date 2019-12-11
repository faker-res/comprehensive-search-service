package la.niub.abcapi.servicecompre.model.response;

import java.util.List;

public class FundCompanyNewsResponse {
    private Long comId;

    private String orgName;

    private String orgSname;

    private List<FundCompanyNewsNewsItemResponse> news;

    private List<FundCompanyNewsNoticeItemResponse> notice;

    private List<FundCompanyNewsReportItemResponse> report;

    public Long getComId() {
        return comId;
    }

    public void setComId(Long comId) {
        this.comId = comId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgSname() {
        return orgSname;
    }

    public void setOrgSname(String orgSname) {
        this.orgSname = orgSname;
    }

    public List<FundCompanyNewsNewsItemResponse> getNews() {
        return news;
    }

    public void setNews(List<FundCompanyNewsNewsItemResponse> news) {
        this.news = news;
    }

    public List<FundCompanyNewsNoticeItemResponse> getNotice() {
        return notice;
    }

    public void setNotice(List<FundCompanyNewsNoticeItemResponse> notice) {
        this.notice = notice;
    }

    public List<FundCompanyNewsReportItemResponse> getReport() {
        return report;
    }

    public void setReport(List<FundCompanyNewsReportItemResponse> report) {
        this.report = report;
    }
}
