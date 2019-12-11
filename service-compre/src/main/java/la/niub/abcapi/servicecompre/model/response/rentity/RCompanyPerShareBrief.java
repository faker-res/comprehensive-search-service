package la.niub.abcapi.servicecompre.model.response.rentity;
//公司每股指标摘要
public class RCompanyPerShareBrief {
    //EPS(基本)
    private String basic_perstock_income;
    //EPS(稀释)
    private String reduce_perstock_income;
    //EPS(摊薄)
    private String eps_diluted_2;
    //扣非后EPS(基本)
    private String eps_diluted_3;
    //每股净资产(BPS)
    private String netassets_ps;
    //每股销售额(SPS)
    private String or_ps;
    //每股经营现金流(OCFPS)
    private String ocf_ps;
    //每股现金净流量(CFPS)
    private String cf_ps;
    //P/E(TTM)
    private String pettm;
    //P/E(LYR)
    private String pelyr;
    //P/B(MRQ)
    private String pbmrq;
    //psttm
    private String psttm;

    public String getBasic_perstock_income() {
        return basic_perstock_income;
    }

    public void setBasic_perstock_income(String basic_perstock_income) {
        this.basic_perstock_income = basic_perstock_income;
    }

    public String getReduce_perstock_income() {
        return reduce_perstock_income;
    }

    public void setReduce_perstock_income(String reduce_perstock_income) {
        this.reduce_perstock_income = reduce_perstock_income;
    }

    public String getEps_diluted_2() {
        return eps_diluted_2;
    }

    public void setEps_diluted_2(String eps_diluted_2) {
        this.eps_diluted_2 = eps_diluted_2;
    }

    public String getEps_diluted_3() {
        return eps_diluted_3;
    }

    public void setEps_diluted_3(String eps_diluted_3) {
        this.eps_diluted_3 = eps_diluted_3;
    }

    public String getNetassets_ps() {
        return netassets_ps;
    }

    public void setNetassets_ps(String netassets_ps) {
        this.netassets_ps = netassets_ps;
    }

    public String getOr_ps() {
        return or_ps;
    }

    public void setOr_ps(String or_ps) {
        this.or_ps = or_ps;
    }

    public String getOcf_ps() {
        return ocf_ps;
    }

    public void setOcf_ps(String ocf_ps) {
        this.ocf_ps = ocf_ps;
    }

    public String getCf_ps() {
        return cf_ps;
    }

    public void setCf_ps(String cf_ps) {
        this.cf_ps = cf_ps;
    }

    public String getPettm() {
        return pettm;
    }

    public void setPettm(String pettm) {
        this.pettm = pettm;
    }

    public String getPelyr() {
        return pelyr;
    }

    public void setPelyr(String pelyr) {
        this.pelyr = pelyr;
    }

    public String getPbmrq() {
        return pbmrq;
    }

    public void setPbmrq(String pbmrq) {
        this.pbmrq = pbmrq;
    }

    public String getPsttm() {
        return psttm;
    }

    public void setPsttm(String psttm) {
        this.psttm = psttm;
    }
}
