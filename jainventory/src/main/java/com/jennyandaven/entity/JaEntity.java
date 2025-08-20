package com.jennyandaven.entity;

import jakarta.persistence.Entity;

@Entity

public class JaEntity {

	private Long id;

	private String sku;

	private String name;

	private String unit;

	private Double costPrice = 0.0;

	private Double salePrice = 0.0;

	private Boolean active = true;
	
}
