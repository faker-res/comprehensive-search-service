package la.niub.abcapi.servicecompre.model.bo.theme;

import com.alibaba.fastjson.JSONObject;

public class RegionDistBO {

    JSONObject countOfProv;

    String tip;

    public JSONObject getCountOfProv() {
        return countOfProv;
    }

    public void setCountOfProv(JSONObject countOfProv) {
        this.countOfProv = countOfProv;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }
}
