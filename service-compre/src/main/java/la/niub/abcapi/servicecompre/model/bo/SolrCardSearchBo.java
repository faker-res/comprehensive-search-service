package la.niub.abcapi.servicecompre.model.bo;

import java.util.List;

public class SolrCardSearchBo {
    private int err_code;
    private String err_msg;
    private String request_id;
    private int  total_count;
    private List<CardBo> data;

    public int getErr_code() {
        return err_code;
    }

    public void setErr_code(int err_code) {
        this.err_code = err_code;
    }

    public String getErr_msg() {
        return err_msg;
    }

    public void setErr_msg(String err_msg) {
        this.err_msg = err_msg;
    }

    public List<CardBo> getData() {
        return data;
    }

    public void setData(List<CardBo> data) {
        this.data = data;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }



    public class CardBo {
        private String name;

        private String code;

        private String type;

        private String cName;



        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getcName() {
            return cName;
        }

        public void setcName(String cName) {
            this.cName = cName;
        }
    }
}
