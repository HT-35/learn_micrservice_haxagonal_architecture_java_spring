package org.example.domain.entity;

import org.example.domain.valueobject.CustomerId;

import lombok.Getter;


@Getter
public class Customer extends AggregateRoot<CustomerId> {

	private String username;
	private String firstName;
	private String lastName;

	public Customer(CustomerId customerId, String username, String firstName, String lastName) {
		super(customerId);
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Customer(CustomerId customerId) {
		super(customerId);
	}


}
