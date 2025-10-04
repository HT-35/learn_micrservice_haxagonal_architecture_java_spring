//package org.example.domain.entity;
//
//import java.util.List;
//import java.util.UUID;
//
//import org.example.domain.valueobject.RestaurantId;
//
//public class Restaurant extends AggregateRoot<RestaurantId> {
//	private final List<Product> products;
//	private boolean active;
//
//	private Restaurant(Builder builder) {
//		super(builder.restaurantId);
//		products = builder.products;
//		active = builder.active;
//	}
//
//	public static Builder builder() {
//		return new Builder();
//	}
//
//	public List<Product> getProducts() {
//		return products;
//	}
//
//	public boolean isActive() {
//		return active;
//	}
//
//	public static final class Builder {
//		private RestaurantId restaurantId;
//		private List<Product> products;
//		private boolean active;
//
//		private Builder() {
//		}
//
//		public Builder restaurantId(@javax.validation.constraints.NotNull RestaurantId val) {
//			restaurantId = val;
//			return this;
//		}
//
//		public Builder products(List<Product> val) {
//			products = val;
//			return this;
//		}
//
//		public Builder active(boolean val) {
//			active = val;
//			return this;
//		}
//
//		public Restaurant build() {
//			return new Restaurant(this);
//		}
//	}
//}


package org.example.domain.entity;

import lombok.Builder;
import lombok.Getter;
import org.example.domain.valueobject.RestaurantId;

import java.util.List;

@Getter
public class Restaurant extends AggregateRoot<RestaurantId> {
	private final List<Product> products;
	private final boolean active;

	@Builder
	public Restaurant(RestaurantId restaurantId, List<Product> products, boolean active) {
		super(restaurantId);
		this.products = products;
		this.active = active;
	}
}