package la.niub.abcapi.servicecompre.component.client;

import la.niub.abcapi.servicecompre.model.response.client.KeywordSuggestResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 关键词服务
 */
@FeignClient(name = "service-suggestion")
public interface IKeywordClient {

    @RequestMapping(value = "/recommend/getautocompletelist", method = RequestMethod.GET)
    String suggest(@RequestParam(value = "keyword") String keyword,
                                   @RequestParam("limit") Integer limit,
                                   @RequestParam("type") Integer type);
}
