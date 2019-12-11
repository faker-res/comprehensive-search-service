package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;
import java.util.Date;

public class ntfEntityLawsuitModel implements Serializable {
    private Integer id;

    private String uuid;

    private Long com_uni_code;

    private String com_name;

    private String url;

    private Integer openlaw_seq;

    private String caseno;

    private String casetype;

    private String doctype;

    private String base;

    private String court;

    private String judgetime;

    private Date submittime;

    private String title;

    private String casereason;

    private String trialprocedure;

    private String legalbasis;

    private String come_source;

    private Date createtime;

    private Date updatetime;

    private Byte status;

    private String plaintext;

    private String head;

    private String partyInfo;

    private String lawsuit_record;

    private String fact;

    private String reason;

    private String result;

    private String tail;

    private String party;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public Long getCom_uni_code() {
        return com_uni_code;
    }

    public void setCom_uni_code(Long com_uni_code) {
        this.com_uni_code = com_uni_code;
    }

    public String getCom_name() {
        return com_name;
    }

    public void setCom_name(String com_name) {
        this.com_name = com_name == null ? null : com_name.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getOpenlaw_seq() {
        return openlaw_seq;
    }

    public void setOpenlaw_seq(Integer openlaw_seq) {
        this.openlaw_seq = openlaw_seq;
    }

    public String getCaseno() {
        return caseno;
    }

    public void setCaseno(String caseno) {
        this.caseno = caseno == null ? null : caseno.trim();
    }

    public String getCasetype() {
        return casetype;
    }

    public void setCasetype(String casetype) {
        this.casetype = casetype == null ? null : casetype.trim();
    }

    public String getDoctype() {
        return doctype;
    }

    public void setDoctype(String doctype) {
        this.doctype = doctype == null ? null : doctype.trim();
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base == null ? null : base.trim();
    }

    public String getCourt() {
        return court;
    }

    public void setCourt(String court) {
        this.court = court == null ? null : court.trim();
    }

    public String getJudgetime() {
        return judgetime;
    }

    public void setJudgetime(String judgetime) {
        this.judgetime = judgetime == null ? null : judgetime.trim();
    }

    public Date getSubmittime() {
        return submittime;
    }

    public void setSubmittime(Date submittime) {
        this.submittime = submittime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getCasereason() {
        return casereason;
    }

    public void setCasereason(String casereason) {
        this.casereason = casereason == null ? null : casereason.trim();
    }

    public String getTrialprocedure() {
        return trialprocedure;
    }

    public void setTrialprocedure(String trialprocedure) {
        this.trialprocedure = trialprocedure == null ? null : trialprocedure.trim();
    }

    public String getLegalbasis() {
        return legalbasis;
    }

    public void setLegalbasis(String legalbasis) {
        this.legalbasis = legalbasis == null ? null : legalbasis.trim();
    }

    public String getCome_source() {
        return come_source;
    }

    public void setCome_source(String come_source) {
        this.come_source = come_source == null ? null : come_source.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getPlaintext() {
        return plaintext;
    }

    public void setPlaintext(String plaintext) {
        this.plaintext = plaintext;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getPartyInfo() {
        return partyInfo;
    }

    public void setPartyInfo(String partyInfo) {
        this.partyInfo = partyInfo;
    }

    public String getLawsuit_record() {
        return lawsuit_record;
    }

    public void setLawsuit_record(String lawsuit_record) {
        this.lawsuit_record = lawsuit_record;
    }

    public String getFact() {
        return fact;
    }

    public void setFact(String fact) {
        this.fact = fact;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getTail() {
        return tail;
    }

    public void setTail(String tail) {
        this.tail = tail;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    @Override
    public String toString() {
        return "ntfEntityLawsuitModel{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", com_uni_code=" + com_uni_code +
                ", com_name='" + com_name + '\'' +
                ", url='" + url + '\'' +
                ", openlaw_seq=" + openlaw_seq +
                ", caseno='" + caseno + '\'' +
                ", casetype='" + casetype + '\'' +
                ", doctype='" + doctype + '\'' +
                ", base='" + base + '\'' +
                ", court='" + court + '\'' +
                ", judgetime='" + judgetime + '\'' +
                ", submittime=" + submittime +
                ", title='" + title + '\'' +
                ", casereason='" + casereason + '\'' +
                ", trialprocedure='" + trialprocedure + '\'' +
                ", legalbasis='" + legalbasis + '\'' +
                ", come_source='" + come_source + '\'' +
                ", createtime=" + createtime +
                ", updatetime=" + updatetime +
                ", status=" + status +
                ", plaintext='" + plaintext + '\'' +
                ", head='" + head + '\'' +
                ", partyInfo='" + partyInfo + '\'' +
                ", lawsuit_record='" + lawsuit_record + '\'' +
                ", fact='" + fact + '\'' +
                ", reason='" + reason + '\'' +
                ", result='" + result + '\'' +
                ", tail='" + tail + '\'' +
                ", party='" + party + '\'' +
                '}';
    }
}