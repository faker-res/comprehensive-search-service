package la.niub.abcapi.servicecompre.model.nosql;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "hb_text")
public class HbTextModel {

    private Integer _id;

    private Integer pageCount;

    private Integer paragraphCount;

    private Integer characterCount;

    private String author;

    private String keywords;

    private String title;

    private String outline;

    private Integer fileId;

    private String file_source;

    private String fileUrl;

    private Integer text_version;

    private String html_file;

    private String text_file;

    private String paragraph_file;

    private Integer state;

    private Date create_time;

    private Date last_updated;

    private String fulltext;

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Integer getParagraphCount() {
        return paragraphCount;
    }

    public void setParagraphCount(Integer paragraphCount) {
        this.paragraphCount = paragraphCount;
    }

    public Integer getCharacterCount() {
        return characterCount;
    }

    public void setCharacterCount(Integer characterCount) {
        this.characterCount = characterCount;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOutline() {
        return outline;
    }

    public void setOutline(String outline) {
        this.outline = outline;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
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

    public Integer getText_version() {
        return text_version;
    }

    public void setText_version(Integer text_version) {
        this.text_version = text_version;
    }

    public String getHtml_file() {
        return html_file;
    }

    public void setHtml_file(String html_file) {
        this.html_file = html_file;
    }

    public String getText_file() {
        return text_file;
    }

    public void setText_file(String text_file) {
        this.text_file = text_file;
    }

    public String getParagraph_file() {
        return paragraph_file;
    }

    public void setParagraph_file(String paragraph_file) {
        this.paragraph_file = paragraph_file;
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

    public String getFulltext() {
        return fulltext;
    }

    public void setFulltext(String fulltext) {
        this.fulltext = fulltext;
    }

    @Override
    public String toString() {
        return "HbTextModel{" +
                "_id=" + _id +
                ", pageCount=" + pageCount +
                ", paragraphCount=" + paragraphCount +
                ", characterCount=" + characterCount +
                ", author='" + author + '\'' +
                ", keywords='" + keywords + '\'' +
                ", title='" + title + '\'' +
                ", outline='" + outline + '\'' +
                ", fileId=" + fileId +
                ", file_source='" + file_source + '\'' +
                ", fileUrl='" + fileUrl + '\'' +
                ", text_version=" + text_version +
                ", html_file='" + html_file + '\'' +
                ", text_file='" + text_file + '\'' +
                ", paragraph_file='" + paragraph_file + '\'' +
                ", state=" + state +
                ", create_time=" + create_time +
                ", last_updated=" + last_updated +
                ", fulltext='" + fulltext + '\'' +
                '}';
    }
}
