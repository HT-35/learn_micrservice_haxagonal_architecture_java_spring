package org.example.domain.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
public abstract class BaseEntity<ID> {
	private ID id;

	public BaseEntity(ID id) {
		this.id = id;
	}

}
