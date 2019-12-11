package la.niub.abcapi.servicecompre.model.response.client.datachart;

import la.niub.abcapi.servicecompre.model.response.client.Search2Option;

import java.io.Serializable;
import java.util.List;

public class DataChartData implements Serializable {

    private List<DataChartItem> item;

    private Integer total_count;

    private String highlight_fields;

    private String request_id;

    private String nlp_str;

    private String searchCompany;

    private String searchIndustry;

    private List<Search2Option> option;

    public List<DataChartItem> getItem() {
        return item;
    }

    public void setItem(List<DataChartItem> item) {
        this.item = item;
    }

    public Integer getTotal_count() {
        return total_count;
    }

    public void setTotal_count(Integer total_count) {
        this.total_count = total_count;
    }

    public String getHighlight_fields() {
        return highlight_fields;
    }

    public void setHighlight_fields(String highlight_fields) {
        this.highlight_fields = highlight_fields;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public String getNlp_str() {
        return nlp_str;
    }

    public void setNlp_str(String nlp_str) {
        this.nlp_str = nlp_str;
    }

    public String getSearchCompany() {
        return searchCompany;
    }

    public void setSearchCompany(String searchCompany) {
        this.searchCompany = searchCompany;
    }

    public String getSearchIndustry() {
        return searchIndustry;
    }

    public void setSearchIndustry(String searchIndustry) {
        this.searchIndustry = searchIndustry;
    }

    public List<Search2Option> getOption() {
        return option;
    }

    public void setOption(List<Search2Option> option) {
        this.option = option;
    }

    @Override
    public String toString() {
        return "DataChartData{" +
                "item=" + item +
                ", total_count=" + total_count +
                ", highlight_fields='" + highlight_fields + '\'' +
                ", request_id='" + request_id + '\'' +
                ", nlp_str='" + nlp_str + '\'' +
                ", searchCompany='" + searchCompany + '\'' +
                ", searchIndustry='" + searchIndustry + '\'' +
                ", option=" + option +
                '}';
    }
}