package la.niub.abcapi.servicecompre.model.bo;

public class SolrCommonSearchDataElementbo {

    private NoticeReturnDataElementResultBo result;

    private float score;

    private String source;

    public NoticeReturnDataElementResultBo getResult() {
        return result;
    }

    public void setResult(NoticeReturnDataElementResultBo result) {
        this.result = result;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

}
