package la.niub.abcapi.servicecompre.model.bo.card;

import java.util.Date;

public class CardStockNewssetBO {

    private Date time;

    private CardStockNewssetCompanyBO company_data;

    private Object stock_data;

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public CardStockNewssetCompanyBO getCompany_data() {
        return company_data;
    }

    public void setCompany_data(CardStockNewssetCompanyBO company_data) {
        this.company_data = company_data;
    }

    public Object getStock_data() {
        return stock_data;
    }

    public void setStock_data(Object stock_data) {
        this.stock_data = stock_data;
    }

    @Override
    public String toString() {
        return "CardStockNewssetBO{" +
                "time=" + time +
                ", company_data=" + company_data +
                ", stock_data=" + stock_data +
                '}';
    }
}
