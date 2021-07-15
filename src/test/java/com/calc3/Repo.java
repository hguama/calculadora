package com.calc3;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.calc3.entity.Reporte;
import com.calc3.utils.CalcularHora;
 

@RunWith(SpringRunner.class) 
@SpringBootTest

class Repo {
 
	private CalcularHora ch;	

	@Test
	void test() {
		 ch=new CalcularHora();
//		fail("Not yet implemented");
	}
	
	 
	
	void verFecha() {
		
		 LocalDateTime fecha1 = LocalDateTime.of(2021,01,1,8,0);
		 LocalDateTime fecha2 = LocalDateTime.of(2021,01,1,10,0);
		 int semana=1;
		 List<Reporte> ls=new LinkedList<Reporte>();
		 ls.add(new Reporte("1","1",fecha1,fecha2));
		 
		 Map<String,Integer> mp=new HashMap<>();
		 mp.put("total_horas", 2);
		 
		 
		 assertTrue(ch.horasTrabajo(ls, semana)==mp);
	}

}
