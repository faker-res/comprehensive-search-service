package la.niub.abcapi.servicecompre.model.request;

public class SearchFinanceCardRequest {

    private String token;

    private String userId;

    private String request_id;

    private String indicators;

    private String beginTime;

    private String endTime;

    private String codes;

    private String timeRangeList;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public String getIndicators() {
        return indicators;
    }

    public void setIndicators(String indicators) {
        this.indicators = indicators;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getCodes() {
        return codes;
    }

    public void setCodes(String codes) {
        this.codes = codes;
    }

    public String getTimeRangeList() {
        return timeRangeList;
    }

    public void setTimeRangeList(String timeRangeList) {
        this.timeRangeList = timeRangeList;
    }
}
