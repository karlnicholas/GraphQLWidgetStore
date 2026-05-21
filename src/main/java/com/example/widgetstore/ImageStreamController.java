package com.example.widgetstore;

import io.r2dbc.spi.Blob;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import java.nio.ByteBuffer;

@RestController
public class ImageStreamController {

  private final DatabaseClient databaseClient;

  public ImageStreamController(DatabaseClient databaseClient) {
    this.databaseClient = databaseClient;
  }

  @GetMapping(value = "/api/images/{id}", produces = "image/jpeg")
  public Flux<ByteBuffer> streamImage(@PathVariable Integer id) {
    return databaseClient.sql("SELECT image_data FROM widget_image WHERE widget_id = :id")
        .bind("id", id)
        .map((row, metadata) -> row.get("image_data", Blob.class))
        // Use .all() to pass the Blob immediately without waiting or canceling
        .all()
        .flatMap(Blob::stream);
  }
}

