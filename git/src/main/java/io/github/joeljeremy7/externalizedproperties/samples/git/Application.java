package io.github.joeljeremy7.externalizedproperties.samples.git;

import io.github.joeljeremy7.externalizedproperties.core.ExternalizedProperties;
import io.github.joeljeremy7.externalizedproperties.resolvers.git.GitRepository;
import io.github.joeljeremy7.externalizedproperties.resolvers.git.GitResolver;

import java.nio.file.Paths;

public class Application {
    public static void main(String[] args) {
        System.out.println("Clone Dir: " + System.getProperty("cloneDir"));

        ExternalizedProperties externalizedProperties = buildExternalizedProperties();

        ApplicationProperties applicationProperties = 
            externalizedProperties.initialize(ApplicationProperties.class);

        String property = applicationProperties.property();
        System.out.println("property: " + property);
    }

    private static ExternalizedProperties buildExternalizedProperties() {
        return ExternalizedProperties.builder()
            .defaults()
            .resolvers(gitResolver())
            .build();
    }

    private static GitResolver gitResolver() {
        return GitResolver.builder()
            .gitRepository(gitRepository())
            .resourceFilePath("git/src/main/resources/app.properties")
            .build();
    }

    private static GitRepository gitRepository() {
        return GitRepository.builder()
            .uri("https://github.com/joeljeremy7/externalized-properties-samples.git")
            .branch("main")
            .cloneDirectory(Paths.get(System.getProperty("cloneDir")))
            .build();
    }
}
