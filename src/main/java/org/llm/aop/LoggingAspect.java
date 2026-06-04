package org.llm.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Aspect
@Component
public class LoggingAspect {

    @Around("@annotation(org.springframework.ai.tool.annotation.Tool) || within(org.llm.component..*) || within(org.llm.service..*)")
    public Object logAround(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature sig = (MethodSignature) pjp.getSignature();
        String methodName = sig.getDeclaringType().getSimpleName() + "." + sig.getName();
        Logger log = LoggerFactory.getLogger(sig.getDeclaringType());

        Instant start = Instant.now();
        log.info("ENTER {} at {}", methodName, start);

        try {
            Object result = pjp.proceed();

            Instant end = Instant.now();
            long durationMs = Duration.between(start, end).toMillis();

            // Avoid logging huge result bodies
            String resultSummary = result == null ? "null" : result.toString();
            if (resultSummary.length() > 1000) {
                resultSummary = resultSummary.substring(0, 1000) + "...[truncated]";
            }

            log.info("EXIT  {} at {} (duration={} ms) result={}", methodName, end, durationMs, resultSummary);
            return result;
        } catch (Throwable t) {
            Instant end = Instant.now();
            long durationMs = Duration.between(start, end).toMillis();
            log.error("EXCEPTION {} at {} (duration={} ms): {}", methodName, end, durationMs, t.toString());
            throw t;
        }
    }
}

