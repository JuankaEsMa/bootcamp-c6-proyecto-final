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
	Nombre VARCHAR(45),
	Apellidos VARCHAR(250),
	Telefono VARCHAR(9),
	DNI VARCHAR(9) UNIQUE,
	Direccion VARCHAR(60),
	Email VARCHAR(50) UNIQUE,
	Fecha_Nacimiento DATE,
	Is_Deleted BOOLEAN
);
CREATE TABLE IF NOT EXISTS empleado ( 
	Id INT auto_increment,
    Id_Usuario INT,
    Cuenta_Bancaria VARCHAR(25),
    Roles varchar(50) DEFAULT NULL,
    PRIMARY KEY(Id),
    FOREIGN KEY(Id_Usuario)
    REFERENCES usuario (Id)
    ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE IF NOT EXISTS cliente ( 
	Id INT auto_increment,
    Id_Usuario INT,
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
    Is_Deleted BOOLEAN,
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

/* Inserts Usuarios */

INSERT INTO usuario (Nombre, Apellidos, Telefono, DNI, Direccion, Email, Fecha_Nacimiento, Is_Deleted)
VALUES 
('Juan', 'González Pérez', '123456789', 'ABC12345X', 'Calle Principal 123', 'juan@example.com', '1990-05-15', 0),
('María', 'López Martínez', '987654321', 'DEF54321Y', 'Avenida Central 456', 'maria@example.com', '1985-09-20', 0),
('Carlos', 'Ruiz García', '111222333', 'GHI67890Z', 'Plaza Mayor 789', 'carlos@example.com', '1998-12-10', 0),
('Laura', 'Fernández Rodríguez', '333444555', 'JKL43210W', 'Calle Secundaria 567', 'laura@example.com', '1982-03-25', 0),
('Pedro', 'Sánchez Gómez', '666777888', 'MNO09876V', 'Paseo Peatonal 890', 'pedro@example.com', '1995-07-08', 0);

/* Inserts Clientes */

INSERT INTO cliente (Id_Usuario, Tarjeta)
VALUES 
(1, '1234-5678-9012-3456'),
(2, '9876-5432-1098-7654'),
(3, '5678-1234-9012-3456'),
(4, '4321-8765-1098-7654'),
(5, '1111-2222-3333-4444');

/* Insert Empleados */

INSERT INTO empleado (Id_Usuario ,Cuenta_Bancaria)
VALUES 
(1, 'ES12-3456-7890-1234-5678'),
(2, 'ES98-7654-3210-9876-5432'),
(3, 'ES56-7890-1234-5678-9012'),
(4, 'ES43-2109-8765-4321-0987'),
(5, 'ES11-2233-4455-6677-8899');

/* Insert Paises */

INSERT INTO pais (Nombre)
VALUES 
('España'),
('Francia'),
('Alemania'),
('Italia'),
('Portugal');

/* Insert Localidad */

INSERT INTO localidad (Nombre, Id_Pais)
VALUES 
('Madrid', 1),
('París', 2),
('Berlín', 3),
('Roma', 4),
('Lisboa', 5),
('Barcelona', 1);

/* Insert Chollos  */

INSERT INTO chollo (Titulo, Imagen, Precio_Persona, Cantidad_Personas, Descripcion, Fecha_Caducidad, Is_Deleted, Id_Empleado, Id_Localidad)
VALUES 
('Oferta en Madrid', 'imagen1.jpg', 25.5, 3, '¡Descuento especial!', '2023-12-31', 0, 1, 1),
('Paquete turístico en París', 'imagen2.jpg', 50.75, 2, 'Visita la Ciudad Luz', '2024-06-30', 0, 2, 2),
('Escapada a Berlín', 'imagen3.jpg', 40.0, 4, 'Recorre la capital alemana', '2023-11-30', 0, 3, 3),
('Tour por Roma', 'imagen4.jpg', 35.25, 3, 'Descubre la historia romana', '2024-04-15', 0, 4, 4),
('Estancia en Lisboa', 'imagen5.jpg', 60.9, 2, 'Disfruta del encanto portugués', '2023-10-20', 0, 5, 5),
('Estancia en Barcelona', 'imagen6.jpg', 60.9, 2, 'Disfruta del encanto catalan', '2023-10-20', 0, 5, 6);

/* Insert Reserva */

INSERT INTO reserva (Id_Cliente, Id_Chollo, Fecha_Compra, Num_Noches, Num_Personas, nota)
VALUES 
(1, 1, '2023-11-01', 4, 2, 8),
(2, 3, '2023-10-15', 3, 1, 9),
(3, 5, '2023-12-20', 2, 2, 7),
(4, 2, '2024-01-05', 5, 2, 10),
(5, 4, '2023-09-10', 2, 3, 9);

/* Insert Tematicas */

INSERT INTO tematica (Nombre)
VALUES 
('Aventura'),
('Playa'),
('Cultura'),
('Gastronomía'),
('Naturaleza');

/* insert Favoritos */

INSERT INTO favorito (Id_Chollo, Id_Cliente)
VALUES 
(2, 1),
(4, 2),
(1, 3),
(3, 4),
(5, 5);

/* insert pertenece */

INSERT INTO pertenece (Id_Chollo, Id_Tematica)
VALUES 
(1, 3),
(2, 1),
(3, 5),
(4, 4),
(5, 2);