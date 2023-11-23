package org.hamaca.main.dto;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class Chollo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String titulo;
	private String imagen;
	private double precioPersona;
	private int cantidadPersonas;
	private String descripcion;
	private Date fechaCaducidad;
	@JoinColumn(name="Id_Localidad")
	private Localidad idLocalidad;
	@JoinColumn(name="Id_Empleado")
	private Localidad idEmpleado;
	
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "Pertenece",
        joinColumns = { @JoinColumn(name = "Id_Chollo") },
        inverseJoinColumns = { @JoinColumn(name = "Id_Tematica")}
    )
    @JsonIgnoreProperties("Id_Chollo")
	private List<Tematica> tematicas;
    
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "Favorito",
        joinColumns = { @JoinColumn(name = "Id_Chollo") },
        inverseJoinColumns = { @JoinColumn(name = "Id_Cliente")}
    )
    @JsonIgnoreProperties("Id_Chollo")
	private List<Cliente> favoritos;
    
	
	public Chollo() {
		
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public double getPrecioPersona() {
		return precioPersona;
	}
	public void setPrecioPersona(double precioPersona) {
		this.precioPersona = precioPersona;
	}
	public int getCantidadPersonas() {
		return cantidadPersonas;
	}
	public void setCantidadPersonas(int cantidadPersonas) {
		this.cantidadPersonas = cantidadPersonas;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Date getFechaCaducidad() {
		return fechaCaducidad;
	}
	public void setFechaCaducidad(Date fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}
	public Localidad getIdLocalidad() {
		return idLocalidad;
	}
	public void setIdLocalidad(Localidad idLocalidad) {
		this.idLocalidad = idLocalidad;
	}
	
}
