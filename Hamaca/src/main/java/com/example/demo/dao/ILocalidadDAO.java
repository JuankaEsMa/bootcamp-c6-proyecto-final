package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.dto.Localidad;
import com.example.demo.dto.Pais;


public interface ILocalidadDAO extends JpaRepository<Localidad,Integer>{
	List<Localidad> findByNombre(String nombre);
	List<Localidad> findByPais(Pais pais);
}
