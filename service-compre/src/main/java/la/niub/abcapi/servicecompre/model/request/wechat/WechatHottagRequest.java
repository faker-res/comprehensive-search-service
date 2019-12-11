package la.niub.abcapi.servicecompre.model.request.wechat;

import la.niub.abcapi.servicecompre.config.enums.WechatPeriodEnum;

public class WechatHottagRequest {

    private Long code;

    private String period = WechatPeriodEnum.W1.name();

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    @Override
    public String toString() {
        return "WechatHottagRequest{" +
                "code=" + code +
                ", period='" + period + '\'' +
                '}';
    }
}
