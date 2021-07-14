package com.calc3.entity;


import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="reportes")
public class Reporte implements Serializable {

	private static final long serialVersionUID = 3836732272688955164L;


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long idreporte;
	private String idtecnico;
	private String idservicio;	
	private LocalDateTime fechainicio;
	private LocalDateTime fechafin;


	public Reporte(String idtecnico, String idservicio, LocalDateTime fechainicio, LocalDateTime fechafin) {
		super();
		this.idtecnico = idtecnico;
		this.idservicio = idservicio;
		this.fechainicio = fechainicio;
		this.fechafin = fechafin;
	}	

	public Reporte() {
		super();
	}


	public String getIdtecnico() {
		return idtecnico;
	}
	public void setIdtecnico(String idtecnico) {
		this.idtecnico = idtecnico;
	}
	public String getIdservicio() {
		return idservicio;
	}
	public void setIdservicio(String idservicio) {
		this.idservicio = idservicio;
	}
	public LocalDateTime getFechainicio() {
		return fechainicio;
	}
	public void setFechainicio(LocalDateTime fechainicio) {
		this.fechainicio = fechainicio;
	}
	public LocalDateTime getFechafin() {
		return fechafin;
	}
	public void setFechafin(LocalDateTime fechafin) {
		this.fechafin = fechafin;
	}


	@Override
	public String toString() {
		return "Reporte [idreporte=" + idreporte + ", idtecnico=" + idtecnico + ", idservicio=" + idservicio
				+ ", fechainicio=" + fechainicio + ", fechafin=" + fechafin + "]";
	}


}