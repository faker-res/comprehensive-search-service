package la.niub.abcapi.servicecompre.service.impl;

import feign.Feign;
import feign.RequestLine;
import la.niub.abcapi.servicecompre.model.request.ReportRequest;
import la.niub.abcapi.servicecompre.service.IParseResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ParseResultServiceImpl implements IParseResultService {

    @Autowired
    IParseResultService parseResultService;

    @Override
    public Boolean isGroupSearch(ReportRequest request) {
        if (request.getGroup_by() != null && (request.getGroup_by().equals("company") || request.getGroup_by().equals("industry"))){
            return true;
        }
        return false;
    }

    @Override
    public Map<String, String> getFilesFormOssByUrl(Map<String, String> urls) {
        Map<String, String> map = new HashMap<>();
        for (Map.Entry<String, String> entry : urls.entrySet()){
            try {
                String ret = parseResultService.curl(entry.getValue());
                if (ret != null){
                    map.put(entry.getKey(),ret);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return map;
    }

    @Override
    public String curl(String url) {
        ICurlClient client = Feign.builder().target(ICurlClient.class,url);
        return client.get();
    }

    interface ICurlClient {
        @RequestLine("GET")
        String get();
    }
}
