package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class OrgBasicInfoWithBLOBs extends OrgBasicInfoModel implements Serializable {
    private String org_profile;

    private String main_business;

    private static final long serialVersionUID = 1L;

    public OrgBasicInfoWithBLOBs(Long org_uni_code, Long com_uni_code, Long org_type, String org_name, String org_sname, String org_ename, String org_espell, BigDecimal org_capital, Long currency_type, Date build_time, Long eco_nature, Long com_nature, String regist_add, String office_add, String contact_add, String org_web, String legal_rp, String gen_manager, String contact_tel, String contact_fax, String email, String official_number, Integer license, Date start_date, Date cutoff_date, Long reason, Date createtime, Date updatetime, String status, String remark, String creator, String editor, String come_source, String org_profile, String main_business) {
        super(org_uni_code, com_uni_code, org_type, org_name, org_sname, org_ename, org_espell, org_capital, currency_type, build_time, eco_nature, com_nature, regist_add, office_add, contact_add, org_web, legal_rp, gen_manager, contact_tel, contact_fax, email, official_number, license, start_date, cutoff_date, reason, createtime, updatetime, status, remark, creator, editor, come_source);
        this.org_profile = org_profile;
        this.main_business = main_business;
    }

    public OrgBasicInfoWithBLOBs() {
        super();
    }

    public String getOrg_profile() {
        return org_profile;
    }

    public void setOrg_profile(String org_profile) {
        this.org_profile = org_profile == null ? null : org_profile.trim();
    }

    public String getMain_business() {
        return main_business;
    }

    public void setMain_business(String main_business) {
        this.main_business = main_business == null ? null : main_business.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", org_profile=").append(org_profile);
        sb.append(", main_business=").append(main_business);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}