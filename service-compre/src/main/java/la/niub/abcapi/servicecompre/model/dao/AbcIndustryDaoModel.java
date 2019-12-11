package la.niub.abcapi.servicecompre.model.dao;

public class AbcIndustryDaoModel {
    private int induUniCode;

    private int induStandard;

    private String induCode;

    private String induName;

    private String indexCode;

    private String parentId;

    public int getInduUniCode() {
        return induUniCode;
    }

    public void setInduUniCode(int induUniCode) {
        this.induUniCode = induUniCode;
    }

    public int getInduStandard() {
        return induStandard;
    }

    public void setInduStandard(int induStandard) {
        this.induStandard = induStandard;
    }

    public String getInduCode() {
        return induCode;
    }

    public void setInduCode(String induCode) {
        this.induCode = induCode;
    }

    public String getInduName() {
        return induName;
    }

    public void setInduName(String induName) {
        this.induName = induName;
    }

    public String getIndexCode() {
        return indexCode;
    }

    public void setIndexCode(String indexCode) {
        this.indexCode = indexCode;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
