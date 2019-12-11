package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;
import java.util.Date;

public class SeccomActivities implements Serializable{

    private static final long serialVersionUID = -8725584629630719981L;

    private String org_name;
    private String event;
    private Date starttime;
    private Date endtime;
    private String place;
    private String industry_id;

    public SeccomActivities() {
        super();
    }

    public SeccomActivities(String org_name, String event, Date starttime, Date endtime, String place, String industry_id) {
        this.org_name = org_name;
        this.event = event;
        this.starttime = starttime;
        this.endtime = endtime;
        this.place = place;
        this.industry_id = industry_id;
    }

    public String getOrg_name() {
        return org_name;
    }

    public void setOrg_name(String org_name) {
        this.org_name = org_name;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getIndustry_id() {
        return industry_id;
    }

    public void setIndustry_id(String industry_id) {
        this.industry_id = industry_id;
    }
}
