package la.niub.abcapi.servicecompre.model.response;

import java.util.List;

public class MyStockDataResponse {

    private Integer count;

    private List<MyStockListResponse> list;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<MyStockListResponse> getList() {
        return list;
    }

    public void setList(List<MyStockListResponse> list) {
        this.list = list;
    }
}
