package org.example.domain.ports.inputs.services;

import org.example.domain.dto.create.CreateOrderCommand;
import org.example.domain.dto.create.CreateOrderRes;
import org.example.domain.dto.track.TrackOrderQuery;
import org.example.domain.dto.track.TrackOrderResponse;

import jakarta.validation.Valid;

public interface  OrderApplicationService {
	CreateOrderRes createOrder(@Valid CreateOrderCommand createOrderCommand);
	TrackOrderResponse trackOrder(@Valid TrackOrderQuery trackOrderQuery);

}
