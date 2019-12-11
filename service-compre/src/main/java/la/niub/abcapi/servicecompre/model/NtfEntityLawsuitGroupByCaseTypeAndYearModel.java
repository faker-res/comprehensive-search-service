package la.niub.abcapi.servicecompre.model;

public class NtfEntityLawsuitGroupByCaseTypeAndYearModel {
    private int judgeYear;

    private String casetype;

    private int count;


    public int getJudgeYear() {
        return judgeYear;
    }

    public void setJudgeYear(int judgeYear) {
        this.judgeYear = judgeYear;
    }

    public String getCasetype() {
        return casetype;
    }

    public void setCasetype(String casetype) {
        this.casetype = casetype;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
