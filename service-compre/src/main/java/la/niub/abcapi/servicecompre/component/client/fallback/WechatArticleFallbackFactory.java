//package la.niub.abcapi.servicecompre.component.client.fallback;
//
//import feign.hystrix.FallbackFactory;
//import la.niub.abcapi.servicecompre.component.client.IWechatArticleClient;
//import la.niub.abcapi.servicecompre.model.request.client.WechatArticleRequest;
//import la.niub.abcapi.servicecompre.model.response.client.WechatArticleResponse;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//public class WechatArticleFallbackFactory implements IWechatArticleClient,FallbackFactory<IWechatArticleClient> {
//    static Logger logger = LogManager.getLogger(WechatArticleFallbackFactory.class);
//
//    private Throwable throwable;
//
//    private void handleError(Object request){
//        logger.error(this.getClass().getSimpleName()+" for  request "+ request);
//        logger.error(throwable.getMessage(),throwable);
//    }
//
//    @Override
//    public IWechatArticleClient create(Throwable throwable) {
//        this.throwable = throwable;
//        return this;
//    }
//    @Override
//    public WechatArticleResponse get(WechatArticleRequest request) {
//        handleError(request);
//        return null;
//    }
//}
//
