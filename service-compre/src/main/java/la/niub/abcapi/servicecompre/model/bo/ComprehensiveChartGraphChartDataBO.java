package la.niub.abcapi.servicecompre.model.bo;

import java.util.List;
import java.util.Map;

public class ComprehensiveChartGraphChartDataBO {

    private Map<String,Object> AlgorithmCommitTime;

    private Map<String,Object> credits;

    private Map<String,Object> title;

    private Map<String,Object> xAxis;

    private List<Map<String,Object>> yAxis;

    private Map<String,Object> tooltip;

    private Map<String,Object> legend;

    private Map<String,Object> plotOptions;

    private List<Map<String,Object>> series;

    public Map<String, Object> getAlgorithmCommitTime() {
        return AlgorithmCommitTime;
    }

    public void setAlgorithmCommitTime(Map<String, Object> algorithmCommitTime) {
        AlgorithmCommitTime = algorithmCommitTime;
    }

    public Map<String, Object> getCredits() {
        return credits;
    }

    public void setCredits(Map<String, Object> credits) {
        this.credits = credits;
    }

    public Map<String, Object> getTitle() {
        return title;
    }

    public void setTitle(Map<String, Object> title) {
        this.title = title;
    }

    public Map<String, Object> getxAxis() {
        return xAxis;
    }

    public void setxAxis(Map<String, Object> xAxis) {
        this.xAxis = xAxis;
    }

    public List<Map<String, Object>> getyAxis() {
        return yAxis;
    }

    public void setyAxis(List<Map<String, Object>> yAxis) {
        this.yAxis = yAxis;
    }

    public Map<String, Object> getTooltip() {
        return tooltip;
    }

    public void setTooltip(Map<String, Object> tooltip) {
        this.tooltip = tooltip;
    }

    public Map<String, Object> getLegend() {
        return legend;
    }

    public void setLegend(Map<String, Object> legend) {
        this.legend = legend;
    }

    public Map<String, Object> getPlotOptions() {
        return plotOptions;
    }

    public void setPlotOptions(Map<String, Object> plotOptions) {
        this.plotOptions = plotOptions;
    }

    public List<Map<String, Object>> getSeries() {
        return series;
    }

    public void setSeries(List<Map<String, Object>> series) {
        this.series = series;
    }

    @Override
    public String toString() {
        return "ComprehensiveChartGraphChartDataBO{" +
                "AlgorithmCommitTime=" + AlgorithmCommitTime +
                ", credits=" + credits +
                ", title=" + title +
                ", xAxis=" + xAxis +
                ", yAxis=" + yAxis +
                ", tooltip=" + tooltip +
                ", legend=" + legend +
                ", plotOptions=" + plotOptions +
                ", series=" + series +
                '}';
    }
}
