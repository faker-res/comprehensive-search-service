package la.niub.abcapi.servicecompre.model.bo.fund;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class FundSubjectBO {

    //行业名称
    private String name;

    //资金流入流出金额
    private BigDecimal amount;

    //资金流入流出比例
    private Double ratio;

    //是否是流入的
    private Boolean income;

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Double getRatio() {
        return ratio;
    }

    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }

    public Boolean getIncome() {
        return income;
    }

    public void setIncome(Boolean income) {
        this.income = income;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "FundSubjectBO{" +
                "name='" + name + '\'' +
                ", amount=" + amount +
                ", ratio=" + ratio +
                ", income=" + income +
                ", date=" + date +
                '}';
    }
}
