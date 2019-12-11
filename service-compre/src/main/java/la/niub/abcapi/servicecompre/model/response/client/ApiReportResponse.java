package la.niub.abcapi.servicecompre.model.response.client;


public class ApiReportResponse {
    private String err_code;

    private String err_msg;

    private String querystr;

    private ApiReportDataResponse data;

    public String getErr_code() {
        return err_code;
    }

    public void setErr_code(String err_code) {
        this.err_code = err_code;
    }

    public String getErr_msg() {
        return err_msg;
    }

    public void setErr_msg(String err_msg) {
        this.err_msg = err_msg;
    }

    public String getQuerystr() {
        return querystr;
    }

    public void setQuerystr(String querystr) {
        this.querystr = querystr;
    }

    public ApiReportDataResponse getData() {
        return data;
    }

    public void setData(ApiReportDataResponse data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ApiReportResponse{" +
                "err_code='" + err_code + '\'' +
                ", err_msg='" + err_msg + '\'' +
                ", querystr='" + querystr + '\'' +
                ", data=" + data +
                '}';
    }
}
