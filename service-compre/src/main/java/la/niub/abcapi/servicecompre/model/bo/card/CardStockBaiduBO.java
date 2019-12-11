package la.niub.abcapi.servicecompre.model.bo.card;

import java.util.Map;

public class CardStockBaiduBO {

    private Map<String,Long> item;

    private Long cur_index;

    private Float rate;

    public Map<String, Long> getItem() {
        return item;
    }

    public void setItem(Map<String, Long> item) {
        this.item = item;
    }

    public Long getCur_index() {
        return cur_index;
    }

    public void setCur_index(Long cur_index) {
        this.cur_index = cur_index;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "CardStockBaiduBO{" +
                "item=" + item +
                ", cur_index='" + cur_index + '\'' +
                ", rate=" + rate +
                '}';
    }
}
