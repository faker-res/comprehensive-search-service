package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;

public class CorporateExecutiveBasicInfoModel implements Serializable{

    private Long peo_uni_code;

    private Long com_uni_code;

    private String led_name;

    private String birth_day;

    private String country;

    private String ename;

    private String occupation;

    private String main_achivement;

    private String back_gro;

    private String per_resume;

//    private String post_name;

    private String oss_path;

    private String bdbk_url;

    private static final long serialVersionUID = 1L;

//    private List<SameComCorporateExecutiveBO> sameComCorporateExecutiveBOList;

//    private List<WorkResumeBO> workResumeBOList;

//    public CorporateExecutiveBasicInfoModel(Long peo_uni_code, Long com_uni_code, String led_name, String birth_day, String country, String en_name, String occupation, String main_achivement, String back_gro, String per_resume, String post_name/*, List<SameComCorporateExecutiveBO> sameComCorporateExecutiveBOList*/) {
//        this.peo_uni_code = peo_uni_code;
//        this.com_uni_code = com_uni_code;
//        this.led_name = led_name;
//        this.birth_day = birth_day;
//        this.country = country;
//        this.en_name = en_name;
//        this.occupation = occupation;
//        this.main_achivement = main_achivement;
//        this.back_gro = back_gro;
//        this.per_resume = per_resume;
//        this.post_name = post_name;
////        this.sameComCorporateExecutiveBOList = sameComCorporateExecutiveBOList;
//    }


    public CorporateExecutiveBasicInfoModel(Long peo_uni_code, Long com_uni_code, String led_name, String birth_day, String country, String ename, String occupation, String main_achivement, String back_gro, String per_resume, String oss_path) {
        this.peo_uni_code = peo_uni_code;
        this.com_uni_code = com_uni_code;
        this.led_name = led_name;
        this.birth_day = birth_day;
        this.country = country;
        this.ename = ename;
        this.occupation = occupation;
        this.main_achivement = main_achivement;
        this.back_gro = back_gro;
        this.per_resume = per_resume;
//        this.post_name = post_name;
        this.oss_path = oss_path;
    }

//    public CorporateExecutiveBasicInfoModel(Long peo_uni_code, Long com_uni_code, String led_name, String birth_day, String country, String ename, String occupation, String main_achivement, String back_gro, String per_resume, String post_name, List<SameComCorporateExecutiveBO> sameComCorporateExecutiveBOList) {
//        this.peo_uni_code = peo_uni_code;
//        this.com_uni_code = com_uni_code;
//        this.led_name = led_name;
//        this.birth_day = birth_day;
//        this.country = country;
//        this.ename = ename;
//        this.occupation = occupation;
//        this.main_achivement = main_achivement;
//        this.back_gro = back_gro;
//        this.per_resume = per_resume;
//        this.post_name = post_name;
//        this.sameComCorporateExecutiveBOList = sameComCorporateExecutiveBOList;
//    }

    public CorporateExecutiveBasicInfoModel() {
        super();
    }

    public Long getPeo_uni_code() {
        return peo_uni_code;
    }

    public void setPeo_uni_code(Long peo_uni_code) {
        this.peo_uni_code = peo_uni_code;
    }

    public Long getCom_uni_code() {
        return com_uni_code;
    }

    public void setCom_uni_code(Long com_uni_code) {
        this.com_uni_code = com_uni_code;
    }

    public String getLed_name() {
        return led_name;
    }

    public void setLed_name(String led_name) {
        this.led_name = led_name;
    }

    public String getBirth_day() {
        return birth_day;
    }

    public void setBirth_day(String birth_day) {
        this.birth_day = birth_day;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getMain_achivement() {
        return main_achivement;
    }

    public void setMain_achivement(String main_achivement) {
        this.main_achivement = main_achivement;
    }

    public String getBack_gro() {
        return back_gro;
    }

    public void setBack_gro(String back_gro) {
        this.back_gro = back_gro;
    }

    public String getPer_resume() {
        return per_resume;
    }

    public void setPer_resume(String per_resume) {
        this.per_resume = per_resume;
    }

//    public String getPost_name() {
//        return post_name;
//    }
//
//    public void setPost_name(String post_name) {
//        this.post_name = post_name;
//    }

    public String getOss_path() {
        return oss_path;
    }

    public void setOss_path(String oss_path) {
        this.oss_path = oss_path;
    }

    //        public List<SameComCorporateExecutiveBO> getSameComCorporateExecutiveBOList() {
//        return sameComCorporateExecutiveBOList;
//    }
//
//    public void setSameComCorporateExecutiveBOList(List<SameComCorporateExecutiveBO> sameComCorporateExecutiveBOList) {
//        this.sameComCorporateExecutiveBOList = sameComCorporateExecutiveBOList;
//    }


    public String getBdbk_url() {
        return bdbk_url;
    }

    public void setBdbk_url(String bdbk_url) {
        this.bdbk_url = bdbk_url;
    }

    @Override
    public String toString() {
        return "CorporateExecutiveBasicInfoModel{" +
                "peo_uni_code=" + peo_uni_code +
                ", com_uni_code='" + com_uni_code + '\'' +
                ", led_name='" + led_name + '\'' +
                ", birth_day='" + birth_day + '\'' +
                ", country='" + country + '\'' +
                ", ename='" + ename + '\'' +
                ", occupation='" + occupation + '\'' +
                ", main_achivement='" + main_achivement + '\'' +
                ", back_gro='" + back_gro + '\'' +
                ", per_resume=" + per_resume +
                ", bdbk_url=" + bdbk_url +
//                ", post_name=" + post_name +
                ", oss_path=" + oss_path +
                '}';
    }
}
