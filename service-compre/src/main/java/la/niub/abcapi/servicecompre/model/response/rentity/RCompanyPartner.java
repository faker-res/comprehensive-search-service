package la.niub.abcapi.servicecompre.model.response.rentity;

//公司股东
public class RCompanyPartner {
    private String id;
    //排名
    private String share_rank;
    //股东名称
    private String shr_hldr_name;
    //变动方向
    private String hold_shr_change;
    //持股数量（股）
    private String hold_shr_vol;
    //持股数量变动（股）
    private String hold_shr_add;
    //占总股本比例（%）
    private String hold_shr_prop;
    //持股比例变动（%）
    private String share_change_rate;
    //股东性质
    private String hold_type;

    public String getShare_rank() {
        return share_rank;
    }

    public void setShare_rank(String share_rank) {
        this.share_rank = share_rank;
    }

    public String getShr_hldr_name() {
        return shr_hldr_name;
    }

    public void setShr_hldr_name(String shr_hldr_name) {
        this.shr_hldr_name = shr_hldr_name;
    }

    public String getHold_shr_change() {
        return hold_shr_change;
    }

    public void setHold_shr_change(String hold_shr_change) {
        this.hold_shr_change = hold_shr_change;
    }

    public String getHold_shr_vol() {
        return hold_shr_vol;
    }

    public void setHold_shr_vol(String hold_shr_vol) {
        this.hold_shr_vol = hold_shr_vol;
    }

    public String getHold_shr_add() {
        return hold_shr_add;
    }

    public void setHold_shr_add(String hold_shr_add) {
        this.hold_shr_add = hold_shr_add;
    }

    public String getHold_shr_prop() {
        return hold_shr_prop;
    }

    public void setHold_shr_prop(String hold_shr_prop) {
        this.hold_shr_prop = hold_shr_prop;
    }

    public String getShare_change_rate() {
        return share_change_rate;
    }

    public void setShare_change_rate(String share_change_rate) {
        this.share_change_rate = share_change_rate;
    }

    public String getHold_type() {
        return hold_type;
    }

    public void setHold_type(String hold_type) {
        this.hold_type = hold_type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
