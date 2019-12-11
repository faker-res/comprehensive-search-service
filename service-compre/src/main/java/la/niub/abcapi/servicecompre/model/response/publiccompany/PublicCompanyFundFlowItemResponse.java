package la.niub.abcapi.servicecompre.model.response.publiccompany;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class PublicCompanyFundFlowItemResponse {

    private Long id;

    private int time;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date date;

    private float mainFlow;

    private float mainPro;

    private float supInFlow;

    private float supOutFlow;

    private float supFlow;

    private float supPro;

    private float bigInFlow;

    private float bigOutFlow;

    private float bigFlow;

    private float bigPro;

    private float midInFlow;

    private float midOutFlow;

    private float midFlow;

    private float midPro;

    private float smInFlow;

    private float smOutFlow;

    private float smFlow;

    private float smPro;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getMainFlow() {
        return mainFlow;
    }

    public void setMainFlow(float mainFlow) {
        this.mainFlow = mainFlow;
    }

    public float getMainPro() {
        return mainPro;
    }

    public void setMainPro(float mainPro) {
        this.mainPro = mainPro;
    }

    public float getSupInFlow() {
        return supInFlow;
    }

    public void setSupInFlow(float supInFlow) {
        this.supInFlow = supInFlow;
    }

    public float getSupOutFlow() {
        return supOutFlow;
    }

    public void setSupOutFlow(float supOutFlow) {
        this.supOutFlow = supOutFlow;
    }

    public float getSupFlow() {
        return supFlow;
    }

    public void setSupFlow(float supFlow) {
        this.supFlow = supFlow;
    }

    public float getSupPro() {
        return supPro;
    }

    public void setSupPro(float supPro) {
        this.supPro = supPro;
    }

    public float getBigInFlow() {
        return bigInFlow;
    }

    public void setBigInFlow(float bigInFlow) {
        this.bigInFlow = bigInFlow;
    }

    public float getBigOutFlow() {
        return bigOutFlow;
    }

    public void setBigOutFlow(float bigOutFlow) {
        this.bigOutFlow = bigOutFlow;
    }

    public float getBigFlow() {
        return bigFlow;
    }

    public void setBigFlow(float bigFlow) {
        this.bigFlow = bigFlow;
    }

    public float getBigPro() {
        return bigPro;
    }

    public void setBigPro(float bigPro) {
        this.bigPro = bigPro;
    }

    public float getMidInFlow() {
        return midInFlow;
    }

    public void setMidInFlow(float midInFlow) {
        this.midInFlow = midInFlow;
    }

    public float getMidOutFlow() {
        return midOutFlow;
    }

    public void setMidOutFlow(float midOutFlow) {
        this.midOutFlow = midOutFlow;
    }

    public float getMidFlow() {
        return midFlow;
    }

    public void setMidFlow(float midFlow) {
        this.midFlow = midFlow;
    }

    public float getMidPro() {
        return midPro;
    }

    public void setMidPro(float midPro) {
        this.midPro = midPro;
    }

    public float getSmInFlow() {
        return smInFlow;
    }

    public void setSmInFlow(float smInFlow) {
        this.smInFlow = smInFlow;
    }

    public float getSmOutFlow() {
        return smOutFlow;
    }

    public void setSmOutFlow(float smOutFlow) {
        this.smOutFlow = smOutFlow;
    }

    public float getSmFlow() {
        return smFlow;
    }

    public void setSmFlow(float smFlow) {
        this.smFlow = smFlow;
    }

    public float getSmPro() {
        return smPro;
    }

    public void setSmPro(float smPro) {
        this.smPro = smPro;
    }
}
