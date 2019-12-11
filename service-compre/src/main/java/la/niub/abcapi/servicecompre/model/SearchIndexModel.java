package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;
import java.util.Date;

public class SearchIndexModel implements Serializable {
    private Long keyword_id;

    private String keyword;

    private String keyword_type;

    private String country;

    private String province;

    private String city;

    private String index_frequency;

    private Date createtime;

    private Date updatetime;

    private Long plate_uni_code;

    private String status;

    private static final long serialVersionUID = 1L;

    public SearchIndexModel(Long keyword_id, String keyword, String keyword_type, String country, String province, String city, String index_frequency, Date createtime, Date updatetime, Long plate_uni_code, String status) {
        this.keyword_id = keyword_id;
        this.keyword = keyword;
        this.keyword_type = keyword_type;
        this.country = country;
        this.province = province;
        this.city = city;
        this.index_frequency = index_frequency;
        this.createtime = createtime;
        this.updatetime = updatetime;
        this.plate_uni_code = plate_uni_code;
        this.status = status;
    }

    public SearchIndexModel() {
        super();
    }

    public Long getKeyword_id() {
        return keyword_id;
    }

    public void setKeyword_id(Long keyword_id) {
        this.keyword_id = keyword_id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword == null ? null : keyword.trim();
    }

    public String getKeyword_type() {
        return keyword_type;
    }

    public void setKeyword_type(String keyword_type) {
        this.keyword_type = keyword_type == null ? null : keyword_type.trim();
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

    public String getIndex_frequency() {
        return index_frequency;
    }

    public void setIndex_frequency(String index_frequency) {
        this.index_frequency = index_frequency == null ? null : index_frequency.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Long getPlate_uni_code() {
        return plate_uni_code;
    }

    public void setPlate_uni_code(Long plate_uni_code) {
        this.plate_uni_code = plate_uni_code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", keyword_id=").append(keyword_id);
        sb.append(", keyword=").append(keyword);
        sb.append(", keyword_type=").append(keyword_type);
        sb.append(", country=").append(country);
        sb.append(", province=").append(province);
        sb.append(", city=").append(city);
        sb.append(", index_frequency=").append(index_frequency);
        sb.append(", createtime=").append(createtime);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", plate_uni_code=").append(plate_uni_code);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}