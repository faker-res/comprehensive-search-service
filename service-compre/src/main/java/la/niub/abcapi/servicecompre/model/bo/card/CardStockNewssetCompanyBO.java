package la.niub.abcapi.servicecompre.model.bo.card;

public class CardStockNewssetCompanyBO {

    private String stock_code;

    private String abc_code;

    private String industry;

    private String stock_name;

    public String getStock_code() {
        return stock_code;
    }

    public void setStock_code(String stock_code) {
        this.stock_code = stock_code;
    }

    public String getAbc_code() {
        return abc_code;
    }

    public void setAbc_code(String abc_code) {
        this.abc_code = abc_code;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getStock_name() {
        return stock_name;
    }

    public void setStock_name(String stock_name) {
        this.stock_name = stock_name;
    }

    @Override
    public String toString() {
        return "CardStockNewssetCompanyBO{" +
                "stock_code='" + stock_code + '\'' +
                ", abc_code='" + abc_code + '\'' +
                ", industry='" + industry + '\'' +
                ", stock_name='" + stock_name + '\'' +
                '}';
    }
}
