package la.niub.abcapi.servicecompre.model.bo.publiccompany;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class PublicCompanyDifferBO {

    private String name;

    private String code;

    private Long sec_uni_code;

    private Double price;

    private Double differ_range;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date date;

    private Long com_uni_code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getSec_uni_code() {
        return sec_uni_code;
    }

    public void setSec_uni_code(Long sec_uni_code) {
        this.sec_uni_code = sec_uni_code;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDiffer_range() {
        return differ_range;
    }

    public void setDiffer_range(Double differ_range) {
        this.differ_range = differ_range;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getCom_uni_code() {
        return com_uni_code;
    }

    public void setCom_uni_code(Long com_uni_code) {
        this.com_uni_code = com_uni_code;
    }

    @Override
    public String toString() {
        return "PublicCompanyDifferBO{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", sec_uni_code=" + sec_uni_code +
                ", price=" + price +
                ", differ_range=" + differ_range +
                ", date=" + date +
                '}';
    }
}
