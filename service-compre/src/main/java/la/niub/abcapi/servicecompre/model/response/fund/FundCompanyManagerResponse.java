package la.niub.abcapi.servicecompre.model.response.fund;

import java.math.BigDecimal;
import java.util.Date;

public class FundCompanyManagerResponse {

    private String id;

    private String name;

    private String image;

    private Date start_time;

    private Date end_time;

    private BigDecimal amount;

    private Integer rank;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "FundCompanyManagerResponse{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", start_time=" + start_time +
                ", end_time=" + end_time +
                ", amount=" + amount +
                ", rank=" + rank +
                '}';
    }
}
