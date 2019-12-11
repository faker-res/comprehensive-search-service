package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;
import java.util.Date;

public class FinancialIndicatorDataNewModel implements Serializable {
    private Integer id;

    private String data_id;

    private String stock_code;

    private String sec_name;

    private String indicator_1;

    private String indicator;

    private String frequency;

    private String unit;

    private String industry;

    private String country;

    private String province;

    private String city;

    private String title;

    private String date_type;

    private String account_year;

    private Date min_date;

    private Date max_date;

    private Byte push_flag;

    private Date push_time;

    private Date update_time;

    private String product;

    private String value_type;

    private static final long serialVersionUID = 1L;

    public FinancialIndicatorDataNewModel(Integer id, String data_id, String stock_code, String sec_name, String indicator_1, String indicator, String frequency, String unit, String industry, String country, String province, String city, String title, String date_type, String account_year, Date min_date, Date max_date, Byte push_flag, Date push_time, Date update_time, String product, String value_type) {
        this.id = id;
        this.data_id = data_id;
        this.stock_code = stock_code;
        this.sec_name = sec_name;
        this.indicator_1 = indicator_1;
        this.indicator = indicator;
        this.frequency = frequency;
        this.unit = unit;
        this.industry = industry;
        this.country = country;
        this.province = province;
        this.city = city;
        this.title = title;
        this.date_type = date_type;
        this.account_year = account_year;
        this.min_date = min_date;
        this.max_date = max_date;
        this.push_flag = push_flag;
        this.push_time = push_time;
        this.update_time = update_time;
        this.product = product;
        this.value_type = value_type;
    }

    public FinancialIndicatorDataNewModel() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getData_id() {
        return data_id;
    }

    public void setData_id(String data_id) {
        this.data_id = data_id == null ? null : data_id.trim();
    }

    public String getStock_code() {
        return stock_code;
    }

    public void setStock_code(String stock_code) {
        this.stock_code = stock_code == null ? null : stock_code.trim();
    }

    public String getSec_name() {
        return sec_name;
    }

    public void setSec_name(String sec_name) {
        this.sec_name = sec_name == null ? null : sec_name.trim();
    }

    public String getIndicator_1() {
        return indicator_1;
    }

    public void setIndicator_1(String indicator_1) {
        this.indicator_1 = indicator_1 == null ? null : indicator_1.trim();
    }

    public String getIndicator() {
        return indicator;
    }

    public void setIndicator(String indicator) {
        this.indicator = indicator == null ? null : indicator.trim();
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency == null ? null : frequency.trim();
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry == null ? null : industry.trim();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getDate_type() {
        return date_type;
    }

    public void setDate_type(String date_type) {
        this.date_type = date_type == null ? null : date_type.trim();
    }

    public String getAccount_year() {
        return account_year;
    }

    public void setAccount_year(String account_year) {
        this.account_year = account_year == null ? null : account_year.trim();
    }

    public Date getMin_date() {
        return min_date;
    }

    public void setMin_date(Date min_date) {
        this.min_date = min_date;
    }

    public Date getMax_date() {
        return max_date;
    }

    public void setMax_date(Date max_date) {
        this.max_date = max_date;
    }

    public Byte getPush_flag() {
        return push_flag;
    }

    public void setPush_flag(Byte push_flag) {
        this.push_flag = push_flag;
    }

    public Date getPush_time() {
        return push_time;
    }

    public void setPush_time(Date push_time) {
        this.push_time = push_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product == null ? null : product.trim();
    }

    public String getValue_type() {
        return value_type;
    }

    public void setValue_type(String value_type) {
        this.value_type = value_type == null ? null : value_type.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", data_id=").append(data_id);
        sb.append(", stock_code=").append(stock_code);
        sb.append(", sec_name=").append(sec_name);
        sb.append(", indicator_1=").append(indicator_1);
        sb.append(", indicator=").append(indicator);
        sb.append(", frequency=").append(frequency);
        sb.append(", unit=").append(unit);
        sb.append(", industry=").append(industry);
        sb.append(", country=").append(country);
        sb.append(", province=").append(province);
        sb.append(", city=").append(city);
        sb.append(", title=").append(title);
        sb.append(", date_type=").append(date_type);
        sb.append(", account_year=").append(account_year);
        sb.append(", min_date=").append(min_date);
        sb.append(", max_date=").append(max_date);
        sb.append(", push_flag=").append(push_flag);
        sb.append(", push_time=").append(push_time);
        sb.append(", update_time=").append(update_time);
        sb.append(", product=").append(product);
        sb.append(", value_type=").append(value_type);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}