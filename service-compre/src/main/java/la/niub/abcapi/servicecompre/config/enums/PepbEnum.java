package la.niub.abcapi.servicecompre.config.enums;

/**
 * 市盈率、市净率
 */
public enum PepbEnum {

    PELYR("pelyr","静态市盈率"),
    PETTM("pettm","动态市盈率(TTM)"),
    PBMRQ("pbmrq","市净率(MRQ)")
    ;

    private String shortName;

    private String chineseName;

    PepbEnum(String shortName, String chineseName) {
        this.shortName = shortName;
        this.chineseName = chineseName;
    }

    public String getShortName() {
        return shortName;
    }

    public String getChineseName() {
        return chineseName;
    }
}
