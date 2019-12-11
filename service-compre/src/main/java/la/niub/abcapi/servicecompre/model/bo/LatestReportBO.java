package la.niub.abcapi.servicecompre.model.bo;

import com.alibaba.fastjson.JSONArray;

import java.util.Date;

public class LatestReportBO {

    private Long id;

    private String title;

    private Long pageCounter;

    private String fileType;

    private String source;

    private Date time;

    private JSONArray author;

    private String Type;


    public LatestReportBO() {
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getPageCounter() {
        return pageCounter;
    }

    public void setPageCounter(Long pageCounter) {
        this.pageCounter = pageCounter;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public JSONArray getAuthor() {
        return author;
    }

    public void setAuthor(JSONArray author) {
        this.author = author;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }
}
