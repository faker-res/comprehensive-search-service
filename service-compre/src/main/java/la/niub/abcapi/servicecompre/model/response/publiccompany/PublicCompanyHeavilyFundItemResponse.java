package la.niub.abcapi.servicecompre.model.response.publiccompany;

import java.util.List;

public class PublicCompanyHeavilyFundItemResponse {
    private String fundName;

    private String fundCode;

    private float position;

    private float diff;

    private float price;

    private String type;

    private List<Float[]> trend;

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }

    public float getPosition() {
        return position;
    }

    public void setPosition(float position) {
        this.position = position;
    }

    public float getDiff() {
        return diff;
    }

    public void setDiff(float diff) {
        this.diff = diff;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Float[]> getTrend() {
        return trend;
    }

    public void setTrend(List<Float[]> trend) {
        this.trend = trend;
    }
}
