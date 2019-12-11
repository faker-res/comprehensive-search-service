package la.niub.abcapi.servicecompre.model.bo.line;

import java.util.Map;

public class TimelineBO {

    private Map<String,TimelineCompanyBO> company;

    public Map<String, TimelineCompanyBO> getCompany() {
        return company;
    }

    public void setCompany(Map<String, TimelineCompanyBO> company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "TimelineBO{" +
                "company=" + company +
                '}';
    }
}
