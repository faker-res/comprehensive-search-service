package la.niub.abcapi.servicecompre.model.dao;

public class HiborGroupByDaoModel {

    private int count;

    private String industryId;

    private String stockcode;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getIndustryId() {
        return industryId;
    }

    public void setIndustryId(String industryId) {
        this.industryId = industryId;
    }

    public String getStockcode() {
        return stockcode;
    }

    public void setStockcode(String stockcode) {
        this.stockcode = stockcode;
    }

    @Override
    public String toString() {
        return "HiborGroupByDaoModel{" +
                "count=" + count +
                ", industryId='" + industryId + '\'' +
                ", stockcode='" + stockcode + '\'' +
                '}';
    }
}
