package la.niub.abcapi.servicecompre.model.bo.fund;

public class FundEarnBO {

    private String title;

    private Double earn;

    public FundEarnBO(String title, Double earn) {
        this.title = title;
        this.earn = earn;
    }

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
