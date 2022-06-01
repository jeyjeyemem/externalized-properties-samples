package io.github.joeljeremy7.externalizedproperties.samples.springboot.controllers;

import io.github.joeljeremy7.externalizedproperties.samples.springboot.ApplicationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/greet")
public class GreetingsController {
    private final ApplicationProperties applicationProperties;
    
    public GreetingsController(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }
    
    @GetMapping
    public String greet() {
        // Resolve greeting.name or fallback to using "Anonymous User" if
        // property does not exist. 
        // Since ExternalizedProperties is configured to retrieve from default resolvers
        // (system properties, environment variables), we can set greeting.names by 
        // passing in -Dgreeting.names={names} as JVM argument when running this application.
        List<String> greetingNames = applicationProperties.greetingNames("Anonymous User");
        return "Greetings, " + String.join(",", greetingNames) + "!";
    }
}
