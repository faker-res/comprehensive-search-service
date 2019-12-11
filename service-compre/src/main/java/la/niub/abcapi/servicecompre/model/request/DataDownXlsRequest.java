package la.niub.abcapi.servicecompre.model.request;

import java.util.List;

public class DataDownXlsRequest {

    private String title;

    private List<List<String>> data;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<List<String>> getData() {
        return data;
    }

    public void setData(List<List<String>> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DataDownXlsRequest{" +
                "title='" + title + '\'' +
                ", data=" + data +
                '}';
    }
}
