package la.niub.abcapi.servicecompre.model.request;

public class ChartDetailRequest extends Request{

    private String id = "";

    private String userId = "";

    private String request_id = "";

    private String device_info = "";

    private String input_from = "direct";

    private String page = "";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        return "ChartDetailRequest{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", request_id='" + request_id + '\'' +
                '}';
    }
}
