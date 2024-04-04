package com.livares.intern.product.repository;

import java.util.List;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.livares.intern.product.dto.ProductsDTO;
import com.livares.intern.product.dto.categoryDTO;
import com.livares.intern.product.models.Products;

public interface ProductRepository extends JpaRepository<Products, Long> {

	@Query("select new com.livares.intern.product.dto.ProductsDTO(p.productName) "
			+ "from Products p "
			+ " where p.quantity >= 10")
	List<ProductsDTO> getProductsByQuantity();
	
	@Query(value="select * from products p "
			+ "join category c "
			+ "where p.quantity >= 10",nativeQuery = true)
	List<Products> getProductsByQuantity1();
	
	Page<Products> findAll(Pageable pageable);
}
