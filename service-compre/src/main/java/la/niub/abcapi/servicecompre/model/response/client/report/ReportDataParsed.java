package la.niub.abcapi.servicecompre.model.response.client.report;

import java.io.Serializable;
import java.util.Map;

public class ReportDataParsed implements Serializable {

    private Map<String,String> timeMap;

    private Map<String,String> sourceMap;

    private Map<String,String> category_map;

    private Map<String,String> industry_map;

    private Map<String,String> company_map;

    public Map<String, String> getTimeMap() {
        return timeMap;
    }

    public void setTimeMap(Map<String, String> timeMap) {
        this.timeMap = timeMap;
    }

    public Map<String, String> getSourceMap() {
        return sourceMap;
    }

    public void setSourceMap(Map<String, String> sourceMap) {
        this.sourceMap = sourceMap;
    }

    public Map<String, String> getCategory_map() {
        return category_map;
    }

    public void setCategory_map(Map<String, String> category_map) {
        this.category_map = category_map;
    }

    public Map<String, String> getIndustry_map() {
        return industry_map;
    }

    public void setIndustry_map(Map<String, String> industry_map) {
        this.industry_map = industry_map;
    }

    public Map<String, String> getCompany_map() {
        return company_map;
    }

    public void setCompany_map(Map<String, String> company_map) {
        this.company_map = company_map;
    }

    @Override
    public String toString() {
        return "ReportDataParsed{" +
                "timeMap=" + timeMap +
                ", sourceMap=" + sourceMap +
                ", category_map=" + category_map +
                ", industry_map=" + industry_map +
                ", company_map=" + company_map +
                '}';
    }
}
