package la.niub.abcapi.servicecompre.model.bo.wechat;

public class WechatTagindexIndexBO {

    private int time;

    private Integer num;

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "WechatTagindexIndexBO{" +
                "time=" + time +
                ", num=" + num +
                '}';
    }
}
