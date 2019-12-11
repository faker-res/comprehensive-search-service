package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;

public class AllSearchTablesModel implements Serializable {
    private Integer id;

    private String tab_ID;

    private String tab_ICON;

    private String tab_Name;

    private String tab_ShortName;

    private String tab_EngName;

    private String category_Code;

    private Integer isImportant;

    private Integer display_Order;

    private String tab_ICON_Freq;

    private static final long serialVersionUID = 1L;

    public AllSearchTablesModel(Integer id, String tab_ID, String tab_ICON, String tab_Name, String tab_ShortName, String tab_EngName, String category_Code, Integer isImportant, Integer display_Order, String tab_ICON_Freq) {
        this.id = id;
        this.tab_ID = tab_ID;
        this.tab_ICON = tab_ICON;
        this.tab_Name = tab_Name;
        this.tab_ShortName = tab_ShortName;
        this.tab_EngName = tab_EngName;
        this.category_Code = category_Code;
        this.isImportant = isImportant;
        this.display_Order = display_Order;
        this.tab_ICON_Freq = tab_ICON_Freq;
    }

    public AllSearchTablesModel() {
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

    public String getTab_ICON() {
        return tab_ICON;
    }

    public void setTab_ICON(String tab_ICON) {
        this.tab_ICON = tab_ICON == null ? null : tab_ICON.trim();
    }

    public String getTab_Name() {
        return tab_Name;
    }

    public void setTab_Name(String tab_Name) {
        this.tab_Name = tab_Name == null ? null : tab_Name.trim();
    }

    public String getTab_ShortName() {
        return tab_ShortName;
    }

    public void setTab_ShortName(String tab_ShortName) {
        this.tab_ShortName = tab_ShortName == null ? null : tab_ShortName.trim();
    }

    public String getTab_EngName() {
        return tab_EngName;
    }

    public void setTab_EngName(String tab_EngName) {
        this.tab_EngName = tab_EngName == null ? null : tab_EngName.trim();
    }

    public String getCategory_Code() {
        return category_Code;
    }

    public void setCategory_Code(String category_Code) {
        this.category_Code = category_Code == null ? null : category_Code.trim();
    }

    public Integer getIsImportant() {
        return isImportant;
    }

    public void setIsImportant(Integer isImportant) {
        this.isImportant = isImportant;
    }

    public Integer getDisplay_Order() {
        return display_Order;
    }

    public void setDisplay_Order(Integer display_Order) {
        this.display_Order = display_Order;
    }

    public String getTab_ICON_Freq() {
        return tab_ICON_Freq;
    }

    public void setTab_ICON_Freq(String tab_ICON_Freq) {
        this.tab_ICON_Freq = tab_ICON_Freq == null ? null : tab_ICON_Freq.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", tab_ID=").append(tab_ID);
        sb.append(", tab_ICON=").append(tab_ICON);
        sb.append(", tab_Name=").append(tab_Name);
        sb.append(", tab_ShortName=").append(tab_ShortName);
        sb.append(", tab_EngName=").append(tab_EngName);
        sb.append(", category_Code=").append(category_Code);
        sb.append(", isImportant=").append(isImportant);
        sb.append(", display_Order=").append(display_Order);
        sb.append(", tab_ICON_Freq=").append(tab_ICON_Freq);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}