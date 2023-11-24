package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.IUsuarioDAO;
import com.example.demo.dto.Usuario;

@Service
public class UsuarioService implements IUsuarioService{

	@Autowired
	IUsuarioDAO dao;
	
	@Override
	public List<Usuario> listUsuario() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public Usuario saveUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		return dao.save(usuario);
	}

	@Override
	public Usuario getUsuario(Integer id) {
		// TODO Auto-generated method stub
		return dao.findById(id).get();
	}

	@Override
	public void deleteUsuario(Integer id) {
		// TODO Auto-generated method stub
		dao.deleteById(id);
	}

}
