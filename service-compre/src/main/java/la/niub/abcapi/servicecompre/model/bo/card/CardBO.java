package la.niub.abcapi.servicecompre.model.bo.card;

import java.util.List;

public class CardBO<T>{

    private String source;

    private Boolean flag;

    private List<T> result;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "CardBO{" +
                "source='" + source + '\'' +
                ", flag=" + flag +
                ", result=" + result +
                '}';
    }
}
