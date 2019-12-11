package la.niub.abcapi.servicecompre.component.client.fallback;

import feign.hystrix.FallbackFactory;
import la.niub.abcapi.servicecompre.component.client.IApiDataTableSingleClient;
import la.niub.abcapi.servicecompre.model.request.client.ClientDataTableRequest;
import la.niub.abcapi.servicecompre.model.response.client.ClientDataTableResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class ApiDataTableSingleFallbackFactory implements IApiDataTableSingleClient,FallbackFactory<IApiDataTableSingleClient> {

    static Logger logger = LogManager.getLogger(ApiDataTableSingleFallbackFactory.class);

    private Throwable throwable;

    private void handleError(Object request){
        logger.error(this.getClass().getSimpleName()+" for  request "+ request);
        logger.error(throwable.getMessage(),throwable);
    }

    @Override
    public IApiDataTableSingleClient create(Throwable throwable) {
        this.throwable = throwable;
        return this;
    }

    @Override
    public ClientDataTableResponse advancedSearch(String keyword, Integer offset, Integer limit) {
        ClientDataTableRequest request = new ClientDataTableRequest();
        request.setKeyword(keyword);
        request.setOffset(offset);
        request.setLimit(limit);
        handleError(request);
        return null;
    }

//    @Override
//    public ClientDataTableResponse advancedSearch(ClientDataTableRequest request) {
//        handleError(request);
//        return null;
//    }
}