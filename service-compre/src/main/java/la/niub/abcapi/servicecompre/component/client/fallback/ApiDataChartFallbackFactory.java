package la.niub.abcapi.servicecompre.component.client.fallback;

import feign.hystrix.FallbackFactory;
import la.niub.abcapi.servicecompre.component.client.IApiDataChartClient;
import la.niub.abcapi.servicecompre.model.request.client.ClientDataChartRequest;
import la.niub.abcapi.servicecompre.model.response.client.ClientDataChartResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class ApiDataChartFallbackFactory implements IApiDataChartClient,FallbackFactory<IApiDataChartClient> {

    static Logger logger = LogManager.getLogger(ApiDataChartFallbackFactory.class);

    private Throwable throwable;

    private void handleError(Object request){
        logger.error(this.getClass().getSimpleName()+" for  request "+ request);
        logger.error(throwable.getMessage(),throwable);
    }

    @Override
    public IApiDataChartClient create(Throwable throwable) {
        this.throwable = throwable;
        return this;
    }

    @Override
    public ClientDataChartResponse advancedSearch(ClientDataChartRequest request) {
        handleError(request);
        return null;
    }
}