package la.niub.abcapi.servicecompre.component.client;

import la.niub.abcapi.servicecompre.config.configuration.FeignObjectConfiguration;
import la.niub.abcapi.servicecompre.model.request.client.ClientNoticeSearch2Request;
import la.niub.abcapi.servicecompre.model.response.client.ApiNoticeSearch2Response;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 公告solr服务：搜索结果页
 */
@CacheConfig(cacheNames = "IApiNoticeSearch2Client")
@FeignClient(name = "${feign.client.noticeSearch2.name}", url = "${feign.client.noticeSearch2.url}"
        , configuration = FeignObjectConfiguration.class
)
public interface IApiNoticeSearch2Client {
    //@Cacheable
    @RequestMapping(value = "", method = RequestMethod.POST)
    ApiNoticeSearch2Response searchResult(@PathVariable(value = "default") ClientNoticeSearch2Request request);
}

