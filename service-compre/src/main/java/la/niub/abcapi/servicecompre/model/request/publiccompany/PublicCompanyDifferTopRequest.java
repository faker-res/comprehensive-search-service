package la.niub.abcapi.servicecompre.model.request.publiccompany;

public class PublicCompanyDifferTopRequest {

    private String name;

    private Long index_uni_code;

    private Integer limit = 10;

    private String type;

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

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "PublicCompanyDifferTopRequest{" +
                "name='" + name + '\'' +
                ", index_uni_code=" + index_uni_code +
                ", limit=" + limit +
                ", type='" + type + '\'' +
                '}';
    }
}
