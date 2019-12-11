package la.niub.abcapi.servicecompre.model.bo.fund;

import java.math.BigDecimal;

public class FundTopInvestBO {

    private String name;

    private String code;

    private BigDecimal netratio;

    private BigDecimal value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getNetratio() {
        return netratio;
    }

    public void setNetratio(BigDecimal netratio) {
        this.netratio = netratio;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "FundTopInvestBO{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", netratio=" + netratio +
                ", value=" + value +
                '}';
    }
}
