package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class NewsSentimentFundModel implements Serializable {
    private Integer id;

    private String dict_word;

    private BigDecimal polarity;

    private Date createtime;

    private String partition_time;

    private static final long serialVersionUID = 1L;

    public NewsSentimentFundModel(Integer id, String dict_word, BigDecimal polarity, Date createtime, String partition_time) {
        this.id = id;
        this.dict_word = dict_word;
        this.polarity = polarity;
        this.createtime = createtime;
        this.partition_time = partition_time;
    }

    public NewsSentimentFundModel() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getPartition_time() {
        return partition_time;
    }

    public void setPartition_time(String partition_time) {
        this.partition_time = partition_time == null ? null : partition_time.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", dict_word=").append(dict_word);
        sb.append(", polarity=").append(polarity);
        sb.append(", createtime=").append(createtime);
        sb.append(", partition_time=").append(partition_time);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}