package la.niub.abcapi.servicecompre.model.request;

public class KlineRequest {

    private String start_time;

    private String stock_code;

    private String 	graph_type;

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

    public String getGraph_type() {
        return graph_type;
    }

    public void setGraph_type(String graph_type) {
        this.graph_type = graph_type;
    }

    @Override
    public String toString() {
        return "KlineRequest{" +
                "start_time='" + start_time + '\'' +
                ", stock_code='" + stock_code + '\'' +
                ", graph_type='" + graph_type + '\'' +
                '}';
    }
}
