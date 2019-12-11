package la.niub.abcapi.servicecompre.component.client.fallback;

import feign.hystrix.FallbackFactory;
import la.niub.abcapi.servicecompre.component.client.IApiParsingClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class ApiParsingFallbackFactory {

    static Logger logger = LogManager.getLogger(ApiParsingFallbackFactory.class);

    private Throwable throwable;

    private void handleError(Object request){
        logger.error(this.getClass().getSimpleName()+" for  request "+ request);
        logger.error(throwable.getMessage(),throwable);
    }


}
