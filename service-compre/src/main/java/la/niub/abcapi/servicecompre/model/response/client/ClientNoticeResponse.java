package la.niub.abcapi.servicecompre.model.response.client;

import la.niub.abcapi.servicecompre.model.response.client.notice.NoticeData;

import java.io.Serializable;

public class ClientNoticeResponse implements Serializable {

    private NoticeData data;

    private Integer err_code;

    private String err_msg;

    private String querystr;

    public NoticeData getData() {
        return data;
    }

    public void setData(NoticeData data) {
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

    public String getQuerystr() {
        return querystr;
    }

    public void setQuerystr(String querystr) {
        this.querystr = querystr;
    }

    @Override
    public String toString() {
        return "ClientNoticeResponse{" +
                "data=" + data +
                ", err_code=" + err_code +
                ", err_msg='" + err_msg + '\'' +
                ", querystr='" + querystr + '\'' +
                '}';
    }
}
