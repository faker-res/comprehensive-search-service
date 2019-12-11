package la.niub.abcapi.servicecompre.model.bo.card;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class CardAnalystBasicInfoBO {

    private String id;

    private String image;

    private String name;

    private String organ;

    private String organ_logo;

    private Integer rank;

    private Integer report_num;

    private double report_num_rate;

    private Integer honor_num;

    private String direction;

    private String email;

    private String tel;

    private String c_id;

    private String h_direction;

    private String summary;


    @JsonFormat(pattern="yyyy",timezone = "GMT+8")
    private Date time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrgan() {
        return organ;
    }

    public void setOrgan(String organ) {
        this.organ = organ;
    }

    public String getOrgan_logo() {
        return organ_logo;
    }

    public void setOrgan_logo(String organ_logo) {
        this.organ_logo = organ_logo;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getReport_num() {
        return report_num;
    }

    public void setReport_num(Integer report_num) {
        this.report_num = report_num;
    }


    public double getReport_num_rate() {
        return report_num_rate;
    }

    public void setReport_num_rate(double report_num_rate) {
        this.report_num_rate = report_num_rate;
    }

    public Integer getHonor_num() {
        return honor_num;
    }

    public void setHonor_num(Integer honor_num) {
        this.honor_num = honor_num;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getC_id() {
        return c_id;
    }

    public void setC_id(String c_id) {
        this.c_id = c_id;
    }

    public String getH_direction() {
        return h_direction;
    }

    public void setH_direction(String h_direction) {
        this.h_direction = h_direction;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Override
    public String toString() {
        return "CardAnalystBasicInfoBO{" +
                "id='" + id + '\'' +
                ", image='" + image + '\'' +
                ", name='" + name + '\'' +
                ", organ='" + organ + '\'' +
                ", organ_logo='" + organ_logo + '\'' +
                ", rank=" + rank +
                ", report_num=" + report_num +
                ", report_num_rate=" + report_num_rate +
                ", honor_num=" + honor_num +
                ", direction='" + direction + '\'' +
                ", email='" + email + '\'' +
                ", tel='" + tel + '\'' +
                ", c_id='" + c_id + '\'' +
                ", time=" + time +
                '}';
    }
}
