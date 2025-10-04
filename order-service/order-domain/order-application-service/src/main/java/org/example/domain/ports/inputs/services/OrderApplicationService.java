package org.example.domain.ports.inputs.services;

import javax.validation.Valid;

import org.example.domain.dto.create.CreateOrderCommand;
import org.example.domain.dto.create.CreateOrderRes;
import org.example.domain.dto.track.TrackOrderQuery;
import org.example.domain.dto.track.TrackOrderResponse;

public interface  OrderApplicationService {
	CreateOrderRes createOrder(@Valid CreateOrderCommand createOrderCommand);
	TrackOrderResponse trackOrder(@Valid TrackOrderQuery trackOrderQuery);

}
