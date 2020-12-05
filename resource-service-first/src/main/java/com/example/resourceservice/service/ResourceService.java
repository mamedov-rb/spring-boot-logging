package com.example.resourceservice.service;

import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class ResourceService {

    @Async
    @NewSpan
    public void someWork() throws InterruptedException {
        Thread.currentThread().sleep(1000);
    }
}
