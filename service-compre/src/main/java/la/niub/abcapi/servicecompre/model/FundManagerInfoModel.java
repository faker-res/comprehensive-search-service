package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class FundManagerInfoModel implements Serializable {
    private Long peo_uni_code;

    private Integer sex_par;

    private Integer age ;

    private String high_edu;

    private String oss_path;

    private String fund_manager_name;

    private Date fund_manager_beg_date;

    private Long fund_manager_tot_days;

    private String present_comp_name;

    private Long fund_count;

    private Long present_comp_fund_count;

    private Long comp_count;

    private BigDecimal job_hopping_freq;

    private BigDecimal fund_manage_nav;

    private BigDecimal tenure_avg_annual_yield;

    private BigDecimal avg_ir;

    private BigDecimal avg_ir_pct;

    private BigDecimal tenure_avg_annual_yield_index;

    private static final long serialVersionUID = 1L;

    public FundManagerInfoModel(Long peo_uni_code, Integer sex_par, Integer age, String high_edu, String oss_path, String fund_manager_name, Date fund_manager_beg_date, Long fund_manager_tot_days, String present_comp_name, Long fund_count, Long present_comp_fund_count, Long comp_count, BigDecimal job_hopping_freq, BigDecimal fund_manage_nav, BigDecimal tenure_avg_annual_yield, BigDecimal avg_ir, BigDecimal avg_ir_pct, BigDecimal tenure_avg_annual_yield_index) {
        this.peo_uni_code = peo_uni_code;
        this.sex_par = sex_par;
        this.age = age;
        this.high_edu = high_edu;
        this.oss_path = oss_path;
        this.fund_manager_name = fund_manager_name;
        this.fund_manager_beg_date = fund_manager_beg_date;
        this.fund_manager_tot_days = fund_manager_tot_days;
        this.present_comp_name = present_comp_name;
        this.fund_count = fund_count;
        this.present_comp_fund_count = present_comp_fund_count;
        this.comp_count = comp_count;
        this.job_hopping_freq = job_hopping_freq;
        this.fund_manage_nav = fund_manage_nav;
        this.tenure_avg_annual_yield = tenure_avg_annual_yield;
        this.avg_ir = avg_ir;
        this.avg_ir_pct = avg_ir_pct;
        this.tenure_avg_annual_yield_index = tenure_avg_annual_yield_index;
    }

    public FundManagerInfoModel() {
        super();
    }

    public Long getPeo_uni_code() {
        return peo_uni_code;
    }

    public void setPeo_uni_code(Long peo_uni_code) {
        this.peo_uni_code = peo_uni_code;
    }

    public Integer getSex_par() {
        return sex_par;
    }

    public void setSex_par(Integer sex_par) {
        this.sex_par = sex_par;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getHigh_edu() {
        return high_edu;
    }

    public void setHigh_edu(String high_edu) {
        this.high_edu = high_edu;
    }

    public String getOss_path() {
        return oss_path;
    }

    public void setOss_path(String oss_path) {
        this.oss_path = oss_path;
    }

    public String getFund_manager_name() {
        return fund_manager_name;
    }

    public void setFund_manager_name(String fund_manager_name) {
        this.fund_manager_name = fund_manager_name;
    }

    public Date getFund_manager_beg_date() {
        return fund_manager_beg_date;
    }

    public void setFund_manager_beg_date(Date fund_manager_beg_date) {
        this.fund_manager_beg_date = fund_manager_beg_date;
    }

    public Long getFund_manager_tot_days() {
        return fund_manager_tot_days;
    }

    public void setFund_manager_tot_days(Long fund_manager_tot_days) {
        this.fund_manager_tot_days = fund_manager_tot_days;
    }

    public String getPresent_comp_name() {
        return present_comp_name;
    }

    public void setPresent_comp_name(String present_comp_name) {
        this.present_comp_name = present_comp_name;
    }

    public Long getFund_count() {
        return fund_count;
    }

    public void setFund_count(Long fund_count) {
        this.fund_count = fund_count;
    }

    public Long getPresent_comp_fund_count() {
        return present_comp_fund_count;
    }

    public void setPresent_comp_fund_count(Long present_comp_fund_count) {
        this.present_comp_fund_count = present_comp_fund_count;
    }

    public Long getComp_count() {
        return comp_count;
    }

    public void setComp_count(Long comp_count) {
        this.comp_count = comp_count;
    }

    public BigDecimal getJob_hopping_freq() {
        return job_hopping_freq;
    }

    public void setJob_hopping_freq(BigDecimal job_hopping_freq) {
        this.job_hopping_freq = job_hopping_freq;
    }

    public BigDecimal getFund_manage_nav() {
        return fund_manage_nav;
    }

    public void setFund_manage_nav(BigDecimal fund_manage_nav) {
        this.fund_manage_nav = fund_manage_nav;
    }

    public BigDecimal getTenure_avg_annual_yield() {
        return tenure_avg_annual_yield;
    }

    public void setTenure_avg_annual_yield(BigDecimal tenure_avg_annual_yield) {
        this.tenure_avg_annual_yield = tenure_avg_annual_yield;
    }

    public BigDecimal getAvg_ir() {
        return avg_ir;
    }

    public void setAvg_ir(BigDecimal avg_ir) {
        this.avg_ir = avg_ir;
    }

    public BigDecimal getAvg_ir_pct() {
        return avg_ir_pct;
    }

    public void setAvg_ir_pct(BigDecimal avg_ir_pct) {
        this.avg_ir_pct = avg_ir_pct;
    }

    public BigDecimal getTenure_avg_annual_yield_index() {
        return tenure_avg_annual_yield_index;
    }

    public void setTenure_avg_annual_yield_index(BigDecimal tenure_avg_annual_yield_index) {
        this.tenure_avg_annual_yield_index = tenure_avg_annual_yield_index;
    }

    @Override
    public String toString() {
        return "FundManagerInfoModel{" +
                "peo_uni_code=" + peo_uni_code +
                ", sex_par=" + sex_par +
                ", age=" + age +
                ", high_edu='" + high_edu + '\'' +
                ", oss_path='" + oss_path + '\'' +
                ", fund_manager_name='" + fund_manager_name + '\'' +
                ", fund_manager_beg_date=" + fund_manager_beg_date +
                ", fund_manager_tot_days=" + fund_manager_tot_days +
                ", present_comp_name='" + present_comp_name + '\'' +
                ", fund_count=" + fund_count +
                ", present_comp_fund_count=" + present_comp_fund_count +
                ", comp_count=" + comp_count +
                ", job_hopping_freq=" + job_hopping_freq +
                ", fund_manage_nav=" + fund_manage_nav +
                ", tenure_avg_annual_yield=" + tenure_avg_annual_yield +
                ", avg_ir=" + avg_ir +
                ", avg_ir_pct=" + avg_ir_pct +
                ", tenure_avg_annual_yield_index=" + tenure_avg_annual_yield_index +
                '}';
    }
}