package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.Cliente;

public interface IClienteService {
	public List<Cliente> listCliente();
	public Cliente saveCliente(Cliente empleado);
	public Cliente getCliente (Integer id);
	public void deleteCliente (Integer id);
}
