package la.niub.abcapi.servicecompre.component.interceptor;

import com.alibaba.fastjson.JSON;
import la.niub.abcapi.servicecompre.model.response.Response;
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
public class RbacUserFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        String userId = httpServletRequest.getParameter("userId");
        if (userId != null && (
                userId.equals("80114769499366811") || userId.equals("80114769628845712") || userId.equals("80114769691558613")
                || userId.equals("80114770507926316") || userId.equals("80114770522206917") || userId.equals("80114770524807218")
        )){
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
