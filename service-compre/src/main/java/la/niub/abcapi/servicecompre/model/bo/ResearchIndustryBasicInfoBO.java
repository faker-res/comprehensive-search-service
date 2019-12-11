package la.niub.abcapi.servicecompre.model.bo;

public class ResearchIndustryBasicInfoBO {

    private Long indu_uni_code;

    private String indu_name;

    public ResearchIndustryBasicInfoBO() {
    }

    public ResearchIndustryBasicInfoBO(Long indu_uni_code, String indu_name) {
        this.indu_uni_code = indu_uni_code;
        this.indu_name = indu_name;
    }

    public Long getIndu_uni_code() {
        return indu_uni_code;
    }

    public void setIndu_uni_code(Long indu_uni_code) {
        this.indu_uni_code = indu_uni_code;
    }

    public String getIndu_name() {
        return indu_name;
    }

    public void setIndu_name(String indu_name) {
        this.indu_name = indu_name;
    }
}
