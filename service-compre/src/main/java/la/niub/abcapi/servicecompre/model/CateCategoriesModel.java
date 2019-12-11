package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;
import java.util.List;

public class CateCategoriesModel implements Serializable {


    private String category_Code;

    private String category_icon;

    private String category_Name;

    private Integer count;

    private List<Object> item;

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

    public String getCategory_Name() {
        return category_Name;
    }

    public void setCategory_Name(String category_Name) {
        this.category_Name = category_Name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<Object> getItem() {
        return item;
    }

    public void setItem(List<Object> item) {
        this.item = item;
    }
}
