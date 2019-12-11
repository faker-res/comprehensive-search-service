package la.niub.abcapi.servicecompre.model.response.client;


import la.niub.abcapi.servicecompre.model.response.client.notice.NoticeSearch2Data;

public class ApiNoticeSearch2Response {

    private String err_code;

    private NoticeSearch2Data data;

    public String getErr_code() {
        return err_code;
    }

    public void setErr_code(String err_code) {
        this.err_code = err_code;
    }

    public NoticeSearch2Data getData() {
        return data;
    }

    public void setData(NoticeSearch2Data data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ApiNoticeSearch2Response{" +
                "err_code='" + err_code + '\'' +
                ", data=" + data +
                '}';
    }
}
