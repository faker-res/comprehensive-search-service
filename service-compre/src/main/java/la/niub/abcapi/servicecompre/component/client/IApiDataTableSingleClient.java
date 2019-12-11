package la.niub.abcapi.servicecompre.component.client;

import la.niub.abcapi.servicecompre.component.client.fallback.ApiDataTableSingleFallbackFactory;
import la.niub.abcapi.servicecompre.config.configuration.FeignDecoderConfiguration;
import la.niub.abcapi.servicecompre.config.configuration.FeignFormDataConfiguration;
import la.niub.abcapi.servicecompre.model.response.client.ClientDataTableResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 数据表solr服务
 */
@FeignClient(name = "${feign.client.dataTableSingle.name}", url = "${feign.client.dataTableSingle.url}"
        , fallbackFactory = ApiDataTableSingleFallbackFactory.class
        , configuration = {FeignFormDataConfiguration.class,FeignDecoderConfiguration.class}
//        , configuration = {FeignObjectFormDataConfiguration.class,FeignDecoderConfiguration.class}
)
public interface IApiDataTableSingleClient {

    @RequestMapping(value = "", method = RequestMethod.POST)
    ClientDataTableResponse advancedSearch(@PathVariable(value = "keyword") String keyword,
                                           @PathVariable(value = "offset") Integer offset,
                                           @PathVariable(value = "limit") Integer limit);

//    @RequestMapping(value = "", method = RequestMethod.POST)
//    ClientDataTableResponse advancedSearch(@PathVariable(value = "default") ClientDataTableRequest request);
}
