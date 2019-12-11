package la.niub.abcapi.servicecompre;

import la.niub.abcapi.servicecompre.model.response.Response;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableHystrix
@EnableCircuitBreaker
//@EnableCaching
@Controller
public class ServiceCompreApplication extends SpringBootServletInitializer {

	@PostConstruct
	void postConstruct() {
		TimeZone.setDefault(TimeZone.getTimeZone("PRC"));
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	@ResponseBody
    Response index() {
		return new Response("Comprehensive Search service");
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ServiceCompreApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(ServiceCompreApplication.class, args);
	}
}
