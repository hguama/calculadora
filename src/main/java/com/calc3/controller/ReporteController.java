package com.calc3.controller;

 

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.calc3.entity.Reporte;
import com.calc3.repository.ReporteRepository;
import com.calc3.utils.CalcularHora;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ReporteController {

	@Autowired
	private  ReporteRepository reporter;	


	//---CONSULTAR TECNICO POR SEMANA. api/idtecnico/semana---	
	@RequestMapping(value="/{idtecnico}/{semana}")
	public ResponseEntity<?>  consulta(@PathVariable("idtecnico") String idtecnico,@PathVariable("semana") String semana) {


		Map<String,Integer> mpsalida;
		mpsalida=new LinkedHashMap<String,Integer>();
		int sem;

		List<Reporte> lr3=reporter.findByIdtecnico(idtecnico); //Busco el Técnico en la db.

		//		valida se el resultado fué vacio. Cuando no encuentra el tecnico o no se trabajó en esa semana
		if(lr3.isEmpty()) {

			mpsalida.put("total_horas", 0);
			mpsalida.put("horas_normales", 0);
			mpsalida.put("horas_normales_extra", 0);
			mpsalida.put("horas_nocturnas", 0);
			mpsalida.put("horas_nocturnas_extra", 0);
			mpsalida.put("horas_dominicales", 0);
			mpsalida.put("horas_dominicales_extra", 0);

			return ResponseEntity.status(HttpStatus.OK).body(mpsalida);

		}

		//Valido que la semana enviada sea un número
		try {			

				sem=Integer.parseInt(semana);


		}catch(Exception e) {

			mpsalida=new HashMap<String,Integer>();
			mpsalida.put("Error-url-semana-incorrecta", 0);			
			return ResponseEntity.status(HttpStatus.OK).body(mpsalida); //Retorno la respuesta al FrontEnd			

		}

		//valido que semana sea >=0
		if(Integer.parseInt(semana)<=530000 && Integer.parseInt(semana)>=0) {

			List<Reporte> lr2=reporter.findByIdtecnico(idtecnico); //Busco por idtecnico en la db.	    	
			mpsalida= CalcularHora.horasTrabajo(lr2,Integer.parseInt(semana)); //Calculo las horas trabajadas
			mpsalida.put("id_tecnico",Integer.parseInt(idtecnico));
			mpsalida.put("semana",Integer.parseInt(semana)); 

			return ResponseEntity.status(HttpStatus.OK).body(mpsalida); //retorno la respuesta al FrontEnd

		}

		else {
			mpsalida=new HashMap<String,Integer>();
			mpsalida.put("Error-semana-incorrecta", 0);
			return ResponseEntity.status(HttpStatus.OK).body(mpsalida); //devuelbo respuesta al FrontEnd que la semana ha sido incorrecta
		}

	}		


	//----REGISTRA EL REPORTE DEL SERVICIO DEL TECNICO----
	@RequestMapping("reportar")
	public ResponseEntity<?> reporte(@RequestBody Map<String,String> mpentrada) {

		Map<String,Integer> mr=new HashMap<>();


		//valido que los datos enviados sean correctos
		try { 

			String idtecnico=mpentrada.get("idtecnico").toString();
			String idservicio=mpentrada.get("idservicio").toString();	
			LocalDateTime fechainicio  = LocalDateTime.parse(mpentrada.get("fechainicio").toString(),DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
			LocalDateTime fechafin  = LocalDateTime.parse(mpentrada.get("fechafin").toString(),DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));

			if(idtecnico=="" || idservicio=="" ||idtecnico.contains(" ") || idservicio.contains(" ")) {
				mr.put("status",0);
				return ResponseEntity.status(HttpStatus.OK).body(mr);

			}else{

				return ResponseEntity.status(HttpStatus.CREATED).body(reporter.save(new Reporte(idtecnico,idservicio,fechainicio,fechafin)));
			}


		} catch (Exception e) {

			mr.put("status",0);
			return ResponseEntity.status(HttpStatus.OK).body(mr);

		}
	}


	//test
	@RequestMapping("get")
	public String test(@RequestBody Map<String,String> mpentrada) {
		
		return "hola desde calculadora";
	}	


}
