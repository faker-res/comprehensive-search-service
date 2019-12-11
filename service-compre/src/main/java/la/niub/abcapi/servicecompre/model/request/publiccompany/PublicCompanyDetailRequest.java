package la.niub.abcapi.servicecompre.model.request.publiccompany;

public class PublicCompanyDetailRequest {

    private String name;

    private Long index_uni_code;

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

    @Override
    public String toString() {
        return "PublicCompanyDetailRequest{" +
                "name='" + name + '\'' +
                ", index_uni_code=" + index_uni_code +
                '}';
    }
}
