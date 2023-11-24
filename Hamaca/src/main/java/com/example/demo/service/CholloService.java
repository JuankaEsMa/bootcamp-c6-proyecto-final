package com.example.demo.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ICholloDAO;
import com.example.demo.dto.Chollo;

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

}
