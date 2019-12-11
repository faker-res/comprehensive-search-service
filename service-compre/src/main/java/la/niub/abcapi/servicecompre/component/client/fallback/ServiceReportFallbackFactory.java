package la.niub.abcapi.servicecompre.component.client.fallback;

import feign.hystrix.FallbackFactory;
import la.niub.abcapi.servicecompre.component.client.IServiceReportClient;
import la.niub.abcapi.servicecompre.model.request.client.KeywordRequest;
import la.niub.abcapi.servicecompre.model.request.client.ReportRequest;
import la.niub.abcapi.servicecompre.model.response.Response;
import la.niub.abcapi.servicecompre.model.response.client.ServiceChartConfigResponse;
import la.niub.abcapi.servicecompre.model.response.client.ServiceSearchReportResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ServiceReportFallbackFactory implements IServiceReportClient,FallbackFactory<IServiceReportClient> {

    static Logger logger = LogManager.getLogger(ServiceReportFallbackFactory.class);

    private Throwable throwable;

    private void handleError(Object request){
        logger.error(this.getClass().getSimpleName()+" for  request "+ request);
        logger.error(throwable.getMessage(),throwable);
    }

    @Override
    public IServiceReportClient create(Throwable throwable) {
        this.throwable = throwable;
        return this;
    }

    @Override
    public Response<ServiceSearchReportResponse> report(ReportRequest request) {
        handleError(request);
        return null;
    }

    @Override
    public Response<List<ServiceChartConfigResponse>> getChartConfig(KeywordRequest request) {
        handleError(request);
        return null;
    }
}