package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.example.demo.entity.Products;
import com.example.demo.repository.ProductsRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@Rollback(false)
public class ProductsRepositoryTests {
	
	
	@Autowired
	private ProductsRepository repo;
	
	@Test
	public void testProducts() {
		Products product=new Products();
		product.setTitle("Printer");
		product.setDescription("HP LaserJet M1005 MFP Multi-function Monochrome Laser Printer  (Toner Cartridge)");
		product.setPrice(17699L);
		product.setImg("https://rukminim2.flixcart.com/image/128/128/k0lbdzk0pkrrdj/printer-refurbished/a/5/h/c-laserjet-m1005-mfp-hp-original-imafjfx2hvjhmysr.jpeg?q=70&crop=false");
		
        Products savedProduct=repo.save(product);
        
        assertThat(savedProduct);
		
	}
	
	@Test
	public void testGetProducts() {
		List<Products> products=repo.findAll();
		
		assertThat(products);
		
	}
	

}
