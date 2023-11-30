package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.Usuario;

public interface IUsuarioService {
	public List<Usuario> listUsuario();
	public Usuario saveUsuario(Usuario usuario);
	public Usuario getUsuario (Integer id);
	public void deleteUsuario (Integer id);
	//public UserDetails loadUserByUsername(String email);
}
