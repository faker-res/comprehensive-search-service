package la.niub.abcapi.servicecompre.model.bo.theme;

import com.alibaba.fastjson.JSONObject;

public class ListedPlateDistBO {

    JSONObject countInPlate;

    String tip;

    public JSONObject getCountInPlate() {
        return countInPlate;
    }

    public void setCountInPlate(JSONObject countInPlate) {
        this.countInPlate = countInPlate;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }
}
