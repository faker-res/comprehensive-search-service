package la.niub.abcapi.servicecompre.model.bo;

import la.niub.abcapi.servicecompre.model.ComprehensiveSearchesTopModel;

import java.util.List;
import java.util.Map;

public class ComprehensiveBO {

    private ComprehensiveHotBO hot;

    private List<ComprehensiveSearchesTopModel> top;

    private ComprehensiveChartBO chart;

    private Map<String,ComprehensiveNoticeBO> notice;

    private ComprehensiveReportBO report;

    private ComprehensiveNewsBO news;

    public ComprehensiveHotBO getHot() {
        return hot;
    }

    public void setHot(ComprehensiveHotBO hot) {
        this.hot = hot;
    }

    public List<ComprehensiveSearchesTopModel> getTop() {
        return top;
    }

    public void setTop(List<ComprehensiveSearchesTopModel> top) {
        this.top = top;
    }

    public ComprehensiveChartBO getChart() {
        return chart;
    }

    public void setChart(ComprehensiveChartBO chart) {
        this.chart = chart;
    }

    public Map<String, ComprehensiveNoticeBO> getNotice() {
        return notice;
    }

    public void setNotice(Map<String, ComprehensiveNoticeBO> notice) {
        this.notice = notice;
    }

    public ComprehensiveReportBO getReport() {
        return report;
    }

    public void setReport(ComprehensiveReportBO report) {
        this.report = report;
    }

    public ComprehensiveNewsBO getNews() {
        return news;
    }

    public void setNews(ComprehensiveNewsBO news) {
        this.news = news;
    }

    @Override
    public String toString() {
        return "ComprehensiveBO{" +
                "hot=" + hot +
                ", top=" + top +
                ", chart=" + chart +
                ", notice=" + notice +
                ", report=" + report +
                ", news=" + news +
                '}';
    }
}
