package la.niub.abcapi.servicecompre.model.bo;

import com.fasterxml.jackson.annotation.JsonInclude;
import la.niub.abcapi.servicecompre.model.ComprehensiveSearchesHotTipsModel;
import la.niub.abcapi.servicecompre.model.response.client.news.BestNewsData;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ComprehensiveHotBO {

    private List<ComprehensiveSearchesHotTipsModel> chart;

    private List<ComprehensiveSearchesHotTipsModel> table;

    private List<ComprehensiveSearchesHotTipsModel> notice;

    private List<ComprehensiveSearchesHotTipsModel> report;

    private List<BestNewsData> news;

    public List<ComprehensiveSearchesHotTipsModel> getChart() {
        return chart;
    }

    public void setChart(List<ComprehensiveSearchesHotTipsModel> chart) {
        this.chart = chart;
    }

    public List<ComprehensiveSearchesHotTipsModel> getTable() {
        return table;
    }

    public void setTable(List<ComprehensiveSearchesHotTipsModel> table) {
        this.table = table;
    }

    public List<ComprehensiveSearchesHotTipsModel> getNotice() {
        return notice;
    }

    public void setNotice(List<ComprehensiveSearchesHotTipsModel> notice) {
        this.notice = notice;
    }

    public List<ComprehensiveSearchesHotTipsModel> getReport() {
        return report;
    }

    public void setReport(List<ComprehensiveSearchesHotTipsModel> report) {
        this.report = report;
    }

    public List<BestNewsData> getNews() {
        return news;
    }

    public void setNews(List<BestNewsData> news) {
        this.news = news;
    }

    @Override
    public String toString() {
        return "ComprehensiveHotBO{" +
                "chart=" + chart +
                ", table=" + table +
                ", notice=" + notice +
                ", report=" + report +
                ", news=" + news +
                '}';
    }
}
