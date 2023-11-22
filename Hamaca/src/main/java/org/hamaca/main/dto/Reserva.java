package org.hamaca.main.dto;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

@Entity
public class Reserva {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="Fecha_Compra")
	private Date fechaCompra;
	@Column(name="Num_Noches")
	private int numNoches;
	@Column(name="Num_Personas")
	private int numPersonas;
	@Column(name="Nota")
	private int nota;
	@JoinColumn(name="id_Chollo")
	private Chollo id_Chollo;
	private Cliente cliente;
	public Reserva() {
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getFechaCompra() {
		return fechaCompra;
	}
	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}
	public int getNumNoches() {
		return numNoches;
	}
	public void setNumNoches(int numNoches) {
		this.numNoches = numNoches;
	}
	public int getNumPersonas() {
		return numPersonas;
	}
	public void setNumPersonas(int numPersonas) {
		this.numPersonas = numPersonas;
	}
	public int getNota() {
		return nota;
	}
	public void setNota(int nota) {
		this.nota = nota;
	}
	public Chollo getId_Chollo() {
		return id_Chollo;
	}
	public void setId_Chollo(Chollo id_Chollo) {
		this.id_Chollo = id_Chollo;
	}
	
}
