package com.flab.bbt.infra.controller;

import com.flab.bbt.infra.util.MutableHealthIndicator;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.health.Health;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class InfraController {

    private final Environment env;
    private final MutableHealthIndicator healthIndicator;

    // IDLE_PROFILE 헬스 체크
    @GetMapping("/health")
    public String health() {
        return "UP";
    }

    @PutMapping(path = "/health/up")
    public void up() {
        final Health up = Health.up().build();
        healthIndicator.setHealth(up);
    }

    @PutMapping(path = "/health/down")
    public void down() {
        final Health down = Health.down().build();
        healthIndicator.setHealth(down);
    }

    @GetMapping("/profile")
    public String getProfile() {
        return Arrays.stream(env.getActiveProfiles())
            .findFirst()
            .orElse("");
    }

}
