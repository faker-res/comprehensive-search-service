package la.niub.abcapi.servicecompre.model.response.fund;

import la.niub.abcapi.servicecompre.model.request.fund.FundOtherHeatBO;
import la.niub.abcapi.servicecompre.model.request.fund.FundOtherSentimentBO;

import java.util.List;

public class FundOtherResponse {

    private List<FundOtherSentimentBO> sentiment;

    private List<FundOtherHeatBO> heat;

    public List<FundOtherSentimentBO> getSentiment() {
        return sentiment;
    }

    public void setSentiment(List<FundOtherSentimentBO> sentiment) {
        this.sentiment = sentiment;
    }

    public List<FundOtherHeatBO> getHeat() {
        return heat;
    }

    public void setHeat(List<FundOtherHeatBO> heat) {
        this.heat = heat;
    }

    @Override
    public String toString() {
        return "FundOtherResponse{" +
                "sentiment=" + sentiment +
                ", heat=" + heat +
                '}';
    }
}
