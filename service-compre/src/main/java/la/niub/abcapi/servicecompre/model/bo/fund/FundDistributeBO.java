package la.niub.abcapi.servicecompre.model.bo.fund;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class FundDistributeBO {

    private String name;

    private Long induStandard;

    private String code;

    private BigDecimal value;

    private Double netratio;

    private Double chgRatio;

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getInduStandard() {
        return induStandard;
    }

    public void setInduStandard(Long induStandard) {
        this.induStandard = induStandard;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Double getNetratio() {
        return netratio;
    }

    public void setNetratio(Double netratio) {
        this.netratio = netratio;
    }

    public Double getChgRatio() {
        return chgRatio;
    }

    public void setChgRatio(Double chgRatio) {
        this.chgRatio = chgRatio;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "FundDistributeBO{" +
                "name='" + name + '\'' +
                ", induStandard=" + induStandard +
                ", code='" + code + '\'' +
                ", value=" + value +
                ", netratio=" + netratio +
                ", chgRatio=" + chgRatio +
                ", date=" + date +
                '}';
    }
}
