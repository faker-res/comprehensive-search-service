package la.niub.abcapi.servicecompre.model.response.client.datachart;

import java.io.Serializable;

public class DataChartImage implements Serializable {

    private String contextLink;

    private Integer height;

    private Integer width;

    private Integer byteSize;

    private String thumbnailLink;

    private Integer thumbnailHeight;

    private Integer thumbnailWidth;

    public String getContextLink() {
        return contextLink;
    }

    public void setContextLink(String contextLink) {
        this.contextLink = contextLink;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getByteSize() {
        return byteSize;
    }

    public void setByteSize(Integer byteSize) {
        this.byteSize = byteSize;
    }

    public String getThumbnailLink() {
        return thumbnailLink;
    }

    public void setThumbnailLink(String thumbnailLink) {
        this.thumbnailLink = thumbnailLink;
    }

    public Integer getThumbnailHeight() {
        return thumbnailHeight;
    }

    public void setThumbnailHeight(Integer thumbnailHeight) {
        this.thumbnailHeight = thumbnailHeight;
    }

    public Integer getThumbnailWidth() {
        return thumbnailWidth;
    }

    public void setThumbnailWidth(Integer thumbnailWidth) {
        this.thumbnailWidth = thumbnailWidth;
    }

    @Override
    public String toString() {
        return "DataChartImage{" +
                "contextLink='" + contextLink + '\'' +
                ", height=" + height +
                ", width=" + width +
                ", byteSize=" + byteSize +
                ", thumbnailLink='" + thumbnailLink + '\'' +
                ", thumbnailHeight=" + thumbnailHeight +
                ", thumbnailWidth=" + thumbnailWidth +
                '}';
    }
}
