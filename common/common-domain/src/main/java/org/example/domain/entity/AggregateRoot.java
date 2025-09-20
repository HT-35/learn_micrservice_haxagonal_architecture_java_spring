package org.example.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class AggregateRoot<ID> extends BaseEntity<ID> {

}
