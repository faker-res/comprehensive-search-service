package la.niub.abcapi.servicecompre.model.response.rentity;

import java.util.Date;

//公司所属行业
public class RCompanyIndustry {
    //id
    private String id;
    //行业标准名称
    private String indu_standard_name;
    //行业名称
    private String indu_name;
    //行业代码
    private String stk_code;
    //进入日期
    private Date sub_beg_date;
    //退出日期
    private Date sub_end_date;
    //状态
    private String if_performed;

    public String getIndu_standard_name() {
        return indu_standard_name;
    }

    public void setIndu_standard_name(String indu_standard_name) {
        this.indu_standard_name = indu_standard_name;
    }

    public String getIndu_name() {
        return indu_name;
    }

    public void setIndu_name(String indu_name) {
        this.indu_name = indu_name;
    }

    public Date getSub_beg_date() {
        return sub_beg_date;
    }

    public void setSub_beg_date(Date sub_beg_date) {
        this.sub_beg_date = sub_beg_date;
    }

    public Date getSub_end_date() {
        return sub_end_date;
    }

    public void setSub_end_date(Date sub_end_date) {
        this.sub_end_date = sub_end_date;
    }

    public String getIf_performed() {
        return if_performed;
    }

    public void setIf_performed(String if_performed) {
        this.if_performed = if_performed;
    }

    public String getStk_code() {
        return stk_code;
    }

    public void setStk_code(String stk_code) {
        this.stk_code = stk_code;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
