package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PeoBasicInfoWithBLOBs extends PeoBasicInfoModel implements Serializable {
    private String back_gro;

    private String per_resume;

    private static final long serialVersionUID = 1L;

    public PeoBasicInfoWithBLOBs(Long peo_uni_code, Long people_char, String name, Byte sex_par, String birth_day, String country, String poli_status, String university, String high_edu, String image, Date createtime, Date updatetime, String status, String remark, String creator, String editor, String come_source, BigDecimal qhqm_seq, String native_place, String occupation, String main_achievement, String spouse, String back_gro, String per_resume) {
        super(peo_uni_code, people_char, name, sex_par, birth_day, country, poli_status, university, high_edu, image, createtime, updatetime, status, remark, creator, editor, come_source, qhqm_seq, native_place, occupation, main_achievement, spouse);
        this.back_gro = back_gro;
        this.per_resume = per_resume;
    }

    public PeoBasicInfoWithBLOBs() {
        super();
    }

    public String getBack_gro() {
        return back_gro;
    }

    public void setBack_gro(String back_gro) {
        this.back_gro = back_gro == null ? null : back_gro.trim();
    }

    public String getPer_resume() {
        return per_resume;
    }

    public void setPer_resume(String per_resume) {
        this.per_resume = per_resume == null ? null : per_resume.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", back_gro=").append(back_gro);
        sb.append(", per_resume=").append(per_resume);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}