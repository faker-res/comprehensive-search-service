package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AnalystForcastModel implements Serializable {
    private Integer id;

    private Long report_id;

    private Date time;

    private String stockcode;

    private String stockname;

    private String industry_id;

    private String publish;

    private String analyst;

    private String sac;

    private String peo_uni_code;

    private String rating;

    private String rating_adjust;

    private BigDecimal target_price;

    private BigDecimal target_price_high;

    private Integer length_cover;

    private Integer reach_days;

    private BigDecimal reach_days_average;

    private BigDecimal price_diff_day;

    private Integer eps_ahead;

    private BigDecimal eps_difference;

    private Date create_time;

    private Date update_time;

    private Integer count;

    private static final long serialVersionUID = 1L;

    public AnalystForcastModel(Integer id, Long report_id, Date time, String stockcode, String stockname, String industry_id, String publish, String analyst, String sac, String peo_uni_code, String rating, String rating_adjust, BigDecimal target_price, BigDecimal target_price_high, Integer length_cover, Integer reach_days, BigDecimal reach_days_average, BigDecimal price_diff_day, Integer eps_ahead, BigDecimal eps_difference, Date create_time, Date update_time) {
        this.id = id;
        this.report_id = report_id;
        this.time = time;
        this.stockcode = stockcode;
        this.stockname = stockname;
        this.industry_id = industry_id;
        this.publish = publish;
        this.analyst = analyst;
        this.sac = sac;
        this.peo_uni_code = peo_uni_code;
        this.rating = rating;
        this.rating_adjust = rating_adjust;
        this.target_price = target_price;
        this.target_price_high = target_price_high;
        this.length_cover = length_cover;
        this.reach_days = reach_days;
        this.reach_days_average = reach_days_average;
        this.price_diff_day = price_diff_day;
        this.eps_ahead = eps_ahead;
        this.eps_difference = eps_difference;
        this.create_time = create_time;
        this.update_time = update_time;
    }

    public AnalystForcastModel() {
        super();
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

    public String getStockcode() {
        return stockcode;
    }

    public void setStockcode(String stockcode) {
        this.stockcode = stockcode == null ? null : stockcode.trim();
    }

    public String getStockname() {
        return stockname;
    }

    public void setStockname(String stockname) {
        this.stockname = stockname == null ? null : stockname.trim();
    }

    public String getIndustry_id() {
        return industry_id;
    }

    public void setIndustry_id(String industry_id) {
        this.industry_id = industry_id == null ? null : industry_id.trim();
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish == null ? null : publish.trim();
    }

    public String getAnalyst() {
        return analyst;
    }

    public void setAnalyst(String analyst) {
        this.analyst = analyst == null ? null : analyst.trim();
    }

    public String getSac() {
        return sac;
    }

    public void setSac(String sac) {
        this.sac = sac == null ? null : sac.trim();
    }

    public String getPeo_uni_code() {
        return peo_uni_code;
    }

    public void setPeo_uni_code(String peo_uni_code) {
        this.peo_uni_code = peo_uni_code == null ? null : peo_uni_code.trim();
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating == null ? null : rating.trim();
    }

    public String getRating_adjust() {
        return rating_adjust;
    }

    public void setRating_adjust(String rating_adjust) {
        this.rating_adjust = rating_adjust == null ? null : rating_adjust.trim();
    }

    public BigDecimal getTarget_price() {
        return target_price;
    }

    public void setTarget_price(BigDecimal target_price) {
        this.target_price = target_price;
    }

    public BigDecimal getTarget_price_high() {
        return target_price_high;
    }

    public void setTarget_price_high(BigDecimal target_price_high) {
        this.target_price_high = target_price_high;
    }

    public Integer getLength_cover() {
        return length_cover;
    }

    public void setLength_cover(Integer length_cover) {
        this.length_cover = length_cover;
    }

    public Integer getReach_days() {
        return reach_days;
    }

    public void setReach_days(Integer reach_days) {
        this.reach_days = reach_days;
    }

    public BigDecimal getReach_days_average() {
        return reach_days_average;
    }

    public void setReach_days_average(BigDecimal reach_days_average) {
        this.reach_days_average = reach_days_average;
    }

    public BigDecimal getPrice_diff_day() {
        return price_diff_day;
    }

    public void setPrice_diff_day(BigDecimal price_diff_day) {
        this.price_diff_day = price_diff_day;
    }

    public Integer getEps_ahead() {
        return eps_ahead;
    }

    public void setEps_ahead(Integer eps_ahead) {
        this.eps_ahead = eps_ahead;
    }

    public BigDecimal getEps_difference() {
        return eps_difference;
    }

    public void setEps_difference(BigDecimal eps_difference) {
        this.eps_difference = eps_difference;
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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
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
        sb.append(", stockcode=").append(stockcode);
        sb.append(", stockname=").append(stockname);
        sb.append(", industry_id=").append(industry_id);
        sb.append(", publish=").append(publish);
        sb.append(", analyst=").append(analyst);
        sb.append(", sac=").append(sac);
        sb.append(", peo_uni_code=").append(peo_uni_code);
        sb.append(", rating=").append(rating);
        sb.append(", rating_adjust=").append(rating_adjust);
        sb.append(", target_price=").append(target_price);
        sb.append(", target_price_high=").append(target_price_high);
        sb.append(", length_cover=").append(length_cover);
        sb.append(", reach_days=").append(reach_days);
        sb.append(", reach_days_average=").append(reach_days_average);
        sb.append(", price_diff_day=").append(price_diff_day);
        sb.append(", eps_ahead=").append(eps_ahead);
        sb.append(", eps_difference=").append(eps_difference);
        sb.append(", create_time=").append(create_time);
        sb.append(", update_time=").append(update_time);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}