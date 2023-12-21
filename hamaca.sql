create database if not exists hamaca2;

USE hamaca2;

drop table if exists pertenece;
drop table if exists reserva;
drop table if exists favorito;
drop table if exists chollo;
drop table if exists localidad;
drop table if exists pais;
drop table if exists tematica;
drop table if exists cliente;
drop table if exists empleado;
drop table if exists usuario;


CREATE TABLE IF NOT EXISTS usuario (
	Id INT AUTO_INCREMENT PRIMARY KEY,
	Nombre VARCHAR(45) not null,
    Roles varchar (50),
    Password VARCHAR(200) not null,
	Apellidos VARCHAR(250),
	Telefono VARCHAR(9),
	DNI VARCHAR(9) UNIQUE,
	Direccion VARCHAR(60),
	Email VARCHAR(50) UNIQUE not null,
	Fecha_Nacimiento DATE,
	Is_Deleted BOOLEAN
);
CREATE TABLE IF NOT EXISTS empleado ( 
	Id INT auto_increment,
    Id_Usuario INT unique,
    Cuenta_Bancaria VARCHAR(25),
    PRIMARY KEY(Id),
    FOREIGN KEY(Id_Usuario)
    REFERENCES usuario (Id)
    ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE IF NOT EXISTS cliente ( 
	Id INT auto_increment,
    Id_Usuario INT unique,
    Tarjeta VARCHAR(25),
    PRIMARY KEY(Id),
    FOREIGN KEY(Id_Usuario)
    REFERENCES usuario (Id)
    ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE IF NOT EXISTS pais ( 
	Id INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR (50) UNIQUE
);
CREATE TABLE IF NOT EXISTS localidad (
	Id INT AUTO_INCREMENT,
    Nombre VARCHAR (50),
    Id_Pais INT,
    PRIMARY KEY(Id),
    FOREIGN KEY (Id_Pais)
    REFERENCES pais (Id)
    ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE IF NOT EXISTS chollo (
    Id INT AUTO_INCREMENT,
    Titulo VARCHAR(125),
    Imagen VARCHAR(250),
    Precio_Persona FLOAT,
    Cantidad_Personas INT,
    Descripcion VARCHAR(250),
    Fecha_Caducidad DATE,
    Is_Deleted BOOLEAN default false,
    Id_Empleado INT,
    Id_Localidad INT,
    PRIMARY KEY (id),
    FOREIGN KEY (Id_Empleado)
        REFERENCES empleado (Id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (Id_Localidad)
        REFERENCES localidad (Id)
        ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE IF NOT EXISTS reserva (
	Id INT AUTO_INCREMENT PRIMARY KEY,
    Id_Cliente INT,
    Id_Chollo INT,
    Fecha_Compra date,
	Num_Noches INT,
	Num_Personas INT,
	nota INT check (nota between 0 and 10),
    FOREIGN KEY (Id_Cliente)
        REFERENCES cliente (Id),
    FOREIGN KEY (Id_Chollo)
        REFERENCES chollo (Id)
        ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE IF NOT EXISTS tematica (
	Id INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(50) UNIQUE
);
CREATE TABLE favorito (
	Id INT AUTO_INCREMENT PRIMARY KEY,
    Id_Chollo INT,
    Id_Cliente INT,
    FOREIGN KEY (Id_Chollo)
        REFERENCES chollo (id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (Id_Cliente)
        REFERENCES cliente (id)
        ON UPDATE CASCADE ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS pertenece (
	Id INT AUTO_INCREMENT PRIMARY KEY,
    Id_Chollo INT,
    Id_Tematica INT,
    FOREIGN KEY (Id_Chollo)
        REFERENCES chollo (Id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (Id_Tematica)
        REFERENCES tematica (Id)
        ON DELETE CASCADE ON UPDATE CASCADE
);