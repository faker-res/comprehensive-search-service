package la.niub.abcapi.servicecompre.model.request.publiccompany;

public class PublicCompanyFundTrendRequest {

    private Long index_uni_code;

    private String start_time;

    public Long getIndex_uni_code() {
        return index_uni_code;
    }

    public void setIndex_uni_code(Long index_uni_code) {
        this.index_uni_code = index_uni_code;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    @Override
    public String toString() {
        return "PublicCompanyFundTrendRequest{" +
                "index_uni_code=" + index_uni_code +
                ", start_time='" + start_time + '\'' +
                '}';
    }
}
