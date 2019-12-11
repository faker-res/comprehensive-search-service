package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AnalystStatisticsModel implements Serializable {
    private Integer id;

    private String peo_uni_code;

    private String analyst;

    private String current_org;

    private String sac;

    private String email;

    private String tel_phone;

    private BigDecimal reachdays_analyst;

    private BigDecimal reachdays_percent;

    private Integer stock_count;

    private Integer recom_times;

    private String analyst_team;

    private String industry_cover;

    private Integer report_count;

    private Integer report_quarter;

    private BigDecimal stock_returns;

    private Date create_time;

    private Date update_time;

    private static final long serialVersionUID = 1L;

    public AnalystStatisticsModel(Integer id, String peo_uni_code, String analyst, String current_org, String sac, String email, String tel_phone, BigDecimal reachdays_analyst, BigDecimal reachdays_percent, Integer stock_count, Integer recom_times, String analyst_team, String industry_cover, Integer report_count, Integer report_quarter, BigDecimal stock_returns, Date create_time, Date update_time) {
        this.id = id;
        this.peo_uni_code = peo_uni_code;
        this.analyst = analyst;
        this.current_org = current_org;
        this.sac = sac;
        this.email = email;
        this.tel_phone = tel_phone;
        this.reachdays_analyst = reachdays_analyst;
        this.reachdays_percent = reachdays_percent;
        this.stock_count = stock_count;
        this.recom_times = recom_times;
        this.analyst_team = analyst_team;
        this.industry_cover = industry_cover;
        this.report_count = report_count;
        this.report_quarter = report_quarter;
        this.stock_returns = stock_returns;
        this.create_time = create_time;
        this.update_time = update_time;
    }

    public AnalystStatisticsModel() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPeo_uni_code() {
        return peo_uni_code;
    }

    public void setPeo_uni_code(String peo_uni_code) {
        this.peo_uni_code = peo_uni_code == null ? null : peo_uni_code.trim();
    }

    public String getAnalyst() {
        return analyst;
    }

    public void setAnalyst(String analyst) {
        this.analyst = analyst == null ? null : analyst.trim();
    }

    public String getCurrent_org() {
        return current_org;
    }

    public void setCurrent_org(String current_org) {
        this.current_org = current_org == null ? null : current_org.trim();
    }

    public String getSac() {
        return sac;
    }

    public void setSac(String sac) {
        this.sac = sac == null ? null : sac.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getTel_phone() {
        return tel_phone;
    }

    public void setTel_phone(String tel_phone) {
        this.tel_phone = tel_phone == null ? null : tel_phone.trim();
    }

    public BigDecimal getReachdays_analyst() {
        return reachdays_analyst;
    }

    public void setReachdays_analyst(BigDecimal reachdays_analyst) {
        this.reachdays_analyst = reachdays_analyst;
    }

    public BigDecimal getReachdays_percent() {
        return reachdays_percent;
    }

    public void setReachdays_percent(BigDecimal reachdays_percent) {
        this.reachdays_percent = reachdays_percent;
    }

    public Integer getStock_count() {
        return stock_count;
    }

    public void setStock_count(Integer stock_count) {
        this.stock_count = stock_count;
    }

    public Integer getRecom_times() {
        return recom_times;
    }

    public void setRecom_times(Integer recom_times) {
        this.recom_times = recom_times;
    }

    public String getAnalyst_team() {
        return analyst_team;
    }

    public void setAnalyst_team(String analyst_team) {
        this.analyst_team = analyst_team == null ? null : analyst_team.trim();
    }

    public String getIndustry_cover() {
        return industry_cover;
    }

    public void setIndustry_cover(String industry_cover) {
        this.industry_cover = industry_cover == null ? null : industry_cover.trim();
    }

    public Integer getReport_count() {
        return report_count;
    }

    public void setReport_count(Integer report_count) {
        this.report_count = report_count;
    }

    public Integer getReport_quarter() {
        return report_quarter;
    }

    public void setReport_quarter(Integer report_quarter) {
        this.report_quarter = report_quarter;
    }

    public BigDecimal getStock_returns() {
        return stock_returns;
    }

    public void setStock_returns(BigDecimal stock_returns) {
        this.stock_returns = stock_returns;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", peo_uni_code=").append(peo_uni_code);
        sb.append(", analyst=").append(analyst);
        sb.append(", current_org=").append(current_org);
        sb.append(", sac=").append(sac);
        sb.append(", email=").append(email);
        sb.append(", tel_phone=").append(tel_phone);
        sb.append(", reachdays_analyst=").append(reachdays_analyst);
        sb.append(", reachdays_percent=").append(reachdays_percent);
        sb.append(", stock_count=").append(stock_count);
        sb.append(", recom_times=").append(recom_times);
        sb.append(", analyst_team=").append(analyst_team);
        sb.append(", industry_cover=").append(industry_cover);
        sb.append(", report_count=").append(report_count);
        sb.append(", report_quarter=").append(report_quarter);
        sb.append(", stock_returns=").append(stock_returns);
        sb.append(", create_time=").append(create_time);
        sb.append(", update_time=").append(update_time);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}