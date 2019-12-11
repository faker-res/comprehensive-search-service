package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AnalystForcastIndexModel implements Serializable {
    private Integer id;

    private Long report_id;

    private Date time;

    private BigDecimal pe;

    private String year;

    private BigDecimal income;

    private BigDecimal sp;

    private BigDecimal cost;

    private BigDecimal tp;

    private BigDecimal np;

    private BigDecimal parent_np;

    private BigDecimal roe;

    private BigDecimal roa;

    private BigDecimal eps;

    private BigDecimal dps;

    private BigDecimal cps;

    private BigDecimal bvps;

    private BigDecimal ebit;

    private BigDecimal ebitda;

    private BigDecimal ev_ebitda;

    private BigDecimal equity;

    private Date create_time;

    private Date update_time;

    private static final long serialVersionUID = 1L;

    private BigDecimal eps_avg;

    public AnalystForcastIndexModel(Integer id, Long report_id, Date time, BigDecimal pe, String year, BigDecimal income, BigDecimal sp, BigDecimal cost, BigDecimal tp, BigDecimal np, BigDecimal parent_np, BigDecimal roe, BigDecimal roa, BigDecimal eps, BigDecimal dps, BigDecimal cps, BigDecimal bvps, BigDecimal ebit, BigDecimal ebitda, BigDecimal ev_ebitda, BigDecimal equity, Date create_time, Date update_time) {
        this.id = id;
        this.report_id = report_id;
        this.time = time;
        this.pe = pe;
        this.year = year;
        this.income = income;
        this.sp = sp;
        this.cost = cost;
        this.tp = tp;
        this.np = np;
        this.parent_np = parent_np;
        this.roe = roe;
        this.roa = roa;
        this.eps = eps;
        this.dps = dps;
        this.cps = cps;
        this.bvps = bvps;
        this.ebit = ebit;
        this.ebitda = ebitda;
        this.ev_ebitda = ev_ebitda;
        this.equity = equity;
        this.create_time = create_time;
        this.update_time = update_time;
    }

    public AnalystForcastIndexModel() {
        super();
    }

    public BigDecimal getEps_avg() {
        return eps_avg;
    }

    public void setEps_avg(BigDecimal eps_avg) {
        this.eps_avg = eps_avg;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getReport_id() {
        return report_id;
    }

    public void setReport_id(Long report_id) {
        this.report_id = report_id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public BigDecimal getPe() {
        return pe;
    }

    public void setPe(BigDecimal pe) {
        this.pe = pe;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year == null ? null : year.trim();
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    public BigDecimal getSp() {
        return sp;
    }

    public void setSp(BigDecimal sp) {
        this.sp = sp;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public BigDecimal getTp() {
        return tp;
    }

    public void setTp(BigDecimal tp) {
        this.tp = tp;
    }

    public BigDecimal getNp() {
        return np;
    }

    public void setNp(BigDecimal np) {
        this.np = np;
    }

    public BigDecimal getParent_np() {
        return parent_np;
    }

    public void setParent_np(BigDecimal parent_np) {
        this.parent_np = parent_np;
    }

    public BigDecimal getRoe() {
        return roe;
    }

    public void setRoe(BigDecimal roe) {
        this.roe = roe;
    }

    public BigDecimal getRoa() {
        return roa;
    }

    public void setRoa(BigDecimal roa) {
        this.roa = roa;
    }

    public BigDecimal getEps() {
        return eps;
    }

    public void setEps(BigDecimal eps) {
        this.eps = eps;
    }

    public BigDecimal getDps() {
        return dps;
    }

    public void setDps(BigDecimal dps) {
        this.dps = dps;
    }

    public BigDecimal getCps() {
        return cps;
    }

    public void setCps(BigDecimal cps) {
        this.cps = cps;
    }

    public BigDecimal getBvps() {
        return bvps;
    }

    public void setBvps(BigDecimal bvps) {
        this.bvps = bvps;
    }

    public BigDecimal getEbit() {
        return ebit;
    }

    public void setEbit(BigDecimal ebit) {
        this.ebit = ebit;
    }

    public BigDecimal getEbitda() {
        return ebitda;
    }

    public void setEbitda(BigDecimal ebitda) {
        this.ebitda = ebitda;
    }

    public BigDecimal getEv_ebitda() {
        return ev_ebitda;
    }

    public void setEv_ebitda(BigDecimal ev_ebitda) {
        this.ev_ebitda = ev_ebitda;
    }

    public BigDecimal getEquity() {
        return equity;
    }

    public void setEquity(BigDecimal equity) {
        this.equity = equity;
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
        sb.append(", report_id=").append(report_id);
        sb.append(", time=").append(time);
        sb.append(", pe=").append(pe);
        sb.append(", year=").append(year);
        sb.append(", income=").append(income);
        sb.append(", sp=").append(sp);
        sb.append(", cost=").append(cost);
        sb.append(", tp=").append(tp);
        sb.append(", np=").append(np);
        sb.append(", parent_np=").append(parent_np);
        sb.append(", roe=").append(roe);
        sb.append(", roa=").append(roa);
        sb.append(", eps=").append(eps);
        sb.append(", dps=").append(dps);
        sb.append(", cps=").append(cps);
        sb.append(", bvps=").append(bvps);
        sb.append(", ebit=").append(ebit);
        sb.append(", ebitda=").append(ebitda);
        sb.append(", ev_ebitda=").append(ev_ebitda);
        sb.append(", equity=").append(equity);
        sb.append(", create_time=").append(create_time);
        sb.append(", update_time=").append(update_time);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}