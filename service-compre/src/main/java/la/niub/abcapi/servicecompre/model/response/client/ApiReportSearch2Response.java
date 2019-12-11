package la.niub.abcapi.servicecompre.model.response.client;


import la.niub.abcapi.servicecompre.model.bo.ReportData;

public class ApiReportSearch2Response {

    private String keyword;

    private String err_msg;

    private String err_code;

    private ReportData data;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getErr_msg() {
        return err_msg;
    }

    public void setErr_msg(String err_msg) {
        this.err_msg = err_msg;
    }

    public String getErr_code() {
        return err_code;
    }

    public void setErr_code(String err_code) {
        this.err_code = err_code;
    }

    public ReportData getData() {
        return data;
    }

    public void setData(ReportData data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ApiReportSearch2Response{" +
                "err_msg='" + err_msg + '\'' +
                ", err_code='" + err_code + '\'' +
                ", data=" + data +
                '}';
    }
}
