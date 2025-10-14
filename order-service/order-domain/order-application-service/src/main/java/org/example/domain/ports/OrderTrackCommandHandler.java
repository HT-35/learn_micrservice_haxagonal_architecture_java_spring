package org.example.domain.ports;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.dto.track.TrackOrderQuery;
import org.example.domain.dto.track.TrackOrderResponse;
import org.example.domain.entity.Order;
import org.example.domain.exception.OrderNotFoundException;
import org.example.domain.mapper.OrderDataMapper;
import org.example.domain.ports.outputs.repository.OrderRespository;
import org.example.domain.valueObject.TrackingId;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@Slf4j
@AllArgsConstructor
public class OrderTrackCommandHandler {

    private final OrderDataMapper orderDataMapper;
    private final OrderRespository orderRespository;

    @Transactional(readOnly = true)
    public TrackOrderResponse trackOrder(TrackOrderQuery trackOrderQuery) {
        Optional<Order> orderResult =  orderRespository.findByTrackingId(new TrackingId(trackOrderQuery.getOrderTrackingId()));
        if(orderResult.isEmpty()){
            log.warn("Order with tracking id {} not found", trackOrderQuery.getOrderTrackingId());
            throw new OrderNotFoundException("Order with tracking id " + trackOrderQuery.getOrderTrackingId() + " not found");
        }
        log.info("Order with tracking id {} found", trackOrderQuery.getOrderTrackingId());
        return orderDataMapper.orderToTrackOrder(orderResult.get());
    }
}
