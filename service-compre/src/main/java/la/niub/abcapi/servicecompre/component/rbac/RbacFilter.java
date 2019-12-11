package la.niub.abcapi.servicecompre.component.rbac;

import com.alibaba.fastjson.JSON;
import la.niub.abcapi.servicecompre.component.util.StringUtil;
import la.niub.abcapi.servicecompre.model.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@WebFilter(urlPatterns = "/**")
public class RbacFilter implements Filter {

    @Autowired
    private AuthSdk authSdk;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        String userId = httpServletRequest.getParameter("userId");
        String uri = httpServletRequest.getRequestURI();
        String moduleId = authSdk.getModuleId(uri);
//        if (moduleId!=null && !"".equals(moduleId) && !authSdk.judgeAccess(userId,moduleId)){
        if (!uri.equals("/access/getAccess") && !StringUtil.isEmpty(moduleId) && !authSdk.judgeAccess(userId,moduleId)){
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            servletOutputStream.write(JSON.toJSONString(new Response<>("",4444,"user block")).getBytes());
            return;
        }

        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
