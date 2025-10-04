package org.example.domain.ports.outputs.repository;

import org.example.domain.entity.Customer;
import org.example.domain.valueobject.CustomerId;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository {

    Optional<Customer> findCustomer(CustomerId customerId);
}
