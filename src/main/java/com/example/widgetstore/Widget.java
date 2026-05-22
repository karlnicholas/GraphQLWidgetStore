package com.example.widgetstore;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import java.math.BigDecimal;

@Table("widget")
public class Widget {

  @Id
  private Integer id;
  private String name;
  private BigDecimal price;

  // Standard Getters
  public Integer getId() { return id; }
  public String getName() { return name; }
  public BigDecimal getPrice() { return price; }

  // Standard Setters
  public void setId(Integer id) { this.id = id; }
  public void setName(String name) { this.name = name; }
  public void setPrice(BigDecimal price) { this.price = price; }

  // GraphQL will automatically call this to resolve the "imageUrl" field
  public String getImageUrl() {
    return "/api/images/" + this.id;
  }
}