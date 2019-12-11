package la.niub.abcapi.servicecompre.service.impl;

import la.niub.abcapi.servicecompre.service.IShareService;
import org.springframework.stereotype.Service;

@Service
public class ShareServiceImpl implements IShareService {

    @Override
    public String buildShareUrl(String type, Integer id) {
        String url = "qqqqqqq";
        switch (type){
            case "chart":
                break;
            case "table":
                break;
            case "img":
                break;
            case "edb":
                break;
            case "news":
                break;
            case "notice":
                break;
            case "report":
                break;
        }
        return url;
    }
}
