package kz.iitu.spring_lab_01.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class LifecycleDemo {

    @PostConstruct
    public void init() {
        System.out.println("LifecycleDemo: @PostConstruct");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("LifecycleDemo: @PreDestroy");
    }
}