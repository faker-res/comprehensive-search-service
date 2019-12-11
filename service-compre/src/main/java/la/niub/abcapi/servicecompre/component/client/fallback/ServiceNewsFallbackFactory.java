package la.niub.abcapi.servicecompre.component.client.fallback;

import feign.hystrix.FallbackFactory;
import la.niub.abcapi.servicecompre.component.client.IServiceNewsClient;
import la.niub.abcapi.servicecompre.model.request.client.ClientNewsRequest;
import la.niub.abcapi.servicecompre.model.response.ApiNewsDataItemResponse;
import la.niub.abcapi.servicecompre.model.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ServiceNewsFallbackFactory implements IServiceNewsClient,FallbackFactory<IServiceNewsClient> {

    static Logger logger = LogManager.getLogger(ServiceNewsFallbackFactory.class);

    private Throwable throwable;

    private void handleError(Object request){
        logger.error(this.getClass().getSimpleName()+" for  request "+ request);
        logger.error(throwable.getMessage(),throwable);
    }

    @Override
    public IServiceNewsClient create(Throwable throwable) {
        this.throwable = throwable;
        return this;
    }

    @Override
    public Response<List<ApiNewsDataItemResponse>> search(ClientNewsRequest request) {
        handleError(request);
        return null;
    }
}