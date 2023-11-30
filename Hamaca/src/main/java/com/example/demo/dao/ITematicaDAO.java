package com.example.demo.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.dto.Tematica;

public interface ITematicaDAO extends JpaRepository<Tematica, Integer>{
	public List<Tematica> findAllByNombre (String nombre);
}
