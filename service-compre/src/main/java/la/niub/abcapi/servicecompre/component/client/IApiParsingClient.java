package la.niub.abcapi.servicecompre.component.client;


import la.niub.abcapi.servicecompre.component.client.fallback.ApiParsingFallbackFactory;
import la.niub.abcapi.servicecompre.config.configuration.FeignObjectConfiguration;
import la.niub.abcapi.servicecompre.model.response.client.ClientParsingResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(
        name = "${feign.client.parsingSend.name}",
        url = "${feign.client.parsingSend.url}",
        fallbackFactory = ApiParsingFallbackFactory.class,
        configuration = FeignObjectConfiguration.class
)

/**
 * Parsing 客户端
 *
 * @author amen
 */
public interface IApiParsingClient {

    @RequestMapping(value = "", method = RequestMethod.POST)
    ClientParsingResponse advancedSearch(@PathVariable(value = "source") String keyword,
                                         @PathVariable(value = "force") String offset,
                                         @PathVariable(value = "priority") Integer limit,
                                         @PathVariable(value = "items") String items,
                                         @PathVariable(value = "is_fileId") String is_fileId
    );
}
