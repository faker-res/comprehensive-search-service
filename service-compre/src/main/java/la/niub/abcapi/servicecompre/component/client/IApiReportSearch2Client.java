package la.niub.abcapi.servicecompre.component.client;

import la.niub.abcapi.servicecompre.config.configuration.FeignObjectConfiguration;
import la.niub.abcapi.servicecompre.model.request.client.ClientReportSearch2Request;
import la.niub.abcapi.servicecompre.model.response.client.ApiReportSearch2Response;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 研报solr服务：搜索结果页
 */
@CacheConfig(cacheNames = "IApiReportSearch2Client")
@FeignClient(name = "${feign.client.solrReport.name}", url = "${feign.client.solrReport.url}"
        , configuration = FeignObjectConfiguration.class
)
public interface IApiReportSearch2Client {
    //@Cacheable
    @RequestMapping(value = "", method = RequestMethod.POST)
    ApiReportSearch2Response searchResult(@PathVariable(value = "default") ClientReportSearch2Request request);
}

