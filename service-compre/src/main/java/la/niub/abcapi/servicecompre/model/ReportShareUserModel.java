package la.niub.abcapi.servicecompre.model;

public class ReportShareUserModel {

    private Integer id;

    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "ReportShareUserModel{" +
                "id=" + id +
                ", email='" + email + '\'' +
                '}';
    }
}
