package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class VNewsSentimentFundModel implements Serializable {
    private Long code;

    private String dict_word;

    private BigDecimal polarity;

    private String partition_time;

    private Date createtime;

    private static final long serialVersionUID = 1L;

    public VNewsSentimentFundModel(Long code, String dict_word, BigDecimal polarity, String partition_time, Date createtime) {
        this.code = code;
        this.dict_word = dict_word;
        this.polarity = polarity;
        this.partition_time = partition_time;
        this.createtime = createtime;
    }

    public VNewsSentimentFundModel() {
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

    public BigDecimal getPolarity() {
        return polarity;
    }

    public void setPolarity(BigDecimal polarity) {
        this.polarity = polarity;
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
        sb.append(", polarity=").append(polarity);
        sb.append(", partition_time=").append(partition_time);
        sb.append(", createtime=").append(createtime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}