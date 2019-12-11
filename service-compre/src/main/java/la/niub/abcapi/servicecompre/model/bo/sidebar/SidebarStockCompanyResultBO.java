package la.niub.abcapi.servicecompre.model.bo.sidebar;

import la.niub.abcapi.servicecompre.model.bo.card.CardStockNewssetBO;

public class SidebarStockCompanyResultBO {

    private String sec_name;

    private String abc_code;

    private Long sec_uni_code;

    private Long com_uni_code;

    private CardStockNewssetBO stock_newsset;

    public String getSec_name() {
        return sec_name;
    }

    public void setSec_name(String sec_name) {
        this.sec_name = sec_name;
    }

    public String getAbc_code() {
        return abc_code;
    }

    public void setAbc_code(String abc_code) {
        this.abc_code = abc_code;
    }

    public Long getSec_uni_code() {
        return sec_uni_code;
    }

    public void setSec_uni_code(Long sec_uni_code) {
        this.sec_uni_code = sec_uni_code;
    }

    public CardStockNewssetBO getStock_newsset() {
        return stock_newsset;
    }

    public void setStock_newsset(CardStockNewssetBO stock_newsset) {
        this.stock_newsset = stock_newsset;
    }

    public Long getCom_uni_code() {
        return com_uni_code;
    }

    public void setCom_uni_code(Long com_uni_code) {
        this.com_uni_code = com_uni_code;
    }

    @Override
    public String toString() {
        return "SidebarStockCompanyResultBO{" +
                "sec_name='" + sec_name + '\'' +
                ", abc_code='" + abc_code + '\'' +
                ", sec_uni_code='" + sec_uni_code + '\'' +
                ", stock_newsset=" + stock_newsset +
                '}';
    }
}
