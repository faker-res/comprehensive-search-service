package la.niub.abcapi.servicecompre.model.bo;

import java.util.List;

public class ComprehensiveChartGraphTableBO {

    private String title;

    private String url;

    private String id;

    private List<ComprehensiveChartGraphTableDataBO> data;

    private Integer pageIndex;

    private Integer row_count;

    private Integer column_count;

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

    public List<ComprehensiveChartGraphTableDataBO> getData() {
        return data;
    }

    public void setData(List<ComprehensiveChartGraphTableDataBO> data) {
        this.data = data;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getRow_count() {
        return row_count;
    }

    public void setRow_count(Integer row_count) {
        this.row_count = row_count;
    }

    public Integer getColumn_count() {
        return column_count;
    }

    public void setColumn_count(Integer column_count) {
        this.column_count = column_count;
    }

    @Override
    public String toString() {
        return "ComprehensiveChartGraphTableBO{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", id='" + id + '\'' +
                ", data=" + data +
                ", pageIndex=" + pageIndex +
                ", row_count=" + row_count +
                ", column_count=" + column_count +
                '}';
    }
}
