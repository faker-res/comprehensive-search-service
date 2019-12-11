package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;

public class ComprehensiveSearchesReportRecommendModel implements Serializable {
    private Integer id;

    private Integer report_id;

    private String charts_id;

    private static final long serialVersionUID = 1L;

    public ComprehensiveSearchesReportRecommendModel(Integer id, Integer report_id, String charts_id) {
        this.id = id;
        this.report_id = report_id;
        this.charts_id = charts_id;
    }

    public ComprehensiveSearchesReportRecommendModel() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getReport_id() {
        return report_id;
    }

    public void setReport_id(Integer report_id) {
        this.report_id = report_id;
    }

    public String getCharts_id() {
        return charts_id;
    }

    public void setCharts_id(String charts_id) {
        this.charts_id = charts_id == null ? null : charts_id.trim();
    }

    @Override
    public String toString() {
        return "ComprehensiveSearchesReportRecommendModel{" +
                "id=" + id +
                ", report_id='" + report_id + '\'' +
                ", charts_id='" + charts_id + '\'' +
                '}';
    }
}