package org.example.domain.ports.outputs.repository;

import org.example.domain.entity.Order;

import java.util.Optional;

public interface OrderRespository {
    Order save(Order order);
    Optional<Order> findByTrackingId(Long orderId);
}
