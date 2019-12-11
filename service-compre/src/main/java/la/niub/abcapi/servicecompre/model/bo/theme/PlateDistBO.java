package la.niub.abcapi.servicecompre.model.bo.theme;

public class PlateDistBO {

    String index_code;

    String index_name;

    RegionDistBO regionDist;

    ListedPlateDistBO listedPlateDist;

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

    public RegionDistBO getRegionDist() {
        return regionDist;
    }

    public void setRegionDist(RegionDistBO regionDist) {
        this.regionDist = regionDist;
    }

    public ListedPlateDistBO getListedPlateDist() {
        return listedPlateDist;
    }

    public void setListedPlateDist(ListedPlateDistBO listedPlateDist) {
        this.listedPlateDist = listedPlateDist;
    }
}
