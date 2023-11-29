package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.Localidad;
import com.example.demo.dto.Pais;


public interface ILocalidadService {
	public List<Localidad> listLocalidad();

	public Localidad saveLocalidad(Localidad localidad);

	public Localidad getLocalidad(Integer id);

	public void deleteLocalidad(Integer id);
	
	public List<Localidad> findLocalidadByNombre(String nombre);
	public List<Localidad> findLocalidadByPais(Pais pais);
}
