package la.niub.abcapi.servicecompre.model.response.rentity;

import java.util.Date;
//公司财务业绩预告
public class RCompanyForeshow {
    //主键id
    private String id;
    //报告期
    private String end_date;
    //最新预告日期
    private Date publish_date;
    //预告类型
    private String fore_type;
    //预告净利润(万元)_low
    private String netprofit_low;
    //预告净利润(万元)_high
    private String netprofit_up;
    //净利润变动(%)_low
    private  String netprofit_change_range_low;
    //净利润变动(%)_high
    private String netprofit_change_range_up;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public Date getPublish_date() {
        return publish_date;
    }

    public void setPublish_date(Date publish_date) {
        this.publish_date = publish_date;
    }

    public String getFore_type() {
        return fore_type;
    }

    public void setFore_type(String fore_type) {
        this.fore_type = fore_type;
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
}
