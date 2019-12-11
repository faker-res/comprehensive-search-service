package la.niub.abcapi.servicecompre.component.client;

import la.niub.abcapi.servicecompre.config.configuration.FeignObjectConfiguration;
import la.niub.abcapi.servicecompre.model.request.client.ClientNoticeRequest;
import la.niub.abcapi.servicecompre.model.response.client.ClientNoticeResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 公告solr服务
 */
@FeignClient(name = "${feign.client.solrNotice.name}", url = "${feign.client.solrNotice.url}"
//        , fallbackFactory = ApiNewsFallbackFactory.class
        , configuration = FeignObjectConfiguration.class
)
public interface IServiceNoticeClient {

    @RequestMapping(value = "", method = RequestMethod.POST)
    ClientNoticeResponse report(@PathVariable(value = "default") ClientNoticeRequest request);
}
