package la.niub.abcapi.servicecompre.model.bo;

import java.util.Map;

public class ComprehensiveChartGraphChartBO {

    private String title;

    private String url;

    private String id;

    private ComprehensiveChartGraphChartDataBO data;

    private Integer pageIndex;

    private Map<String,Float> area;

    private Float x;

    private Float y;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ComprehensiveChartGraphChartDataBO getData() {
        return data;
    }

    public void setData(ComprehensiveChartGraphChartDataBO data) {
        this.data = data;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Map<String, Float> getArea() {
        return area;
    }

    public void setArea(Map<String, Float> area) {
        this.area = area;
    }

    public Float getX() {
        return x;
    }

    public void setX(Float x) {
        this.x = x;
    }

    public Float getY() {
        return y;
    }

    public void setY(Float y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "ComprehensiveChartGraphChartBO{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", id='" + id + '\'' +
                ", data=" + data +
                ", pageIndex=" + pageIndex +
                ", area=" + area +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
