package la.niub.abcapi.servicecompre.model.nosql;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "juchao_text")
public class JuchaoTextModel {

    private String fileId;

    private int text_version;

    private String text_file = "";

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public int getText_version() {
        return text_version;
    }

    public void setText_version(int text_version) {
        this.text_version = text_version;
    }

    public String getText_file() {
        return text_file;
    }

    public void setText_file(String text_file) {
        this.text_file = text_file;
    }
}


