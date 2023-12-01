package com.example.demo.service;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Pageable; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ICholloDAO;
import com.example.demo.dto.Chollo;
import com.example.demo.dto.Localidad;
import com.example.demo.dto.Pais;
import com.example.demo.dto.Tematica;

@Service
public class CholloService implements ICholloService{

	@Autowired
	ICholloDAO cholloDAO;
	
	@Override
	public List<Chollo> listChollo() {
		// TODO Auto-generated method stub
		return cholloDAO.findAll();
	}

	@Override
	public Chollo saveChollo(Chollo chollo) {
		// TODO Auto-generated method stub
		return cholloDAO.save(chollo);
	}

	@Override
	public Chollo getChollo(Integer id) {
		// TODO Auto-generated method stub
		return cholloDAO.findById(id).get();
	}

	@Override
	public void deleteChollo(Integer id) {
		// TODO Auto-generated method stub
		cholloDAO.deleteById(id);
	}

	@Override
	public List<Chollo> findCholloByLocalidad(Localidad localidad) {
		// TODO Auto-generated method stub
		return cholloDAO.findByLocalidad(localidad);
	}
	
	@Override
	public List<Chollo> findCholloByTematica(Tematica tematica) {
		return cholloDAO.findByTematicas(tematica );
	}

	@Override
	public List<Chollo> findCholloByDates(Date start, Date fin) {
		// TODO Auto-generated method stub
		return cholloDAO.findAllByFechaCaducidadBetween(start, fin );
	}

	@Override
	public List<Chollo> findCholloByPrecios(Double min, Double max) {
		// TODO Auto-generated method stub
		return cholloDAO.findAllByPrecioPersonaBetween(min, max);
	}

	@Override
	public Page<Chollo> getPaginatedChollos(Pageable pageable) {
		// TODO Auto-generated method stub
		return cholloDAO.findAll(pageable);
	}

}
