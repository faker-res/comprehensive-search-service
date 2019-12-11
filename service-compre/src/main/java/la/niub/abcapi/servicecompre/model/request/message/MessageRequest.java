package la.niub.abcapi.servicecompre.model.request.message;

public class MessageRequest {

    private String keyword;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String toString() {
        return "MessageRequest{" +
                "keyword='" + keyword + '\'' +
                '}';
    }
}
