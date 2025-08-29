package com.course.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.course.dto.ProductDto;

@Mapper
public interface ProductMapper {

	@Select("SELECT * FROM PRODUCT P JOIN PRODUCT_PRICE R ON R.PRODUCT_ID = P.ID")
	public List<ProductDto> findAll();
	
	@Select("SELECT * FROM PRODUCT P JOIN PRODUCT_PRICE R ON R.PRODUCT_ID = P.ID WHERE P.ID = #{id}")
	public ProductDto findById(Long id);
	
	@Select("SELECT * FROM PRODUCT P WHERE P.CODE = #{code}")
	public List<ProductDto> findByCode(String code);
	
	// SELECT PRODUCT_REVIEW BY id
	
	@Select("SELECT * FROM PRODUCT_REVIEW V WHERE V.PRODUCT_ID = #{id}")
	public List<ProductDto> findReviewById(Long id);

	@Select("SELECT * FROM PRODUCT_REVIEW V")
	public List<ProductDto> findAllReview();
}
