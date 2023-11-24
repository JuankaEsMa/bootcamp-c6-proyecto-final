package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ILocalidadDAO;
import com.example.demo.dto.Localidad;

@Service
public class LocalidadService implements ILocalidadService {

	@Autowired
	ILocalidadDAO localidadDAO;

	@Override
	public List<Localidad> listLocalidad() {
		// TODO Auto-generated method stub
		return localidadDAO.findAll();
	}

	@Override
	public Localidad saveLocalidad(Localidad localidad) {
		// TODO Auto-generated method stub
		return localidadDAO.save(localidad);
	}

	@Override
	public Localidad getLocalidad(Integer id) {
		// TODO Auto-generated method stub
		return localidadDAO.findById(id).get();
	}

	@Override
	public void deleteLocalidad(Integer id) {
		// TODO Auto-generated method stub
		localidadDAO.deleteById(id);
	}

}
