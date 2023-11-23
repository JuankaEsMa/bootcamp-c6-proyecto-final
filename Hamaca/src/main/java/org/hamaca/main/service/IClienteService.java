package org.hamaca.main.service;

import java.util.List;

import org.hamaca.main.dto.Cliente;
import org.hamaca.main.dto.Usuario;

public interface IClienteService {
	public List<Cliente> listCliente();
	public Cliente saveCliente(Cliente cliente);
	public Cliente getCliente(Usuario usuario);
	public void deleteCliente(Usuario usuario);
}
