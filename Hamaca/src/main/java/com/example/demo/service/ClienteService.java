package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.IClienteDAO;
import com.example.demo.dto.Cliente;

@Service
public class ClienteService implements IClienteService{

	@Autowired
	IClienteDAO dao;
	
	@Override
	public List<Cliente> listCliente() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public Cliente saveCliente(Cliente empleado) {
		// TODO Auto-generated method stub
		return dao.save(empleado);
	}

	@Override
	public Cliente getCliente(Integer id) {
		// TODO Auto-generated method stub
		return dao.findById(id).get();
	}

	@Override
	public void deleteCliente(Integer id) {
		// TODO Auto-generated method stub
		dao.deleteById(id);
	}

}
