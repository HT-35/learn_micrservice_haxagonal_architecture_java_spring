package org.example.domain.valueobject;

import java.math.BigDecimal;
import java.math.RoundingMode;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Getter
public class Money {
	private final BigDecimal amount;

	// check amount lớn hơn không
	public boolean isGreateThanZero() {
		return this.amount != null && this.amount.compareTo(BigDecimal.ZERO) > 0;
	}

	// check amount lớn hơn input money
	public boolean isGreateThan(Money money) {
		return this.amount != null && this.amount.compareTo(money.getAmount()) > 0;
	}

	// cộng money vào this.amount
	public Money add(Money money) {
		return new Money(setScale(this.amount.add(money.getAmount())));
	}

	// trừ money khỏi this.amount
	public Money subtract(Money money) {
		return new Money(setScale(this.amount.subtract(money.getAmount())));
	}

	// nhân this.amount với multiplier
	public Money multiply(int multiplier) {
		return new Money(setScale(this.amount.multiply(BigDecimal.valueOf(multiplier))));
	}

	// Làm tròn amount đến 2 chữ số thập phân, 1.235 → 1.24
	private BigDecimal setScale(BigDecimal value) {
		return value.setScale(2, RoundingMode.HALF_EVEN);
	}

}
