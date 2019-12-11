package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;

public class FreqSearchTablesModel implements Serializable {
    private Integer id;

    private String tab_ID;

    private Integer display_Order;

    private static final long serialVersionUID = 1L;

    public FreqSearchTablesModel(Integer id, String tab_ID, Integer display_Order) {
        this.id = id;
        this.tab_ID = tab_ID;
        this.display_Order = display_Order;
    }

    public FreqSearchTablesModel() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTab_ID() {
        return tab_ID;
    }

    public void setTab_ID(String tab_ID) {
        this.tab_ID = tab_ID == null ? null : tab_ID.trim();
    }

    public Integer getDisplay_Order() {
        return display_Order;
    }

    public void setDisplay_Order(Integer display_Order) {
        this.display_Order = display_Order;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", tab_ID=").append(tab_ID);
        sb.append(", display_Order=").append(display_Order);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}