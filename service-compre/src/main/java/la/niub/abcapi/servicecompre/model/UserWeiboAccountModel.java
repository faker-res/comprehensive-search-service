package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;

public class UserWeiboAccountModel implements Serializable {
    private String account_id;

    private String user_id;

    private String account_name;

    private String verify;

    private String position;

    private String brief;

    private String tags;

    private Integer follow;

    private Integer follower;

    private Integer blog_num;

    private String head;

    private String url;

    private static final long serialVersionUID = 1L;

    public UserWeiboAccountModel(String account_id, String user_id, String account_name, String verify, String position, String brief, String tags, Integer follow, Integer follower, Integer blog_num, String head, String url) {
        this.account_id = account_id;
        this.user_id = user_id;
        this.account_name = account_name;
        this.verify = verify;
        this.position = position;
        this.brief = brief;
        this.tags = tags;
        this.follow = follow;
        this.follower = follower;
        this.blog_num = blog_num;
        this.head = head;
        this.url = url;
    }

    public UserWeiboAccountModel() {
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

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name == null ? null : account_name.trim();
    }

    public String getVerify() {
        return verify;
    }

    public void setVerify(String verify) {
        this.verify = verify == null ? null : verify.trim();
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief == null ? null : brief.trim();
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags == null ? null : tags.trim();
    }

    public Integer getFollow() {
        return follow;
    }

    public void setFollow(Integer follow) {
        this.follow = follow;
    }

    public Integer getFollower() {
        return follower;
    }

    public void setFollower(Integer follower) {
        this.follower = follower;
    }

    public Integer getBlog_num() {
        return blog_num;
    }

    public void setBlog_num(Integer blog_num) {
        this.blog_num = blog_num;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head == null ? null : head.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", account_id=").append(account_id);
        sb.append(", user_id=").append(user_id);
        sb.append(", account_name=").append(account_name);
        sb.append(", verify=").append(verify);
        sb.append(", position=").append(position);
        sb.append(", brief=").append(brief);
        sb.append(", tags=").append(tags);
        sb.append(", follow=").append(follow);
        sb.append(", follower=").append(follower);
        sb.append(", blog_num=").append(blog_num);
        sb.append(", head=").append(head);
        sb.append(", url=").append(url);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}