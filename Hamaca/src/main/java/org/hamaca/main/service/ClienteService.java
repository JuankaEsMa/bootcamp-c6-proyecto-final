package org.hamaca.main.service;

import java.util.List;

import org.hamaca.main.dao.IClienteDAO;
import org.hamaca.main.dto.Cliente;
import org.hamaca.main.dto.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService implements IClienteService{

	@Autowired
	IClienteDAO iCliente;
	
	@Override
	public List<Cliente> listCliente(){
		return iCliente.findAll();
	}
	
	@Override
	public Cliente saveCliente(Cliente cliente) {
		return iCliente.save(cliente);
	}
	
	@Override
	public Cliente getCliente(Usuario usuario) {
		return iCliente.findById(usuario).get();
	}
	
	@Override
	public void deleteCliente(Usuario usuario) {
		iCliente.deleteById(usuario);

	}
}
