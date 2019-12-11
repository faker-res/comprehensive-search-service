package la.niub.abcapi.servicecompre.model.bo;

import java.util.List;
import java.util.Map;

public class SolrBitmapBo {

    private Boolean ok = false;

    private int code;

    private String msg;

    private String data;

    public void setOk(Boolean ok) {
        this.ok = ok;
    }

    public Boolean getOk() {
        return ok;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    //
//    public List<DataBo> getData() {
//        return data;
//    }
//
//    public void setData(List<DataBo> data) {
//        this.data = data;
//    }
//
//    public class DataBo {
//
//        private List<String> fails;
//
//        private Map<String,String> imported;
//
//        public List<String> getFails() {
//            return fails;
//        }
//
//        public void setFails(List<String> fails) {
//            this.fails = fails;
//        }
//
//        public Map<String, String> getImported() {
//            return imported;
//        }
//
//        public void setImported(Map<String, String> imported) {
//            this.imported = imported;
//        }
//    }

}
