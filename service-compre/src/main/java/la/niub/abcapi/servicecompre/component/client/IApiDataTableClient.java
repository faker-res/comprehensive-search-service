package la.niub.abcapi.servicecompre.component.client;

import la.niub.abcapi.servicecompre.component.client.fallback.ApiDataTableFallbackFactory;
import la.niub.abcapi.servicecompre.config.configuration.FeignDecoderConfiguration;
import la.niub.abcapi.servicecompre.config.configuration.FeignObjectConfiguration;
import la.niub.abcapi.servicecompre.config.configuration.FeignObjectFormDataConfiguration;
import la.niub.abcapi.servicecompre.model.request.client.ClientDataTableRequest;
import la.niub.abcapi.servicecompre.model.response.client.ClientDataTableResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 数据表solr服务
 */
@FeignClient(name = "${feign.client.dataTable.name}", url = "${feign.client.dataTable.url}"
        , fallbackFactory = ApiDataTableFallbackFactory.class
//        , configuration = {FeignObjectFormDataConfiguration.class,FeignDecoderConfiguration.class}
        , configuration = {FeignDecoderConfiguration.class,FeignObjectConfiguration.class}

)
public interface IApiDataTableClient {

    @RequestMapping(value = "", method = RequestMethod.POST)
    ClientDataTableResponse advancedSearch(@PathVariable(value = "default") ClientDataTableRequest request);
}
