package la.niub.abcapi.servicecompre.model.response.fund;

public class FundEarnResponse {

    private String title;

    private Double earn;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getEarn() {
        return earn;
    }

    public void setEarn(Double earn) {
        this.earn = earn;
    }

    @Override
    public String toString() {
        return "FundEarnResponse{" +
                "title='" + title + '\'' +
                ", earn=" + earn +
                '}';
    }
}
