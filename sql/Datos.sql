ALTER TABLE `gestor_banco`.`solicitud_activacion` DROP COLUMN `id_usuario`, DROP INDEX `id_cliente` ;

INSERT INTO gestor_banco.usuario (nombre,apellido,email,contrasena,activo,rol,direccion,telefono) VALUES('Fernando', 'Calvo Díaz', 'algo@gmail.com', '123', 0, 'cliente', 'direc', '95155555'); 
INSERT INTO gestor_banco.usuario (nombre,apellido,email,contrasena,activo,rol,direccion,telefono) VALUES('Alba', 'Sanchez Ibañez', 'algo1@gmail.com', '123', 1, 'cliente', 'direc', '95155555');
INSERT INTO gestor_banco.usuario (nombre,apellido,email,contrasena,activo,rol,direccion,telefono) VALUES('Miguel', 'Moya Castillo', 'algo2@gmail.com', '123', 1, 'empresa', 'direc', '95155555'); 
INSERT INTO gestor_banco.usuario (nombre,apellido,email,contrasena,activo,rol,direccion,telefono) VALUES('Jose', 'Torres Postigo', 'algo3@gmail.com', '123', 1, 'empresa', 'direc', '95155555'); 
INSERT INTO gestor_banco.usuario (nombre,apellido,email,contrasena,activo,rol,direccion,telefono) VALUES('Oscar', 'Hidalgo Puertas', 'algo4@gmail.com', '123', 1, 'empresa', 'direc', '95155555');

INSERT INTO gestor_banco.empleado VALUES(111,  'Fernando Calvo', 'algo@gmail.com', 'gestor');
INSERT INTO gestor_banco.empleado VALUES(222,  'Alba Sanchez', 'algo1@gmail.com', 'gestor');
INSERT INTO gestor_banco.empleado VALUES(333,  'Miguel Moya', 'algo2@gmail.com', 'cajero');
INSERT INTO gestor_banco.empleado VALUES(444,  'Jose Torres', 'algo3@gmail.com', 'asistente');
INSERT INTO gestor_banco.empleado VALUES(55,  'Oscar Hidalgo', 'algo4@gmail.com', 'asistente');

INSERT INTO gestor_banco.solicitud_alta VALUES (1,  111, '2023/03/25', 'pendiente', 1);
UPDATE gestor_banco.usuario SET gestor_banco.usuario.activo = 0 WHERE gestor_banco.usuario.nombre = 'Alba';
INSERT INTO gestor_banco.solicitud_alta VALUES (2,  111, '2023/03/25', 'pendiente', 3);
INSERT INTO gestor_banco.solicitud_alta VALUES (3,  111, '2023/03/25', 'activo', 4);
INSERT INTO gestor_banco.solicitud_alta VALUES (4,  111, '2023/03/25', 'activo', 5);
INSERT INTO gestor_banco.solicitud_alta VALUES (5,  111, '2023/03/25', 'activo', 6);

INSERT INTO gestor_banco.solicitud_activacion (fecha_solicitud, aprobada, usuario_id, empleado_id_gestor) VALUES ('2023/03/25', 0, 5, 111);
INSERT INTO gestor_banco.solicitud_activacion (fecha_solicitud, aprobada, usuario_id, empleado_id_gestor) VALUES ('2023/03/28', 0, 5, 111);
INSERT INTO gestor_banco.solicitud_activacion (fecha_solicitud, aprobada, usuario_id, empleado_id_gestor) VALUES ('2023/03/25', 0, 6, 111);
INSERT INTO gestor_banco.solicitud_activacion (fecha_solicitud, aprobada, usuario_id, empleado_id_gestor) VALUES ('2023/03/28', 0, 4, 111);

INSERT INTO gestor_banco.conversacion VALUES(1, 2, 55, 5);
INSERT INTO gestor_banco.conversacion VALUES(2, 2, 55, 3);

INSERT INTO gestor_banco.mensaje VALUES(1, 4, 'Hola', 1);
INSERT INTO gestor_banco.mensaje VALUES(2, 5, 'Adios', 1);
INSERT INTO gestor_banco.mensaje VALUES(3, 20, 'Me cobraron comision', 2);
INSERT INTO gestor_banco.mensaje VALUES(4, 15, 'Denme el dinero', 2);

INSERT INTO gestor_banco.divisa VALUES(1, 'Euro', '€', 1.0);
INSERT INTO gestor_banco.divisa VALUES(2, 'Dolar', '$', 1.8);
INSERT INTO gestor_banco.divisa VALUES(3, 'Libra', '£', 0.88);

INSERT INTO gestor_banco.cuenta_bancaria (id_cliente, tipo, moneda, saldo, divisa_id) VALUES (4, 'Crédito', 'Euro', 1000.0, 1);
INSERT INTO gestor_banco.cuenta_bancaria (id_cliente, tipo, moneda, saldo, divisa_id) VALUES (5, 'Crédito', 'Euro', 1000.0, 1);
INSERT INTO gestor_banco.cuenta_bancaria (id_cliente, tipo, moneda, saldo, divisa_id) VALUES (6, 'Crédito', 'Euro', 1000.0, 1);

INSERT INTO gestor_banco.operacion_bancaria (id_cuenta_origen, id_cuenta_destino, fecha, cantidad) VALUES (1, 2, '2023/03/28', 500.0);
INSERT INTO gestor_banco.operacion_bancaria (id_cuenta_origen, id_cuenta_destino, fecha, cantidad) VALUES (2, 1, '2023/03/28', 500.0);
INSERT INTO gestor_banco.operacion_bancaria (id_cuenta_origen, id_cuenta_destino, fecha, cantidad) VALUES (1, 3, '2023/03/28', 99.75);
INSERT INTO gestor_banco.operacion_bancaria (id_cuenta_origen, id_cuenta_destino, fecha, cantidad) VALUES (3, 1, '2023/03/28', 99.75);