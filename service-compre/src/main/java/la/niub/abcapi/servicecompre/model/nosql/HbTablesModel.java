package la.niub.abcapi.servicecompre.model.nosql;

import la.niub.abcapi.servicecompre.model.bo.ComprehensiveChartGraphTableDataBO;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Document(collection = "hb_tables")
public class HbTablesModel {

    private String _id;

    private Long fileId;

    private String file_source;

    private Integer pageIndex;

    private String algorithm;

    private String algorithm_version;

    private Float confidence;

    private Integer table_version;

    private String title;

    private String caps;

    private String shoes;

    private Boolean inline_title;

    private String unit_line;

    private Map<String,Float> page_area;

    private Map<String,Float> area;

    private List<ComprehensiveChartGraphTableDataBO> data;

    private Integer rowCount;

    private Integer columnCount;

    private String data_file;

    private String html_file;

    private Date create_time;

    private Date last_updated;

    private Integer state;

    private Object from;

    private Object file_data;

    private Float x;

    private Float y;

    private Float w;

    private Float h;

    private Integer row;

    private Integer column;

    private String text;

    private String pngFile;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public String getFile_source() {
        return file_source;
    }

    public void setFile_source(String file_source) {
        this.file_source = file_source;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public String getAlgorithm_version() {
        return algorithm_version;
    }

    public void setAlgorithm_version(String algorithm_version) {
        this.algorithm_version = algorithm_version;
    }

    public Float getConfidence() {
        return confidence;
    }

    public void setConfidence(Float confidence) {
        this.confidence = confidence;
    }

    public Integer getTable_version() {
        return table_version;
    }

    public void setTable_version(Integer table_version) {
        this.table_version = table_version;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCaps() {
        return caps;
    }

    public void setCaps(String caps) {
        this.caps = caps;
    }

    public String getShoes() {
        return shoes;
    }

    public void setShoes(String shoes) {
        this.shoes = shoes;
    }

    public Boolean getInline_title() {
        return inline_title;
    }

    public void setInline_title(Boolean inline_title) {
        this.inline_title = inline_title;
    }

    public String getUnit_line() {
        return unit_line;
    }

    public void setUnit_line(String unit_line) {
        this.unit_line = unit_line;
    }

    public Map<String, Float> getPage_area() {
        return page_area;
    }

    public void setPage_area(Map<String, Float> page_area) {
        this.page_area = page_area;
    }

    public Map<String, Float> getArea() {
        return area;
    }

    public void setArea(Map<String, Float> area) {
        this.area = area;
    }

    public List<ComprehensiveChartGraphTableDataBO> getData() {
        return data;
    }

    public void setData(List<ComprehensiveChartGraphTableDataBO> data) {
        this.data = data;
    }

    public Integer getRowCount() {
        return rowCount;
    }

    public void setRowCount(Integer rowCount) {
        this.rowCount = rowCount;
    }

    public Integer getColumnCount() {
        return columnCount;
    }

    public void setColumnCount(Integer columnCount) {
        this.columnCount = columnCount;
    }

    public String getData_file() {
        return data_file;
    }

    public void setData_file(String data_file) {
        this.data_file = data_file;
    }

    public String getHtml_file() {
        return html_file;
    }

    public void setHtml_file(String html_file) {
        this.html_file = html_file;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(Date last_updated) {
        this.last_updated = last_updated;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Object getFrom() {
        return from;
    }

    public void setFrom(Object from) {
        this.from = from;
    }

    public Object getFile_data() {
        return file_data;
    }

    public void setFile_data(Object file_data) {
        this.file_data = file_data;
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

    public Float getW() {
        return w;
    }

    public void setW(Float w) {
        this.w = w;
    }

    public Float getH() {
        return h;
    }

    public void setH(Float h) {
        this.h = h;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getColumn() {
        return column;
    }

    public void setColumn(Integer column) {
        this.column = column;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPngFile() {
        return pngFile;
    }

    public void setPngFile(String pngFile) {
        this.pngFile = pngFile;
    }

    @Override
    public String toString() {
        return "HbTablesModel{" +
                "_id='" + _id + '\'' +
                ", fileId=" + fileId +
                ", file_source='" + file_source + '\'' +
                ", pageIndex=" + pageIndex +
                ", algorithm='" + algorithm + '\'' +
                ", algorithm_version='" + algorithm_version + '\'' +
                ", confidence=" + confidence +
                ", table_version=" + table_version +
                ", title='" + title + '\'' +
                ", caps='" + caps + '\'' +
                ", shoes='" + shoes + '\'' +
                ", inline_title=" + inline_title +
                ", unit_line='" + unit_line + '\'' +
                ", page_area=" + page_area +
                ", area=" + area +
                ", data=" + data +
                ", rowCount=" + rowCount +
                ", columnCount=" + columnCount +
                ", data_file='" + data_file + '\'' +
                ", html_file='" + html_file + '\'' +
                ", create_time=" + create_time +
                ", last_updated=" + last_updated +
                ", state=" + state +
                ", from=" + from +
                ", file_data=" + file_data +
                ", x=" + x +
                ", y=" + y +
                ", w=" + w +
                ", h=" + h +
                ", row=" + row +
                ", column=" + column +
                ", text='" + text + '\'' +
                ", pngFile='" + pngFile + '\'' +
                '}';
    }
}
