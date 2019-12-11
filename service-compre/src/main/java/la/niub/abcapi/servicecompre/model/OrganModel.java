package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;

public class OrganModel implements Serializable {
    private Integer id;

    private String org_id;

    private String publish;

    private String logo;

    private Integer number;

    private String link;

    private static final long serialVersionUID = 1L;

    public OrganModel(Integer id, String org_id, String publish, String logo, Integer number) {
        this.id = id;
        this.org_id = org_id;
        this.publish = publish;
        this.logo = logo;
        this.number = number;
    }

    public OrganModel() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrg_id() {
        return org_id;
    }

    public void setOrg_id(String org_id) {
        this.org_id = org_id;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish == null ? null : publish.trim();
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo == null ? null : logo.trim();
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", org_id=").append(org_id);
        sb.append(", publish=").append(publish);
        sb.append(", logo=").append(logo);
        sb.append(", number=").append(number);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}