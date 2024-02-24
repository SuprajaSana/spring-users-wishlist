package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="wishlist")
public class Wishlist {
    
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
    private Long product_id;
	
	private String product_title;
	
	private String product_description;
	
	private Long product_price;
	
	private String product_img;
	
	private String user_email;

	public Wishlist(Long product_id, String product_title, String product_description, Long product_price,
			String product_img,String user_email) {
		super();
		this.product_id = product_id;
		this.product_title = product_title;
		this.product_description = product_description;
		this.product_price = product_price;
		this.product_img = product_img;
		this.user_email=user_email;
	}
	
	
	public Wishlist() {
		// TODO Auto-generated constructor stub
	}


	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Long product_id) {
		this.product_id = product_id;
	}

	public String getProduct_title() {
		return product_title;
	}

	public void setProduct_title(String product_title) {
		this.product_title = product_title;
	}

	public String getProduct_description() {
		return product_description;
	}

	public void setProduct_description(String product_description) {
		this.product_description = product_description;
	}

	public Long getProduct_price() {
		return product_price;
	}

	public void setProduct_price(Long product_price) {
		this.product_price = product_price;
	}

	public String getProduct_img() {
		return product_img;
	}

	public void setProduct_img(String product_img) {
		this.product_img = product_img;
	}
	
}
