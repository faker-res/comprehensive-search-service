package la.niub.abcapi.servicecompre.model.bo.fund;

import java.math.BigDecimal;
import java.util.Date;

public class FundManagerBO {

    private Long id;

    //职位
    private String position;

    //人名
    private String name;

    //公司名
    private String company_name;

    //头像
    private String head_img;

    //入职时间
    private Date start_time;

    //离职时间
    private Date end_time;

    //累计任职天数
    private Integer office_term;

    //回报率
    private Double return_ratio;

    //管理资产总规模
    private BigDecimal amount;

    //整体排名
    private Integer rank;

    //获奖
    private String honor;

    //研报数量
    private Integer report_num;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getHead_img() {
        return head_img;
    }

    public void setHead_img(String head_img) {
        this.head_img = head_img;
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

    public Integer getOffice_term() {
        return office_term;
    }

    public void setOffice_term(Integer office_term) {
        this.office_term = office_term;
    }

    public Double getReturn_ratio() {
        return return_ratio;
    }

    public void setReturn_ratio(Double return_ratio) {
        this.return_ratio = return_ratio;
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

    public String getHonor() {
        return honor;
    }

    public void setHonor(String honor) {
        this.honor = honor;
    }

    public Integer getReport_num() {
        return report_num;
    }

    public void setReport_num(Integer report_num) {
        this.report_num = report_num;
    }

    @Override
    public String toString() {
        return "FundManagerBO{" +
                "id=" + id +
                ", position='" + position + '\'' +
                ", name='" + name + '\'' +
                ", company_name='" + company_name + '\'' +
                ", head_img='" + head_img + '\'' +
                ", start_time=" + start_time +
                ", end_time=" + end_time +
                ", office_term=" + office_term +
                ", return_ratio=" + return_ratio +
                ", amount=" + amount +
                ", rank=" + rank +
                ", honor='" + honor + '\'' +
                ", report_num=" + report_num +
                '}';
    }
}
