package la.niub.abcapi.servicecompre.model.bo;

import java.util.List;

public class SolrCommonSearchBo {
    private int error_code;
    private String err_message;
    private String request_id;
    private int  total_count;
    private List<SolrCommonSearchDataElementbo> data;
    private List<CardBo> card;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getErr_message() {
        return err_message;
    }

    public void setErr_message(String err_message) {
        this.err_message = err_message;
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

    public List<SolrCommonSearchDataElementbo> getData() {
        return data;
    }

    public void setData(List<SolrCommonSearchDataElementbo> data) {
        this.data = data;
    }

    public List<CardBo> getCard() {
        return card;
    }

    public void setCard(List<CardBo> card) {
        this.card = card;
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
