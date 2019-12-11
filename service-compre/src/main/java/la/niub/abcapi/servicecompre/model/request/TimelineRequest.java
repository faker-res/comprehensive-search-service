package la.niub.abcapi.servicecompre.model.request;

public class TimelineRequest {

    private String start_time;

    private String stock_code;

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getStock_code() {
        return stock_code;
    }

    public void setStock_code(String stock_code) {
        this.stock_code = stock_code;
    }

    @Override
    public String toString() {
        return "KlineRequest{" +
                "start_time='" + start_time + '\'' +
                ", stock_code='" + stock_code + '\'' +
                '}';
    }
}
