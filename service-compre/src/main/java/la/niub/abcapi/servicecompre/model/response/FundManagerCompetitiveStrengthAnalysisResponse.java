package la.niub.abcapi.servicecompre.model.response;

import java.util.List;
import java.util.Map;

public class FundManagerCompetitiveStrengthAnalysisResponse {
    private Long peo_uni_code;
    private String fund_manager_name;
    private Map<Long, Map<Integer, List<FundManagerCompetitiveStrengthAnalysisItemResponse>>> info;

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

    public Map<Long, Map<Integer, List<FundManagerCompetitiveStrengthAnalysisItemResponse>>> getInfo() {
        return info;
    }

    public void setInfo(Map<Long, Map<Integer, List<FundManagerCompetitiveStrengthAnalysisItemResponse>>> info) {
        this.info = info;
    }
}
