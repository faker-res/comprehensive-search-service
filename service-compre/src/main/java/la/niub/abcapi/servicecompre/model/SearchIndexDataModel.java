package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;
import java.util.Date;

public class SearchIndexDataModel implements Serializable {
    private Long id;

    private Long keyword_id;

    private String index_type;

    private Long search_index;

    private Long media_index;

    private Date data_time;

    private String data_source;

    private String initia_source_url;

    private Date createtime;

    private Date updatetime;

    private String status;

    private static final long serialVersionUID = 1L;

    public SearchIndexDataModel(Long id, Long keyword_id, String index_type, Long search_index, Long media_index, Date data_time, String data_source, String initia_source_url, Date createtime, Date updatetime, String status) {
        this.id = id;
        this.keyword_id = keyword_id;
        this.index_type = index_type;
        this.search_index = search_index;
        this.media_index = media_index;
        this.data_time = data_time;
        this.data_source = data_source;
        this.initia_source_url = initia_source_url;
        this.createtime = createtime;
        this.updatetime = updatetime;
        this.status = status;
    }

    public SearchIndexDataModel() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getKeyword_id() {
        return keyword_id;
    }

    public void setKeyword_id(Long keyword_id) {
        this.keyword_id = keyword_id;
    }

    public String getIndex_type() {
        return index_type;
    }

    public void setIndex_type(String index_type) {
        this.index_type = index_type == null ? null : index_type.trim();
    }

    public Long getSearch_index() {
        return search_index;
    }

    public void setSearch_index(Long search_index) {
        this.search_index = search_index;
    }

    public Long getMedia_index() {
        return media_index;
    }

    public void setMedia_index(Long media_index) {
        this.media_index = media_index;
    }

    public Date getData_time() {
        return data_time;
    }

    public void setData_time(Date data_time) {
        this.data_time = data_time;
    }

    public String getData_source() {
        return data_source;
    }

    public void setData_source(String data_source) {
        this.data_source = data_source == null ? null : data_source.trim();
    }

    public String getInitia_source_url() {
        return initia_source_url;
    }

    public void setInitia_source_url(String initia_source_url) {
        this.initia_source_url = initia_source_url == null ? null : initia_source_url.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", keyword_id=").append(keyword_id);
        sb.append(", index_type=").append(index_type);
        sb.append(", search_index=").append(search_index);
        sb.append(", media_index=").append(media_index);
        sb.append(", data_time=").append(data_time);
        sb.append(", data_source=").append(data_source);
        sb.append(", initia_source_url=").append(initia_source_url);
        sb.append(", createtime=").append(createtime);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}