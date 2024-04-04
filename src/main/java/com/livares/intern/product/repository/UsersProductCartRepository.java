package com.livares.intern.product.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.livares.intern.product.dto.ProductsDTO;
import com.livares.intern.product.models.Products;
import com.livares.intern.product.models.UserProductCart;

@Repository
public interface UsersProductCartRepository extends JpaRepository<UserProductCart, Long> {

	@Query(value = "select p.* from products p" + " join user_product_cart upc on  p.id = upc.product_id"
			+ " join users u on  u.id = upc.user_id " + " where upc.user_id = :id", nativeQuery = true)
	public List<Products> ViewCartDataById(@Param("id") Long id);

//	private String name;
//	private String description;
//	private Long price;
//	private Long categoryid;
//	private String image;
//	private Long quantity;
	@Query("select new com.livares.intern.product.dto.ProductsDTO(p.productName,p.productDesc,p.price,p.category.id,"
			+ "p.productImage,p.quantity) from com.livares.intern.product.models.Products p"
			+ " join com.livares.intern.product.models.UserProductCart upc on  p.id = upc.products.id"
			+ " join com.livares.intern.product.models.Users u on  u.id = upc.users.id " + " where upc.users.id = :id")
	public List<ProductsDTO> ViewCartDataById1(@Param("id") Long id);

}
