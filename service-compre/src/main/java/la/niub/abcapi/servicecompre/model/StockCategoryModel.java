package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;
import java.util.Date;

public class StockCategoryModel implements Serializable {
    private Integer id;

    private String stock_code;

    private String stock_category;

    private Date update_time;

    private static final long serialVersionUID = 1L;

    public StockCategoryModel(Integer id, String stock_code, String stock_category, Date update_time) {
        this.id = id;
        this.stock_code = stock_code;
        this.stock_category = stock_category;
        this.update_time = update_time;
    }

    public StockCategoryModel() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStock_code() {
        return stock_code;
    }

    public void setStock_code(String stock_code) {
        this.stock_code = stock_code == null ? null : stock_code.trim();
    }

    public String getStock_category() {
        return stock_category;
    }

    public void setStock_category(String stock_category) {
        this.stock_category = stock_category == null ? null : stock_category.trim();
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", stock_code=").append(stock_code);
        sb.append(", stock_category=").append(stock_category);
        sb.append(", update_time=").append(update_time);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}