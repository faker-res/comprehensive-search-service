package la.niub.abcapi.servicecompre.model.response.analyst;

import la.niub.abcapi.servicecompre.model.bo.analyst.AnalystCompetitionBO;

import java.util.List;

public class AnalystCompetitionResponse {

    private List<AnalystCompetitionBO> analyst;

    private List<AnalystCompetitionBO> other_analyst;

    public List<AnalystCompetitionBO> getAnalyst() {
        return analyst;
    }

    public void setAnalyst(List<AnalystCompetitionBO> analyst) {
        this.analyst = analyst;
    }

    public List<AnalystCompetitionBO> getOther_analyst() {
        return other_analyst;
    }

    public void setOther_analyst(List<AnalystCompetitionBO> other_analyst) {
        this.other_analyst = other_analyst;
    }

    @Override
    public String toString() {
        return "AnalystCompetitionResponse{" +
                "analyst=" + analyst +
                ", other_analyst=" + other_analyst +
                '}';
    }
}
