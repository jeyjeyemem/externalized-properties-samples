open module io.github.jeyjeyemem.externalizedproperties.samples.springboot {
    requires io.github.jeyjeyemem.externalizedproperties.core;
    requires static spring.context;
    requires spring.web;
    requires spring.boot;
    requires spring.boot.autoconfigure;

    exports io.github.jeyjeyemem.externalizedproperties.samples.springboot;
    exports io.github.jeyjeyemem.externalizedproperties.samples.springboot.configs;
    exports io.github.jeyjeyemem.externalizedproperties.samples.springboot.controllers;
}
