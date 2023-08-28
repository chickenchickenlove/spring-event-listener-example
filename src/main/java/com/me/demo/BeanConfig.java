package com.me.demo;

import io.micrometer.core.aop.CountedAspect;
import io.micrometer.core.aop.MeterTag;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.MeterBinder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class BeanConfig {
    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(50);
        threadPoolTaskExecutor.setMaxPoolSize(1000);
        threadPoolTaskExecutor.setQueueCapacity(1000);
        threadPoolTaskExecutor.setPrestartAllCoreThreads(true);

        // Policy 설정
        threadPoolTaskExecutor.setRejectedExecutionHandler(new LogDiscardPolicy());
        return threadPoolTaskExecutor;
    }

    @Bean
    public MeterBinder queSize() {
        final ThreadPoolTaskExecutor taskExecutor = (ThreadPoolTaskExecutor) taskExecutor();
        return registry -> Gauge.builder(
                "async-que-size",
                taskExecutor,
                ThreadPoolTaskExecutor::getQueueSize).register(registry);
    }

    @Bean
    public CountedAspect countedAspect(MeterRegistry meterRegistry) {
        return new CountedAspect(meterRegistry);
    }
}
