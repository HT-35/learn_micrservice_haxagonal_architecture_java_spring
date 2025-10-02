package org.example.domain.entity;

import org.example.domain.valueobject.Money;
import org.example.domain.valueobject.ProductId;

import lombok.Getter;

@Getter
// @AllArgsConstructor
public class Product extends BaseEntity<ProductId> {
	private String name;
	private Money price; // Money là Value Object

	public Product(ProductId id, String name, Money price) {
		super(id);
		this.name = name;
		this.price = price;
	}

	public void updateWithConfirmedNameAndPrice(String name, Money price) {
		this.name = name;
		this.price = price;
	}

}
