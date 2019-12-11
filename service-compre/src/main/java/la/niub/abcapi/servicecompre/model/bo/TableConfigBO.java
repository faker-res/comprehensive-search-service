package la.niub.abcapi.servicecompre.model.bo;

import la.niub.abcapi.servicecompre.model.AllSearchTablesModel;

import java.util.List;

public class TableConfigBO {

    private String tab_id;

    private String icon;

    private String name;

    private String shortName;

    private String engName;

    private String category_Name;

    private String category_Code;

    private String category_icon;

    private List<AllSearchTablesModel> item;

    private Integer count;

    private String link;

    public String getTab_id() {
        return tab_id;
    }

    public void setTab_id(String tab_id) {
        this.tab_id = tab_id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getEngName() {
        return engName;
    }

    public void setEngName(String engName) {
        this.engName = engName;
    }

    public String getCategory_Name() {
        return category_Name;
    }

    public void setCategory_Name(String category_Name) {
        this.category_Name = category_Name;
    }

    public String getCategory_Code() {
        return category_Code;
    }

    public void setCategory_Code(String category_Code) {
        this.category_Code = category_Code;
    }

    public String getCategory_icon() {
        return category_icon;
    }

    public void setCategory_icon(String category_icon) {
        this.category_icon = category_icon;
    }

    public List<AllSearchTablesModel> getItem() {
        return item;
    }

    public void setItem(List<AllSearchTablesModel> item) {
        this.item = item;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "TableConfigBO{" +
                "tab_id='" + tab_id + '\'' +
                ", icon='" + icon + '\'' +
                ", name='" + name + '\'' +
                ", shortName='" + shortName + '\'' +
                ", engName='" + engName + '\'' +
                ", category_Name='" + category_Name + '\'' +
                ", category_Code='" + category_Code + '\'' +
                ", category_icon='" + category_icon + '\'' +
                ", item=" + item +
                ", count=" + count +
                ", link='" + link + '\'' +
                '}';
    }
}
