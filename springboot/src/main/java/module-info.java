open module io.github.joeljeremy7.externalizedproperties.samples.springboot {
    requires transitive io.github.joeljeremy7.externalizedproperties.core;
    
    requires static spring.context;
    requires transitive spring.core;
    requires spring.web;
    requires spring.boot;
    requires spring.boot.autoconfigure;

    exports io.github.joeljeremy7.externalizedproperties.samples.springboot;
    exports io.github.joeljeremy7.externalizedproperties.samples.springboot.configs;
    exports io.github.joeljeremy7.externalizedproperties.samples.springboot.controllers;
}
