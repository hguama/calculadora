package com.calc3.utils;

 

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.calc3.entity.Reporte;


public class CalcularHora {

	//Calcula las horas trabajadas por semana del año
	public static  Map<String,Integer> horasTrabajo(List<Reporte> lr,int semana_tecnico) { 

		//Listas para guardar el tipo de hora trabajada
		List<LocalDateTime> lh_normales =new ArrayList<LocalDateTime>();
		List<LocalDateTime> lh_nocturnas =new ArrayList<LocalDateTime>();
		List<LocalDateTime> lh_dominicales =new ArrayList<LocalDateTime>();
		List<LocalDateTime> lh_normales_extra =new ArrayList<LocalDateTime>();
		List<LocalDateTime> lh_nocturnas_extra =new ArrayList<LocalDateTime>();
		List<LocalDateTime> lh_dominicales_extra =new ArrayList<LocalDateTime>();

		//para calcular la semana del año
		WeekFields semana = WeekFields.of(Locale.getDefault());


		//Ciclo donde se recorre el rango de fechas y se calcula los días y horas trabajadas
		for (Reporte rep : lr) {

			long horas=Duration.between(rep.getFechainicio(),rep.getFechafin()).toHours();

			System.out.println("----");			 
			System.out.println(rep.getFechainicio());		
			System.out.println("horas: "+horas);
			System.out.println(rep.getFechafin());
			System.out.println("----");

			//Se calcula el tipo de horas trabajadas
			for (int i = 0; i < Duration.between(rep.getFechainicio(),rep.getFechafin()).toHours(); i++) {

				LocalDateTime dtemp=rep.getFechainicio().plusHours(i);

				int nsemana = dtemp.get(semana.weekOfWeekBasedYear());			 

				//Busca la semana enviada desde el FrontEnd. 0 es para retornar todos los días
				if(nsemana==semana_tecnico || semana_tecnico==0) {

					System.out.print("semana: "+nsemana+"-");
					
//					valida horas normales de lunes - sábado
					if(dtemp.getDayOfWeek().getValue()>=1 && dtemp.getDayOfWeek().getValue()<=6) {


						//valida horas normales de 7am - 8pm
						if(dtemp.getHour()>=7 && dtemp.getHour()<20)					 {

							if(lh_normales.size()<48)//valida horas normales semanales <=48
							{
								System.out.print(" normal: "+dtemp);
								System.out.print(" dia: "+dtemp.getDayOfWeek().getValue());
								lh_normales.add(dtemp);
								System.out.println(" normal-conteo : "+lh_normales.size());

							}else { //registra horas normales semanales extra >48

								System.out.print(" normal extra: "+dtemp);
								System.out.print(" dia: "+dtemp.getDayOfWeek().getValue());
								lh_normales_extra.add(dtemp);
								System.out.println(" normal-extra-conteo : "+lh_normales_extra.size());

							}

						}

						//valida horas normales nocturnas 8pm-7am
						else if(dtemp.getHour()>=20 || dtemp.getHour()<=7) {

							if(lh_nocturnas.size()<48) {//valida horas normales nocturnas semanales <=48

								System.out.print(" normal-noct: "+dtemp);
								System.out.print(" dia: "+dtemp.getDayOfWeek().getValue());
								lh_nocturnas.add(dtemp); 
								System.out.println(" nocturna-conteo: "+lh_nocturnas.size());

							}else {//registra horas normales nocturnas extra >=48

								System.out.print(" normal-noct-extra: "+dtemp);
								System.out.print(" dia: "+dtemp.getDayOfWeek().getValue());
								lh_nocturnas_extra.add(dtemp);
								System.out.println(" nocturna-extra-conteo: "+lh_nocturnas_extra.size());

							}


						}


					}
					else {

						//valida horas dominicales
						if(dtemp.getDayOfWeek().getValue()==7) {

							if(lh_dominicales.size()<48) { //valida horas semanales dominicales normales <48

								System.out.print("dominical: "+dtemp);
								System.out.print(" dia: "+dtemp.getDayOfWeek().getValue());
								lh_dominicales.add(dtemp);
								System.out.println(" dominicales-conteo: "+lh_dominicales.size());

							}
							else { //registra horas dominicales extra >=48

								System.out.print("dominical-extra: "+dtemp);
								System.out.print(" dia: "+dtemp.getDayOfWeek().getValue());
								lh_dominicales_extra.add(dtemp); 
								System.out.println(" dominicales-extra-conteo: "+lh_dominicales_extra.size());						 

							}


						}
					}



				} 


			}


		}


		//Se guarda en un map los registros de las horas trabajadas	 
		Map<String,Integer> mh=new LinkedHashMap<String,Integer>();
		mh.put("horas_normales", lh_normales.size());
		mh.put("horas_normales_extra", lh_normales_extra.size());
		mh.put("horas_nocturnas", lh_nocturnas.size());
		mh.put("horas_nocturnas_extra", lh_nocturnas_extra.size());
		mh.put("horas_dominicales", lh_dominicales.size());
		mh.put("horas_dominicales_extra", lh_dominicales_extra.size());
		mh.put("total_horas", 0);


		int total=lh_normales.size()+lh_normales_extra.size()+lh_nocturnas.size()+lh_nocturnas_extra.size()+lh_dominicales.size()+lh_dominicales_extra.size();
		mh.put("total_horas", total);
		System.out.println();
		System.out.println(mh);

		return mh;		

	}

}
