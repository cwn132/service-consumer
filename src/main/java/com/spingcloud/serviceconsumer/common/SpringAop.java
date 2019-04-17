package com.spingcloud.serviceconsumer.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.MDC;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.UUID;
import java.util.logging.Logger;

@Aspect
@Configuration
public class SpringAop {

    Logger logger = Logger.getLogger(String.valueOf(SpringAop.class));

    /**
     * 定义切点Pointcut
     * 第一个*号：表示返回类型， *号表示所有的类型
     * 第二个*号：表示类名，*号表示所有的类
     * 第三个*号：表示方法名，*号表示所有的方法
     * 后面括弧里面表示方法的参数，两个句点表示任何参数
     */
        //被注解CustomAopAnnotation表示的方法
        //@Pointcut("@annotation(com.only.mate.springboot.annotation.CustomAopAnnotation")
//        @Pointcut("execution(public * com.spingcloud.serviceconsumer.controller.*.*(..))")
        @Pointcut("execution(* com.spingcloud.serviceconsumer.controller..*.*(..))")
        public void executionService(){

        }

    /**
     * 方法调用之前调用
     * @param joinPoint
     */
    @Before(value = "executionService()")
    public void doBefore(JoinPoint joinPoint){


//添加日志打印
        String requestId = String.valueOf(UUID.randomUUID());
        MDC.put("requestId",requestId);

        logger.info("【注解：Before】------------------切面  before");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        logger.info("【注解：Before】浏览器输入的网址=URL : " + request.getRequestURL().toString());
        logger.info("【注解：Before】HTTP_METHOD : " + request.getMethod());
        logger.info("【注解：Before】IP : " + request.getRemoteAddr());
        logger.info("【注解：Before】执行的业务方法名=CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("【注解：Before】业务方法获得的参数=ARGS : " + Arrays.toString(joinPoint.getArgs()));


    }

    /**
     * 方法之后调用
     * @param joinPoint
     * @param returnValue 方法返回值
     */
    @AfterReturning(pointcut = "executionService()",returning="returnValue")
    public void  doAfterReturning(JoinPoint joinPoint,Object returnValue){

        // 处理完请求，返回内容
        logger.info("【注解：AfterReturning】这个会在切面最后的最后打印，方法的返回值 : " + returnValue);
    }

    /**
     * @Description: 后置异常通知
     * @Title: afterThrowing
     * @param jp
     */
    @AfterThrowing("executionService()")
    public void afterThrowing(JoinPoint jp){
        logger.info("【注解：AfterThrowing】方法异常时执行.....");
    }


//        /**
//         * @Description: 后置最终通知,final增强，不管是抛出异常或者正常退出都会执行
//         * @Title: after
//         * @param jp
//         */
//        @After("executionService()")
//        public void after(JoinPoint jp){
//            logger.info("【注解：After】方法最后执行.....");
//        }
//
//        /**
//         * @Description: 环绕通知,环绕增强，相当于MethodInterceptor
//         * @Title: around
//         * @param pjp
//         * @return
//         */
//        @Around("executionService()")
//        public Object around(ProceedingJoinPoint pjp) {
//            logger.info("【注解：Around . 环绕前】方法环绕start.....");
//            try {
//                //如果不执行这句，会不执行切面的Before方法及controller的业务方法
//                Object o =  pjp.proceed();
//                logger.info("【注解：Around. 环绕后】方法环绕proceed，结果是 :" + o);
//                return o;
//            } catch (Throwable e) {
//                e.printStackTrace();
//                return null;
//            }
//        }



}
