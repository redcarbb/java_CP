package com.course.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.course.dto.ProductDto;
import com.course.vo.ProductQueryParam;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
public class ProductCustomRepository {

	// @Autowired
	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public List<ProductDto> findAllProduct() {
		// SQL
		// Product 
		// JOIN ProductPrice
		// JOIN 其他
		String sql = """
				SELECT
				P.ID,
				P.CODE,
				P.NAME,
				R.LIST_PRICE,
				R.SALES_PRICE,
				V.MEMO 
				FROM PRODUCT P
				JOIN PRODUCT_PRICE R ON R.PRODUCT_ID = P.ID
				LEFT JOIN PRODUCT_REVIEW V ON V.PRODUCT_ID = P.ID
			""";
		
		// Query query = entityManager.createNativeQuery(sql, ProductDto.class);
		
		// ProductDtoMapping
		Query query = entityManager.createNativeQuery(sql, "ProductDtoMapping");
		return query.getResultList();
	}
	
    /**
     * 依條件查詢
     * @param queryParam
     * @return
     */
    @SuppressWarnings("unchecked")
	public List<ProductDto> findByCondition(ProductQueryParam queryParam) {

    	// StringBuffer
    	StringBuilder sql = new StringBuilder();
    	sql.append("SELECT ");
    	sql.append("P.CODE, ");
    	sql.append("P.NAME, ");
    	sql.append("R.LIST_PRICE, ");
    	sql.append("R.SALES_PRICE ");
    	sql.append("FROM PRODUCT P ");
    	sql.append("JOIN PRODUCT_PRICE R ON R.PRODUCT_ID = P.ID ");
    	
    	// 固定為 true 的條件 (1 = 1)
    	sql.append("WHERE P.ID IS NOT NULL ");
    	
    	if (queryParam.getProductName() != null && !queryParam.getProductName().isBlank()) {
    		sql.append("AND P.NAME LIKE :PRODUCTNAME ");
    	}
    	
    	if (queryParam.getMinPrice() != null) {
    		sql.append("AND R.SALES_PRICE >= :MINPRICE ");
    	}
    	
    	if (queryParam.getMaxPrice() != null) {
    		sql.append("AND R.SALES_PRICE <= :MAXPRICE ");
    	}

    	Query query = entityManager.createNativeQuery(sql.toString(),  ProductDto.class);
    	
    	if (queryParam.getProductName() != null && !queryParam.getProductName().isBlank()) {
    		query.setParameter("PRODUCTNAME", "%" + queryParam.getProductName() + "%");
    	}
    	
    	if (queryParam.getMinPrice() != null) {
    		query.setParameter("MINPRICE", queryParam.getMinPrice());
    	}
    	
    	if (queryParam.getMaxPrice() != null) {
    		query.setParameter("MAXPRICE", queryParam.getMaxPrice());
    	}
    	
    	return query.getResultList();
    }
}
