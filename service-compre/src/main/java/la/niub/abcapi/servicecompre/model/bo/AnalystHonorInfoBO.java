package la.niub.abcapi.servicecompre.model.bo;

import java.io.Serializable;

public class AnalystHonorInfoBO implements Serializable{

    private String peo_uni_code;

    private Integer time;

    private Integer ranking;

    private String honor;

    public AnalystHonorInfoBO() {
        super();
    }

    public AnalystHonorInfoBO(String peo_uni_code, Integer time, Integer ranking, String honor) {
        this.peo_uni_code = peo_uni_code;
        this.time = time;
        this.ranking = ranking;
        this.honor = honor;
    }

    public String getPeo_uni_code() {
        return peo_uni_code;
    }

    public void setPeo_uni_code(String peo_uni_code) {
        this.peo_uni_code = peo_uni_code;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public String getHonor() {
        return honor;
    }

    public void setHonor(String honor) {
        this.honor = honor;
    }
}
