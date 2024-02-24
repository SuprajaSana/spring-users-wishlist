package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.example.demo.entity.Wishlist;
import com.example.demo.repository.WishlistRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@Rollback(false)
public class WishlistRepositoryTests {
	
	@Autowired
	private WishlistRepository repo;
	
	@Test
	public void testPostWishlist() {
		Wishlist wishlist=new Wishlist();
		wishlist.setProduct_id(4L);
		wishlist.setProduct_title("Printer");
		wishlist.setProduct_description("HP LaserJet M1005 MFP Multi-function Monochrome Laser Printer  (Toner Cartridge)");
		wishlist.setProduct_price(17699L);
		wishlist.setProduct_img("https://rukminim2.flixcart.com/image/128/128/k0lbdzk0pkrrdj/printer-refurbished/a/5/h/c-laserjet-m1005-mfp-hp-original-imafjfx2hvjhmysr.jpeg?q=70&crop=false");
		wishlist.setUser_email("s1@gmail.com");
		
		Wishlist savedUser=repo.save(wishlist);
		
		assertThat(savedUser);;
	}
	
	@Test
	public void testGetWishlistByUserEmail() {
		List<Wishlist> wishlist=repo.findByUserEmail("s123@gmail.com");
		
		assertThat(wishlist);
		
	}
	
	@Test
	public void testDeleteWishlist() {
		repo.deleteById(1L);
	}
	

}
