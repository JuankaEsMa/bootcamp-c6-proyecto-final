package com.example.demo.service;

import java.sql.Date;
import java.util.List;

import com.example.demo.dto.Chollo;
import com.example.demo.dto.Localidad;
import com.example.demo.dto.Tematica;


public interface ICholloService {
	public List<Chollo> listChollo();

	public Chollo saveChollo(Chollo chollo);

	public Chollo getChollo(Integer id);

	public void deleteChollo(Integer id);
	
	public List<Chollo> findCholloByLocalidad(Localidad localidad);
	public List<Chollo> findCholloByTematica(Tematica tematica);
	public List<Chollo> findCholloByDates (Date inicio, Date fin);
	public List<Chollo> findCholloByPrecios (Double min, Double max);


}
