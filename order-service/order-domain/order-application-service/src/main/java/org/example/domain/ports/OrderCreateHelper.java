package org.example.domain.ports;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.OrderDomainService;
import org.example.domain.dto.create.CreateOrderCommand;
import org.example.domain.entity.Customer;
import org.example.domain.entity.Order;
import org.example.domain.entity.Restaurant;
import org.example.domain.event.OrderCreatedEvent;
import org.example.domain.exception.OrderDomainException;
import org.example.domain.mapper.OrderDataMapper;
import org.example.domain.ports.outputs.repository.CustomerRepository;
import org.example.domain.ports.outputs.repository.OrderRespository;
import org.example.domain.ports.outputs.repository.RestaurantRepository;
import org.example.domain.valueobject.CustomerId;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Slf4j
@Component
@AllArgsConstructor
@Getter
public class OrderCreateHelper {
    private final OrderDomainService orderDomainService;
    private final OrderRespository orderRespository;
    private final CustomerRepository customerRepository;
    private final RestaurantRepository restaurantRepository;
    private final OrderDataMapper orderDataMapper;


    @Transactional
    public OrderCreatedEvent persistOrder(CreateOrderCommand createOrderCommand) {
        // check if customer exists
        checkCustomer(createOrderCommand.getCustomerId());
        // check if restaurant exists
        checkRestaurant(createOrderCommand);
        Order order = orderDataMapper.createOrderCommandToOrder(createOrderCommand);
        Restaurant restaurant = checkRestaurant(createOrderCommand);
        OrderCreatedEvent orderCreatedEvent = orderDomainService.validateAndInitiateOrder(order, restaurant);
        saveOrder(order);
        log.info("Order created event: {}", orderCreatedEvent.getOrder().getId().getValue());
        return orderCreatedEvent;
    }

    private Restaurant checkRestaurant(@NotNull CreateOrderCommand createOrderCommand) {
        Restaurant restaurant = orderDataMapper.createOrderCommandToRestaurant(createOrderCommand);
        Optional<Restaurant> restaurantOptional = restaurantRepository.findRestaurantInformation(restaurant);
        if (restaurantOptional.isEmpty()) {
            log.warn("Restaurant with id {} not found", createOrderCommand.getRestaurantId());
            throw new OrderDomainException("Restaurant with id " + createOrderCommand.getRestaurantId() + " not found");
        }
        return restaurantOptional.get();
    }

    private void checkCustomer(@NotNull CustomerId customerId) {
        Optional<Customer> customerOptional = customerRepository.findCustomer(customerId);

        if (customerOptional.isEmpty()) {
            log.warn("Customer with id {} not found", customerId);
            throw new OrderDomainException("Customer with id " + customerId + " not found");
        }
    }

    private Order saveOrder(Order order) {
        Order orderResult = orderRespository.save(order);
        if (orderResult == null) {
            log.warn("Order with id {} not saved", order.getId());
            throw new OrderDomainException("Order with id " + order.getId() + " already exists");
        }
        log.info("Order with id {} saved", order.getId());
        return orderResult;
    }
}
