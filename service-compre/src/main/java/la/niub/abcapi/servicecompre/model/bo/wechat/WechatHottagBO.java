package la.niub.abcapi.servicecompre.model.bo.wechat;

public class WechatHottagBO {

    private String name;

    private Float rate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "WechatHottagBO{" +
                "name='" + name + '\'' +
                ", rate=" + rate +
                '}';
    }
}
