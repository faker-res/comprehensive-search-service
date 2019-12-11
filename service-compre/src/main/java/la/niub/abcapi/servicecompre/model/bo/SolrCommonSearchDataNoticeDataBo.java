package la.niub.abcapi.servicecompre.model.bo;

import java.util.List;

public class SolrCommonSearchDataNoticeDataBo {

    private List<SolrCommonSearchDataNoticeDataItemBo> item;

    private int total_count;

    private int abcscore = 0;

    private String request_id;

    private String nlp_str = "";


    public List<SolrCommonSearchDataNoticeDataItemBo> getItem() {
        return item;
    }

    public void setItem(List<SolrCommonSearchDataNoticeDataItemBo> item) {
        this.item = item;
    }

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    public int getAbcscore() {
        return abcscore;
    }

    public void setAbcscore(int abcscore) {
        this.abcscore = abcscore;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public String getNlp_str() {
        return nlp_str;
    }

    public void setNlp_str(String nlp_str) {
        this.nlp_str = nlp_str;
    }
}
