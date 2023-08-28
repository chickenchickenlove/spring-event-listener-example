package com.me.demo;

import io.micrometer.core.annotation.Counted;

import java.util.concurrent.ThreadPoolExecutor;

public class LogDiscardPolicy extends ThreadPoolExecutor.DiscardPolicy {

    @Counted("dropped-request")
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
        super.rejectedExecution(r, e);
    }
}
