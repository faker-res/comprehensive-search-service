package la.niub.abcapi.servicecompre.model.bo.theme;

import java.util.List;

public class FinancialStatisticsBO {

    String index_code;

    String index_name;

    List<PepbDistBO> PEPB_dist_list;

    List<BalanceDistBO> balance_dist_list;

    public String getIndex_code() {
        return index_code;
    }

    public void setIndex_code(String index_code) {
        this.index_code = index_code;
    }

    public String getIndex_name() {
        return index_name;
    }

    public void setIndex_name(String index_name) {
        this.index_name = index_name;
    }

    public List<PepbDistBO> getPEPB_dist_list() {
        return PEPB_dist_list;
    }

    public void setPEPB_dist_list(List<PepbDistBO> PEPB_dist_list) {
        this.PEPB_dist_list = PEPB_dist_list;
    }

    public List<BalanceDistBO> getBalance_dist_list() {
        return balance_dist_list;
    }

    public void setBalance_dist_list(List<BalanceDistBO> balance_dist_list) {
        this.balance_dist_list = balance_dist_list;
    }
}
