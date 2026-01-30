package com.example.Blogging_platform2.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {
    
    // Logger for writing log messages
    // LoggerFactory creates loggers.
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* com.example.Blogging_platform2.service.*.*(..))")
    public void logBeforeMethod(JoinPoint joinPoint) {
        // Get the method name
        String methodName = joinPoint.getSignature().getName();
        
        // Get the class name
        String className = joinPoint.getTarget().getClass().getSimpleName();
        
        // Get the method arguments
        Object[] args = joinPoint.getArgs();
        
        // Log the information
        logger.info("  Calling {}.{} with arguments: {}",
                    className, methodName, Arrays.toString(args));
    }
    

    @AfterReturning(
        pointcut = "execution(* com.example.Blogging_platform2.service.*.*(..))",
        returning = "result"
    )
    public void logAfterMethod(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        
        // Log the successful completion
        logger.info(" {}.{} completed successfully. Returned: {}",
                    className, methodName, result);
    }
    

    @AfterThrowing(
        pointcut = "execution(* com.example.Blogging_platform2.service.*.*(..))",
        throwing = "exception"
    )
    public void logAfterException(JoinPoint joinPoint, Exception exception) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        
        // Log the error
        logger.error(" {}.{} threw an exception: {}",
                     className, methodName, exception.getMessage());
    }
}
