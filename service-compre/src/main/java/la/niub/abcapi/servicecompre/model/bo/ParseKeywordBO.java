package la.niub.abcapi.servicecompre.model.bo;

import java.util.Set;

public class ParseKeywordBO {

    private String searchCompany;

    private String searchIndustry;

    private ReportDataParsed parsed;

    private Set<String> trim;

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

    public ReportDataParsed getParsed() {
        return parsed;
    }

    public void setParsed(ReportDataParsed parsed) {
        this.parsed = parsed;
    }

    public Set<String> getTrim() {
        return trim;
    }

    public void setTrim(Set<String> trim) {
        this.trim = trim;
    }

    @Override
    public String toString() {
        return "ParseKeywordBO{" +
                "searchCompany='" + searchCompany + '\'' +
                ", searchIndustry='" + searchIndustry + '\'' +
                ", parsed=" + parsed +
                ", trim=" + trim +
                '}';
    }
}
