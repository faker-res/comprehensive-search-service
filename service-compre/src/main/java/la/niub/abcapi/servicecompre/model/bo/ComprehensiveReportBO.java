package la.niub.abcapi.servicecompre.model.bo;

import la.niub.abcapi.servicecompre.model.OrganModel;
import la.niub.abcapi.servicecompre.model.response.client.ServiceChartConfigResponse;

import java.util.List;

public class ComprehensiveReportBO {

    List<OrganModel> organ;

    List<ServiceChartConfigResponse> topic;

    ItemAndLinkBO list;

    public List<OrganModel> getOrgan() {
        return organ;
    }

    public void setOrgan(List<OrganModel> organ) {
        this.organ = organ;
    }

    public List<ServiceChartConfigResponse> getTopic() {
        return topic;
    }

    public void setTopic(List<ServiceChartConfigResponse> topic) {
        this.topic = topic;
    }

    public ItemAndLinkBO getList() {
        return list;
    }

    public void setList(ItemAndLinkBO list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "ComprehensiveReportBO{" +
                "organ=" + organ +
                ", topic=" + topic +
                ", list=" + list +
                '}';
    }
}