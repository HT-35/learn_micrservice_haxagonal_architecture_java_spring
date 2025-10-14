package org.example.domain.ports.outputs.repository;

import org.example.domain.entity.Order;
import org.example.domain.valueObject.TrackingId;

import java.util.Optional;
import java.util.UUID;

public interface OrderRespository {
    Order save(Order order);
    Optional<Order> findByTrackingId(TrackingId orderId);
}
