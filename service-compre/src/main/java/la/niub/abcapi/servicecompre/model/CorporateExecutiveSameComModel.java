package la.niub.abcapi.servicecompre.model;

public class CorporateExecutiveSameComModel {

    private Long peo_uni_code;

    private Long com_uni_code;

    private String led_name;

    private String post_name;

    private String oss_path;

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

    public String getPost_name() {
        return post_name;
    }

    public void setPost_name(String post_name) {
        this.post_name = post_name;
    }

    public String getOss_path() {
        return oss_path;
    }

    public void setOss_path(String oss_path) {
        this.oss_path = oss_path;
    }

    public CorporateExecutiveSameComModel(Long peo_uni_code, Long com_uni_code, String led_name, String post_name, String oss_path) {
        this.peo_uni_code = peo_uni_code;
        this.com_uni_code = com_uni_code;
        this.led_name = led_name;
        this.post_name = post_name;
        this.oss_path = oss_path;
    }

    public CorporateExecutiveSameComModel() {
    }

    @Override
    public String toString() {
        return "CorporateExecutiveSameComModel{" +
                "peo_uni_code=" + peo_uni_code +
                ", com_uni_code=" + com_uni_code +
                ", led_name='" + led_name + '\'' +
                ", post_name='" + post_name + '\'' +
                ", oss_path='" + oss_path + '\'' +
                '}';
    }
}
