package la.niub.abcapi.servicecompre.service.impl;

import la.niub.abcapi.servicecompre.model.response.rentity.*;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
@Component
public class CustomNavigation {
    //财务摘要导航
    public Object financialsummary(){
        List datalist=new ArrayList<>();
        datalist.add("reporttype");
        //利润表摘要
        HashMap profitBrief=new HashMap();
        profitBrief.put("profitBrief",convertObj(RCompanyProfitBrief.class));
        datalist.add(profitBrief);
        //资产负债摘要
        HashMap liabilitiesBrief=new HashMap();
        liabilitiesBrief.put("profitstatement",convertObj(RCompanyLiabilitiesBrief.class));
        datalist.add(liabilitiesBrief);
        //现金流量表摘要
        HashMap cashBrief=new HashMap();
        cashBrief.put("cashBrief",convertObj(RCompanyCashBrief.class));
        datalist.add(cashBrief);
        //关键比率
        HashMap keyRatioBrief=new HashMap();
        keyRatioBrief.put("profitstatement",convertObj(RCompanyKeyRatioBrief.class));
        datalist.add(keyRatioBrief);
        //每股指标
        HashMap perShareBrief=new HashMap();
        perShareBrief.put("perShareBrief",convertObj(RCompanyPerShareBrief.class));
        datalist.add(perShareBrief);
        //其他指标
        HashMap other=new HashMap();
        other.put("other", Arrays.asList("total_staff"));
        datalist.add(other);
        return  datalist;
    }

    private List<String> convertObj(Class cls){
        List<String> result=new ArrayList<String>();
        Field[] fields = cls.getDeclaredFields();
        for (int i=0;i<fields.length;i++){
            try {
                Field field = fields[i];
                String name = field.getName();
                result.add(name);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
