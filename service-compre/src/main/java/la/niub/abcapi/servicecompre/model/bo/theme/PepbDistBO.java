package la.niub.abcapi.servicecompre.model.bo.theme;

import java.math.BigDecimal;
import java.util.Date;

public class PepbDistBO {

    Long sec_uni_code;

    BigDecimal pe;

    BigDecimal pb;

    Date account_date;

    public PepbDistBO() {
    }

    public PepbDistBO(Long sec_uni_code, BigDecimal pe, BigDecimal pb, Date account_date) {
        this.sec_uni_code = sec_uni_code;
        this.pe = pe;
        this.pb = pb;
        this.account_date = account_date;
    }

    public Long getSec_uni_code() {
        return sec_uni_code;
    }

    public void setSec_uni_code(Long sec_uni_code) {
        this.sec_uni_code = sec_uni_code;
    }

    public BigDecimal getPe() {
        return pe;
    }

    public void setPe(BigDecimal pe) {
        this.pe = pe;
    }

    public BigDecimal getPb() {
        return pb;
    }

    public void setPb(BigDecimal pb) {
        this.pb = pb;
    }

    public Date getAccount_date() {
        return account_date;
    }

    public void setAccount_date(Date account_date) {
        this.account_date = account_date;
    }
}
