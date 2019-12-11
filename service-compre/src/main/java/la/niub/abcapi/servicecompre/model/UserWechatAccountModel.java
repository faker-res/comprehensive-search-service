package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;

public class UserWechatAccountModel implements Serializable {
    private String account_id;

    private String user_id;

    private String head;

    private String account_name;

    private String brief;

    private String verify;

    private String type;

    private String author;

    private static final long serialVersionUID = 1L;

    public UserWechatAccountModel(String account_id, String user_id, String head, String account_name, String brief, String verify, String type, String author) {
        this.account_id = account_id;
        this.user_id = user_id;
        this.head = head;
        this.account_name = account_name;
        this.brief = brief;
        this.verify = verify;
        this.type = type;
        this.author = author;
    }

    public UserWechatAccountModel() {
        super();
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id == null ? null : account_id.trim();
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id == null ? null : user_id.trim();
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head == null ? null : head.trim();
    }

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name == null ? null : account_name.trim();
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief == null ? null : brief.trim();
    }

    public String getVerify() {
        return verify;
    }

    public void setVerify(String verify) {
        this.verify = verify == null ? null : verify.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", account_id=").append(account_id);
        sb.append(", user_id=").append(user_id);
        sb.append(", head=").append(head);
        sb.append(", account_name=").append(account_name);
        sb.append(", brief=").append(brief);
        sb.append(", verify=").append(verify);
        sb.append(", type=").append(type);
        sb.append(", author=").append(author);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}