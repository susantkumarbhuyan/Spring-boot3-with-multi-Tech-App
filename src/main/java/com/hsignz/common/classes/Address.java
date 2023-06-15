package com.hsignz.common.classes;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Address {
	@Column(name = "address_id", nullable = true)
	private long addressId;
	@Column(name = "city_name", nullable = true)
	private String cityName;

}
