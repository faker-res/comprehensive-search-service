package la.niub.abcapi.servicecompre.model.bo.line;

import java.util.Map;

public class KlineBO {

    private Map<String,KlineCompanyBO> company;

    public Map<String, KlineCompanyBO> getCompany() {
        return company;
    }

    public void setCompany(Map<String, KlineCompanyBO> company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "KlineBO{" +
                "company=" + company +
                '}';
    }
}
