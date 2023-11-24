package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.IEmpleadoDAO;
import com.example.demo.dto.Empleado;

@Service
public class EmpleadoService implements IEmpleadoService{

	@Autowired
	IEmpleadoDAO dao;
	@Override
	public List<Empleado> listEmpleado() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public Empleado saveEmpleado(Empleado empleado) {
		// TODO Auto-generated method stub
		return dao.save(empleado);
	}

	@Override
	public Empleado getEmpleado(Integer id) {
		// TODO Auto-generated method stub
		return dao.findById(id).get();
	}

	@Override
	public void deleteEmpleado(Integer id) {
		// TODO Auto-generated method stub
		dao.deleteById(id);
	}

}
