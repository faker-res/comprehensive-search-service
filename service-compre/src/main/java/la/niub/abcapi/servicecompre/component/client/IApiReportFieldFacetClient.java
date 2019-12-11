package la.niub.abcapi.servicecompre.component.client;

import la.niub.abcapi.servicecompre.config.configuration.FeignObjectConfiguration;
import la.niub.abcapi.servicecompre.model.request.client.ClientReportFieldFacetRequest;
import la.niub.abcapi.servicecompre.model.response.client.ApiReportFieldFacetResponse;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 研报solr服务：研究覆盖股票数量统计和最新时间
 */
@CacheConfig(cacheNames = "IApiReportFieldFacetClient")
@FeignClient(name = "${feign.client.apiReportFieldFacet.name}", url = "${feign.client.apiReportFieldFacet.url}"
        , configuration = FeignObjectConfiguration.class
)
public interface IApiReportFieldFacetClient {
    //@Cacheable
    @RequestMapping(value = "", method = RequestMethod.GET)
    ApiReportFieldFacetResponse reportFieldFacet(@PathVariable(value = "default") ClientReportFieldFacetRequest request);
}

