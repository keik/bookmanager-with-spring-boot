package info.keik.bookmanager.monitor;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceMonitor {

    private static final Logger logger = LoggerFactory
            .getLogger(ServiceMonitor.class);

    @Before(value = "execution(* info.keik.bookmanager.service.*.*(..))")
    public void logEntities(JoinPoint joinPoint) {
        logger.info(String.format("%s#%s ( %s )", joinPoint.getSignature()
                .getDeclaringTypeName(), joinPoint.getSignature().getName(),
                ToStringBuilder.reflectionToString(joinPoint.getArgs())));
    }
}
