package org.example.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public abstract class BaseEntity<ID> {
	private ID id;

}
