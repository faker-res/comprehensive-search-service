package la.niub.abcapi.servicecompre.component.client;

import la.niub.abcapi.servicecompre.component.client.fallback.ServiceReportFallbackFactory;
import la.niub.abcapi.servicecompre.config.configuration.FeignObjectConfiguration;
import la.niub.abcapi.servicecompre.model.request.client.KeywordRequest;
import la.niub.abcapi.servicecompre.model.request.client.ReportRequest;
import la.niub.abcapi.servicecompre.model.response.Response;
import la.niub.abcapi.servicecompre.model.response.client.ServiceChartConfigResponse;
import la.niub.abcapi.servicecompre.model.response.client.ServiceSearchReportResponse;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 研报微服务
 */
@CacheConfig(cacheNames = "IServiceReportClient")
@FeignClient(name = "service-report"
        , fallbackFactory = ServiceReportFallbackFactory.class
        , configuration = FeignObjectConfiguration.class
)
public interface IServiceReportClient {

//    @Cacheable
    @RequestMapping(value = "/", method = RequestMethod.GET)
    Response<ServiceSearchReportResponse> report(@PathVariable(value = "default") ReportRequest request);

    @RequestMapping(value = "/operate-config/chart-config", method = RequestMethod.GET)
    Response<List<ServiceChartConfigResponse>> getChartConfig(@PathVariable(value = "default") KeywordRequest request);
}
