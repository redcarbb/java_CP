package com.course.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.course.dto.ProductDto;
import com.course.entity.ProductEntity;
import com.course.vo.ProductQueryParam;
import com.course.vo.ProductVo;

public interface ProductService {

	void addProductForEach(ProductVo vo);

	/**
	 * 新增商品
	 * @param vo
	 */
	void addProduct(ProductVo vo);

	List<ProductEntity> getAllProductReturnEntity();

	/**
	 * 取得所有商品
	 * @return
	 */
	List<ProductVo> getAllProduct();

	/**
	 * 取得所有商品
	 * @return
	 */
	List<ProductVo> getAllProductForPrice();

	/**
	 * 透過ID取得商品
	 * @return
	 */
	ProductVo getProductById(Long id);

	/**
	 * 取得所有商品，使用EntityManager
	 * @return
	 */
	List<ProductDto> getAllProductData();

	/**
	 * 取得所有商品，使用EntityManager
	 * @return
	 */
	List<ProductDto> getProductByCondition(ProductQueryParam queryParam);

	/**
	 * 取得所有商品
	 * @return
	 */
	List<ProductVo> getAllProductQuery();

}