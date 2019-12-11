package la.niub.abcapi.servicecompre.model.response;

import java.util.Map;

public class FundCompanyLawsuitColumnItemResponse {

    private String name;

    private Map<Integer, Integer> data;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Integer, Integer> getData() {
        return data;
    }

    public void setData(Map<Integer, Integer> data) {
        this.data = data;
    }
}
