package la.niub.abcapi.servicecompre.model.nosql;

import java.util.Date;
import java.util.List;

public class ChartsModel {

    private String _id;

    private Boolean is_ppt;

    private String algorithmCommitTime;

    private Long fileId;

    private Integer pageIndex;

    private String chartType;

    private List<Object> subtypes;

    private List<Object> legends;

    private List<Object> vAxisTextL;

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

    private Object data;

    private Object area;

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

    public List<Object> getLegends() {
        return legends;
    }

    public void setLegends(List<Object> legends) {
        this.legends = legends;
    }

    public List<Object> getvAxisTextL() {
        return vAxisTextL;
    }

    public void setvAxisTextL(List<Object> vAxisTextL) {
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getArea() {
        return area;
    }

    public void setArea(Object area) {
        this.area = area;
    }
}
