package org.hamaca.main.service;

import java.util.List;

import org.hamaca.main.dao.ITematicaDAO;
import org.hamaca.main.dto.Tematica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
