package la.niub.abcapi.servicecompre.model.bo;

public class ComprehensiveChartGraphTableDataBO {

    private Integer column;

    private String text;

    private Integer w;

    private Integer h;

    private Integer y;

    private Integer x;

    private Integer row;

    public Integer getColumn() {
        return column;
    }

    public void setColumn(Integer column) {
        this.column = column;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getW() {
        return w;
    }

    public void setW(Integer w) {
        this.w = w;
    }

    public Integer getH() {
        return h;
    }

    public void setH(Integer h) {
        this.h = h;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    @Override
    public String toString() {
        return "ComprehensiveChartGraphTableDataBO{" +
                "column=" + column +
                ", text='" + text + '\'' +
                ", w=" + w +
                ", h=" + h +
                ", y=" + y +
                ", x=" + x +
                ", row=" + row +
                '}';
    }
}
