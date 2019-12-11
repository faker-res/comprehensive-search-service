package la.niub.abcapi.servicecompre.model.bo;

import java.util.List;

public class ComprehensiveChartUseBO {

    private List<TableConfigBO> use;

    private List<TableConfigBO> cate;

    private List<TableConfigBO> other;

    public List<TableConfigBO> getUse() {
        return use;
    }

    public void setUse(List<TableConfigBO> use) {
        this.use = use;
    }

    public List<TableConfigBO> getCate() {
        return cate;
    }

    public void setCate(List<TableConfigBO> cate) {
        this.cate = cate;
    }

    public List<TableConfigBO> getOther() {
        return other;
    }

    public void setOther(List<TableConfigBO> other) {
        this.other = other;
    }

    @Override
    public String toString() {
        return "ComprehensiveChartUseBO{" +
                "use=" + use +
                ", cate=" + cate +
                ", other=" + other +
                '}';
    }
}
