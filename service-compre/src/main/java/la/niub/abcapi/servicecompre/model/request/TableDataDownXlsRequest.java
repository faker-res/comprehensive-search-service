package la.niub.abcapi.servicecompre.model.request;

public class TableDataDownXlsRequest {

    private String text;

    private Integer rowSpan = 1; // 合并行 默认1无合并

    private Integer colSpan = 1; // 合并列

    private Integer column;

    private Integer row;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getColumn() {
        return column;
    }

    public void setColumn(Integer column) {
        this.column = column;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getRowSpan() {
        return rowSpan;
    }

    public void setRowSpan(Integer rowSpan) {
        this.rowSpan = rowSpan;
    }

    public Integer getColSpan() {
        return colSpan;
    }

    public void setColSpan(Integer colSpan) {
        this.colSpan = colSpan;
    }
}
