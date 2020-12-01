package com.example.resourceservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;

@Log4j2
@RestController
@RequestMapping("/resource")
@RequiredArgsConstructor
public class ResourceController {

    private final Environment environment;

    @GetMapping
    public String info() {
        final String host = InetAddress.getLoopbackAddress().getHostAddress();
        final String port = environment.getProperty("server.port");
        final String format = String.format("host '%s', port '%s'", host, port);
        log.info(format);
        return format;
    }
}
