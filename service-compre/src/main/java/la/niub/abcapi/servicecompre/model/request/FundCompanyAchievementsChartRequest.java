package la.niub.abcapi.servicecompre.model.request;

public class FundCompanyAchievementsChartRequest {
    private String com_uni_codes;
    private String plate_code;
    private String year;
    private String field;

    public String getCom_uni_codes() {
        return com_uni_codes;
    }

    public void setCom_uni_codes(String com_uni_codes) {
        this.com_uni_codes = com_uni_codes;
    }

    public String getPlate_code() {
        return plate_code;
    }

    public void setPlate_code(String plate_code) {
        this.plate_code = plate_code;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
