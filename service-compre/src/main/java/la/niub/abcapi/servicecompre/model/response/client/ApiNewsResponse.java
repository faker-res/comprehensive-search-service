package la.niub.abcapi.servicecompre.model.response.client;

public class ApiNewsResponse {

    private int err_code;

    private ApiNewsDataResponse data;

    public int getErr_code() {
        return err_code;
    }

    public void setErr_code(int err_code) {
        this.err_code = err_code;
    }

    public ApiNewsDataResponse getData() {
        return data;
    }

    public void setData(ApiNewsDataResponse data) {
        this.data = data;
    }
}
