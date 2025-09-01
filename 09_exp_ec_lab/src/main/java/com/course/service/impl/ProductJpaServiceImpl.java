package com.course.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.course.dto.ProductDto;
import com.course.entity.CategoryEntity;
import com.course.entity.ProductEntity;
import com.course.entity.ProductPriceEntity;
import com.course.entity.ProductReviewEntity;
import com.course.repository.ProductCustomRepository;
import com.course.repository.ProductPriceRepository;
import com.course.repository.ProductRepository;
import com.course.service.ProductService;
import com.course.vo.ProductQueryParam;
import com.course.vo.ProductVo;

@Service
public class ProductJpaServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductPriceRepository productPriceRepository;
	
	@Autowired
	private ProductCustomRepository customRepository;
	
	@Override
	@Transactional
	public void addProductForEach(ProductVo vo) {

		// 新增 Product 資料
		ProductEntity productEntity = new ProductEntity();
		productEntity.setCode(vo.getCode());
		productEntity.setName(vo.getName());
		productEntity = productRepository.save(productEntity);
		
		// 新增 ProductPrice 資料
		ProductPriceEntity priceEntity = new ProductPriceEntity();
		priceEntity.setListPrice(vo.getListPrice());
		priceEntity.setSalesPrice(vo.getSalesPrice());
		// priceEntity.setProductId(productEntity.getId());

		productPriceRepository.save(priceEntity);
	}
	
	/**
	 * 新增商品
	 * @param vo
	 */
	@Override
	@Transactional
	public void addProduct(ProductVo vo) {

		// 新增 Product 資料
		ProductEntity productEntity = new ProductEntity();
		productEntity.setCode(vo.getCode());
		productEntity.setName(vo.getName());
		// System.out.println(productEntity.getId());
		
		// 新增 ProductPrice 資料
		ProductPriceEntity priceEntity = new ProductPriceEntity();
		priceEntity.setListPrice(vo.getListPrice());
		priceEntity.setSalesPrice(vo.getSalesPrice());
//		priceEntity.setProductId(productEntity.getId());
		priceEntity.setProduct(productEntity);
		productEntity.setPriceEntity(priceEntity);

		productRepository.save(productEntity);
	}
	
	@Override
	public List<ProductEntity> getAllProductReturnEntity() {
		// 取得所有商品
		return productRepository.findAll();
	}
	
	/**
	 * 取得所有商品
	 * @return
	 */
	@Override
	public List<ProductVo> getAllProduct() {
		// 取得所有商品
		List<ProductEntity> productList = productRepository.findAll();
		
		// Integer.parseInt("ABC");
		return productList.stream().map(product -> {
			ProductVo vo = new ProductVo();
			// 取得 Entity 欄位資料，並放到 Vo 當中

			vo.setCode(product.getCode());
			vo.setName(product.getName());
			// 取得 Price 資料
			ProductPriceEntity priceEntity = product.getPriceEntity();
			vo.setListPrice(priceEntity.getListPrice());
			vo.setSalesPrice(priceEntity.getSalesPrice());
			if (product.getReviews() != null && product.getReviews().size() > 0) {
				List<ProductReviewEntity> reviews = product.getReviews();
				List<String> memos = reviews.stream().map(ProductReviewEntity::getMemo).collect(Collectors.toList());
//				List<String> memos = new ArrayList<>();
//				for (ProductReviewEntity review : reviews) {
//					memos.add(review.getMemo());
//				}
				vo.setMemos(memos);
			}
			
			if (product.getCategoryList() != null && product.getCategoryList().size() > 0) {
				List<CategoryEntity> categoryList = product.getCategoryList();
				List<String> names = categoryList.stream().map(CategoryEntity::getName).collect(Collectors.toList());
				vo.setCategories(names);
			}

			
			return vo;
		}).collect(Collectors.toList());
	}
	
	/**
	 * 取得所有商品
	 * @return
	 */
	@Override
	public List<ProductVo> getAllProductForPrice() {
		// 取得所有商品
		List<ProductPriceEntity> productPriceList = productPriceRepository.findAll();
		
		return productPriceList.stream().map(price -> {
			ProductVo vo = new ProductVo();
			// 取得 Entity 欄位資料，並放到 Vo 當中

			vo.setCode(price.getProduct().getCode());
			vo.setName(price.getProduct().getName());
			// 取得 Price 資料
			vo.setListPrice(price.getListPrice());
			vo.setSalesPrice(price.getSalesPrice());
			return vo;
		}).collect(Collectors.toList());
	}
	
	/**
	 * 透過ID取得商品
	 * @return
	 */
	@Override
	public ProductVo getProductById(Long id) {
		// 取得所有商品
		ProductEntity product = null;
		ProductVo vo = new ProductVo();
		// 取得 Price 資料
		
		// 取得 多筆 Review 資料

		
		// 取得 多筆 Category 資料

		return vo;
	}
	
	/**
	 * 取得所有商品，使用EntityManager
	 * @return
	 */
	@Override
	public List<ProductDto> getAllProductData() {
		return customRepository.findAllProduct();
	}
	
	/**
	 * 取得所有商品，使用EntityManager
	 * @return
	 */
	@Override
	public List<ProductDto> getProductByCondition(ProductQueryParam queryParam) {
		BigDecimal min = queryParam.getMinPrice();
		BigDecimal total = new BigDecimal("10000");
		total = total.add(min);
		
		return customRepository.findByCondition(queryParam);
	}
	

	/**
	 * 取得所有商品
	 * @return
	 */
	@Override
	public List<ProductVo> getAllProductQuery() {
		// 取得所有商品
		List<ProductDto> productList = productRepository.findByQuery();
		
		return productList.stream().map(product -> {
			ProductVo vo = new ProductVo();
			// 取得 Entity 欄位資料，並放到 Vo 當中
			vo.setCode(product.getCode());
			vo.setName(product.getName());
			return vo;
		}).collect(Collectors.toList());
	}

	@Override
	public ProductVo getProductByCodeXml(String code) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
