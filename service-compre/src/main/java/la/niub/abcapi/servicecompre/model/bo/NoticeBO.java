package la.niub.abcapi.servicecompre.model.bo;

import la.niub.abcapi.servicecompre.model.response.client.notice.NoticeItem;

import java.util.List;

public class NoticeBO {

    private List<NoticeItem> item;

    public List<NoticeItem> getItem() {
        return item;
    }

    public void setItem(List<NoticeItem> item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "NoticeBO{" +
                "item=" + item +
                '}';
    }
}
