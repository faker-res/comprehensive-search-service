package la.niub.abcapi.servicecompre.component.interceptor;

import la.niub.abcapi.servicecompre.component.exception.ServiceException;
import la.niub.abcapi.servicecompre.component.exception.ValidatorException;
import la.niub.abcapi.servicecompre.config.code.SystemEnumCodeConfig;
import la.niub.abcapi.servicecompre.model.response.ErrorResponse;
import la.niub.abcapi.servicecompre.model.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@ControllerAdvice
class ControllerInterceptor implements ErrorController {

    private static final Logger logger = LogManager.getLogger(ControllerInterceptor.class);

    @Value("${spring.profiles.active}")
    private String env;

    @Override
    public String getErrorPath(){
        return "";
    }

    /**
     * 404
     * @return
     */
    @RequestMapping("/error")
    public Response errorRequestUrlExceptionHanlder() {
        return new ErrorResponse(SystemEnumCodeConfig.ERROR_REQUEST_URL.getCode(), SystemEnumCodeConfig.ERROR_REQUEST_URL.getMessage());
    }

    /**
     * 默认500
     * @param request
     * @param e
     *
     * @return
     */
    @ExceptionHandler(Throwable.class)
    public Response defaultExceptionHandler(HttpServletRequest request, Exception e) {
        logger.error(request.getRequestURL() + ": " + e.getMessage(),e);
        e.printStackTrace();

        String message = "";
        if (env.equals("local") || env.equals("dev") || env.equals("test")) {
            message = e.getMessage();
        } else {
            message = SystemEnumCodeConfig.ERROR_SERVER.getMessage();
        }
        return new ErrorResponse(SystemEnumCodeConfig.ERROR_SERVER.getCode(), message);
    }

    /**
     * 错误http method
     * @return
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Response methodNotSupportedExceptionHandler() {
        return new ErrorResponse(SystemEnumCodeConfig.ERROR_METHOD_NOT_SUPPORTED.getCode(), SystemEnumCodeConfig.ERROR_METHOD_NOT_SUPPORTED.getMessage());
    }


    @ExceptionHandler(ValidatorException.class)
    public Response validatorExceptionHandler(ValidatorException e) {
        logger.error(e);
        e.printStackTrace();
        return new ErrorResponse(e.getCode(), e.getMessage());
    }


    @ExceptionHandler(ServiceException.class)
    public Response serviceExceptionHandler(ValidatorException e) {
        logger.error(e);
        e.printStackTrace();
        return new ErrorResponse(e.getCode(), e.getMessage());
    }
}