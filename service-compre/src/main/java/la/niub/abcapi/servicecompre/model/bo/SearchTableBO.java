package la.niub.abcapi.servicecompre.model.bo;

import la.niub.abcapi.servicecompre.model.response.client.Search2Option;

import java.util.List;
import java.util.Map;

public class SearchTableBO {

    private Integer current_page;

    private Integer total_count;

    private Integer total_page;

    private String keyword;

    private String request_id;

    private List<Map<String,Object>> items;

    private List<Search2Option> option;

    private List<Map<String, String>> selected;

    public Integer getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(Integer current_page) {
        this.current_page = current_page;
    }

    public Integer getTotal_count() {
        return total_count;
    }

    public void setTotal_count(Integer total_count) {
        this.total_count = total_count;
    }

    public Integer getTotal_page() {
        return total_page;
    }

    public void setTotal_page(Integer total_page) {
        this.total_page = total_page;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public List<Map<String, Object>> getItems() {
        return items;
    }

    public void setItems(List<Map<String, Object>> items) {
        this.items = items;
    }

    public List<Search2Option> getOption() {
        return option;
    }

    public void setOption(List<Search2Option> option) {
        this.option = option;
    }

    public List<Map<String, String>> getSelected() {
        return selected;
    }

    public void setSelected(List<Map<String, String>> selected) {
        this.selected = selected;
    }

    @Override
    public String toString() {
        return "SearchTableBO{" +
                "current_page=" + current_page +
                ", total_count=" + total_count +
                ", total_page=" + total_page +
                ", keyword='" + keyword + '\'' +
                ", request_id='" + request_id + '\'' +
                ", items=" + items +
                '}';
    }
}
