package la.niub.abcapi.servicecompre.model.request;

public class BatchExportRequest extends Request{

    private String keyword = "";

    private String search_type = "";

    private String ids = "";

    private Integer limit = 200;

    private Integer offset = 0;

    private String selected; // 搜索2.0选中

    private String start_time;

    private String end_time;

    // 日志参数
    private String userId = "";

    private String request_id = "";

    private String device_info = "";

    private String input_from = "";

    private String page = "";

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getSearch_type() {
        return search_type;
    }

    public void setSearch_type(String search_type) {
        this.search_type = search_type;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    @Override
    public Integer getLimit() {
        return limit;
    }

    @Override
    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    @Override
    public Integer getOffset() {
        return offset;
    }

    @Override
    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    @Override
    public String getUserId() {
        return userId;
    }

    @Override
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public String getDevice_info() {
        return device_info;
    }

    public void setDevice_info(String device_info) {
        this.device_info = device_info;
    }

    public String getInput_from() {
        return input_from;
    }

    public void setInput_from(String input_from) {
        this.input_from = input_from;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "BatchExportRequest{" +
                "keyword='" + keyword + '\'' +
                ", search_type='" + search_type + '\'' +
                ", ids='" + ids + '\'' +
                ", limit=" + limit +
                ", offset=" + offset +
                ", userId='" + userId + '\'' +
                ", request_id='" + request_id + '\'' +
                ", device_info='" + device_info + '\'' +
                ", input_from='" + input_from + '\'' +
                ", page='" + page + '\'' +
                '}';
    }
}
