package la.niub.abcapi.servicecompre.model.response;

import java.util.List;

public class FundCompanyLawsuitResponse {

    private List<String> caseTypes;

    private List<FundCompanyLawsuitListItemResponse> lawsuitList;

    private List<FundCompanyLawsuitColumnItemResponse> column;

    public List<String> getCaseTypes() {
        return caseTypes;
    }

    public void setCaseTypes(List<String> caseTypes) {
        this.caseTypes = caseTypes;
    }

    public List<FundCompanyLawsuitListItemResponse> getLawsuitList() {
        return lawsuitList;
    }

    public void setLawsuitList(List<FundCompanyLawsuitListItemResponse> lawsuitList) {
        this.lawsuitList = lawsuitList;
    }

    public List<FundCompanyLawsuitColumnItemResponse> getColumn() {
        return column;
    }

    public void setColumn(List<FundCompanyLawsuitColumnItemResponse> column) {
        this.column = column;
    }
}
