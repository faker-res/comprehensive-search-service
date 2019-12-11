package la.niub.abcapi.servicecompre.model;

public class WechatPublicMoreModel extends WechatPublicModel{

    private String theme_name;

    public WechatPublicMoreModel() {
    }

    public WechatPublicMoreModel(Long id, String classify, String public_name, String public_account, String public_image, String public_summary, String public_authentication, String public_tags, String qr_code, Integer month_count, Integer source, Integer createtime, Integer updatetime, String lable, Integer follower, String tags, Integer ranking, Integer reading, String theme_name) {
        super(id, classify, public_name, public_account, public_image, public_summary, public_authentication, public_tags, qr_code, month_count, source, createtime, updatetime, lable, follower, tags, ranking, reading);
        this.theme_name = theme_name;
    }

    public String getTheme_name() {
        return theme_name;
    }

    public void setTheme_name(String theme_name) {
        this.theme_name = theme_name;
    }
}
