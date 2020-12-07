package com.example.resourceservice.controller;

import com.example.resourceservice.service.ResourceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.InetAddress;

@Log4j2
@RestController
@RequestMapping("/resource")
@RequiredArgsConstructor
public class ResourceController {

    private final static String URL = "http://resource-service-second/resource";

    private final Environment environment;
    private final RestTemplate restTemplate;
    private final ResourceService resourceService;

    @GetMapping
    public String info() throws InterruptedException {
        final String host = InetAddress.getLoopbackAddress().getHostAddress();
        final String port = environment.getProperty("server.port");
        resourceService.someWork();
        final ResponseEntity<String> exchange =
                restTemplate.exchange(URL, HttpMethod.GET, null, String.class);
        final String format = String.format(
                "Service-1 info: host '%s', port '%s'\nService-2 info: %s",
                host,
                port,
                exchange.getBody()
        );
        log.info(format);
        return format;
    }
}
