package la.niub.abcapi.servicecompre.model.response;

import la.niub.abcapi.servicecompre.model.response.client.report.ReportAanlystHonorData;

import java.util.List;

public class FundCompanyNewsReportItemResponse {

    private int id;

    private String title;

    private String url;

    private String category;

    private String report_type;

    private int time;

    private List<ReportAanlystHonorData> analyst_honor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getReport_type() {
        return report_type;
    }

    public void setReport_type(String report_type) {
        this.report_type = report_type;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public List<ReportAanlystHonorData> getAnalyst_honor() {
        return analyst_honor;
    }

    public void setAnalyst_honor(List<ReportAanlystHonorData> analyst_honor) {
        this.analyst_honor = analyst_honor;
    }
}
