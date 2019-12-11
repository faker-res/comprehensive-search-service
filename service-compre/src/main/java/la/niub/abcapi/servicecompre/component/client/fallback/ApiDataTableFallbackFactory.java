package la.niub.abcapi.servicecompre.component.client.fallback;

import feign.hystrix.FallbackFactory;
import la.niub.abcapi.servicecompre.component.client.IApiDataTableClient;
import la.niub.abcapi.servicecompre.model.request.client.ClientDataTableRequest;
import la.niub.abcapi.servicecompre.model.response.client.ClientDataTableResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class ApiDataTableFallbackFactory implements IApiDataTableClient,FallbackFactory<IApiDataTableClient> {

    static Logger logger = LogManager.getLogger(ApiDataTableFallbackFactory.class);

    private Throwable throwable;

    private void handleError(Object request){
        logger.error(this.getClass().getSimpleName()+" for  request "+ request);
        logger.error(throwable.getMessage(),throwable);
    }

    @Override
    public IApiDataTableClient create(Throwable throwable) {
        this.throwable = throwable;
        return this;
    }

    @Override
    public ClientDataTableResponse advancedSearch(ClientDataTableRequest request) {
        handleError(request);
        return null;
    }
}