package com.calc3.repository;

 

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.calc3.entity.Reporte;


 
public interface ReporteRepository extends JpaRepository<Reporte,Long> {
	
	List<Reporte> findById(long id);
	List<Reporte> findByIdtecnico(String idt);

//	  List<Libro> findByTitulo(String titulo);
//	  List<Libro> findByAutor(String autor);
//	  List<Libro> findByAutorAndTitulo(String autor, String titulo);
//	  @Query ("select l from Libro l where l.precio>20")
//	  List<Libro> findByCaros();
//
//	//  Consultar por nombre de autor
//	  @Query("select u from Libro u where u.autor = ?1")
//	  List<Libro> findByName(String autor);
//	  
//	// Consultar por 2 campos
//	  @Query("select u from Libro u where u.autor = ?1 and u.titulo=?2")
//	  List<Libro> findByNameytitulo(String autor,String titulo);
//	  
//	  //Con query nativo
//	  @Query(value = "SELECT * FROM libros WHERE autor = ?1", nativeQuery = true)
//	  List<Libro> findByNameNativo(String autor);
	 
}
