//package la.niub.abcapi.servicecompre.controller;
//
//import la.niub.abcapi.servicecompre.component.exception.ServiceException;
//import la.niub.abcapi.servicecompre.model.response.ErrorResponse;
//import la.niub.abcapi.servicecompre.model.response.Response;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpServletRequest;
//
///**
// * 错误捕获控制器
// * @author xwu.abcft
// */
//@ControllerAdvice
//@RestController
//public class ExceptionController {
//
//    static Logger logger = LogManager.getLogger(ExceptionController.class);
//
//    @ExceptionHandler(value = Exception.class)
//    public Response defaultExceptionHandler(HttpServletRequest request, Exception e){
//        logger.error(request.getRequestURL() + ": " + e.getMessage(),e);
//        e.printStackTrace();
//        return new ErrorResponse(e.getMessage());
//    }
//
//    @ExceptionHandler(value = ServiceException.class)
//    public Response validatorExceptionHandler(ServiceException e){
//        logger.error(e);
//        e.printStackTrace();
//        return new ErrorResponse(e.getCode(), e.getMessage());
//    }
//
//}
