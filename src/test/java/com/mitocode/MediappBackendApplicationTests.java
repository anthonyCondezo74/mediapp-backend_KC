package com.mitocode;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.mitocode.model.Usuario;
import com.mitocode.repo.IUsuarioRepo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MediappBackendApplicationTests {
	@Autowired
	private IUsuarioRepo repo;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	@Test
	public void contextLoads() {
		Usuario us = new Usuario();
		us.setIdUsuario(1);
		us.setUsername("anthonycondezo1@gmail.com");
		us.setPassword(bcrypt.encode("123"));
		//us.setPassword("123");
		us.setEnabled(true);
		
		Usuario retorno = repo.save(us);
		
		assertTrue(retorno.getPassword().equalsIgnoreCase(us.getPassword()));
	}

}
