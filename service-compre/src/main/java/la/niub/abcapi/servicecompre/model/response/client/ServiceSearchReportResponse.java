package la.niub.abcapi.servicecompre.model.response.client;

import com.fasterxml.jackson.annotation.JsonInclude;
import la.niub.abcapi.servicecompre.model.response.client.report.ReportDataParsed;
import la.niub.abcapi.servicecompre.model.response.client.report.ReportItemResponse;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServiceSearchReportResponse {

    private List<ReportItemResponse> items = new ArrayList<>();

    private Integer current_page = 1;

    private Integer total_count = 0;

    private Integer total_page = 0;

    private String keyword = "";

    private String request_id = "";

    private Integer abcscore = 0;

    private String searchCompany;

    private String m_keyword = "";

    private String searchIndustry;

    private ReportDataParsed parsed;

    private List<String> source;

    public List<ReportItemResponse> getItems() {
        return items;
    }

    public void setItems(List<ReportItemResponse> items) {
        this.items = items;
    }

    public Integer getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(Integer current_page) {
        this.current_page = current_page;
    }

    public Integer getTotal_count() {
        return total_count;
    }

    public void setTotal_count(Integer total_count) {
        this.total_count = total_count;
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

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public Integer getAbcscore() {
        return abcscore;
    }

    public void setAbcscore(Integer abcscore) {
        this.abcscore = abcscore;
    }

    public String getSearchCompany() {
        return searchCompany;
    }

    public void setSearchCompany(String searchCompany) {
        this.searchCompany = searchCompany;
    }

    public String getM_keyword() {
        return m_keyword;
    }

    public void setM_keyword(String m_keyword) {
        this.m_keyword = m_keyword;
    }

    public String getSearchIndustry() {
        return searchIndustry;
    }

    public void setSearchIndustry(String searchIndustry) {
        this.searchIndustry = searchIndustry;
    }

    public ReportDataParsed getParsed() {
        return parsed;
    }

    public void setParsed(ReportDataParsed parsed) {
        this.parsed = parsed;
    }

    public List<String> getSource() {
        return source;
    }

    public void setSource(List<String> source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return "ServiceSearchReportResponse{" +
                "items=" + items +
                ", current_page=" + current_page +
                ", total_count=" + total_count +
                ", total_page=" + total_page +
                ", keyword='" + keyword + '\'' +
                ", request_id='" + request_id + '\'' +
                ", abcscore=" + abcscore +
                ", searchCompany='" + searchCompany + '\'' +
                ", m_keyword='" + m_keyword + '\'' +
                ", searchIndustry='" + searchIndustry + '\'' +
                ", parsed=" + parsed +
                ", source='" + source + '\'' +
                '}';
    }
}
