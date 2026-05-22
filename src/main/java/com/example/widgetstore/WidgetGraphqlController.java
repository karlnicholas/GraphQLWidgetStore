package com.example.widgetstore;

import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@Controller
public class WidgetGraphqlController {

  private final WidgetRepository widgetRepository;

  public WidgetGraphqlController(WidgetRepository widgetRepository) {
    this.widgetRepository = widgetRepository;
  }

  @QueryMapping
  public Flux widgets() {
    // Executes "SELECT id, name, price FROM widget" automatically
    return widgetRepository.findAll();
  }
}