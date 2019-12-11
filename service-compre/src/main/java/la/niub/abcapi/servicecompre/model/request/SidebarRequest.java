package la.niub.abcapi.servicecompre.model.request;

import com.alibaba.fastjson.JSON;

public class SidebarRequest {

    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public <T> T parseData(Class<T> clz) {
        return JSON.parseObject(data,clz);
    }

    public String parseType() {
        return JSON.parseObject(data,TypeParser.class).getType();
    }

    static class TypeParser {

        private String type;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    @Override
    public String toString() {
        return "SidebarRequest{" +
                "data='" + data + '\'' +
                '}';
    }
}
