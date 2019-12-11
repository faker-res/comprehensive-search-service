package la.niub.abcapi.servicecompre.model.response.client;

import la.niub.abcapi.servicecompre.model.response.client.news.BestNewsData;

import java.util.List;

public class ApiBestNewsResponse {

    private String msg;

    private String code;

    private String businessCode;

    private String businessFlag;

    private Boolean success;

    private List<BestNewsData> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    public String getBusinessFlag() {
        return businessFlag;
    }

    public void setBusinessFlag(String businessFlag) {
        this.businessFlag = businessFlag;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<BestNewsData> getData() {
        return data;
    }

    public void setData(List<BestNewsData> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ApiBestNewsResponse{" +
                "msg='" + msg + '\'' +
                ", code='" + code + '\'' +
                ", businessCode='" + businessCode + '\'' +
                ", businessFlag='" + businessFlag + '\'' +
                ", success=" + success +
                ", data=" + data +
                '}';
    }
}
