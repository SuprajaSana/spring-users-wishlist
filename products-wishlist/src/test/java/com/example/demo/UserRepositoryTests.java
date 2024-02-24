package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.example.demo.entity.Users;
import com.example.demo.repository.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
	
	@Autowired
	private UserRepository repo;

	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testCreateUsers(){
		Users user=new Users();
		user.setEmail("s1@gmail.com");
        user.setFullName("S1");
        user.setPassword("123");
        
        Users savedUser=repo.save(user);
        
        Users existUser=entityManager.find(Users.class,savedUser.getId());
        
        assertThat(existUser.getEmail()).isEqualTo(user.getEmail());
        
	}
	
	@Test
	public void testFindUsersByEmail() {
		String email="s1@gmail.com";
		
		Users user=repo.findByEmail(email);
		
	    assertThat(user).isNotNull();			
	}
	
	
}
