package la.niub.abcapi.servicecompre.model.bo;

import java.util.List;

public class MatchParagraphBO {

    private List<String> paragraphs;

    private String summary;

    public List<String> getParagraphs() {
        return paragraphs;
    }

    public void setParagraphs(List<String> paragraphs) {
        this.paragraphs = paragraphs;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Override
    public String toString() {
        return "MatchParagraphBO{" +
                "paragraphs=" + paragraphs +
                ", summary='" + summary + '\'' +
                '}';
    }
}
