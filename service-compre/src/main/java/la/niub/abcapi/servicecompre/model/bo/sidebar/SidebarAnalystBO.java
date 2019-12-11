package la.niub.abcapi.servicecompre.model.bo.sidebar;

import java.util.List;

public class SidebarAnalystBO extends SidebarBO {

    private String direction;

    private List<SidebarAnalystOrderBO>  honor_rank;

    private List<SidebarAnalystOrderBO>  honor_num;

    private List<SidebarAnalystOrderBO>  report_num;

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public List<SidebarAnalystOrderBO> getHonor_rank() {
        return honor_rank;
    }

    public void setHonor_rank(List<SidebarAnalystOrderBO> honor_rank) {
        this.honor_rank = honor_rank;
    }

    public List<SidebarAnalystOrderBO> getHonor_num() {
        return honor_num;
    }

    public void setHonor_num(List<SidebarAnalystOrderBO> honor_num) {
        this.honor_num = honor_num;
    }

    public List<SidebarAnalystOrderBO> getReport_num() {
        return report_num;
    }

    public void setReport_num(List<SidebarAnalystOrderBO> report_num) {
        this.report_num = report_num;
    }

    @Override
    public String toString() {
        return "SidebarAnalystBO{" +
                "direction='" + direction + '\'' +
                ", honor_rank=" + honor_rank +
                ", honor_num=" + honor_num +
                ", report_num=" + report_num +
                '}';
    }
}
