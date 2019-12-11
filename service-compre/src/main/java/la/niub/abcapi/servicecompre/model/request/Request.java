package la.niub.abcapi.servicecompre.model.request;

public class Request {

    private Boolean nolog = false;

    private String userId = "";

    private String channel;

    private Integer offset = 0;

    private Integer limit = 20;

    private String version;

    private String language;

    public Boolean getNolog() {
        return nolog;
    }

    public void setNolog(Boolean nolog) {
        this.nolog = nolog;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
