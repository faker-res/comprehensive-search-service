package la.niub.abcapi.servicecompre.model.response.client;

import la.niub.abcapi.servicecompre.model.response.client.news.RecommendNewsData;

import java.util.List;

public class ApiRecommendNewsResponse {

    private String msg;

    private String code;

    private String businessCode;

    private String businessFlag;

    private Boolean success;

    private Integer offset;

    private Integer limit;

    private List<RecommendNewsData> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    public String getBusinessFlag() {
        return businessFlag;
    }

    public void setBusinessFlag(String businessFlag) {
        this.businessFlag = businessFlag;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<RecommendNewsData> getData() {
        return data;
    }

    public void setData(List<RecommendNewsData> data) {
        RecommendNewsData model = data.get(0);
        if (model.getPageNo() != null && model.getPageSize() != null){
            setOffset(model.getPageNo());
            setLimit(model.getPageSize());
            data = data.subList(1,data.size());
        }
        this.data = data;
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

    @Override
    public String toString() {
        return "ApiRecommendNewsResponse{" +
                "msg='" + msg + '\'' +
                ", code='" + code + '\'' +
                ", businessCode='" + businessCode + '\'' +
                ", businessFlag='" + businessFlag + '\'' +
                ", success=" + success +
                ", offset=" + offset +
                ", limit=" + limit +
                ", data=" + data +
                '}';
    }
}
