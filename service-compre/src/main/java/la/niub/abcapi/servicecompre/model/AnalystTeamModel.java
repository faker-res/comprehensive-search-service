package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AnalystTeamModel implements Serializable {
    private Long id;

    private String current_org;

    private Long org_uni_code;

    private String analyst_team;

    private Integer report_count;

    private Integer report_quarter;

    private BigDecimal report_change;

    private Date create_time;

    private Date update_time;

    private static final long serialVersionUID = 1L;

    public AnalystTeamModel(Long id, String current_org, Long org_uni_code, String analyst_team, Integer report_count, Integer report_quarter, BigDecimal report_change, Date create_time, Date update_time) {
        this.id = id;
        this.current_org = current_org;
        this.org_uni_code = org_uni_code;
        this.analyst_team = analyst_team;
        this.report_count = report_count;
        this.report_quarter = report_quarter;
        this.report_change = report_change;
        this.create_time = create_time;
        this.update_time = update_time;
    }

    public AnalystTeamModel() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCurrent_org() {
        return current_org;
    }

    public void setCurrent_org(String current_org) {
        this.current_org = current_org == null ? null : current_org.trim();
    }

    public Long getOrg_uni_code() {
        return org_uni_code;
    }

    public void setOrg_uni_code(Long org_uni_code) {
        this.org_uni_code = org_uni_code;
    }

    public String getAnalyst_team() {
        return analyst_team;
    }

    public void setAnalyst_team(String analyst_team) {
        this.analyst_team = analyst_team == null ? null : analyst_team.trim();
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

    public BigDecimal getReport_change() {
        return report_change;
    }

    public void setReport_change(BigDecimal report_change) {
        this.report_change = report_change;
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
        sb.append(", current_org=").append(current_org);
        sb.append(", org_uni_code=").append(org_uni_code);
        sb.append(", analyst_team=").append(analyst_team);
        sb.append(", report_count=").append(report_count);
        sb.append(", report_quarter=").append(report_quarter);
        sb.append(", report_change=").append(report_change);
        sb.append(", create_time=").append(create_time);
        sb.append(", update_time=").append(update_time);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}