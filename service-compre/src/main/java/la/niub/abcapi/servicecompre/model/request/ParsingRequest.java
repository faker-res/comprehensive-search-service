package la.niub.abcapi.servicecompre.model.request;

import com.alibaba.fastjson.JSON;

public class ParsingRequest extends Request{

    /**
     * imageId
     */
    private String imageId = "";

    private String realId = "";

    private String source = "";

    private String requester = "Analysis.AI";

    private Boolean force = false;

    private int priority = 7;

    private String id = "";

    private Boolean is_fileId = false;

    private String type = "";

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getForce() {
        return force;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setForce(Boolean force) {
        this.force = force;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    public String getSource() {
        return source;
    }

    public void setIs_fileId(Boolean is_fileId) {
        this.is_fileId = is_fileId;
    }

    public Boolean getIs_fileId() {
        return is_fileId;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getRequester() {
        return requester;
    }

    public void setRequester(String requester) {
        this.requester = requester;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {

        this.imageId = imageId;
    }

    public String getRealId() {
        return realId;
    }

    public void setRealId(String realId) {
        this.realId = realId;
    }
}