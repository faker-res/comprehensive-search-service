package la.niub.abcapi.servicecompre.model.bo.sidebar;

public class SidebarStockExpertResultBO {

    private String peo_uni_code;

    private String image;

    private String name;

    private String direction;

    private String organ;

    private String analyst_code;

    public String getPeo_uni_code() {
        return peo_uni_code;
    }

    public void setPeo_uni_code(String peo_uni_code) {
        this.peo_uni_code = peo_uni_code;
    }

    public String getAnalyst_code() {
        return analyst_code;
    }

    public void setAnalyst_code(String analyst_code) {
        this.analyst_code = analyst_code;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getOrgan() {
        return organ;
    }

    public void setOrgan(String organ) {
        this.organ = organ;
    }

    @Override
    public String toString() {
        return "SidebarStockExpertResultBO{" +
                "image='" + image + '\'' +
                ", name='" + name + '\'' +
                ", direction='" + direction + '\'' +
                ", organ='" + organ + '\'' +
                ", analyst_code='" + analyst_code + '\'' +
                '}';
    }
}
