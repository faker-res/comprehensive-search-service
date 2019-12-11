package la.niub.abcapi.servicecompre.model.response.client;


import la.niub.abcapi.servicecompre.model.response.client.report.ReportFieldFacetData;

import java.util.List;

public class ApiReportFieldFacetResponse {

    private String err_msg;

    private String err_code;

    private List<ReportFieldFacetData> data;

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

    public List<ReportFieldFacetData> getData() {
        return data;
    }

    public void setData(List<ReportFieldFacetData> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ApiReportFieldFacetResponse{" +
                "err_msg='" + err_msg + '\'' +
                ", err_code='" + err_code + '\'' +
                ", data=" + data +
                '}';
    }
}
