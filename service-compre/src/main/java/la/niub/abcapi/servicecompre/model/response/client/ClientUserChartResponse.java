package la.niub.abcapi.servicecompre.model.response.client;

import la.niub.abcapi.servicecompre.model.response.client.UserChart.UserChartData;

import java.io.Serializable;

public class ClientUserChartResponse implements Serializable{

    private UserChartData data;

    private Integer err_code;

    private String err_msg;

    public UserChartData getData() {
        return data;
    }

    public void setData(UserChartData data) {
        this.data = data;
    }

    public Integer getErr_code() {
        return err_code;
    }

    public void setErr_code(Integer err_code) {
        this.err_code = err_code;
    }

    public String getErr_msg() {
        return err_msg;
    }

    public void setErr_msg(String err_msg) {
        this.err_msg = err_msg;
    }

    @Override
    public String toString() {
        return "ClientUserChartResponse{" +
                "data=" + data +
                ", err_code=" + err_code +
                ", err_msg='" + err_msg + '\'' +
                '}';
    }
}