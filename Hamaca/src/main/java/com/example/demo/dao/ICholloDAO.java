package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.dto.Chollo;
import com.example.demo.dto.Localidad;
import com.example.demo.dto.Tematica;

public interface ICholloDAO extends JpaRepository<Chollo,Integer>{
	List<Chollo> findByLocalidad(Localidad localidad);
	List<Chollo> findByTematicas(Tematica tematica);
}

