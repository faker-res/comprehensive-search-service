package la.niub.abcapi.servicecompre.model.bo.fund;

import java.util.List;

public class FundCompanyManagerBO {

    //现任基金经理
    private List<FundManagerBO> manager_now;

    //历任基金经理
    private List<FundManagerBO> manager_history;

    public List<FundManagerBO> getManager_now() {
        return manager_now;
    }

    public void setManager_now(List<FundManagerBO> manager_now) {
        this.manager_now = manager_now;
    }

    public List<FundManagerBO> getManager_history() {
        return manager_history;
    }

    public void setManager_history(List<FundManagerBO> manager_history) {
        this.manager_history = manager_history;
    }

    @Override
    public String toString() {
        return "FundCompanyManagerBO{" +
                "manager_now=" + manager_now +
                ", manager_history=" + manager_history +
                '}';
    }
}
