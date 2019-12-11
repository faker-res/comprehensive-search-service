package la.niub.abcapi.servicecompre.model.response.client.edb;

import org.springframework.data.mongodb.core.aggregation.VariableOperators;

public class DataSearchResponce {
    private VariableOperators.Map result;

    private String type;

    private Integer total_count;

    public VariableOperators.Map getResult() {
        return result;
    }

    public void setResult(VariableOperators.Map result) {
        this.result = result;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getTotal_count() {
        return total_count;
    }

    public void setTotal_count(Integer total_count) {
        this.total_count = total_count;
    }
}
