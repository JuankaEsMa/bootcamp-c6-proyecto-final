package com.example.demo.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Id")
	private int id;
	@OneToOne
	@JoinColumn(name = "Id_Usuario")
	private Usuario usuario;
	@Column(name="Tarjeta")
	private String tarjeta;
	@ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "Favorito",
        joinColumns = { @JoinColumn(name = "Id_Cliente") },
        inverseJoinColumns = { @JoinColumn(name = "Id_Chollo")}
    )
    @JsonIgnoreProperties("clientesFavoritos")
    private List<Chollo> chollosFavoritos;
	public Cliente() {
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public String getTarjeta() {
		return tarjeta;
	}
	public void setTarjeta(String tarjeta) {
		this.tarjeta = tarjeta;
	}
	public List<Chollo> getChollosFavoritos() {
		return chollosFavoritos;
	}
	public void setChollosFavoritos(List<Chollo> chollosFavoritos) {
		this.chollosFavoritos = chollosFavoritos;
	}

	
}
