//package la.niub.abcapi.servicecompre.component.client;
//
//import la.niub.abcapi.servicecompre.component.client.fallback.WechatArticleFallbackFactory;
//import la.niub.abcapi.servicecompre.config.configuration.FeignObjectConfiguration;
//import la.niub.abcapi.servicecompre.model.request.client.WechatArticleRequest;
//import la.niub.abcapi.servicecompre.model.response.client.WechatArticleResponse;
//import org.springframework.cloud.netflix.feign.FeignClient;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//@FeignClient(name = "${feign.client.wechatArticle.name}", url = "${feign.client.wechatArticle.url}"
//        , fallbackFactory = WechatArticleFallbackFactory.class
//        , configuration = FeignObjectConfiguration.class
//)
//public interface IWechatArticleClient {
//
//    @RequestMapping(value = "", method = RequestMethod.GET)
//    WechatArticleResponse get(@PathVariable(value = "default") WechatArticleRequest request);
//}
