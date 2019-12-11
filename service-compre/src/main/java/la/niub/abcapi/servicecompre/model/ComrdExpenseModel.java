package la.niub.abcapi.servicecompre.model;

import java.util.Date;

public class ComrdExpenseModel {
    //主键id
    private String id;
    //公司统一编码
    private String com_uni_code;
    //截止日期
    private Date end_date;
    //公告日期
    private Date decl_date;
    //报告类型
    private String reporttype;
    //币种名称编码
    private String currency_code;
    //研发费用总额
    private String rd_total;
    //其中：资本化的研发费用
    private String rd_capi;
    //资本化的研发费用占研发费用总额的比重
    private String rd_capi_propor;
    //研发费用占营业收入的比重
    private String rese_dev;
    //说明
    private String description;
    //状态
    private String status;
    //创建时间
    private Date createtime;
    //更新时间
    private Date updatetime;
    //数据来源
    private String come_source;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCom_uni_code() {
        return com_uni_code;
    }

    public void setCom_uni_code(String com_uni_code) {
        this.com_uni_code = com_uni_code;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public Date getDecl_date() {
        return decl_date;
    }

    public void setDecl_date(Date decl_date) {
        this.decl_date = decl_date;
    }

    public String getReporttype() {
        return reporttype;
    }

    public void setReporttype(String reporttype) {
        this.reporttype = reporttype;
    }

    public String getCurrency_code() {
        return currency_code;
    }

    public void setCurrency_code(String currency_code) {
        this.currency_code = currency_code;
    }

    public String getRd_total() {
        return rd_total;
    }

    public void setRd_total(String rd_total) {
        this.rd_total = rd_total;
    }

    public String getRd_capi() {
        return rd_capi;
    }

    public void setRd_capi(String rd_capi) {
        this.rd_capi = rd_capi;
    }

    public String getRd_capi_propor() {
        return rd_capi_propor;
    }

    public void setRd_capi_propor(String rd_capi_propor) {
        this.rd_capi_propor = rd_capi_propor;
    }

    public String getRese_dev() {
        return rese_dev;
    }

    public void setRese_dev(String rese_dev) {
        this.rese_dev = rese_dev;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getCome_source() {
        return come_source;
    }

    public void setCome_source(String come_source) {
        this.come_source = come_source;
    }
}
