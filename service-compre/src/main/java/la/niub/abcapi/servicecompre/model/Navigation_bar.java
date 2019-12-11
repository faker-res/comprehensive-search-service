package la.niub.abcapi.servicecompre.model;

import java.util.Date;
import java.util.List;

/**
 *   @author adams
 */
public class Navigation_bar {
    //主键
    private String id;
    //父ID
    private String parent_id;
    //菜单名称
    private String name;
    //菜单URL
    private String url;
    //菜单描述
    private String content;
    //菜单状态(1：可用；0：不可用)
    private String status;
    // 菜单排序
    private String sort_order;
    //菜单类型
    private String type;
    //菜单模块
    private String module;
    //菜单图标
    private String icon;
    //是否卡片
    private String iscard;
    //节点编码
    private String encode_name;
    //创建时间
    private Date created;
    //更新时间
    private Date updated;


    //注入实体
    private List<Navigation_bar> children;

    public List<Navigation_bar> getChildren() {
        return children;
    }

    public void setChildren(List<Navigation_bar> children) {
        this.children = children;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return this.id;
    }
    public void setParent_id(String parent_id){
        this.parent_id = parent_id;
    }

    public String getParent_id(){
        return this.parent_id;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
    public void setUrl(String url){
        this.url = url;
    }

    public String getUrl(){
        return this.url;
    }
    public void setContent(String content){
        this.content = content;
    }

    public String getContent(){
        return this.content;
    }
    public void setStatus(String status){
        this.status = status;
    }

    public String getStatus(){
        return this.status;
    }
    public void setSort_order(String sort_order){
        this.sort_order = sort_order;
    }

    public String getSort_order(){
        return this.sort_order;
    }
    public void setType(String type){
        this.type = type;
    }

    public String getType(){
        return this.type;
    }
    public void setIcon(String icon){
        this.icon = icon;
    }

    public String getIcon(){
        return this.icon;
    }
    public void setCreated(Date created){
        this.created = created;
    }

    public Date getCreated(){
        return this.created;
    }
    public void setUpdated(Date updated){
        this.updated = updated;
    }

    public Date getUpdated(){
        return this.updated;
    }

     public Navigation_bar() {

     }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getIscard() {
        return iscard;
    }

    public void setIscard(String iscard) {
        this.iscard = iscard;
    }

    public String getEncode_name() {
        return encode_name;
    }

    public void setEncode_name(String encode_name) {
        this.encode_name = encode_name;
    }
}