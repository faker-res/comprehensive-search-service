package la.niub.abcapi.servicecompre.model.bo;

import la.niub.abcapi.servicecompre.model.response.client.news.BestNewsData;
import la.niub.abcapi.servicecompre.model.response.client.news.RecommendNewsData;

import java.util.List;

public class ComprehensiveNewsBO {

    List<BestNewsData> hot;

    List<BestNewsData> best;

    List<RecommendNewsData> recommend;

    public List<BestNewsData> getHot() {
        return hot;
    }

    public void setHot(List<BestNewsData> hot) {
        this.hot = hot;
    }

    public List<BestNewsData> getBest() {
        return best;
    }

    public void setBest(List<BestNewsData> best) {
        this.best = best;
    }

    public List<RecommendNewsData> getRecommend() {
        return recommend;
    }

    public void setRecommend(List<RecommendNewsData> recommend) {
        this.recommend = recommend;
    }

    @Override
    public String toString() {
        return "ComprehensiveNewsBO{" +
                "hot=" + hot +
                ", best=" + best +
                ", recommend=" + recommend +
                '}';
    }
}
