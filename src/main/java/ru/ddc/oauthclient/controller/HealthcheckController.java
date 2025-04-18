package ru.ddc.oauthclient.controller;

import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthcheckController {

    @GetMapping(value = "/health")
    public ResponseEntity<HealthcheckResponse> healthCheck() {
        HealthcheckResponse response = new HealthcheckResponse(200, "OK");
        return ResponseEntity.ok(response);
    }

    @Getter
    public static class HealthcheckResponse {
        int code;
        String status;

        public HealthcheckResponse(int code, String status) {
            this.code = code;
            this.status = status;
        }
    }
}
