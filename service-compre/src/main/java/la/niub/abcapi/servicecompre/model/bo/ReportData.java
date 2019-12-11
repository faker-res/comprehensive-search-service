package la.niub.abcapi.servicecompre.model.bo;

import la.niub.abcapi.servicecompre.model.response.client.Search2Option;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class ReportData implements Serializable {

    private List<ReportItem> item;

    private Integer total_count;

    private String request_id;

    private String nlp_str;

    private String searchCompany;

    private String searchIndustry;

    private Integer abcscore;

    private List<String> source;

    private ReportDataParsed parsed;

    private Map<String,String> new_count;

    private Integer current_page;

    private Integer total_page;

    private String keyword;

    private List<Search2Option> option;

    public List<ReportItem> getItem() {
        return item;
    }

    public void setItem(List<ReportItem> item) {
        this.item = item;
    }

    public Integer getTotal_count() {
        return total_count;
    }

    public void setTotal_count(Integer total_count) {
        this.total_count = total_count;
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

    public Integer getAbcscore() {
        return abcscore;
    }

    public void setAbcscore(Integer abcscore) {
        this.abcscore = abcscore;
    }

    public List<String> getSource() {
        return source;
    }

    public void setSource(List<String> source) {
        this.source = source;
    }

    public ReportDataParsed getParsed() {
        return parsed;
    }

    public void setParsed(ReportDataParsed parsed) {
        this.parsed = parsed;
    }

    public Map<String, String> getNew_count() {
        return new_count;
    }

    public void setNew_count(Map<String, String> new_count) {
        this.new_count = new_count;
    }

    public Integer getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(Integer current_page) {
        this.current_page = current_page;
    }

    public Integer getTotal_page() {
        return total_page;
    }

    public void setTotal_page(Integer total_page) {
        this.total_page = total_page;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public List<Search2Option> getOption() {
        return option;
    }

    public void setOption(List<Search2Option> option) {
        this.option = option;
    }

    @Override
    public String toString() {
        return "ReportData{" +
                "item=" + item +
                ", total_count=" + total_count +
                ", request_id='" + request_id + '\'' +
                ", nlp_str='" + nlp_str + '\'' +
                ", searchCompany='" + searchCompany + '\'' +
                ", searchIndustry='" + searchIndustry + '\'' +
                ", abcscore=" + abcscore +
                ", source=" + source +
                ", parsed=" + parsed +
                ", new_count=" + new_count +
                ", current_page=" + current_page +
                ", total_page=" + total_page +
                ", keyword='" + keyword + '\'' +
                '}';
    }
}
