package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.Tematica;


public interface ITematicaService {
	public List<Tematica> listTematica();
	public Tematica saveTematica(Tematica tematica);
	public Tematica getTematica(Integer id);
	public void deleteTematica(Integer id);
}
