package la.niub.abcapi.servicecompre.component.client;

import la.niub.abcapi.servicecompre.component.client.fallback.ApiDataChartFallbackFactory;
import la.niub.abcapi.servicecompre.config.configuration.FeignDecoderConfiguration;
import la.niub.abcapi.servicecompre.config.configuration.FeignObjectConfiguration;
import la.niub.abcapi.servicecompre.model.request.client.ClientDataChartRequest;
import la.niub.abcapi.servicecompre.model.response.client.ClientDataChartResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 数据图solr服务
 */
@FeignClient(name = "${feign.client.dataChart.name}", url = "${feign.client.dataChart.url}"
        , fallbackFactory = ApiDataChartFallbackFactory.class
        , configuration = {FeignDecoderConfiguration.class,FeignObjectConfiguration.class}
)
public interface IApiDataChartClient {

    @RequestMapping(value = "", method = RequestMethod.POST)
    ClientDataChartResponse advancedSearch(@PathVariable(value = "default") ClientDataChartRequest request);
}
