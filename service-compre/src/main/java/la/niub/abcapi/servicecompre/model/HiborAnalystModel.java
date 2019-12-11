package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;

public class HiborAnalystModel implements Serializable {
    private Long added_id;

    private Long id;

    private String analyst;

    private String name;

    private String sac;

    private String email;

    private String tel;

    private static final long serialVersionUID = 1L;

    public HiborAnalystModel(Long added_id, Long id, String analyst, String name, String sac, String email, String tel) {
        this.added_id = added_id;
        this.id = id;
        this.analyst = analyst;
        this.name = name;
        this.sac = sac;
        this.email = email;
        this.tel = tel;
    }

    public HiborAnalystModel() {
        super();
    }

    public Long getAdded_id() {
        return added_id;
    }

    public void setAdded_id(Long added_id) {
        this.added_id = added_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnalyst() {
        return analyst;
    }

    public void setAnalyst(String analyst) {
        this.analyst = analyst == null ? null : analyst.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSac() {
        return sac;
    }

    public void setSac(String sac) {
        this.sac = sac == null ? null : sac.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", added_id=").append(added_id);
        sb.append(", id=").append(id);
        sb.append(", analyst=").append(analyst);
        sb.append(", name=").append(name);
        sb.append(", sac=").append(sac);
        sb.append(", email=").append(email);
        sb.append(", tel=").append(tel);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}