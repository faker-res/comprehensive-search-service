package la.niub.abcapi.servicecompre.model.bo.theme;

import com.alibaba.fastjson.JSONObject;

public class MarketStatisticsBO {

    String index_code;

    String index_name;

    JSONObject indu_index;

    JSONObject value_scale_dist;

    public String getIndex_code() {
        return index_code;
    }

    public void setIndex_code(String index_code) {
        this.index_code = index_code;
    }

    public String getIndex_name() {
        return index_name;
    }

    public void setIndex_name(String index_name) {
        this.index_name = index_name;
    }

    public JSONObject getIndu_index() {
        return indu_index;
    }

    public void setIndu_index(JSONObject indu_index) {
        this.indu_index = indu_index;
    }

    public JSONObject getValue_scale_dist() {
        return value_scale_dist;
    }

    public void setValue_scale_dist(JSONObject value_scale_dist) {
        this.value_scale_dist = value_scale_dist;
    }
}
