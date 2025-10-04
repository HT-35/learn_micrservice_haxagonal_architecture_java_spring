package org.example.domain.mapper;

import org.example.domain.dto.create.CreateOrderCommand;
import org.example.domain.dto.create.CreateOrderRes;
import org.example.domain.dto.create.OrderAddress;
import org.example.domain.dto.message.RestaurantApprovalResponse;
import org.example.domain.entity.Order;
import org.example.domain.entity.OrderItem;
import org.example.domain.entity.Product;
import org.example.domain.entity.Restaurant;
import org.example.domain.valueObject.StreetAddress;
import org.example.domain.valueobject.Money;
import org.example.domain.valueobject.ProductId;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class OrderDataMapper {
//
//    void orderApproved(@Valid RestaurantApprovalResponse restaurantApprovalResponse) ;
//    void orderRejected(@Valid RestaurantApprovalResponse restaurantApprovalResponse);


    public Restaurant createOrderCommandToRestaurant(@Valid CreateOrderCommand createOrderCommand) {
        return Restaurant.builder()
                .restaurantId(createOrderCommand.getRestaurantId())
                .products(createOrderCommand.getItems().stream().map(orderItem -> new Product(orderItem.getProduct().getId()))
                        .toList())
                .build();
    }

    public Order createOrderCommandToOrder(@Valid CreateOrderCommand createOrderCommand) {
        return Order.builder()
                .customerId(createOrderCommand.getCustomerId())
                .restaurantId(createOrderCommand.getRestaurantId())
                .price(createOrderCommand.getPrice())
                .items(createOrderCommand.getItems())
                .streetAddress(orderAddressToStreetAddress(createOrderCommand.getAddress()))
                .build();
    }

    public StreetAddress orderAddressToStreetAddress(@NotNull OrderAddress address) {
        return new StreetAddress(
                UUID.randomUUID(),
                address.getStreet(),
                address.getPostalCode(),
                address.getCity());
    }

    public CreateOrderRes orderToCreateOrderRes(Order order) {
        return CreateOrderRes
                .builder()
                .orderStatus(order.getOrderStatus())
                .orderTrackingId(order.getTrackingId().getValue())
                .build();

    }

    // convert Dto OrderItem  ===> OrderItem Entity
    private List<OrderItem> orderItemsToOrderItemEntities(
            List<org.example.domain.dto.create.OrderItem> orderItemsDto) {
        return orderItemsDto.stream()
                .map(OrItem ->
                        OrderItem.builder()
                                .product(new Product(OrItem.getProductId()))
                                .price(new Money(OrItem.getPrice()))
                                .quantity(OrItem.getQuantity())
                                .subTotal(new Money(OrItem.getSubTotal()))
                                .build()).collect(Collectors.toList());
    }

}
