package org.example.domain.ports;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.dto.create.CreateOrderCommand;
import org.example.domain.dto.create.CreateOrderRes;
import org.example.domain.dto.track.TrackOrderQuery;
import org.example.domain.dto.track.TrackOrderResponse;
import org.example.domain.ports.inputs.services.OrderApplicationService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;


@Service
@Validated
@Slf4j
@AllArgsConstructor
public class OrderApplicationServiceImpl implements OrderApplicationService {
    private final OrderCreatedCommandHandler orderCreatedCommandHandler;
    private final OrderTrackCommandHandler orderTrackCommandHandler;

    @Override
    public CreateOrderRes createOrder(CreateOrderCommand createOrderCommand) {
        return orderCreatedCommandHandler.createOrder(createOrderCommand);
    }

    @Override
    public TrackOrderResponse trackOrder(TrackOrderQuery trackOrderQuery) {
        return orderTrackCommandHandler.trackOrder(trackOrderQuery);
    }
}
