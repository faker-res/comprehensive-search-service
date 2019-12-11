package la.niub.abcapi.servicecompre.model.response;

import com.alibaba.fastjson.JSONArray;

public class FundManagerPositionDistributionResponse {
    private Long peo_uni_code;

    private String fund_manager_name;

    private JSONArray fund_com;

    public FundManagerPositionDistributionResponse() {
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

    public JSONArray getFund_com() {
        return fund_com;
    }

    public void setFund_com(JSONArray fund_com) {
        this.fund_com = fund_com;
    }
}
