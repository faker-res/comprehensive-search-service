package la.niub.abcapi.servicecompre.model.response.publiccompany;

import la.niub.abcapi.servicecompre.model.bo.publiccompany.PublicCompanyIndexBO;

import java.util.List;

public class PublicCompanyIndexResponse {

    private List<PublicCompanyIndexBO> list;

    public List<PublicCompanyIndexBO> getList() {
        return list;
    }

    public void setList(List<PublicCompanyIndexBO> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "PublicCompanyIndexResponse{" +
                "list=" + list +
                '}';
    }
}
