package la.niub.abcapi.servicecompre.model.response.client;

public class Search2OptionItem {
    private String name;

    private Integer count;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "NoticeSearch2OptionItem{" +
                "name='" + name + '\'' +
                ", count=" + count +
                '}';
    }
}
