package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;

public class WechatOperationModel extends WechatOperationKey implements Serializable {
    private Integer total_read_num;

    private Integer top_read_num;

    private static final long serialVersionUID = 1L;

    public WechatOperationModel(Long wechat_id, Integer date, Integer total_read_num, Integer top_read_num) {
        super(wechat_id, date);
        this.total_read_num = total_read_num;
        this.top_read_num = top_read_num;
    }

    public WechatOperationModel() {
        super();
    }

    public Integer getTotal_read_num() {
        return total_read_num;
    }

    public void setTotal_read_num(Integer total_read_num) {
        this.total_read_num = total_read_num;
    }

    public Integer getTop_read_num() {
        return top_read_num;
    }

    public void setTop_read_num(Integer top_read_num) {
        this.top_read_num = top_read_num;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", total_read_num=").append(total_read_num);
        sb.append(", top_read_num=").append(top_read_num);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}