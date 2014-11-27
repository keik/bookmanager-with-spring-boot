package info.keik.bookmanager.monitor;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceMonitor {

    private static final Logger logger = LoggerFactory
            .getLogger(ServiceMonitor.class);

    @AfterReturning(value = "execution(* info.keik.boookmanager.service.*.*(..))")
    public void logEntities(JoinPoint joinPoint) {
        logger.info(String.format("#%s ( %s )", joinPoint.getSignature()
                .getName(), ToStringBuilder.reflectionToString(joinPoint
                .getArgs())));
    }
}
