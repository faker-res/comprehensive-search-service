package la.niub.abcapi.servicecompre.model.response.client;

import la.niub.abcapi.servicecompre.model.response.client.datatable.DataTableData;

import java.io.Serializable;

public class ClientDataTableResponse implements Serializable{

    private DataTableData data;

    private Integer err_code;

    private String err_msg;

    private Integer abcscore;

    private String keyword;

    private String graph;

    private String searchCompany;

    private String searchIndustry;

    public DataTableData getData() {
        return data;
    }

    public void setData(DataTableData data) {
        this.data = data;
    }

    public Integer getErr_code() {
        return err_code;
    }

    public void setErr_code(Integer err_code) {
        this.err_code = err_code;
    }

    public String getErr_msg() {
        return err_msg;
    }

    public void setErr_msg(String err_msg) {
        this.err_msg = err_msg;
    }

    public Integer getAbcscore() {
        return abcscore;
    }

    public void setAbcscore(Integer abcscore) {
        this.abcscore = abcscore;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getGraph() {
        return graph;
    }

    public void setGraph(String graph) {
        this.graph = graph;
    }

    public String getSearchCompany() {
        return searchCompany;
    }

    public void setSearchCompany(String searchCompany) {
        this.searchCompany = searchCompany;
    }

    public String getSearchIndustry() {
        return searchIndustry;
    }

    public void setSearchIndustry(String searchIndustry) {
        this.searchIndustry = searchIndustry;
    }

    @Override
    public String toString() {
        return "ClientDataTableResponse{" +
                "data=" + data +
                ", err_code=" + err_code +
                ", err_msg='" + err_msg + '\'' +
                ", abcscore=" + abcscore +
                ", keyword='" + keyword + '\'' +
                ", graph='" + graph + '\'' +
                ", searchCompany='" + searchCompany + '\'' +
                ", searchIndustry='" + searchIndustry + '\'' +
                '}';
    }

}