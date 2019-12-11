package la.niub.abcapi.servicecompre.component.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class ApplicationConfigurer extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new UrlVerifyInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}
