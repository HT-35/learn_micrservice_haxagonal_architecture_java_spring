package org.example.domain.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(callSuper = true)
@Getter
public abstract class AggregateRoot<ID> extends BaseEntity<ID> {
	public AggregateRoot(ID id) {
		super(id);
	}
}
