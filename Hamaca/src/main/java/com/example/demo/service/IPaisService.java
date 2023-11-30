package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.Chollo;
import com.example.demo.dto.Localidad;
import com.example.demo.dto.Pais;

public interface IPaisService {

	public List <Pais> listPais();

	public Pais savePais(Pais pais);

	public Pais getPais(Integer id);

	public void deletePais(Integer id);
	
	public Pais findPaisByNombre(String nombre);
}
