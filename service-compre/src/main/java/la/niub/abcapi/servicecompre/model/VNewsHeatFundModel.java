package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class VNewsHeatFundModel implements Serializable {
    private Long code;

    private String dict_word;

    private BigDecimal heat;

    private String partition_time;

    private Date createtime;

    private static final long serialVersionUID = 1L;

    public VNewsHeatFundModel(Long code, String dict_word, BigDecimal heat, String partition_time, Date createtime) {
        this.code = code;
        this.dict_word = dict_word;
        this.heat = heat;
        this.partition_time = partition_time;
        this.createtime = createtime;
    }

    public VNewsHeatFundModel() {
        super();
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getDict_word() {
        return dict_word;
    }

    public void setDict_word(String dict_word) {
        this.dict_word = dict_word == null ? null : dict_word.trim();
    }

    public BigDecimal getHeat() {
        return heat;
    }

    public void setHeat(BigDecimal heat) {
        this.heat = heat;
    }

    public String getPartition_time() {
        return partition_time;
    }

    public void setPartition_time(String partition_time) {
        this.partition_time = partition_time == null ? null : partition_time.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", code=").append(code);
        sb.append(", dict_word=").append(dict_word);
        sb.append(", heat=").append(heat);
        sb.append(", partition_time=").append(partition_time);
        sb.append(", createtime=").append(createtime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}