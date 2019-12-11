package la.niub.abcapi.servicecompre.component.client;

import la.niub.abcapi.servicecompre.component.client.fallback.ApiNewsFallbackFactory;
import la.niub.abcapi.servicecompre.config.configuration.FeignObjectConfiguration;
import la.niub.abcapi.servicecompre.model.request.client.ClientNewsRequest;
import la.niub.abcapi.servicecompre.model.response.client.ApiNewsResponse;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//@CacheConfig(cacheNames = "IApiNewsClient")
@FeignClient(name = "${feign.client.apiNews.name}", url = "${feign.client.apiNews.url}"
//        , fallbackFactory = ApiNewsFallbackFactory.class
        , configuration = FeignObjectConfiguration.class
)
/**
 * 资讯
 */
public interface IApiNewsClient {


    @RequestMapping(value = "", method = RequestMethod.POST)
    ApiNewsResponse news(@PathVariable(value = "default") ClientNewsRequest request);
}
