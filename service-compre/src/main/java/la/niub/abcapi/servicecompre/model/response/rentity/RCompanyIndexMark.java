package la.niub.abcapi.servicecompre.model.response.rentity;

import java.util.Date;

//公司所属指数
public class RCompanyIndexMark {
    //id
    private String id;
    //指数类型
    private String index_type;
    //指数代码
    private String index_code;
    //指数名称
    private String index_name;
    //进入日期
    private Date in_date;
    //退出日期
    private Date out_date;
    //状态
    private String status;

    public String getIndex_type() {
        return index_type;
    }

    public void setIndex_type(String index_type) {
        this.index_type = index_type;
    }

    public String getIndex_code() {
        return index_code;
    }

    public void setIndex_code(String index_code) {
        this.index_code = index_code;
    }

    public String getIndex_name() {
        return index_name;
    }

    public void setIndex_name(String index_name) {
        this.index_name = index_name;
    }

    public Date getIn_date() {
        return in_date;
    }

    public void setIn_date(Date in_date) {
        this.in_date = in_date;
    }

    public Date getOut_date() {
        return out_date;
    }

    public void setOut_date(Date out_date) {
        this.out_date = out_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
