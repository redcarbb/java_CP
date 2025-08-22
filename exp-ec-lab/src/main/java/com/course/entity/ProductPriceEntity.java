package com.course.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "PRODUCT_PRICE")
public class ProductPriceEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCT_PRICE_SEQ_GENERATOR")
	@SequenceGenerator(name = "PRODUCT_PRICE_SEQ_GENERATOR", sequenceName = "PRODUCT_PRICE_SEQ", allocationSize = 1)
	private Long id;
	
	@Column
	private BigDecimal listPrice;
	
	@Column
	private BigDecimal salesPrice;
	
//	@Column
//	private Long productId;
	
    @OneToOne
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID")
    private ProductEntity product;
}
