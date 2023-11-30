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
	public Page<Chollo> findCholloByLocalidad(Localidad localidad, Pageable pageable) {
		// TODO Auto-generated method stub
		return cholloDAO.findByLocalidad(localidad, pageable);
	}
	
	@Override
	public Page<Chollo> findCholloByTematica(Tematica tematica, Pageable pageable) {
		return cholloDAO.findByTematicas(tematica, pageable);
	}

	@Override
	public Page<Chollo> findCholloByDates(Date start, Date fin, Pageable pageable) {
		// TODO Auto-generated method stub
		return cholloDAO.findAllByFechaCaducidadBetween(start, fin, pageable);
	}

	@Override
	public Page<Chollo> findCholloByPrecios(Double min, Double max, Pageable pageable) {
		// TODO Auto-generated method stub
		return cholloDAO.findAllByPrecioPersonaBetween(min, max, pageable);
	}

	@Override
	public Page<Chollo> getPaginatedChollos(PageRequest pageable) {
		// TODO Auto-generated method stub
		return cholloDAO.findAll(pageable);
	}

}
