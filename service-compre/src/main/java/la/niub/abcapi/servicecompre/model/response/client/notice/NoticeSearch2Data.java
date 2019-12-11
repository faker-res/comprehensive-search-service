package la.niub.abcapi.servicecompre.model.response.client.notice;

import la.niub.abcapi.servicecompre.model.response.client.Search2Option;

import java.io.Serializable;
import java.util.List;

public class NoticeSearch2Data implements Serializable {

    private List<NoticeSearch2Item> item;

    private Integer total_count;

    private List<Search2Option> option;

    private String solrquery;

    public List<NoticeSearch2Item> getItem() {
        return item;
    }

    public void setItem(List<NoticeSearch2Item> item) {
        this.item = item;
    }

    public Integer getTotal_count() {
        return total_count;
    }

    public void setTotal_count(Integer total_count) {
        this.total_count = total_count;
    }

    public List<Search2Option> getOption() {
        return option;
    }

    public void setOption(List<Search2Option> option) {
        this.option = option;
    }

    public String getSolrquery() {
        return solrquery;
    }

    public void setSolrquery(String solrquery) {
        this.solrquery = solrquery;
    }

    @Override
    public String toString() {
        return "NoticeSearch2Data{" +
                "item=" + item +
                ", total_count=" + total_count +
                ", option=" + option +
                '}';
    }
}
