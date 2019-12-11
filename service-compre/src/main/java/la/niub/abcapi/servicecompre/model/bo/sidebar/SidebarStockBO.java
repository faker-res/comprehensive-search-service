package la.niub.abcapi.servicecompre.model.bo.sidebar;

public class SidebarStockBO extends SidebarBO{

    private SidebarStockCompanyBO company;

    private SidebarStockExpertBO stock_expert;

    public SidebarStockCompanyBO getCompany() {
        return company;
    }

    public void setCompany(SidebarStockCompanyBO company) {
        this.company = company;
    }

    public SidebarStockExpertBO getStock_expert() {
        return stock_expert;
    }

    public void setStock_expert(SidebarStockExpertBO stock_expert) {
        this.stock_expert = stock_expert;
    }

    @Override
    public String toString() {
        return "SidebarStockBO{" +
                "company=" + company +
                ", stock_expert=" + stock_expert +
                '}';
    }
}
