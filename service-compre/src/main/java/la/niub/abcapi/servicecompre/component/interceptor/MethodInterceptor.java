package la.niub.abcapi.servicecompre.component.interceptor;

import com.alibaba.fastjson.JSON;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 方法运行时间测试
 * @author liuyt
 * @date   2014-11-16 下午3:39:08
 * bolgs   http://www.cnblogs.com/liuyitian/
 *
 * @modified wuxiao 2018-6-10
 */
//@Aspect
//@Component
public class MethodInterceptor {

    private static final Logger logger = LogManager.getLogger(MethodInterceptor.class);

    /**
     * 自定义map，value：[0：方法绝对限定名，1：运行时间]
     */
    private static ThreadLocal<Map<Integer,String[]>> stackTrace = ThreadLocal.withInitial(() -> new TreeMap<>());
    private static ThreadLocal<Integer> stackIndex = ThreadLocal.withInitial(() -> 1);
    private static ThreadLocal<Boolean> stackRecordOpen = ThreadLocal.withInitial(() -> false);//当前线程是否记录堆栈

    private static ThreadLocal<Long> startTimeMillis = new ThreadLocal<>(); // 开始时间
    private static ThreadLocal<String> requestPath = new ThreadLocal<>(); // 请求地址
    private static ThreadLocal<Map<?,?>> inputParamMap = new ThreadLocal<>(); // 传入参数

//    @Pointcut("execution(public * la.niub.abcapi.servicecompre.service.impl.*.*(..))" +
//            "|| execution(public * la.niub.abcapi.servicecompre.dao.*.*.*(..))")
    @Pointcut("execution(public * la.niub..*.*(..))")
    public void pointCut(){}

    @Before("execution(* la.niub.abcapi.servicecompre.controller..*.*(..))")
    public void before(JoinPoint joinPoint) {
        startTimeMillis.set(System.currentTimeMillis()); // 记录方法开始执行的时间

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        // 获取输入参数
        inputParamMap.set(request.getParameterMap());
        // 获取请求地址
        requestPath.set(request.getRequestURI());
        stackRecordOpen.set(true);
    }

    @After("execution(* la.niub.abcapi.servicecompre.controller..*.*(..))")
    public void after(JoinPoint joinPoint) {
        Long takesTimeMillis = System.currentTimeMillis() - startTimeMillis.get(); // 记录方法执行完成的时间

        logger.debug("Request begin============================");
        String optTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startTimeMillis.get());
        logger.debug("Url："+requestPath.get()+"; Start time：" + optTime + " Takes time：" + takesTimeMillis + "ms ;"
                +" Request params："+ JSON.toJSONString(inputParamMap.get()).replace("\\",""));

        // 方法名称
        Method targetMethod = ((MethodSignature)joinPoint.getSignature()).getMethod();
        String className = targetMethod.getDeclaringClass().getName();
        String methodName = targetMethod.getName();
        String controllerMethodPath = className+ "."+methodName;

        Map<Integer,String[]> stackTraceLocal = stackTrace.get();
        stackTraceLocal.put(0,new String[]{controllerMethodPath,String.valueOf(takesTimeMillis)});

        /**
         * 自定义map集合，key：方法名，value：[0：运行次数，1：总时间]
         */
        Map<String,Long[]> runtime = new HashMap<>();
        for (Map.Entry<Integer,String[]> entry : stackTraceLocal.entrySet()){
            String[] item = entry.getValue();
            if (item == null || item.length == 0){
                continue;
            }
            String methodPath = item[0];
            Integer spendTime = item.length == 2 ? Integer.valueOf(item[1]) : 0;

            logger.debug("Executed Method [ {} ] Takes [ {} ] Milliseconds",methodPath,spendTime);

            if (!runtime.containsKey(methodPath)){
                runtime.put(methodPath,new Long[]{1L,spendTime.longValue()});
            }else{
                Long[] runtimeRecord = runtime.get(methodPath);
                runtimeRecord[0]++;
                runtimeRecord[1] += spendTime;
            }
        }

        logger.debug("-----------------------------------------");

        List<Map.Entry<String,Long[]>> runtimeList = new LinkedList<>(runtime.entrySet());
        //升序排序
        Collections.sort(runtimeList, (o1, o2) -> o2.getValue()[1].compareTo(o1.getValue()[1]));

        for(Map.Entry<String,Long[]> entry:runtimeList.subList(0,Math.min(10,runtimeList.size()))){
            logger.debug("Most Time Spend top10 Method [ {} ] Run {} Times Total Takes [ {} ] Milliseconds",entry.getKey(),entry.getValue()[0],entry.getValue()[1]);
        }

        logger.debug("Request end==============================");
    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) {
        Object result = null;
        try {
            Integer stackIndexLocal = stackIndex.get();
            stackIndex.set(stackIndexLocal+1);

            Long startTimeMillis = System.currentTimeMillis();
            // 执行方法
            try {
                result =  joinPoint.proceed();
            } catch (Throwable t) {
                logger.error("Runtime error: "+t.getMessage(),t);
                t.printStackTrace();
            }
            // 获取时间
            Long takesTimeMillis = System.currentTimeMillis() - startTimeMillis;

            if (stackRecordOpen.get()){
                // 方法名称
                Method targetMethod = ((MethodSignature)joinPoint.getSignature()).getMethod();
                String className = targetMethod.getDeclaringClass().getName();
                String methodName = targetMethod.getName();
                String methodPath = className+ "."+methodName;

                Map<Integer,String[]> stackTraceLocal = stackTrace.get();
                stackTraceLocal.put(stackIndexLocal,new String[]{methodPath,String.valueOf(takesTimeMillis)});
            }

        } catch (Exception e) {
            logger.error("Aspect error: "+e.getMessage(),e);
            e.printStackTrace();
        }
        return result;
    }

}
