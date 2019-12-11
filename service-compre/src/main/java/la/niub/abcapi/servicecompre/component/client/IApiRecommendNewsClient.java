package la.niub.abcapi.servicecompre.component.client;

import la.niub.abcapi.servicecompre.config.configuration.FeignObjectConfiguration;
import la.niub.abcapi.servicecompre.model.request.client.ClientBestNewsRequest;
import la.niub.abcapi.servicecompre.model.response.client.ApiRecommendNewsResponse;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 研报solr服务
 */
@CacheConfig(cacheNames = "IApiRecommendNewsClient")
@FeignClient(name = "${feign.client.apiRecommendNews.name}", url = "${feign.client.apiRecommendNews.url}"
        , configuration = FeignObjectConfiguration.class
)
public interface IApiRecommendNewsClient {

//    @Cacheable
    @RequestMapping(value = "", method = RequestMethod.POST)
    ApiRecommendNewsResponse recommendNews(@PathVariable(value = "default") ClientBestNewsRequest request);
}
