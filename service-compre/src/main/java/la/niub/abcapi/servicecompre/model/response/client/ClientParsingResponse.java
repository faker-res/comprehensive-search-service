package la.niub.abcapi.servicecompre.model.response.client;

import java.io.Serializable;

/**
 * Parsing 返回的数据结构
 *
 * @author amen
 */
public class ClientParsingResponse implements Serializable {

    private Integer code;

    private String msg;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
