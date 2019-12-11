package la.niub.abcapi.servicecompre.component.rbac;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import la.niub.abcapi.servicecompre.component.util.HttpUtil;
import la.niub.abcapi.servicecompre.component.util.Md5Util;
import la.niub.abcapi.servicecompre.component.util.StringUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class AuthSdk {

    @Value("${rbac.serviceHost}")
    private  String serviceHost;

    @Value("${rbac.client_key}")
    private  String client_key;

    @Value("${rbac.app_secret}")
    private  String app_secret;

    @Value("${rbac.accessMap}")
    private String accessMap;

    private static Map map;


    /**
     * 用户可以展示哪些权限
     * @param userId
     * @return
     */
    public List<Map<String,Object>> getAccess (String userId) {

        String url = serviceHost + "/external/accessIdListByUserId";
        Map<String, String> params = new HashMap<>();
        params.put("client_key", client_key);
        params.put("app_secret", app_secret);
        params.put("userId", userId);
        //params.put("accessIds", String.join(",", AccessIds));
        String sign = getSign(params);
        params.put("client_secret", sign);
        params.remove("app_secret");


        String ret = HttpUtil.get(url, params, null);

        List<Map<String,Object>> showItems = new ArrayList<>();
        if (ret != null && !ret.isEmpty()) {
            JSONObject modelsRet = JSON.parseObject(ret);
            if (modelsRet != null && modelsRet.get("data") != null) {
                if( modelsRet.get("data") instanceof JSONArray ) {
                    JSONArray array = (JSONArray)modelsRet.get("data");
                    if(array.size()>=0){
                        for (int i = 0; i < array.size(); i++) {
                            showItems.add((Map)array.get(i));
                        }
                    }
                }
            }
        }
        return showItems;

    }
    public String getModuleId(String url){
        if(map == null){
            map = new HashMap();
            if(!StringUtil.isEmpty(accessMap)){
                String[] ss = accessMap.split(",");
                for (int i = 0; i < ss.length; i++) {
                    String s = ss[i].trim();
                    String[] mapString = s.split("=");
                    map.put(mapString[0],mapString[1]);
                }
            }
        }
        for(Object key:map.keySet()){
            String values = map.get(key).toString();
            String[] urls = values.split(";");
            for (int i = 0; i < urls.length; i++) {
                if(url.startsWith(urls[i])){
                    return key.toString();
                }
            }
        }
        return null;
    }


    /**
     * 用户是否可以访问该权限
     * @param userId
     * @param module
     * @return
     */
    public Boolean judgeAccess(String userId, String module) {

//        List<String> modules = new ArrayList<String>() {{
//            add(module);
//        }};
        String url = serviceHost + "/external/judgeAuthority";
        Map<String, String> params = new HashMap<>();
        params.put("client_key", client_key);
        params.put("app_secret", app_secret);
        params.put("userId", userId);
        params.put("accessId", module);
        String sign = getSign(params);
        params.put("client_secret", sign);
        params.remove("app_secret");


        String ret = HttpUtil.get(url, params, null);

        Boolean result = false;
        if (ret != null && !ret.isEmpty()) {
            JSONObject modelsRet = JSON.parseObject(ret);
            if (modelsRet != null && modelsRet.get("data") != null  ) {
                result = (Boolean)modelsRet.get("data");
            }
        }
        return result;
    }
    public static void main(String[] args) {

        //getAccess("666000020");
        //judgeAccess("666000020","129");
    }




    private String getSign(Map<String, String> params) {


        Map<String, String> filtedMap = new TreeMap<>();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            filtedMap.put(entry.getKey(), entry.getValue());
        }

        List<String> array = new LinkedList<>();
        for (Map.Entry<String, String> entry : filtedMap.entrySet()) {
            String pair = entry.getKey() + "=" + entry.getValue();
            array.add(pair.trim());
        }

        String signStr = String.join("&", array);

        String str =  Md5Util.md5(signStr).toLowerCase();

        return str;
    }


}
