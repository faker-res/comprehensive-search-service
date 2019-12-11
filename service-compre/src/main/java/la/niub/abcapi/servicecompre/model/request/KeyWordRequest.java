package la.niub.abcapi.servicecompre.model.request;

public class KeyWordRequest {

    private Integer type = 1;

    private Integer module = 10001;

    private Integer cate = 0;

    private Integer terminal = 0;

    private Integer lan = 0;

    private Integer offset = 0;

    private Integer limit = 1;

    public Integer getType() { return type; }

    public void setType(Integer type) { this.type=type; }

    public Integer getModule() { return module; }

    public void setModule(Integer module) { this.module=module; }

    public Integer getCate() { return cate; }

    public void setCate(Integer cate) { this.cate=cate; }

    public Integer getTerminal() { return terminal; }

    public void setTerminal(Integer terminal) { this.terminal=terminal; }

    public Integer getLan() { return lan; }

    public void setLan(Integer lan) { this.lan=lan; }

    public Integer getOffset() { return offset; }

    public void setOffset(Integer offset) { this.offset=offset; }

    public Integer getLimit() { return limit; }

    public void setLimit(Integer limit) { this.limit=limit; }

}
