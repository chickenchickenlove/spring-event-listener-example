package com.me.demo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class MyController {
    private final ApplicationEventPublisher eventPublisher;

    @GetMapping("/event")
    public String callEvent() {
        LogEvent logEvent = new LogEvent();
        eventPublisher.publishEvent(logEvent);
        return "ok";
    }
}