open module io.github.joeljeremy.externalizedproperties.samples.springboot {
  requires transitive io.github.joeljeremy.externalizedproperties.core;
  requires static spring.context;
  requires transitive spring.core;
  requires spring.web;
  requires spring.boot;
  requires spring.boot.autoconfigure;

  exports io.github.joeljeremy.externalizedproperties.samples.springboot;
  exports io.github.joeljeremy.externalizedproperties.samples.springboot.configs;
  exports io.github.joeljeremy.externalizedproperties.samples.springboot.controllers;
}
