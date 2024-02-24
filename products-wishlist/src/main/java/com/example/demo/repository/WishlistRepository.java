package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Wishlist;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
	
	@Query("SELECT u FROM Wishlist u WHERE u.user_email=?1 ")
	List<Wishlist> findByUserEmail(String email);
	
	@Query("SELECT u FROM Wishlist u WHERE u.product_id=?1 ")
	List<Wishlist> findByProductIdAndEmail(Long productId);
	
}
