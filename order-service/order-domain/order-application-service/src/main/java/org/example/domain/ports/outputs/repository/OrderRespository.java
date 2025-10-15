package org.example.domain.ports.outputs.repository;

import java.util.Optional;

import org.example.domain.entity.Order;
import org.example.domain.valueObject.TrackingId;

public interface OrderRespository {
    Order save(Order order);
    Optional<Order> findByTrackingId(TrackingId orderId);
}
