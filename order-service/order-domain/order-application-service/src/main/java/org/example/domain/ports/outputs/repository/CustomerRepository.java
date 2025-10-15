package org.example.domain.ports.outputs.repository;

import java.util.Optional;

import org.example.domain.entity.Customer;
import org.example.domain.valueobject.CustomerId;

public interface CustomerRepository {

    Optional<Customer> findCustomer(CustomerId customerId);
}
