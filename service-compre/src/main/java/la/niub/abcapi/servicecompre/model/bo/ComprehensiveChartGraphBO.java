package la.niub.abcapi.servicecompre.model.bo;

import la.niub.abcapi.servicecompre.model.HiborModel;

public class ComprehensiveChartGraphBO {

    private HiborModel report;

    private String link;

    private Integer table_num;

    private Integer chart_num;

    private ComprehensiveChartGraphChartBO chart_data;

    public HiborModel getReport() {
        return report;
    }

    public void setReport(HiborModel report) {
        this.report = report;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Integer getTable_num() {
        return table_num;
    }

    public void setTable_num(Integer table_num) {
        this.table_num = table_num;
    }

    public Integer getChart_num() {
        return chart_num;
    }

    public void setChart_num(Integer chart_num) {
        this.chart_num = chart_num;
    }

    public ComprehensiveChartGraphChartBO getChart_data() {
        return chart_data;
    }

    public void setChart_data(ComprehensiveChartGraphChartBO chart_data) {
        this.chart_data = chart_data;
    }

    @Override
    public String toString() {
        return "ComprehensiveChartGraphBO{" +
                "report=" + report +
                ", link='" + link + '\'' +
                ", table_num=" + table_num +
                ", chart_num=" + chart_num +
                ", chart_data=" + chart_data +
                '}';
    }
}
