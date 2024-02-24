package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Products;
import com.example.demo.entity.UserLogin;
import com.example.demo.entity.Users;
import com.example.demo.entity.Wishlist;
import com.example.demo.repository.ProductsRepository;
import com.example.demo.repository.UserLoginRepository;
import com.example.demo.repository.WishlistRepository;
import com.example.demo.service.CustomUserDetailsService;
import com.example.demo.service.ProductsService;

@Controller
public class ProductsController {
	
	@Autowired
	private ProductsRepository repo;
	
	@Autowired
	private WishlistRepository wishlistrepo; 
	
	@Autowired
	private UserLoginRepository userloginrepo;
	
	@Autowired
	private ProductsService productService;
	
	@Autowired
	private CustomUserDetailsService serv;

	@PostMapping("/loginpage")
	public String PostLoginDetails(Users user) {
		UserDetails userAuth=serv.loadUserByUsername(user.getEmail());
		UserDetails authPassword=serv.loadUserByPassword(user.getPassword());
		
		if(userAuth!=null && authPassword!=null){
			UserLogin userlogin=new UserLogin();
			userlogin.setEmail(user.getEmail());
			userlogin.setPassword(user.getPassword());
			
			userloginrepo.save(userlogin);
			
            return "redirect:/home";			
		}
		return "redirect:/loginpage?error";
	}
	
	@GetMapping("/home")
	public ModelAndView GetHomeProducts() {
		List<Products> product=repo.findAll();
		return new ModelAndView("home","products",product);
	}
	
	@GetMapping("/products")
	public ModelAndView GetProducts() {
		List<Products> product=repo.findAll();
		return new ModelAndView("home","products",product);
	}
	
	@RequestMapping("/wishlist/{id}")
	public String addProductToWishlist(@PathVariable("id") Long id) {
		Products product=productService.findProductById(id);
		List<UserLogin> userlogin=userloginrepo.findAll();
		
		List <Wishlist> wishlistFilter=wishlistrepo.findByProductIdAndEmail(id);
		
		List<Wishlist> wishlistEmail=wishlistrepo.findByUserEmail(userlogin.get(0).getEmail());
		
		if(wishlistFilter.isEmpty() && wishlistEmail.isEmpty()||(wishlistFilter.isEmpty() &&!wishlistEmail.isEmpty()) ||(!wishlistFilter.isEmpty() && wishlistEmail.isEmpty())) {
		Wishlist wishlist=new Wishlist(product.getId(),product.getTitle(),product.getDescription(),product.getPrice(),product.getImg(),userlogin.get(0).getEmail());
		wishlistrepo.save(wishlist);
		}
		return "redirect:/wishlist";
	}
	
	@GetMapping("/wishlist")
	public ModelAndView GetWishlist() {
		List<UserLogin> userlogin=userloginrepo.findAll();
		
		List<Wishlist> wishlist=productService.findWishlistByUserEmail(userlogin.get(0).getEmail());
		return new ModelAndView("wishlist","wishlist",wishlist);
		
	}
	
	@RequestMapping("/deletewishlist/{id}")
	public String DeleteWishlist(@PathVariable("id") Long id) {
		
		productService.deleteFromWishlist(id);
		return "redirect:/wishlist";
	}
	
	@RequestMapping("/logout")
	public String DeleteUserLogin() {
		userloginrepo.deleteAll();
		return "redirect:/loginpage?logout";
	}
	
}
