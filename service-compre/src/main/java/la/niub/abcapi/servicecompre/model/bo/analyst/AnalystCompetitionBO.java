package la.niub.abcapi.servicecompre.model.bo.analyst;

public class AnalystCompetitionBO {

    private String name;

    private String code;

    private Integer num;

    private Double ratio;

    private String peo_uni_code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Double getRatio() {
        return ratio;
    }

    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }

    public String getPeo_uni_code() {
        return peo_uni_code;
    }

    public void setPeo_uni_code(String peo_uni_code) {
        this.peo_uni_code = peo_uni_code;
    }

    @Override
    public String toString() {
        return "AnalystCompetitionBO{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", num=" + num +
                ", ratio=" + ratio +
                ", peo_uni_code='" + peo_uni_code + '\'' +
                '}';
    }
}
