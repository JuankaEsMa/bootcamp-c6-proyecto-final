package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ITematicaDAO;
import com.example.demo.dto.Tematica;

@Service
public class TematicaService implements ITematicaService{

	@Autowired
	ITematicaDAO tematicaDAO;
	
	@Override
	public List<Tematica> listTematica() {
		// TODO Auto-generated method stub
		return tematicaDAO.findAll();
	}

	@Override
	public Tematica saveTematica(Tematica tematica) {
		// TODO Auto-generated method stub
		return tematicaDAO.save(tematica);
	}

	@Override
	public Tematica getTematica(Integer id) {
		// TODO Auto-generated method stub
		return tematicaDAO.findById(id).get();
	}

	@Override
	public void deleteTematica(Integer id) {
		// TODO Auto-generated method stub
		tematicaDAO.deleteById(id);
	}

}
