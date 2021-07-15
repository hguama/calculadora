package com.calc3;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.calc3.controller.ReporteController;
import com.calc3.entity.Reporte;
import com.calc3.repository.ReporteRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
class Calculadora3ApplicationTests {
	
	@Autowired
	 private ReporteController controller;
	
	@Autowired
	 private ReporteRepository repo;
	
	private Reporte r;
	

	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}
	
	@Test
	void repositoryCreation() {
		assertThat(repo).isNotNull();
	}
	
	@Test
	void reporteCreation() {
		Reporte r=new Reporte ();
		assertThat(r).isNotNull();
	}

}
