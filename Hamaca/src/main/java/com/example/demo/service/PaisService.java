package com.example.demo.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.IPaisDAO;
import com.example.demo.dto.Pais;

@Service
public class PaisService implements IPaisService {

	@Autowired
	IPaisDAO paisDAO;

	@Override
	public List<Pais> listPais() {
		// TODO Auto-generated method stub
		return paisDAO.findAll();
	}

	@Override
	public Pais savePais(Pais pais) {
		// TODO Auto-generated method stub
		return paisDAO.save(pais);
	}

	@Override
	public Pais getPais(Integer id) {
		// TODO Auto-generated method stub
		return paisDAO.findById(id).get();
	}

	@Override
	public void deletePais(Integer id) {
		// TODO Auto-generated method stub
		paisDAO.deleteById(id);
	}

	@Override
	public Pais getPaisByNombre(String nombre) {
		// TODO Auto-generated method stub
		return paisDAO.findByNombre(nombre);
	}
}
