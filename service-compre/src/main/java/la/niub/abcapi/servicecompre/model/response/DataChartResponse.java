package la.niub.abcapi.servicecompre.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import la.niub.abcapi.servicecompre.model.response.client.Search2Option;

import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataChartResponse {

    private Integer current_page;

    private Integer total_count;

    private Integer total_page;

    private String request_id;

    private String unhighlight_fields;

    private String highlight_fields;

    private Map<String,String> keyword_suggest;

    private List<Map<String,Object>> items;

    private List<Map<String, String>> selected;

    private List<Search2Option> option;

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

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public String getUnhighlight_fields() {
        return unhighlight_fields;
    }

    public void setUnhighlight_fields(String unhighlight_fields) {
        this.unhighlight_fields = unhighlight_fields;
    }

    public String getHighlight_fields() {
        return highlight_fields;
    }

    public void setHighlight_fields(String highlight_fields) {
        this.highlight_fields = highlight_fields;
    }

    public Map<String, String> getKeyword_suggest() {
        return keyword_suggest;
    }

    public void setKeyword_suggest(Map<String, String> keyword_suggest) {
        this.keyword_suggest = keyword_suggest;
    }

    public List<Map<String, Object>> getItems() {
        return items;
    }

    public void setItems(List<Map<String, Object>> items) {
        this.items = items;
    }

    public List<Map<String, String>> getSelected() {
        return selected;
    }

    public void setSelected(List<Map<String, String>> selected) {
        this.selected = selected;
    }

    public List<Search2Option> getOption() {
        return option;
    }

    public void setOption(List<Search2Option> option) {
        this.option = option;
    }

    @Override
    public String toString() {
        return "ClientDataChartResponse{" +
                "current_page=" + current_page +
                ", total_count=" + total_count +
                ", total_page=" + total_page +
                ", request_id='" + request_id + '\'' +
                ", unhighlight_fields='" + unhighlight_fields + '\'' +
                ", highlight_fields='" + highlight_fields + '\'' +
                ", keyword_suggest=" + keyword_suggest +
                ", items=" + items +
                '}';
    }
}
