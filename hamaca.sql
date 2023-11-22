
USE railway;

CREATE TABLE IF NOT EXISTS Usuario (
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

CREATE TABLE IF NOT EXISTS Cliente ( 
	Id INT,
    Tarjeta VARCHAR(25),
    PRIMARY KEY(Id),
    FOREIGN KEY(Id)
    REFERENCES Usuario (Id)
    ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS Empleado ( 
	Id INT,
    Cuenta_Bancaria VARCHAR(25),
    PRIMARY KEY(Id),
    FOREIGN KEY(Id)
    REFERENCES Usuario (Id)
    ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS Pais ( 
	Id INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR (50) UNIQUE
);

CREATE TABLE IF NOT EXISTS Localidad (
	Id INT AUTO_INCREMENT,
    Nombre VARCHAR (50),
    Id_Pais INT,
    PRIMARY KEY(Id),
    FOREIGN KEY (Id_Pais)
    REFERENCES Pais (Id)
    ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS Chollo (
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
        REFERENCES Empleado (Id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (Id_Localidad)
        REFERENCES Localidad (Id)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS Reserva (
	Id INT AUTO_INCREMENT PRIMARY KEY,
    Id_Cliente INT,
    Id_Chollo INT,
    Fecha_Compra date,
	Num_Noches INT,
	Num_Personas INT,
	nota INT check (nota between 0 and 10),
    FOREIGN KEY (Id_Cliente)
        REFERENCES Cliente (Id),
    FOREIGN KEY (Id_Chollo)
        REFERENCES Chollo (Id)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS Tematica (
	Id INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(50) UNIQUE
);

CREATE TABLE Favorito (
	Id INT AUTO_INCREMENT PRIMARY KEY,
    Id_Chollo INT,
    Id_Cliente INT,
    FOREIGN KEY (Id_Chollo)
        REFERENCES Chollo (id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (Id_Cliente)
        REFERENCES Cliente (id)
        ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Pertenece (
	Id INT AUTO_INCREMENT PRIMARY KEY,
    Id_Chollo INT,
    Id_Tematica INT,
    FOREIGN KEY (Id_Chollo)
        REFERENCES Chollo (Id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (Id_Tematica)
        REFERENCES Tematica (Id)
        ON DELETE CASCADE ON UPDATE CASCADE
);

