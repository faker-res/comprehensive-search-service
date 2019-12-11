package la.niub.abcapi.servicecompre.model.response;

import java.util.Map;

/**
 * 通用的搜索结果参数
 */
public class SearchGeneralResponse {
    private String err_code;

    private String err_msg;

    private Map<String,Object> data;

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

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
