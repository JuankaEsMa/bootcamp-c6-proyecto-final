package com.example.demo.dao;

import org.springframework.data.domain.Pageable; 
import java.sql.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.dto.Chollo;
import com.example.demo.dto.Localidad;
import com.example.demo.dto.Tematica;

public interface ICholloDAO extends JpaRepository<Chollo,Integer>{
	Page<Chollo> findByLocalidad(Localidad localidad, Pageable pageable);
	Page<Chollo> findByTematicas(Tematica tematica, Pageable pageable);
	Page<Chollo> findAllByFechaCaducidadBetween(Date start, Date end, Pageable pageable);
	Page<Chollo> findAllByPrecioPersonaBetween(Double min, Double max, Pageable pageable);
	Page<Chollo> findAll(Pageable pageable);
}

