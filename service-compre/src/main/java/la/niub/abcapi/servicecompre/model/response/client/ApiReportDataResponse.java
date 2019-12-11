package la.niub.abcapi.servicecompre.model.response.client;

import la.niub.abcapi.servicecompre.model.response.ApiReportItemResponse;

import java.util.List;

public class ApiReportDataResponse {

    List<ApiReportItemResponse> item;

    Integer total_count;

    public List<ApiReportItemResponse> getItem() {
        return item;
    }

    public void setItem(List<ApiReportItemResponse> item) {
        this.item = item;
    }

    public Integer getTotal_count() {
        return total_count;
    }

    public void setTotal_count(Integer total_count) {
        this.total_count = total_count;
    }

    @Override
    public String toString() {
        return "ApiReportDataResponse{" +
                "item=" + item +
                '}';
    }
}
