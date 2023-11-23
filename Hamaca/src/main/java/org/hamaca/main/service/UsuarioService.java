package org.hamaca.main.service;

import java.util.List;

import org.hamaca.main.dao.IUsuarioDAO;
import org.hamaca.main.dto.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements IUsuarioService{

	@Autowired
	IUsuarioDAO usuarioDAO;
	
	@Override
	public List<Usuario> listUsuario() {
		// TODO Auto-generated method stub
		return usuarioDAO.findAll();
	}

	@Override
	public Usuario saveUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		return usuarioDAO.save(usuario);
	}

	@Override
	public Usuario getUsuario(Integer id) {
		// TODO Auto-generated method stub
		return usuarioDAO.findById(null).get();
	}

	@Override
	public void deleteUsuario(Integer id) {
		// TODO Auto-generated method stub
		usuarioDAO.deleteById(id);
	}

}
