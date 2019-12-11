package la.niub.abcapi.servicecompre.model.bo.theme;

import java.util.List;
import java.util.Map;

public class MarketValueScaleBO {

    List<Map<String, Integer>> scale_dist;

    String tip;

    public List<Map<String, Integer>> getScale_dist() {
        return scale_dist;
    }

    public void setScale_dist(List<Map<String, Integer>> scale_dist) {
        this.scale_dist = scale_dist;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }
}
