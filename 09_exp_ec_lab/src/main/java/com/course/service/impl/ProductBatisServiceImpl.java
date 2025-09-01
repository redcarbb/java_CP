package com.course.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course.dto.ProductDto;
import com.course.entity.ProductEntity;
import com.course.mapper.ProductMapper;
import com.course.service.ProductService;
import com.course.vo.ProductQueryParam;
import com.course.vo.ProductVo;

@Service
public class ProductBatisServiceImpl implements ProductService {

	@Autowired
	private ProductMapper productMapper;
	
	@Override
	public void addProductForEach(ProductVo vo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addProduct(ProductVo vo) {
		// TODO Auto-generated method stub
		//productMapper.insertProduct(vo.getCode(), vo.getName());
		
		Long productSeq = productMapper.getProductSeq();
		vo.setId(productSeq);
		productMapper.insertProductByVo(vo);
		productMapper.insertPrice(vo.getId(), vo.getListPrice(), vo.getSalesPrice());
		
	}

	@Override
	public List<ProductEntity> getAllProductReturnEntity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductVo> getAllProduct() {
		List<ProductDto> dtos = productMapper.findAll();
		
//		List<ProductVo> voList = new ArrayList<>();
//		for (ProductDto dto : dtos) {
//			ProductVo vo = new ProductVo();
//			vo.setCode(dto.getCode());
//			vo.setName(dto.getName());
//			voList.add(vo);
//		}
		

		
		// 先撈出所有的評論資料，再將資料依照 productId 分組
		List<ProductDto> reviews = productMapper.findAllReview();
//		Map<Long, List<String>> memoMap = new HashMap<>();

		// 將資料群組為 Map，Key: productId, Value: List<Memo>
//		for (ProductDto reviewData : reviews) {
//			Long id = reviewData.getProductId();
//			if (memoMap.containsKey(id)) {
//				List<String> existMemoList = memoMap.get(id);
//				existMemoList.add(reviewData.getMemo());
//			} else {
//				List<String> tempMemoList = new ArrayList<>();
//				tempMemoList.add(reviewData.getMemo());
//				memoMap.put(id, tempMemoList);
//			}
//		}
		
	       Map<Long, List<String>> memoMap = reviews.stream()
	               .collect(Collectors.groupingBy(
	                   ProductDto::getProductId,
	                   Collectors.mapping(ProductDto::getMemo, Collectors.toList())
	               ));
		
		return dtos.stream().map(dto -> {
			ProductVo vo = new ProductVo();
			vo.setCode(dto.getCode());
			vo.setName(dto.getName());
			vo.setListPrice(dto.getListPrice());
			vo.setSalesPrice(dto.getSalesPrice());

			//List<ProductDto> views = productMapper.findReviewById(dto.getId());
			//List<String> memos = views.stream().map(ProductDto::getMemo).collect(Collectors.toList());
			
			vo.setMemos(memoMap.get(dto.getId()));
			return vo;
		}).collect(Collectors.toList());
	}

	@Override
	public List<ProductVo> getAllProductForPrice() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductVo getProductById(Long id) {
		ProductDto dto = productMapper.findById(id);
		ProductVo vo = new ProductVo();
		vo.setCode(dto.getCode());
		vo.setName(dto.getName());

		return vo;
	}

	@Override
	public List<ProductDto> getAllProductData() {
		
		
		return productMapper.selectProductWithReviews();
	}

	@Override
	public List<ProductDto> getProductByCondition(ProductQueryParam queryParam) {
		return productMapper.findByCondition(queryParam);
	}

	@Override
	public List<ProductVo> getAllProductQuery() {
		// TODO Auto-generated method stub
		return null;
	}

	public ProductVo getProductByCode(String code) {
		List<ProductDto> dtos = productMapper.findByCode(code);
		ProductDto dto = dtos.get(0);
		ProductVo vo = new ProductVo();
		vo.setCode(dto.getCode());
		vo.setName(dto.getName());

		return vo;
	}
	
	public ProductVo getProductByCodeXml(String code) {
		ProductDto dto = productMapper.findByCodeXml(code);
		ProductVo vo = new ProductVo();
		vo.setCode(dto.getCode());
		vo.setName(dto.getName());

		return vo;
	}
	
}
