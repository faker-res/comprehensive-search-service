package la.niub.abcapi.servicecompre.component.client.fallback;

import feign.hystrix.FallbackFactory;
import la.niub.abcapi.servicecompre.component.client.IApiNewsClient;
import la.niub.abcapi.servicecompre.model.request.client.ClientNewsRequest;
import la.niub.abcapi.servicecompre.model.response.client.ApiNewsResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class ApiNewsFallbackFactory implements IApiNewsClient,FallbackFactory<IApiNewsClient> {

    static Logger logger = LogManager.getLogger(ApiNewsFallbackFactory.class);

    private Throwable throwable;

    private void handleError(Object request){
        logger.error(this.getClass().getSimpleName()+" for  request "+ request);
        logger.error(throwable.getMessage(),throwable);
    }

    @Override
    public IApiNewsClient create(Throwable throwable) {
        this.throwable = throwable;
        return this;
    }

    @Override
    public ApiNewsResponse news(ClientNewsRequest request) {
        handleError(request);
        return null;
    }
}