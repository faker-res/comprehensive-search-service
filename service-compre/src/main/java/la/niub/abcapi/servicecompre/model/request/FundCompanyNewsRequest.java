package la.niub.abcapi.servicecompre.model.request;

public class FundCompanyNewsRequest {

    private String user_id;

    private Long comId;

    private int limitNews = 6;

    private int limitNotice = 6;

    private int limitReport = 5;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public Long getComId() {
        return comId;
    }

    public void setComId(Long comId) {
        this.comId = comId;
    }

    public int getLimitNews() {
        return limitNews;
    }

    public void setLimitNews(int limitNews) {
        this.limitNews = limitNews;
    }

    public int getLimitNotice() {
        return limitNotice;
    }

    public void setLimitNotice(int limitNotice) {
        this.limitNotice = limitNotice;
    }

    public int getLimitReport() {
        return limitReport;
    }

    public void setLimitReport(int limitReport) {
        this.limitReport = limitReport;
    }
}
