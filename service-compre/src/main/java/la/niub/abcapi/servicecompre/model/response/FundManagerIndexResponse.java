package la.niub.abcapi.servicecompre.model.response;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class FundManagerIndexResponse {

    private Long peo_uni_code;

    private String fund_manager_name;

    private Map<Date, List<BigDecimal>> index;

    public FundManagerIndexResponse() {
    }

    public FundManagerIndexResponse(Long peo_uni_code, String fund_manager_name, Map<Date, List<BigDecimal>> index) {
        this.peo_uni_code = peo_uni_code;
        this.fund_manager_name = fund_manager_name;
        this.index = index;
    }

    public Long getPeo_uni_code() {
        return peo_uni_code;
    }

    public void setPeo_uni_code(Long peo_uni_code) {
        this.peo_uni_code = peo_uni_code;
    }

    public String getFund_manager_name() {
        return fund_manager_name;
    }

    public void setFund_manager_name(String fund_manager_name) {
        this.fund_manager_name = fund_manager_name;
    }

    public Map<Date, List<BigDecimal>> getIndex() {
        return index;
    }

    public void setIndex(Map<Date, List<BigDecimal>> index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "FundManagerIndexResponse{" +
                "peo_uni_code=" + peo_uni_code +
                ", fund_manager_name='" + fund_manager_name + '\'' +
                ", index=" + index +
                '}';
    }
}
