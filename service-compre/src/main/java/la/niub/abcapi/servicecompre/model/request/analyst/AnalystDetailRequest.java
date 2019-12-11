package la.niub.abcapi.servicecompre.model.request.analyst;

public class AnalystDetailRequest {

    private String peo_uni_code;

    public String getPeo_uni_code() {
        return peo_uni_code;
    }

    public void setPeo_uni_code(String peo_uni_code) {
        this.peo_uni_code = peo_uni_code;
    }

    @Override
    public String toString() {
        return "AnalystDetailRequest{" +
                ", peo_uni_code='" + peo_uni_code + '\'' +
                '}';
    }
}
