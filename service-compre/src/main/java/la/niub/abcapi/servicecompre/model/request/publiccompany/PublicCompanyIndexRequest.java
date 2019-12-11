package la.niub.abcapi.servicecompre.model.request.publiccompany;

public class PublicCompanyIndexRequest {

    private String name;

    private Long index_uni_code;

    private String start_time;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
        return "PublicCompanyIndexRequest{" +
                "name='" + name + '\'' +
                ", index_uni_code=" + index_uni_code +
                ", start_time='" + start_time + '\'' +
                '}';
    }
}
