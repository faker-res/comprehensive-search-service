package la.niub.abcapi.servicecompre.model.nosql;

import la.niub.abcapi.servicecompre.model.bo.ComprehensiveChartGraphChartDataBO;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Document(collection = "hb_charts")
public class HbChartsModel {

    private String _id;

    private Boolean is_ppt;

    private String algorithmCommitTime;

    private Long fileId;

    private Integer pageIndex;

    private String chartType;

    private List<Object> subtypes;

    private List<Map<String,Object>> legends;

    private List<String> vAxisTextL;

    private List<Object> vAxisChunksR;

    private List<Object> hAxisChunksD;

    private List<Object> hAxisChunksU;

    private Boolean is_bitmap;

    private String file_source;

    private String fileUrl;

    private Integer chart_version;

    private Integer state;

    private Date create_time;

    private Date last_updated;

    private Boolean deleted;

    private String title;

    private String svgFile;

    private String pngFile;

    private String confidence;

    private String data_file;

    private String bitmap_ver;

    public String getBitmap_ver() {
        return bitmap_ver;
    }

    public void setBitmap_ver(String bitmap_ver) {
        this.bitmap_ver = bitmap_ver;
    }

    public String getData_file() {
        return data_file;
    }

    public void setData_file(String data_file) {
        this.data_file = data_file;
    }

    public String getConfidence() {
        return confidence;
    }

    public void setConfidence(String confidence) {
        this.confidence = confidence;
    }

    private ComprehensiveChartGraphChartDataBO data;

    private Map<String,Float> area;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Boolean getIs_ppt() {
        return is_ppt;
    }

    public void setIs_ppt(Boolean is_ppt) {
        this.is_ppt = is_ppt;
    }

    public String getAlgorithmCommitTime() {
        return algorithmCommitTime;
    }

    public void setAlgorithmCommitTime(String algorithmCommitTime) {
        this.algorithmCommitTime = algorithmCommitTime;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public String getChartType() {
        return chartType;
    }

    public void setChartType(String chartType) {
        this.chartType = chartType;
    }

    public List<Object> getSubtypes() {
        return subtypes;
    }

    public void setSubtypes(List<Object> subtypes) {
        this.subtypes = subtypes;
    }

    public List<Map<String,Object>> getLegends() {
        return legends;
    }

    public void setLegends(List<Map<String,Object>> legends) {
        this.legends = legends;
    }

    public List<String> getvAxisTextL() {
        return vAxisTextL;
    }

    public void setvAxisTextL(List<String> vAxisTextL) {
        this.vAxisTextL = vAxisTextL;
    }

    public List<Object> getvAxisChunksR() {
        return vAxisChunksR;
    }

    public void setvAxisChunksR(List<Object> vAxisChunksR) {
        this.vAxisChunksR = vAxisChunksR;
    }

    public List<Object> gethAxisChunksD() {
        return hAxisChunksD;
    }

    public void sethAxisChunksD(List<Object> hAxisChunksD) {
        this.hAxisChunksD = hAxisChunksD;
    }

    public List<Object> gethAxisChunksU() {
        return hAxisChunksU;
    }

    public void sethAxisChunksU(List<Object> hAxisChunksU) {
        this.hAxisChunksU = hAxisChunksU;
    }

    public Boolean getIs_bitmap() {
        return is_bitmap;
    }

    public void setIs_bitmap(Boolean is_bitmap) {
        this.is_bitmap = is_bitmap;
    }

    public String getFile_source() {
        return file_source;
    }

    public void setFile_source(String file_source) {
        this.file_source = file_source;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public Integer getChart_version() {
        return chart_version;
    }

    public void setChart_version(Integer chart_version) {
        this.chart_version = chart_version;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSvgFile() {
        return svgFile;
    }

    public void setSvgFile(String svgFile) {
        this.svgFile = svgFile;
    }

    public String getPngFile() {
        return pngFile;
    }

    public void setPngFile(String pngFile) {
        this.pngFile = pngFile;
    }

    public ComprehensiveChartGraphChartDataBO getData() {
        return data;
    }

    public void setData(ComprehensiveChartGraphChartDataBO data) {
        this.data = data;
    }

    public Map<String,Float> getArea() {
        return area;
    }

    public void setArea(Map<String,Float> area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return "HbChartsModel{" +
                "_id='" + _id + '\'' +
                ", is_ppt=" + is_ppt +
                ", algorithmCommitTime='" + algorithmCommitTime + '\'' +
                ", fileId=" + fileId +
                ", pageIndex=" + pageIndex +
                ", chartType='" + chartType + '\'' +
                ", subtypes=" + subtypes +
                ", legends=" + legends +
                ", vAxisTextL=" + vAxisTextL +
                ", vAxisChunksR=" + vAxisChunksR +
                ", hAxisChunksD=" + hAxisChunksD +
                ", hAxisChunksU=" + hAxisChunksU +
                ", is_bitmap=" + is_bitmap +
                ", file_source='" + file_source + '\'' +
                ", fileUrl='" + fileUrl + '\'' +
                ", chart_version=" + chart_version +
                ", state=" + state +
                ", create_time=" + create_time +
                ", last_updated=" + last_updated +
                ", deleted=" + deleted +
                ", title='" + title + '\'' +
                ", svgFile='" + svgFile + '\'' +
                ", pngFile='" + pngFile + '\'' +
                ", data=" + data +
                ", area=" + area +
                '}';
    }
}
