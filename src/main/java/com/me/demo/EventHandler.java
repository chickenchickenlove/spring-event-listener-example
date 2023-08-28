package com.me.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EventHandler {

    @EventListener
    @Async
    public void eventListener(LogEvent logEvent) {
        log.info("logEvent = {}, ThreadName = {}", logEvent, Thread.currentThread().getName());
    }
}