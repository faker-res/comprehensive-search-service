package la.niub.abcapi.servicecompre.model.bo.card;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.Map;

public class CardStockSharePriceChangeBO {

    private Map<String,CardStockSharePriceChangeCompanyBO> company;

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date update_time;

    public Map<String, CardStockSharePriceChangeCompanyBO> getCompany() {
        return company;
    }

    public void setCompany(Map<String, CardStockSharePriceChangeCompanyBO> company) {
        this.company = company;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    @Override
    public String toString() {
        return "CardStockSharePriceChangeBO{" +
                "company=" + company +
                ", update_time='" + update_time + '\'' +
                '}';
    }
}
