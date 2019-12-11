package la.niub.abcapi.servicecompre.model.bo;

import java.util.List;

public class NoticeReturnBo {

    private int new_count;

    private String request_id;

    private int current_page;

    private int total_count;

    private int total_page;

    private String keyword;

    private String m_keyword;

    private int abcscore;

    private String searchCompany;

    private List<NoticeReturnItemBo> item;

    private String category;

    public int getNew_count() {
        return new_count;
    }

    public void setNew_count(int new_count) {
        this.new_count = new_count;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    public int getTotal_page() {
        return total_page;
    }

    public void setTotal_page(int total_page) {
        this.total_page = total_page;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getAbcscore() {
        return abcscore;
    }

    public void setAbcscore(int abcscore) {
        this.abcscore = abcscore;
    }

    public List<NoticeReturnItemBo> getItem() {
        return item;
    }

    public void setItem(List<NoticeReturnItemBo> item) {
        this.item = item;
    }

    public String getSearchCompany() {
        return searchCompany;
    }

    public void setSearchCompany(String searchCompany) {
        this.searchCompany = searchCompany;
    }

    public String getM_keyword() {
        return m_keyword;
    }

    public void setM_keyword(String m_keyword) {
        this.m_keyword = m_keyword;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
