package com.course.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.course.dto.ProductDto;
import com.course.entity.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long>{

	@Query("select new com.course.dto.ProductDto(p.code, p.name) from ProductEntity p")
	public List<ProductDto> findByQuery();
	
//	@Query("select new com.course.dto.ProductDto(p.code, p.name, r.listPrice) from ProductEntity p join ProductPriceEntity r on r.productId = p.id")
//	public List<ProductDto> findByQuery2();
}
