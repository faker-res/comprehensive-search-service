package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AnalystForcastWithCount extends AnalystForcastModel implements Serializable {

    private Integer count;

    public AnalystForcastWithCount(Integer id, Long report_id, Date time, String stockcode, String stockname, String industry_id, String publish, String analyst, String sac, String peo_uni_code, String rating, String rating_adjust, BigDecimal target_price, BigDecimal target_price_high, Integer length_cover, Integer reach_days, BigDecimal reach_days_average, BigDecimal price_diff_day, Integer eps_ahead, BigDecimal eps_difference, Date create_time, Date update_time, Integer count) {
        super(id, report_id, time, stockcode, stockname, industry_id, publish, analyst, sac, peo_uni_code, rating, rating_adjust, target_price, target_price_high, length_cover, reach_days, reach_days_average, price_diff_day, eps_ahead, eps_difference, create_time, update_time);
        this.count = count;
    }

    public AnalystForcastWithCount() {
        super();
    }

    @Override
    public Integer getCount() {
        return count;
    }

    @Override
    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "AnalystForcastWithCount{" +
                "count=" + count +
                '}';
    }
}