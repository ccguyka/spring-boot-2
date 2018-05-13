package com.github.ccguyka.springboot2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import reactor.core.publisher.Mono;

@RestController
@Timed
public class EchoController {

    private static final Logger logger = LoggerFactory.getLogger(EchoController.class);

    private final MeterRegistry registry;

    public EchoController(final MeterRegistry registry) {
        this.registry = registry;
    }

    @GetMapping(path = "/echo", produces = MediaType.TEXT_PLAIN_VALUE)
    public Mono<String> getEcho() {
        registry.counter("echooooo").increment();
        logger.info("echooooo1");
        logger.info("echooooo2");
        return Mono.just("echoo");
    }

    @PostMapping(path = "/echo", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public Mono<String> echo(@RequestBody final String echo) {
        return Mono.just(echo);
    }
}
