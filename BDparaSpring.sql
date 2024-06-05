CREATE TABLE entidad(
	id_entidad INTEGER NOT NULL PRIMARY KEY,
	nombre_entidad VARCHAR(40) NOT NULL
);

CREATE TABLE escolaridad(
	id_escolaridad INTEGER NOT NULL PRIMARY KEY,
	escolaridad VARCHAR(40) NOT NULL
);

CREATE TABLE persona(
	id_persona integer NOT NULL PRIMARY KEY AUTO_INCREMENT,
	nombre VARCHAR(20) NOT NULL,
	primer_apellido VARCHAR(20) NOT NULL,
	segundo_apellido VARCHAR(20),
	fecha_nacimiento DATE NOT NULL,
	id_escolaridad INTEGER NOT NULL REFERENCES escolaridad(id_escolaridad),
	id_entidad_nacimiento INTEGER NOT NULL REFERENCES ENTIDAD(id_entidad)
);
		
		
INSERT INTO entidad (id_entidad, nombre_entidad) VALUES (1, 'Aguascalientes');
INSERT INTO entidad (id_entidad, nombre_entidad) VALUES (2, 'Baja California');
INSERT INTO entidad (id_entidad, nombre_entidad) VALUES (3, 'Baja California Sur');
INSERT INTO entidad (id_entidad, nombre_entidad) VALUES (4, 'Campeche');
INSERT INTO entidad (id_entidad, nombre_entidad) VALUES (5, 'Chiapas');
INSERT INTO entidad (id_entidad, nombre_entidad) VALUES (6, 'Chihuahua');
INSERT INTO entidad (id_entidad, nombre_entidad) VALUES (7, 'Coahuila de Zaragoza');
INSERT INTO entidad (id_entidad, nombre_entidad) VALUES (8, 'Colima');
INSERT INTO entidad (id_entidad, nombre_entidad) VALUES (9, 'Ciudad de México');
INSERT INTO entidad (id_entidad, nombre_entidad) VALUES (10, 'Durango');
INSERT INTO entidad (id_entidad, nombre_entidad) VALUES (11, 'Guanajuato');
INSERT INTO entidad (id_entidad, nombre_entidad) VALUES (12, 'Guerrero');
INSERT INTO entidad (id_entidad, nombre_entidad) VALUES (13, 'Hidalgo');
INSERT INTO entidad (id_entidad, nombre_entidad) VALUES (14, 'Jalisco');
INSERT INTO entidad (id_entidad, nombre_entidad) VALUES (15, 'Estado de México');
INSERT INTO entidad (id_entidad, nombre_entidad) VALUES (16, 'Michoacán de Ocampo');
INSERT INTO entidad (id_entidad, nombre_entidad) VALUES (17, 'Morelos');
INSERT INTO entidad (id_entidad, nombre_entidad) VALUES (18, 'Nayarit');
INSERT INTO entidad (id_entidad, nombre_entidad) VALUES (19, 'Nuevo León');
INSERT INTO entidad (id_entidad, nombre_entidad) VALUES (20, 'Oaxaca');
INSERT INTO entidad (id_entidad, nombre_entidad) VALUES (21, 'Puebla');
INSERT INTO entidad (id_entidad, nombre_entidad) VALUES (22, 'Querétaro');
INSERT INTO entidad (id_entidad, nombre_entidad) VALUES (23, 'Quintana Roo');
INSERT INTO entidad (id_entidad, nombre_entidad) VALUES (24, 'San Luis Potosí');
INSERT INTO entidad (id_entidad, nombre_entidad) VALUES (25, 'Sinaloa');
INSERT INTO entidad (id_entidad, nombre_entidad) VALUES (26, 'Sonora');
INSERT INTO entidad (id_entidad, nombre_entidad) VALUES (27, 'Tabasco');
INSERT INTO entidad (id_entidad, nombre_entidad) VALUES (28, 'Tamaulipas');
INSERT INTO entidad (id_entidad, nombre_entidad) VALUES (29, 'Tlaxcala');
INSERT INTO entidad (id_entidad, nombre_entidad) VALUES (30, 'Veracruz de Ignacio de la Llave');
INSERT INTO entidad (id_entidad, nombre_entidad) VALUES (31, 'Yucatán');
INSERT INTO entidad (id_entidad, nombre_entidad) VALUES (32, 'Zacatecas');
INSERT INTO entidad (id_entidad, nombre_entidad) VALUES (33, 'Extranjero');
 
INSERT INTO escolaridad (id_escolaridad, escolaridad) VALUES (1, 'Sin estudios');
INSERT INTO escolaridad (id_escolaridad, escolaridad) VALUES (2, 'Primaria trunca');
INSERT INTO escolaridad (id_escolaridad, escolaridad) VALUES (3, 'Primaria terminada');
INSERT INTO escolaridad (id_escolaridad, escolaridad) VALUES (4, 'Secundaria trunca');
INSERT INTO escolaridad (id_escolaridad, escolaridad) VALUES (5, 'Secundaria terminada');
INSERT INTO escolaridad (id_escolaridad, escolaridad) VALUES (6, 'Preparatoria trunca');
INSERT INTO escolaridad (id_escolaridad, escolaridad) VALUES (7, 'Preparatoria terminada');
INSERT INTO escolaridad (id_escolaridad, escolaridad) VALUES (8, 'Universidad trunca');
INSERT INTO escolaridad (id_escolaridad, escolaridad) VALUES (9, 'Universidad terminada');
INSERT INTO escolaridad (id_escolaridad, escolaridad) VALUES (10, 'Postgrado');
					    
INSERT INTO persona (nombre, primer_apellido, segundo_apellido, fecha_nacimiento,id_entidad_nacimiento, id_escolaridad) VALUES ('Michell', 'Erdman', 'Kessler', '2002-06-20', 33, 4);
INSERT INTO persona (nombre, primer_apellido, segundo_apellido, fecha_nacimiento,id_entidad_nacimiento, id_escolaridad) VALUES ('Dave', 'Heller', NULL, '1968-05-24', 33, 5);
INSERT INTO persona (nombre, primer_apellido, segundo_apellido, fecha_nacimiento,id_entidad_nacimiento, id_escolaridad) VALUES ('Ane', 'Schaden', NULL, '1981-12-24', 33, 6);
INSERT INTO persona (nombre, primer_apellido, segundo_apellido, fecha_nacimiento,id_entidad_nacimiento, id_escolaridad) VALUES ('Dameon', 'Gislason', NULL, '1988-09-12', 33, 1);
INSERT INTO persona (nombre, primer_apellido, segundo_apellido, fecha_nacimiento,id_entidad_nacimiento, id_escolaridad) VALUES ('Colin', 'Schneider', NULL, '1979-03-21', 33, 2);
INSERT INTO persona (nombre, primer_apellido, segundo_apellido, fecha_nacimiento,id_entidad_nacimiento, id_escolaridad) VALUES ('Abigail', 'Wilker', NULL, '1970-01-09', 33, 10);
INSERT INTO persona (nombre, primer_apellido, segundo_apellido, fecha_nacimiento,id_entidad_nacimiento, id_escolaridad) VALUES ('Raul', 'Esquivel', 'Zaragoza', '2015-12-01', 2, 3);
INSERT INTO persona (nombre, primer_apellido, segundo_apellido, fecha_nacimiento,id_entidad_nacimiento, id_escolaridad) VALUES ('Ana Karen', 'Villanueva', 'Jimenez', '2005-05-05', 5, 7);
INSERT INTO persona (nombre, primer_apellido, segundo_apellido, fecha_nacimiento,id_entidad_nacimiento, id_escolaridad) VALUES ('Marco Antonio', 'Vazquez', 'Velazquez', '2008-06-07', 3, 5);
INSERT INTO persona (nombre, primer_apellido, segundo_apellido, fecha_nacimiento,id_entidad_nacimiento, id_escolaridad) VALUES ('Estela', 'Valtierra', 'Feliciano', '2005-09-07', 4, 9);
INSERT INTO persona (nombre, primer_apellido, segundo_apellido, fecha_nacimiento,id_entidad_nacimiento, id_escolaridad) VALUES ('Oscar', 'Salgado', 'Bojorquez', '1980-02-01', 8, 8);
INSERT INTO persona (nombre, primer_apellido, segundo_apellido, fecha_nacimiento,id_entidad_nacimiento, id_escolaridad) VALUES ('Janet', 'Sanchez', 'Franco', '1968-03-31', 9, 6);
INSERT INTO persona (nombre, primer_apellido, segundo_apellido, fecha_nacimiento,id_entidad_nacimiento, id_escolaridad) VALUES ('Pablo', 'Ramirez', 'Lopez', '2004-12-10', 10, 4);
INSERT INTO persona (nombre, primer_apellido, segundo_apellido, fecha_nacimiento,id_entidad_nacimiento, id_escolaridad) VALUES ('Felipe', 'Lopez', 'Sarabia', '2006-10-05', 11, 3);
INSERT INTO persona (nombre, primer_apellido, segundo_apellido, fecha_nacimiento,id_entidad_nacimiento, id_escolaridad) VALUES ('Johnatan', 'Ortiz', 'Castro', '1988-06-14', 21, 5);
INSERT INTO persona (nombre, primer_apellido, segundo_apellido, fecha_nacimiento,id_entidad_nacimiento, id_escolaridad) VALUES ('Jesus', 'Ochoa', 'Gonzalez', '1998-09-16', 27, 1);
INSERT INTO persona (nombre, primer_apellido, segundo_apellido, fecha_nacimiento,id_entidad_nacimiento, id_escolaridad) VALUES ('Yareli', 'Hernandez', 'Ponce', '2016-11-20', 28, 2);
INSERT INTO persona (nombre, primer_apellido, segundo_apellido, fecha_nacimiento,id_entidad_nacimiento, id_escolaridad) VALUES ('Montserrat', 'Dominguez', 'Torres', '2004-04-21', 29, 4);
INSERT INTO persona (nombre, primer_apellido, segundo_apellido, fecha_nacimiento,id_entidad_nacimiento, id_escolaridad) VALUES ('Yamilet', 'Escudero', 'Vazquez', '2007-03-05', 32, 7);
INSERT INTO persona (nombre, primer_apellido, segundo_apellido, fecha_nacimiento,id_entidad_nacimiento, id_escolaridad) VALUES ('Angel', 'Gamez', 'Granados', '2005-02-02', 1, 8);
INSERT INTO persona (nombre, primer_apellido, segundo_apellido, fecha_nacimiento,id_entidad_nacimiento, id_escolaridad) VALUES ('Guadalupe', 'Gomez', 'Vazquez', '2014-02-24', 2, 9);
INSERT INTO persona (nombre, primer_apellido, segundo_apellido, fecha_nacimiento,id_entidad_nacimiento, id_escolaridad) VALUES ('Alondra', 'Delgadillo', 'Rojas', '2000-10-15', 3, 10);
INSERT INTO persona (nombre, primer_apellido, segundo_apellido, fecha_nacimiento,id_entidad_nacimiento, id_escolaridad) VALUES ('Mireya', 'Barcenas', 'Roque', '2003-06-01', 3, 10);
INSERT INTO persona (nombre, primer_apellido, segundo_apellido, fecha_nacimiento,id_entidad_nacimiento, id_escolaridad) VALUES ('Elizabeth', 'Avalos', 'Madrigal', '1978-07-31', 5, 5);
INSERT INTO persona (nombre, primer_apellido, segundo_apellido, fecha_nacimiento,id_entidad_nacimiento, id_escolaridad) VALUES ('Joselin', 'Alvarado', 'Trejo', '2008-08-08', 6, 9);
INSERT INTO persona (nombre, primer_apellido, segundo_apellido, fecha_nacimiento,id_entidad_nacimiento, id_escolaridad) VALUES ('Alejandro', 'Carmona', 'Jara', '1975-07-30', 18, 7);
INSERT INTO persona (nombre, primer_apellido, segundo_apellido, fecha_nacimiento,id_entidad_nacimiento, id_escolaridad) VALUES ('Roberto', 'Luna', 'Angeles', '1990-04-18', 9, 4);
INSERT INTO persona (nombre, primer_apellido, segundo_apellido, fecha_nacimiento,id_entidad_nacimiento, id_escolaridad) VALUES ('Diana', 'Martinez', 'Barrera', '1991-07-01', 9, 5);
INSERT INTO persona (nombre, primer_apellido, segundo_apellido, fecha_nacimiento,id_entidad_nacimiento, id_escolaridad) VALUES ('Isabel', 'Meza', 'Marquez', '2001-02-02', 12, 8);
INSERT INTO persona (nombre, primer_apellido, segundo_apellido, fecha_nacimiento,id_entidad_nacimiento, id_escolaridad) VALUES ('Valeria', 'Moreno', 'Patricio', '2005-05-24', 14, 6);



