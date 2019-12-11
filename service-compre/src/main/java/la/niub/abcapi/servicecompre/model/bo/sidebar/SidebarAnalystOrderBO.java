package la.niub.abcapi.servicecompre.model.bo.sidebar;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SidebarAnalystOrderBO {
    private String analyst;

    private String image;

    private String organ;

    private String peo_uni_code;

    private Integer honor_total;

    private Integer ranking;

    private String analyst_code;

    @JsonFormat(pattern="yyyy",timezone = "GMT+8")
    private Date time;

    private Integer report_total;

    public String getAnalyst() {
        return analyst;
    }

    public void setAnalyst(String analyst) {
        this.analyst = analyst;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getOrgan() {
        return organ;
    }

    public void setOrgan(String organ) {
        this.organ = organ;
    }

    public String getPeo_uni_code() {
        return peo_uni_code;
    }

    public void setPeo_uni_code(String peo_uni_code) {
        this.peo_uni_code = peo_uni_code;
    }

    public Integer getHonor_total() {
        return honor_total;
    }

    public void setHonor_total(Integer honor_total) {
        this.honor_total = honor_total;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getReport_total() {
        return report_total;
    }

    public void setReport_total(Integer report_total) {
        this.report_total = report_total;
    }

    public String getAnalyst_code() {
        return analyst_code;
    }

    public void setAnalyst_code(String analyst_code) {
        this.analyst_code = analyst_code;
    }

    @Override
    public String toString() {
        return "SidebarAnalystOrderBO{" +
                "analyst='" + analyst + '\'' +
                ", image='" + image + '\'' +
                ", organ='" + organ + '\'' +
                ", peo_uni_code='" + peo_uni_code + '\'' +
                ", honor_total=" + honor_total +
                ", ranking=" + ranking +
                ", analyst_code='" + analyst_code + '\'' +
                ", time=" + time +
                ", report_total=" + report_total +
                '}';
    }
}
