package la.niub.abcapi.servicecompre.model.response.client.report;


public class ReportFieldFacetData {
    private Integer count;

    private String name;

    private Integer time;

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ReportFieldFacetData{" +
                "count=" + count +
                ", name='" + name + '\'' +
                ", time=" + time +
                '}';
    }
}
