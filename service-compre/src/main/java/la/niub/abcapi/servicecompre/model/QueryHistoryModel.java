package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;
import java.util.Date;

public class QueryHistoryModel implements Serializable {
    private Long id;

    private String user_id;

    private String keyword;

    private String input_from;

    private String page;

    private Date lastmodify;

    private Integer status = 0; // 一分钟内重复是否展示

    private static final long serialVersionUID = 1L;

    public QueryHistoryModel(Long id, String user_id, String keyword, String input_from, String page, Date lastmodify) {
        this.id = id;
        this.user_id = user_id;
        this.keyword = keyword;
        this.input_from = input_from;
        this.page = page;
        this.lastmodify = lastmodify;
    }
    public QueryHistoryModel(String user_id, String keyword, String input_from, String page) {
        this.user_id = user_id;
        this.keyword = keyword;
        this.input_from = input_from;
        this.page = page;
    }

    public QueryHistoryModel() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id == null ? null : user_id.trim();
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword == null ? null : keyword.trim();
    }

    public String getInput_from() {
        return input_from;
    }

    public void setInput_from(String input_from) {
        this.input_from = input_from == null ? null : input_from.trim();
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page == null ? null : page.trim();
    }

    public Date getLastmodify() {
        return lastmodify;
    }

    public void setLastmodify(Date lastmodify) {
        this.lastmodify = lastmodify;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", user_id=").append(user_id);
        sb.append(", keyword=").append(keyword);
        sb.append(", input_from=").append(input_from);
        sb.append(", page=").append(page);
        sb.append(", lastmodify=").append(lastmodify);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}