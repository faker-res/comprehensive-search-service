package la.niub.abcapi.servicecompre.component.client.fallback;

import feign.hystrix.FallbackFactory;
import la.niub.abcapi.servicecompre.component.client.IApiBestNewsClient;
import la.niub.abcapi.servicecompre.model.request.client.ClientBestNewsRequest;
import la.niub.abcapi.servicecompre.model.response.client.ApiBestNewsResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class ApiBestNewsFallbackFactory implements IApiBestNewsClient,FallbackFactory<IApiBestNewsClient> {

    static Logger logger = LogManager.getLogger(ApiBestNewsFallbackFactory.class);

    private Throwable throwable;

    private void handleError(Object request){
        logger.error(this.getClass().getSimpleName()+" for  request "+ request);
        logger.error(throwable.getMessage(),throwable);
    }

    @Override
    public IApiBestNewsClient create(Throwable throwable) {
        this.throwable = throwable;
        return this;
    }

    @Override
    public ApiBestNewsResponse bestNews(ClientBestNewsRequest request) {
        handleError(request);
        return null;
    }
}