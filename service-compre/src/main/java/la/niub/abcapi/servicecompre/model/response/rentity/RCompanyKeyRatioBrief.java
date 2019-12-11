package la.niub.abcapi.servicecompre.model.response.rentity;
//公司关键比率摘要
public class RCompanyKeyRatioBrief {
    //ROE(摊薄)(%)
    private String roe_diluted;
    //ROE(加权)(%)
    private String roe_weigh;
    //扣非后ROE(摊薄)(%)
    private String deduct_roe_diluted;
    //ROA(%)
    private String roa_2;
    //ROIC(%)
    private String roic;
    //销售毛利率(%)
    private String grossprofit_margin;
    //销售净利率(%)
    private String netprofit_margin;
    //EBIT margin
    private String ebit_per_gr;
    //EBITDA margin(%)
    private String ebitda_per_gr;
    //资产负债率(%)
    private String debt_assets;
    //资产周转率(倍)
    private String assets_turn;

    public String getRoe_diluted() {
        return roe_diluted;
    }

    public void setRoe_diluted(String roe_diluted) {
        this.roe_diluted = roe_diluted;
    }

    public String getRoe_weigh() {
        return roe_weigh;
    }

    public void setRoe_weigh(String roe_weigh) {
        this.roe_weigh = roe_weigh;
    }

    public String getDeduct_roe_diluted() {
        return deduct_roe_diluted;
    }

    public void setDeduct_roe_diluted(String deduct_roe_diluted) {
        this.deduct_roe_diluted = deduct_roe_diluted;
    }

    public String getRoa_2() {
        return roa_2;
    }

    public void setRoa_2(String roa_2) {
        this.roa_2 = roa_2;
    }

    public String getRoic() {
        return roic;
    }

    public void setRoic(String roic) {
        this.roic = roic;
    }

    public String getGrossprofit_margin() {
        return grossprofit_margin;
    }

    public void setGrossprofit_margin(String grossprofit_margin) {
        this.grossprofit_margin = grossprofit_margin;
    }

    public String getNetprofit_margin() {
        return netprofit_margin;
    }

    public void setNetprofit_margin(String netprofit_margin) {
        this.netprofit_margin = netprofit_margin;
    }

    public String getEbit_per_gr() {
        return ebit_per_gr;
    }

    public void setEbit_per_gr(String ebit_per_gr) {
        this.ebit_per_gr = ebit_per_gr;
    }

    public String getEbitda_per_gr() {
        return ebitda_per_gr;
    }

    public void setEbitda_per_gr(String ebitda_per_gr) {
        this.ebitda_per_gr = ebitda_per_gr;
    }

    public String getDebt_assets() {
        return debt_assets;
    }

    public void setDebt_assets(String debt_assets) {
        this.debt_assets = debt_assets;
    }

    public String getAssets_turn() {
        return assets_turn;
    }

    public void setAssets_turn(String assets_turn) {
        this.assets_turn = assets_turn;
    }
}
