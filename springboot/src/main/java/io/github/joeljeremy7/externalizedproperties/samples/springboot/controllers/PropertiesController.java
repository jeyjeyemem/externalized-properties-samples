package io.github.joeljeremy7.externalizedproperties.samples.springboot.controllers;

import io.github.joeljeremy7.externalizedproperties.samples.springboot.ApplicationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/properties")
public class PropertiesController {
    private final ApplicationProperties applicationProperties;

    public PropertiesController(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    @GetMapping("/javaVersion")
    public String javaVersion(String name) {
        return applicationProperties.javaVersion();
    }

    @GetMapping("/pathEnv")
    public String pathEnv(String name) {
        return applicationProperties.pathEnv();
    }

    @GetMapping("/greetingNames")
    public List<String> greetingNames() {
        return applicationProperties.greetingNames();
    }
}
