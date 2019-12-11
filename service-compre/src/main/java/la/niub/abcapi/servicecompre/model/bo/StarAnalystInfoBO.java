package la.niub.abcapi.servicecompre.model.bo;

public class StarAnalystInfoBO {

    private String peo_uni_code;

    private String name;

    private String image;

    private String indu_name;

    private String honor;

    private Integer isPrized;

    public String getPeo_uni_code() {
        return peo_uni_code;
    }

    public void setPeo_uni_code(String peo_uni_code) {
        this.peo_uni_code = peo_uni_code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getIndu_name() {
        return indu_name;
    }

    public void setIndu_name(String indu_name) {
        this.indu_name = indu_name;
    }

    public String getHonor() {
        return honor;
    }

    public void setHonor(String honor) {
        this.honor = honor;
    }

    public Integer getIsPrized() {
        return isPrized;
    }

    public void setIsPrized(Integer isPrized) {
        this.isPrized = isPrized;
    }
}
