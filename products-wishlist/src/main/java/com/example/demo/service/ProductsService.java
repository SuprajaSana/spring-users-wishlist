package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Products;
import com.example.demo.entity.Wishlist;
import com.example.demo.repository.ProductsRepository;
import com.example.demo.repository.WishlistRepository;

@Service
public class ProductsService {

	@Autowired
	private ProductsRepository repo;
	
	@Autowired
	private WishlistRepository wishlistrepo;
	
	public Products findProductById(Long id) {
		return repo.findById(id).get();
	}
	
	public List<Wishlist> findWishlistByUserEmail(String email){
		return wishlistrepo.findByUserEmail(email);
	}
	
	public void deleteFromWishlist(Long id) {
		wishlistrepo.deleteById(id);
	}
	
}
