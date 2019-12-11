package la.niub.abcapi.servicecompre.model;

import java.util.Date;

public class UserWeiboInfoModel {

    Long peo_uni_code;

    Long publish_time;

//    Date crawl_time;
//
//    Long comment_num;
//
//    Long praise_num;
//
//    Long repeat_num;

    String weibo_article_id;

    String url;

    String content;

    String account_name;

//    String source;

    String img;

    Long member_ranking;

    String head;

    String verify;

    String type;

    public UserWeiboInfoModel() {
    }

//    public UserWeiboInfoModel(String peo_uni_code, Date publish_time, Date crawl_time, Long comment_num, Long praise_num, Long repeat_num, String weibo_article_id, String url, String content, String account_name, String source, String imag, Long member_ranking, String head, String verify, String type) {
//        this.peo_uni_code = peo_uni_code;
//        this.publish_time = publish_time;
//        this.crawl_time = crawl_time;
//        this.comment_num = comment_num;
//        this.praise_num = praise_num;
//        this.repeat_num = repeat_num;
//        this.weibo_article_id = weibo_article_id;
//        this.url = url;
//        this.content = content;
//        this.account_name = account_name;
//        this.source = source;
//        this.imag = imag;
//        this.member_ranking = member_ranking;
//        this.head = head;
//        this.verify = verify;
//        this.type = type;
//    }


    public Long getPeo_uni_code() {
        return peo_uni_code;
    }

    public void setPeo_uni_code(Long peo_uni_code) {
        this.peo_uni_code = peo_uni_code;
    }

    public Long getPublish_time() {
        return publish_time;
    }

    public void setPublish_time(Long publish_time) {
        this.publish_time = publish_time;
    }

    //    public Long getComment_num() {
//        return comment_num;
//    }
//
//    public void setComment_num(Long comment_num) {
//        this.comment_num = comment_num;
//    }
//
//    public Long getPraise_num() {
//        return praise_num;
//    }
//
//    public void setPraise_num(Long praise_num) {
//        this.praise_num = praise_num;
//    }
//
//    public Long getRepeat_num() {
//        return repeat_num;
//    }
//
//    public void setRepeat_num(Long repeat_num) {
//        this.repeat_num = repeat_num;
//    }

    public String getWeibo_article_id() {
        return weibo_article_id;
    }

    public void setWeibo_article_id(String weibo_article_id) {
        this.weibo_article_id = weibo_article_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

//    public String getSource() {
//        return source;
//    }
//
//    public void setSource(String source) {
//        this.source = source;
//    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Long getMember_ranking() {
        return member_ranking;
    }

    public void setMember_ranking(Long member_ranking) {
        this.member_ranking = member_ranking;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getVerify() {
        return verify;
    }

    public void setVerify(String verify) {
        this.verify = verify;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

//    public Date getCrawl_time() {
//        return crawl_time;
//    }
//
//    public void setCrawl_time(Date crawl_time) {
//        this.crawl_time = crawl_time;
//    }
}
