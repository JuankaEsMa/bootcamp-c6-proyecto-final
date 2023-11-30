package com.example.demo.dto;

import java.sql.Date;

public record UserRecord(Integer id, String nombre, String apellidos, String telefono, String dni,  String direccion, String email, Date fechaNacimiento, String password){}