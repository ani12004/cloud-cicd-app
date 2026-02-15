package com.anil.cloud;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CloudController {

    @GetMapping("/")
    public String home() {
        return "Anil's CI/CD Cloud App is Live 🚀";
    }
}
