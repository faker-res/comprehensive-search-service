package la.niub.abcapi.servicecompre.component.client;

import la.niub.abcapi.servicecompre.component.client.fallback.ServiceNewsFallbackFactory;
import la.niub.abcapi.servicecompre.config.configuration.FeignObjectConfiguration;
import la.niub.abcapi.servicecompre.model.request.client.ClientNewsRequest;
import la.niub.abcapi.servicecompre.model.response.ApiNewsDataItemResponse;
import la.niub.abcapi.servicecompre.model.response.Response;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 资讯
 */
@FeignClient(name = "service-news"
        , fallbackFactory = ServiceNewsFallbackFactory.class
        , configuration = FeignObjectConfiguration.class
)
public interface IServiceNewsClient {

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    Response<List<ApiNewsDataItemResponse>> search(@PathVariable(value = "default") ClientNewsRequest request);
}
