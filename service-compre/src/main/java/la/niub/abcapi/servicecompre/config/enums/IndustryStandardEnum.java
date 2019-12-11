package la.niub.abcapi.servicecompre.config.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 行业标准
 */
public enum IndustryStandardEnum {
    C1001001(1001001,"证监会行业(2001)"),
    C1001002(1001002,"上海证券交易所行业"),
    C1001003(1001003,"新华富时行业"),
    C1001004(1001004,"国家统计局行业"),
    C1001005(1001005,"新财富行业"),
    C1001006(1001006,"新闻行业"),
    C1001007(1001007,"GICS行业"),
    C1001008(1001008,"MSCI行业"),
    C1001009(1001009,"申银万国行业(2011)"),
    C1001010(1001010,"恒生行业"),
    C1001011(1001011,"巨潮行业"),
    C1001012(1001012,"中证行业"),
    C1001013(1001013,"证监会行业(2012)"),
    C1001014(1001014,"申银万国行业(2014)"),
    C1001015(1001015,"ABC行业"),
    C1001016(1001016,"中信行业"),
    C1001017(1001017,"恒生行业(2013)"),
    ;

    private Integer indu_standard;

    private String indu_standard_name;

    IndustryStandardEnum(Integer indu_standard, String indu_standard_name) {
        this.indu_standard = indu_standard;
        this.indu_standard_name = indu_standard_name;
    }

    public Integer getIndu_standard() {
        return indu_standard;
    }

    public String getIndu_standard_name() {
        return indu_standard_name;
    }

    private static Map<Integer,IndustryStandardEnum> map = new HashMap<>();

    static {
        //初始化对应关系
        for (IndustryStandardEnum item : IndustryStandardEnum.values()){
            map.put(item.getIndu_standard(),item);
        }
    }

    /**
     * 根据行业标准id获取
     * @param code
     * @return
     */
    public static IndustryStandardEnum byCode(Integer code){
        return map.get(code);
    }
}
