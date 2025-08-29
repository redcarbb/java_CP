package com.course.dto;

import java.math.BigDecimal;

//@SqlResultSetMapping(
//	    name = "ProductDtoMapping",
//	    classes = @ConstructorResult(
//	        targetClass = ProductDto.class,
//	        columns = {
//        		@ColumnResult(name = "ID", type = Long.class),
//        		@ColumnResult(name = "CODE", type = String.class),
//	            @ColumnResult(name = "NAME", type = String.class),
//	            @ColumnResult(name = "LIST_PRICE", type = BigDecimal.class),
//	            @ColumnResult(name = "SALES_PRICE", type = BigDecimal.class),
//	            @ColumnResult(name = "MEMO", type = String.class)
//	        }
//	    )
//	)
//@Entity
public class ProductDto {

//	@Id
	private Long id;
	
	private String code;
	
	private String name;
	
	private BigDecimal listPrice;
	
	private BigDecimal salesPrice;
	
	private String memo;
	
	private String cname;

	private Long ProductId;
	
	public ProductDto() {

	}

	public Long getProductId() {
		return ProductId;
	}

	public void setProductId(Long productId) {
		ProductId = productId;
	}

	public ProductDto(String code, String name) {
		super();
		this.code = code;
		this.name = name;
	}

	public ProductDto(Long id, String code, String name, BigDecimal listPrice, BigDecimal salesPrice, String memo) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.listPrice = listPrice;
		this.salesPrice = salesPrice;
		this.memo = memo;
	}
	
	public ProductDto(String code, String name, BigDecimal listPrice, BigDecimal salesPrice) {
		super();
		this.code = code;
		this.name = name;
		this.listPrice = listPrice;
		this.salesPrice = salesPrice;
	}
	
	public ProductDto(String code, String name, BigDecimal listPrice, BigDecimal salesPrice, String memo) {
		super();
		this.code = code;
		this.name = name;
		this.listPrice = listPrice;
		this.salesPrice = salesPrice;
		this.memo = memo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getListPrice() {
		return listPrice;
	}

	public void setListPrice(BigDecimal listPrice) {
		this.listPrice = listPrice;
	}

	public BigDecimal getSalesPrice() {
		return salesPrice;
	}

	public void setSalesPrice(BigDecimal salesPrice) {
		this.salesPrice = salesPrice;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

}
