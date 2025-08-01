# app-trafico
![img.png](img.png)

# trafico-back


CREATE TABLE persona (
nit VARCHAR(20) PRIMARY KEY,
nombre VARCHAR(100),
apellidos VARCHAR(100),
calle VARCHAR(100),
numero VARCHAR(10),
codigo_postal VARCHAR(10),
municipio VARCHAR(50),
provincia VARCHAR(50),
sexo CHAR(1),
fecha_nacimiento DATE
);

-- INSERT
INSERT INTO persona (nit, nombre, apellidos, calle, numero, codigo_postal, municipio, provincia, sexo, fecha_nacimiento)
VALUES ('123456789', 'Juan', 'Pérez', 'Calle Falsa', '123', '11001', 'Bogotá', 'Cundinamarca', 'M', '1980-01-01');

-- SELECT
SELECT * FROM persona WHERE nit = '123456789';

-- UPDATE
UPDATE persona SET nombre = 'Carlos' WHERE nit = '123456789';

-- DELETE
DELETE FROM persona WHERE nit = '123456789';


CREATE TABLE unidad_transito (
id INT PRIMARY KEY,
nombre VARCHAR(100)
);

-- INSERT
INSERT INTO unidad_transito (id, nombre) VALUES (1, 'Unidad Central');

-- SELECT
SELECT * FROM unidad_transito;

-- UPDATE
UPDATE unidad_transito SET nombre = 'Unidad Norte' WHERE id = 1;

-- DELETE
DELETE FROM unidad_transito WHERE id = 1;


CREATE TABLE agente (
id INT PRIMARY KEY,
persona_id VARCHAR(20),
unidad_id INT,
FOREIGN KEY (persona_id) REFERENCES persona(nit),
FOREIGN KEY (unidad_id) REFERENCES unidad_transito(id)
);

-- INSERT
INSERT INTO agente (id, persona_id, unidad_id) VALUES (100, '123456789', 1);

-- SELECT
SELECT * FROM agente WHERE id = 100;

-- UPDATE
UPDATE agente SET unidad_id = 2 WHERE id = 100;

-- DELETE
DELETE FROM agente WHERE id = 100;


CREATE TABLE marca (
id INT PRIMARY KEY,
nombre VARCHAR(100),
direccion_social VARCHAR(150)
);

-- INSERT
INSERT INTO marca (id, nombre, direccion_social) VALUES (1, 'Toyota', 'Av. del Auto 123');

-- SELECT
SELECT * FROM marca;

-- UPDATE
UPDATE marca SET nombre = 'Mazda' WHERE id = 1;

-- DELETE
DELETE FROM marca WHERE id = 1;


CREATE TABLE modelo (
id INT PRIMARY KEY,
nombre VARCHAR(100),
potencia VARCHAR(50),
marca_id INT,
FOREIGN KEY (marca_id) REFERENCES marca(id)
);

-- INSERT
INSERT INTO modelo (id, nombre, potencia, marca_id) VALUES (1, 'Corolla', '1.8L', 1);

-- SELECT
SELECT * FROM modelo;

-- UPDATE
UPDATE modelo SET potencia = '2.0L' WHERE id = 1;

-- DELETE
DELETE FROM modelo WHERE id = 1;


CREATE TABLE vehiculo (
bastidor VARCHAR(50) PRIMARY KEY,
fecha_matriculacion DATE,
matricula VARCHAR(20),
modelo_id INT,
FOREIGN KEY (modelo_id) REFERENCES modelo(id)
);

-- INSERT
INSERT INTO vehiculo (bastidor, fecha_matriculacion, matricula, modelo_id)
VALUES ('ABC123456789', '2020-05-10', 'XYZ123', 1);

-- SELECT
SELECT * FROM vehiculo;

-- UPDATE
UPDATE vehiculo SET matricula = 'XYZ456' WHERE bastidor = 'ABC123456789';

-- DELETE
DELETE FROM vehiculo WHERE bastidor = 'ABC123456789';


CREATE TABLE propiedad_vehiculo (
id INT PRIMARY KEY,
activo BOOLEAN,
fecha_inicio DATE,
fecha_fin DATE,
propietario_nit VARCHAR(20),
vehiculo_bastidor VARCHAR(50),
FOREIGN KEY (propietario_nit) REFERENCES persona(nit),
FOREIGN KEY (vehiculo_bastidor) REFERENCES vehiculo(bastidor)
);

-- INSERT
INSERT INTO propiedad_vehiculo (id, activo, fecha_inicio, fecha_fin, propietario_nit, vehiculo_bastidor)
VALUES (1, TRUE, '2020-05-10', NULL, '123456789', 'ABC123456789');

-- SELECT
SELECT * FROM propiedad_vehiculo;

-- UPDATE
UPDATE propiedad_vehiculo SET activo = FALSE, fecha_fin = CURRENT_DATE WHERE id = 1;

-- DELETE
DELETE FROM propiedad_vehiculo WHERE id = 1;


CREATE TABLE infraccion (
numero_expediente VARCHAR(30) PRIMARY KEY,
articulo_infringido VARCHAR(100),
carretera VARCHAR(100),
direccion VARCHAR(150),
estado VARCHAR(50),
fecha DATE,
importe DECIMAL(10, 2),
kilometro INT,
agente_id INT,
persona_nit VARCHAR(20),
vehiculo_bastidor VARCHAR(50),
FOREIGN KEY (agente_id) REFERENCES agente(id),
FOREIGN KEY (persona_nit) REFERENCES persona(nit),
FOREIGN KEY (vehiculo_bastidor) REFERENCES vehiculo(bastidor)
);

-- INSERT
INSERT INTO infraccion (
numero_expediente, articulo_infringido, carretera, direccion, estado, fecha,
importe, kilometro, agente_id, persona_nit, vehiculo_bastidor
) VALUES (
'INF001', 'Art. 123', 'Autopista Norte', 'Calle 123', 'Pendiente',
'2023-10-15', 200000, 12, 100, '123456789', 'ABC123456789'
);

-- SELECT
SELECT * FROM infraccion;

-- UPDATE
UPDATE infraccion SET estado = 'Pagado' WHERE numero_expediente = 'INF001';

-- DELETE
DELETE FROM infraccion WHERE numero_expediente = 'INF001';


