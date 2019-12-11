package la.niub.abcapi.servicecompre.model;

import java.util.Date;

public class ComForecastModel {
    //主键id
    private String id;
    //公司统一编码
    private String com_uni_code;
    //股票代码
    private String abc_code;
    //公告表的src_id
    private String notice_id;
    //报告日期
    private Date end_date;
    //公告类别
    private String reporttype;
    //公告日期
    private Date publish_date;
    //货币名称
    private String currency_type;
    //业绩预告类型
    private String fore_type;
    //业绩预告内容
    private String fore_content;
    //业绩预告变动原因
    private String fore_reason;
    //预告净利润变动幅度
    private String netprofit_change_range;
    //预告净利润变动幅度下限(%)
    private String netprofit_change_range_low;
    //预告净利润变动幅度上限(%)
    private String netprofit_change_range_up;
    //业绩预告摘要
    private String fore_abstract;
    //预告净利润下限(万元)
    private String netprofit_low;
    //预告净利润上限(万元)
    private String netprofit_up;
    //上年同期归属于母公司股东的净利润
    private String last_parentnp;
    //预计每股收益下限
    private String fore_eps_low;
    //预告市盈率上限
    private String fore_pe_up;
    //预计净利润偏离度
    private String fore_profit_deviat;
    //预计每股收益上限
    private String fore_eps_up;
    //上年同期基本每股收益
    private String eps_last_year;
    //预告市盈率下限
    private String fore_pe_low;
    //创建时间
    private Date createtime;
    //更新时间
    private Date updatetime;
    //状态
    private String status;
    //备注
    private String remark;
    //创建人
    private String creator;
    //编辑人
    private String editor;
    //数据来源
    private String come_source;
    //数据对应公告ID
    private String src_id;

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

    public String getAbc_code() {
        return abc_code;
    }

    public void setAbc_code(String abc_code) {
        this.abc_code = abc_code;
    }

    public String getNotice_id() {
        return notice_id;
    }

    public void setNotice_id(String notice_id) {
        this.notice_id = notice_id;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public String getReporttype() {
        return reporttype;
    }

    public void setReporttype(String reporttype) {
        this.reporttype = reporttype;
    }

    public Date getPublish_date() {
        return publish_date;
    }

    public void setPublish_date(Date publish_date) {
        this.publish_date = publish_date;
    }

    public String getCurrency_type() {
        return currency_type;
    }

    public void setCurrency_type(String currency_type) {
        this.currency_type = currency_type;
    }

    public String getFore_type() {
        return fore_type;
    }

    public void setFore_type(String fore_type) {
        this.fore_type = fore_type;
    }

    public String getFore_content() {
        return fore_content;
    }

    public void setFore_content(String fore_content) {
        this.fore_content = fore_content;
    }

    public String getFore_reason() {
        return fore_reason;
    }

    public void setFore_reason(String fore_reason) {
        this.fore_reason = fore_reason;
    }

    public String getNetprofit_change_range() {
        return netprofit_change_range;
    }

    public void setNetprofit_change_range(String netprofit_change_range) {
        this.netprofit_change_range = netprofit_change_range;
    }

    public String getNetprofit_change_range_low() {
        return netprofit_change_range_low;
    }

    public void setNetprofit_change_range_low(String netprofit_change_range_low) {
        this.netprofit_change_range_low = netprofit_change_range_low;
    }

    public String getNetprofit_change_range_up() {
        return netprofit_change_range_up;
    }

    public void setNetprofit_change_range_up(String netprofit_change_range_up) {
        this.netprofit_change_range_up = netprofit_change_range_up;
    }

    public String getFore_abstract() {
        return fore_abstract;
    }

    public void setFore_abstract(String fore_abstract) {
        this.fore_abstract = fore_abstract;
    }

    public String getNetprofit_low() {
        return netprofit_low;
    }

    public void setNetprofit_low(String netprofit_low) {
        this.netprofit_low = netprofit_low;
    }

    public String getNetprofit_up() {
        return netprofit_up;
    }

    public void setNetprofit_up(String netprofit_up) {
        this.netprofit_up = netprofit_up;
    }

    public String getLast_parentnp() {
        return last_parentnp;
    }

    public void setLast_parentnp(String last_parentnp) {
        this.last_parentnp = last_parentnp;
    }

    public String getFore_eps_low() {
        return fore_eps_low;
    }

    public void setFore_eps_low(String fore_eps_low) {
        this.fore_eps_low = fore_eps_low;
    }

    public String getFore_pe_up() {
        return fore_pe_up;
    }

    public void setFore_pe_up(String fore_pe_up) {
        this.fore_pe_up = fore_pe_up;
    }

    public String getFore_profit_deviat() {
        return fore_profit_deviat;
    }

    public void setFore_profit_deviat(String fore_profit_deviat) {
        this.fore_profit_deviat = fore_profit_deviat;
    }

    public String getFore_eps_up() {
        return fore_eps_up;
    }

    public void setFore_eps_up(String fore_eps_up) {
        this.fore_eps_up = fore_eps_up;
    }

    public String getEps_last_year() {
        return eps_last_year;
    }

    public void setEps_last_year(String eps_last_year) {
        this.eps_last_year = eps_last_year;
    }

    public String getFore_pe_low() {
        return fore_pe_low;
    }

    public void setFore_pe_low(String fore_pe_low) {
        this.fore_pe_low = fore_pe_low;
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
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public String getCome_source() {
        return come_source;
    }

    public void setCome_source(String come_source) {
        this.come_source = come_source;
    }

    public String getSrc_id() {
        return src_id;
    }

    public void setSrc_id(String src_id) {
        this.src_id = src_id;
    }
}
