package la.niub.abcapi.servicecompre.model.response;

public class MyStockResponse {

    private String code;

    private String msg;

    private MyStockDataResponse data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public MyStockDataResponse getData() {
        return data;
    }

    public void setData(MyStockDataResponse data) {
        this.data = data;
    }
}
