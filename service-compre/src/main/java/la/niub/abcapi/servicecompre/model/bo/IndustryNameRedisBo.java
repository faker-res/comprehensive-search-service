package la.niub.abcapi.servicecompre.model.bo;

import java.util.Map;

public class IndustryNameRedisBo {
    private Map<String, String> first;

    private Map<String, String> second;

    private Map<String, String> third;

    public Map<String, String> getFirst() {
        return first;
    }

    public void setFirst(Map<String, String> first) {
        this.first = first;
    }

    public Map<String, String> getSecond() {
        return second;
    }

    public void setSecond(Map<String, String> second) {
        this.second = second;
    }

    public Map<String, String> getThird() {
        return third;
    }

    public void setThird(Map<String, String> third) {
        this.third = third;
    }
}
