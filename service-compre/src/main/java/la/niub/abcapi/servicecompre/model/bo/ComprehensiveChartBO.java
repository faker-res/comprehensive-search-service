package la.niub.abcapi.servicecompre.model.bo;

import java.util.List;

public class ComprehensiveChartBO {

    private List<ComprehensiveChartGraphBO> graph;

    private ComprehensiveChartUseBO use;

    public List<ComprehensiveChartGraphBO> getGraph() {
        return graph;
    }

    public void setGraph(List<ComprehensiveChartGraphBO> graph) {
        this.graph = graph;
    }

    public ComprehensiveChartUseBO getUse() {
        return use;
    }

    public void setUse(ComprehensiveChartUseBO use) {
        this.use = use;
    }

    @Override
    public String toString() {
        return "ComprehensiveChartBO{" +
                "graph=" + graph +
                ", use=" + use +
                '}';
    }
}
