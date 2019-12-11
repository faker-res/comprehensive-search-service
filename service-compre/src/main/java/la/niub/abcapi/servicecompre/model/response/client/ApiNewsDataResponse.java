package la.niub.abcapi.servicecompre.model.response.client;

import la.niub.abcapi.servicecompre.model.response.ApiNewsDataItemResponse;
import la.niub.abcapi.servicecompre.model.response.ApiNewsDataOptionResponse;

import java.util.List;

public class ApiNewsDataResponse {

    List<ApiNewsDataItemResponse> item;

    private Integer total_count;

    private List<ApiNewsDataOptionResponse> option;

    public List<ApiNewsDataItemResponse> getItem() {
        return item;
    }

    public void setItem(List<ApiNewsDataItemResponse> item) {
        this.item = item;
    }

    public Integer getTotal_count() {
        return total_count;
    }

    public void setTotal_count(Integer total_count) {
        this.total_count = total_count;
    }

    public List<ApiNewsDataOptionResponse> getOption() {
        return option;
    }

    public void setOption(List<ApiNewsDataOptionResponse> option) {
        this.option = option;
    }
}
