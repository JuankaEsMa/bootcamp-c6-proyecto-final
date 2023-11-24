package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.Chollo;


public interface ICholloService {
	public List<Chollo> listChollo();

	public Chollo saveChollo(Chollo chollo);

	public Chollo getChollo(Integer id);

	public void deleteChollo(Integer id);
}
