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
		
		
		
		// reviews
		// Key: product, Value: Memos
		Map<Long, List<String>> memoMap = new HashMap<>();
		
		List<ProductDto> reviews = productMapper.findAllReview();

		for (ProductDto d : reviews) {
			Long id = d.getProductId();
			if (memoMap.containsKey(id)) {
				List<String> ms = memoMap.get(id);
				ms.add(d.getMemo());
			} else {
				List<String> m = new ArrayList<>();
				m.add(d.getMemo());
				memoMap.put(id, m);
			}
		}
		

		
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductDto> getProductByCondition(ProductQueryParam queryParam) {
		// TODO Auto-generated method stub
		return null;
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
}
