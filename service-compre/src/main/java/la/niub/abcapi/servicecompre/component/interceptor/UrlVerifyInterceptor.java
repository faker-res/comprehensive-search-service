package la.niub.abcapi.servicecompre.component.interceptor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * url参数校验
 */
public class UrlVerifyInterceptor implements HandlerInterceptor {

    static Logger logger = LogManager.getLogger(UrlVerifyInterceptor.class);

    // 这些url不会进行client-security校验。 例如 "/notify"
    private List<String> excludeUrls = new ArrayList<>();

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
//        logger.info("---------------------开始进入请求地址拦截----------------------------");
        logger.debug("enter preHandle with url={}", httpServletRequest.getRequestURI());

//        String userId = httpServletRequest.getParameter("userId");
//        if (userId != null && (userId.equals("80114769499366811") || userId.equals("80114769628845712") || userId.equals("80114769691558613"))){
//            throw new ValidatorException(4444,"user block");
//        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
//        logger.info("--------------处理请求完成后视图渲染之前的处理操作---------------");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
//        logger.info("---------------视图渲染之后的操作-------------------------0");
    }
}
