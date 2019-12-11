package la.niub.abcapi.servicecompre.model.bo.fund;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class FundCompanyHolderBO {

    private String investor_name;

    private Double shareholding_ratio;

    private String nature;

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date begin_date;

    public String getInvestor_name() {
        return investor_name;
    }

    public void setInvestor_name(String investor_name) {
        this.investor_name = investor_name;
    }

    public Double getShareholding_ratio() {
        return shareholding_ratio;
    }

    public void setShareholding_ratio(Double shareholding_ratio) {
        this.shareholding_ratio = shareholding_ratio;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public Date getBegin_date() {
        return begin_date;
    }

    public void setBegin_date(Date begin_date) {
        this.begin_date = begin_date;
    }

    @Override
    public String toString() {
        return "FundCompanyHolderBO{" +
                "investor_name='" + investor_name + '\'' +
                ", shareholding_ratio=" + shareholding_ratio +
                ", nature='" + nature + '\'' +
                ", begin_date=" + begin_date +
                '}';
    }
}
