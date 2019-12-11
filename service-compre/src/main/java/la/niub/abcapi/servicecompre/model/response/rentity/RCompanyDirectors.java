package la.niub.abcapi.servicecompre.model.response.rentity;

import java.util.Date;

//公司高管与董事
public class RCompanyDirectors {
    //姓名
    private String led_name;
    //职务
    private String post_name;
    //任职日期
    private String in_date;
    //离职日期
    private String off_date;
    //性别
    private String sex_par;
    //国籍
    private String nati_name;
    //学历
    private String high_edu;
    //出生年份
    private String birth_day;
    //简历
    private String back_gro;

    public String getLed_name() {
        return led_name;
    }

    public void setLed_name(String led_name) {
        this.led_name = led_name;
    }

    public String getPost_name() {
        return post_name;
    }

    public void setPost_name(String post_name) {
        this.post_name = post_name;
    }

    public String getIn_date() {
        return in_date;
    }

    public void setIn_date(String in_date) {
        this.in_date = in_date;
    }

    public String getOff_date() {
        return off_date;
    }

    public void setOff_date(String off_date) {
        this.off_date = off_date;
    }

    public String getSex_par() {
        return sex_par;
    }

    public void setSex_par(String sex_par) {
        this.sex_par = sex_par;
    }

    public String getNati_name() {
        return nati_name;
    }

    public void setNati_name(String nati_name) {
        this.nati_name = nati_name;
    }

    public String getHigh_edu() {
        return high_edu;
    }

    public void setHigh_edu(String high_edu) {
        this.high_edu = high_edu;
    }

    public String getBirth_day() {
        return birth_day;
    }

    public void setBirth_day(String birth_day) {
        this.birth_day = birth_day;
    }

    public String getBack_gro() {
        return back_gro;
    }

    public void setBack_gro(String back_gro) {
        this.back_gro = back_gro;
    }

    public RCompanyDirectors(String led_name, String post_name, String in_date, String off_date, String sex_par, String nati_name, String high_edu, String birth_day, String back_gro) {
        this.led_name = led_name;
        this.post_name = post_name;
        this.in_date = in_date;
        this.off_date = off_date;
        this.sex_par = sex_par;
        this.nati_name = nati_name;
        this.high_edu = high_edu;
        this.birth_day = birth_day;
        this.back_gro = back_gro;
    }

    @Override
    public String toString() {
        return "RCompanyDirectors{" +
                "led_name='" + led_name + '\'' +
                ", post_name='" + post_name + '\'' +
                ", in_date=" + in_date +
                ", off_date=" + off_date +
                ", sex_par='" + sex_par + '\'' +
                ", nati_name='" + nati_name + '\'' +
                ", high_edu='" + high_edu + '\'' +
                ", birth_day='" + birth_day + '\'' +
                ", back_gro='" + back_gro + '\'' +
                '}';
    }
}
