package com.flab.bbt.infra.controller;

import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class infraController {

    private final Environment env;

    @GetMapping("/health")
    public String health() {
        return "healthy";
    }

    @GetMapping("/profile")
    public String getProfile() {
        return Arrays.stream(env.getActiveProfiles())
            .findFirst()
            .orElse("");
    }

}
