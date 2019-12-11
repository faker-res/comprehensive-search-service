package la.niub.abcapi.servicecompre.model.bo.analyst;

public class AnalystHeatBO {

    //团队名
    private String name;

    //本季度研报数量
    private Integer num;

    //相较上季度变化
    private Double chg_ratio;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Double getChg_ratio() {
        return chg_ratio;
    }

    public void setChg_ratio(Double chg_ratio) {
        this.chg_ratio = chg_ratio;
    }

    @Override
    public String toString() {
        return "AnalystHeatBO{" +
                "name='" + name + '\'' +
                ", num=" + num +
                ", chg_ratio=" + chg_ratio +
                '}';
    }
}
