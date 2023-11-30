package com.example.demo.service;

import org.springframework.data.domain.Pageable; 

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.example.demo.dto.Chollo;
import com.example.demo.dto.Localidad;
import com.example.demo.dto.Pais;
import com.example.demo.dto.Tematica;


public interface ICholloService {
	public List<Chollo> listChollo();

	public Chollo saveChollo(Chollo chollo);

	public Chollo getChollo(Integer id);

	public void deleteChollo(Integer id);
	
	public Page<Chollo> findCholloByLocalidad(Localidad localidad, Pageable pageable);
	public Page<Chollo> findCholloByTematica(Tematica tematica, Pageable pageable);
	public Page<Chollo> findCholloByDates (Date inicio, Date fin, Pageable pageable);
	public Page<Chollo> findCholloByPrecios (Double min, Double max, Pageable pageable);

	Page<Chollo> getPaginatedChollos(PageRequest pageable);

}
