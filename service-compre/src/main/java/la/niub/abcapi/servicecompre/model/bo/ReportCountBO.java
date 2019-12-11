package la.niub.abcapi.servicecompre.model.bo;

public class ReportCountBO {

    private String type_id;

    private String type_name;

    private Integer count;

    public ReportCountBO() {
    }

    public ReportCountBO(String type_id, String type_name, Integer count) {
        this.type_id = type_id;
        this.type_name = type_name;
        this.count = count;
    }

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
