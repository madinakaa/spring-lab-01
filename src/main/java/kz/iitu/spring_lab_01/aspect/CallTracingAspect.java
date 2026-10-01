package kz.iitu.spring_lab_01.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(4)
public class CallTracingAspect {

    private static final Logger log =
            LoggerFactory.getLogger(CallTracingAspect.class);

    private final ThreadLocal<Integer> depth =
            ThreadLocal.withInitial(() -> 0);

    @Before("kz.iitu.spring_lab_01.aspect.Pointcuts.serviceOperation()")
    public void before(JoinPoint jp) {

        int currentDepth = depth.get();

        String indent = "  ".repeat(currentDepth);

        log.info("[TRACE] {}-> {}",
                indent,
                jp.getSignature().toShortString());

        depth.set(currentDepth + 1);
    }

    @After("kz.iitu.spring_lab_01.aspect.Pointcuts.serviceOperation()")
    public void after(JoinPoint jp) {

        int currentDepth = Math.max(0, depth.get() - 1);

        depth.set(currentDepth);

        String indent = "  ".repeat(currentDepth);

        log.info("[TRACE] {}<- {}",
                indent,
                jp.getSignature().toShortString());

        if (currentDepth == 0) {
            depth.remove();
        }
    }
}