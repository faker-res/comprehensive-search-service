package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;

public class AllTableGroupsModel implements Serializable {
    private Integer id;

    private String category_Name;

    private String category_Code;

    private Integer isDisplay;

    private Integer display_Order;

    private String grp_ICON;

    private static final long serialVersionUID = 1L;

    public AllTableGroupsModel(Integer id, String category_Name, String category_Code, Integer isDisplay, Integer display_Order, String grp_ICON) {
        this.id = id;
        this.category_Name = category_Name;
        this.category_Code = category_Code;
        this.isDisplay = isDisplay;
        this.display_Order = display_Order;
        this.grp_ICON = grp_ICON;
    }

    public AllTableGroupsModel() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategory_Name() {
        return category_Name;
    }

    public void setCategory_Name(String category_Name) {
        this.category_Name = category_Name == null ? null : category_Name.trim();
    }

    public String getCategory_Code() {
        return category_Code;
    }

    public void setCategory_Code(String category_Code) {
        this.category_Code = category_Code == null ? null : category_Code.trim();
    }

    public Integer getIsDisplay() {
        return isDisplay;
    }

    public void setIsDisplay(Integer isDisplay) {
        this.isDisplay = isDisplay;
    }

    public Integer getDisplay_Order() {
        return display_Order;
    }

    public void setDisplay_Order(Integer display_Order) {
        this.display_Order = display_Order;
    }

    public String getGrp_ICON() {
        return grp_ICON;
    }

    public void setGrp_ICON(String grp_ICON) {
        this.grp_ICON = grp_ICON == null ? null : grp_ICON.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", category_Name=").append(category_Name);
        sb.append(", category_Code=").append(category_Code);
        sb.append(", isDisplay=").append(isDisplay);
        sb.append(", display_Order=").append(display_Order);
        sb.append(", grp_ICON=").append(grp_ICON);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}