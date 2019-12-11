package la.niub.abcapi.servicecompre.model.response;

public class DataDetailResponse<T> extends Response<T> {

    private String type;

    private Boolean isSharePage;

    private Boolean isChartImage;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getIsSharePage() {
        return isSharePage;
    }

    public void setIsSharePage(Boolean sharePage) {
        isSharePage = sharePage;
    }

    public Boolean getIsChartImage() {
        return isChartImage;
    }

    public void setIsChartImage(Boolean chartImage) {
        isChartImage = chartImage;
    }

    @Override
    public String toString() {
        return "DataDetailResponse{" +
                "type='" + type + '\'' +
                ", isSharePage=" + isSharePage +
                ", isChartImage=" + isChartImage +
                '}';
    }
}
