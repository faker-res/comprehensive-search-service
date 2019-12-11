package la.niub.abcapi.servicecompre.config.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ResearchIndustryMap {

    public static List<String> getResearchIndustry(String indu_name) {return RESEACH_INDUSTRY_TYPE.get(indu_name);}

    private static final HashMap<String, List<String>> RESEACH_INDUSTRY_TYPE = new HashMap<String, List<String>>() {
        {
            put("采掘", new ArrayList<String>(){{add("煤炭开采"); add("采掘");}});
            put("公用事业", new ArrayList<String>(){{add("环保"); add("电力及公用事业"); add("电力、煤气及水等公共事业"); add("环保与公用事业");}});
            put("机械设备", new ArrayList<String>(){{add("机械");}});
            put("电子", new ArrayList<String>(){{add("电子");}});
            put("房地产", new ArrayList<String>(){{add("房地产");}});
            put("传媒", new ArrayList<String>(){{add("传播与文化");}});
            put("汽车", new ArrayList<String>(){{add("汽车和汽车零部件"); add("汽车");}});
            put("食品饮料", new ArrayList<String>(){{add("食品饮料");}});
            put("农林牧渔", new ArrayList<String>(){{add("农林牧渔");}});
            put("建筑装饰", new ArrayList<String>(){{add("建筑与工程"); add("建筑");}});
            put("建筑材料", new ArrayList<String>(){{add("非金属类建材");}});
            put("交通运输", new ArrayList<String>(){{add("交通运输仓储"); add("公路港口航运"); add("航空运输");}});
            put("有色金属", new ArrayList<String>(){{add("有色金属");}});
            put("家用电器", new ArrayList<String>(){{add("家电");}});
            put("非银金融", new ArrayList<String>(){{add("非银行金融");}});
            put("化工", new ArrayList<String>(){{add("基础化工"); add("石油化工");}});
            put("医药生物", new ArrayList<String>(){{add("医药生物");}});
            put("电气设备", new ArrayList<String>(){{add("电力设备与新能源"); add("电力设备");}});
            put("休闲服务", new ArrayList<String>(){{add("社会服务"); add("家电、休闲品和奢侈品");}});
            put("通信", new ArrayList<String>(){{add("通信");}});
            put("银行", new ArrayList<String>(){{add("银行"); add("金融"); add("银行和金融服务");}});
            put("计算机", new ArrayList<String>(){{add("计算机");}});
            put("轻工制造", new ArrayList<String>(){{add("造纸印刷"); add("轻工造纸");}});
            put("纺织服装", new ArrayList<String>(){{add("纺织服装");}});
            put("钢铁", new ArrayList<String>(){{add("钢铁");}});
            put("商业贸易", new ArrayList<String>(){{add("批发和零售贸易");}});
            put("国防军工", new ArrayList<String>(){{add("军工");}});
            put("综合", new ArrayList<String>(){{add("综合");}});
        }
    };
}
