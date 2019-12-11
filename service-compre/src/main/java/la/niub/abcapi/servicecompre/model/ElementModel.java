package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;

public class ElementModel implements Serializable {
    private Integer ID;

    private String sheet_name;

    private String field_name;

    private String data_unit;

    private String data_frequency;

    private String data_update_frequency;

    private String abc_date_update_time;

    private String data_source;

    private String data_source_code;

    private static final long serialVersionUID = 1L;

    public ElementModel(Integer ID, String sheet_name, String field_name, String data_unit, String data_frequency, String data_update_frequency, String abc_date_update_time, String data_source, String data_source_code) {
        this.ID = ID;
        this.sheet_name = sheet_name;
        this.field_name = field_name;
        this.data_unit = data_unit;
        this.data_frequency = data_frequency;
        this.data_update_frequency = data_update_frequency;
        this.abc_date_update_time = abc_date_update_time;
        this.data_source = data_source;
        this.data_source_code = data_source_code;
    }

    public ElementModel() {
        super();
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getSheet_name() {
        return sheet_name;
    }

    public void setSheet_name(String sheet_name) {
        this.sheet_name = sheet_name == null ? null : sheet_name.trim();
    }

    public String getField_name() {
        return field_name;
    }

    public void setField_name(String field_name) {
        this.field_name = field_name == null ? null : field_name.trim();
    }

    public String getData_unit() {
        return data_unit;
    }

    public void setData_unit(String data_unit) {
        this.data_unit = data_unit == null ? null : data_unit.trim();
    }

    public String getData_frequency() {
        return data_frequency;
    }

    public void setData_frequency(String data_frequency) {
        this.data_frequency = data_frequency == null ? null : data_frequency.trim();
    }

    public String getData_update_frequency() {
        return data_update_frequency;
    }

    public void setData_update_frequency(String data_update_frequency) {
        this.data_update_frequency = data_update_frequency == null ? null : data_update_frequency.trim();
    }

    public String getAbc_date_update_time() {
        return abc_date_update_time;
    }

    public void setAbc_date_update_time(String abc_date_update_time) {
        this.abc_date_update_time = abc_date_update_time == null ? null : abc_date_update_time.trim();
    }

    public String getData_source() {
        return data_source;
    }

    public void setData_source(String data_source) {
        this.data_source = data_source == null ? null : data_source.trim();
    }

    public String getData_source_code() {
        return data_source_code;
    }

    public void setData_source_code(String data_source_code) {
        this.data_source_code = data_source_code == null ? null : data_source_code.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", ID=").append(ID);
        sb.append(", sheet_name=").append(sheet_name);
        sb.append(", field_name=").append(field_name);
        sb.append(", data_unit=").append(data_unit);
        sb.append(", data_frequency=").append(data_frequency);
        sb.append(", data_update_frequency=").append(data_update_frequency);
        sb.append(", abc_date_update_time=").append(abc_date_update_time);
        sb.append(", data_source=").append(data_source);
        sb.append(", data_source_code=").append(data_source_code);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}