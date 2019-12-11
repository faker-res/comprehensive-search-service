package la.niub.abcapi.servicecompre.model.bo.fund;

import java.util.Date;

public class FundDetailBO {

    //代码
    private String code;

    //名称
    private String name;

    //全称
    private String fullname;

    //基金类型
    private String type;

    //申购赎回状态
    private String redem;

    //运作方式
    private String method;

    //业绩基准
    private String benchmark;

    //风险等级
    private String risk;

    //成立日期
    private Date establishTime;

    private Boolean isExpired;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRedem() {
        return redem;
    }

    public void setRedem(String redem) {
        this.redem = redem;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getBenchmark() {
        return benchmark;
    }

    public void setBenchmark(String benchmark) {
        this.benchmark = benchmark;
    }

    public String getRisk() {
        return risk;
    }

    public void setRisk(String risk) {
        this.risk = risk;
    }

    public Date getEstablishTime() {
        return establishTime;
    }

    public void setEstablishTime(Date establishTime) {
        this.establishTime = establishTime;
    }

    public Boolean getIsExpired() {
        return isExpired;
    }

    public void setIsExpired(Boolean expired) {
        isExpired = expired;
    }

    @Override
    public String toString() {
        return "FundDetailBO{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", fullname='" + fullname + '\'' +
                ", type='" + type + '\'' +
                ", redem='" + redem + '\'' +
                ", method='" + method + '\'' +
                ", benchmark='" + benchmark + '\'' +
                ", risk='" + risk + '\'' +
                ", establishTime='" + establishTime + '\'' +
                '}';
    }
}
