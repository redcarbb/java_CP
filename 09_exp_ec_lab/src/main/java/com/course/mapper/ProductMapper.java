package com.course.mapper;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.course.dto.ProductDto;
import com.course.vo.ProductQueryParam;
import com.course.vo.ProductVo;

// @Mapper
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
	
	@Select("select PRODUCT_SEQ.nextval from dual")
	public Long getProductSeq();
	
	@Insert("INSERT INTO PRODUCT (ID, CODE, NAME) VALUES (PRODUCT_SEQ.nextval, #{code}, #{name}) ")
	public void insertProduct(String code, String name);
	
	@Insert("INSERT INTO PRODUCT (ID, CODE, NAME) VALUES (#{id}, #{code}, #{name}) ")
	public void insertProductByVo(ProductVo vo);
	
	@Insert("INSERT INTO PRODUCT_PRICE (ID, PRODUCT_ID, LIST_PRICE, SALES_PRICE) VALUES (PRODUCT_PRICE_SEQ.nextval, #{productId}, #{listPrice}, #{salesPrice}) ")
	public void insertPrice(Long productId, BigDecimal listPrice, BigDecimal salesPrice);
	
	public ProductDto findByCodeXml(String code);
	
	public List<ProductDto> findByCondition(ProductQueryParam condition);
	
	public List<ProductDto> selectProductWithReviews();
}
