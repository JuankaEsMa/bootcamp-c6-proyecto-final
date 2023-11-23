package org.hamaca.main.service;

import java.util.List;

import org.hamaca.main.dto.Usuario;

public interface IUsuarioService {
	public List<Usuario> listUsuario();

	public Usuario saveUsuario(Usuario usuario);

	public Usuario getUsuario(Integer id);

	public void deleteUsuario(Integer id);
}
