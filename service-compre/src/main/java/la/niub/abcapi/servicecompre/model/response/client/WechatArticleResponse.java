package la.niub.abcapi.servicecompre.model.response.client;

import la.niub.abcapi.servicecompre.model.response.client.wechat.WechatArticleDataResponse;

public class WechatArticleResponse {

    private WechatArticleDataResponse data;

    private String err_msg;

    private int err_code;

    public WechatArticleDataResponse getData() {
        return data;
    }

    public void setData(WechatArticleDataResponse data) {
        this.data = data;
    }

    public String getErr_msg() {
        return err_msg;
    }

    public void setErr_msg(String err_msg) {
        this.err_msg = err_msg;
    }

    public int getErr_code() {
        return err_code;
    }

    public void setErr_code(int err_code) {
        this.err_code = err_code;
    }
}
