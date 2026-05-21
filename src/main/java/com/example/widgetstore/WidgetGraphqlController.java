package com.example.widgetstore;

import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@Controller
public class WidgetGraphqlController {

  private final DatabaseClient databaseClient;

  public WidgetGraphqlController(DatabaseClient databaseClient) {
    this.databaseClient = databaseClient;
  }

  @QueryMapping
  public Flux<WidgetDto> widgets() {
    return databaseClient.sql("SELECT id, name, price FROM widget")
        .map((row, metadata) -> new WidgetDto(
            row.get("id", Integer.class),
            row.get("name", String.class),
            // Fetch straight as BigDecimal
            row.get("price", java.math.BigDecimal.class),
            "/api/images/" + row.get("id", Integer.class)
        ))
        .all();
  }
}

