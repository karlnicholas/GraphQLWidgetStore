package com.example.widgetstore;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

// <DomainClass, IdClass>
public interface WidgetRepository extends ReactiveCrudRepository<Widget, Integer> {
}