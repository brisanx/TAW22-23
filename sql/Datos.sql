delete from  gestor_banco.mensaje;
ALTER TABLE gestor_banco.mensaje AUTO_INCREMENT = 1;
delete from  gestor_banco.conversacion;
ALTER TABLE gestor_banco.conversacion AUTO_INCREMENT = 1;
delete from  gestor_banco.solicitud_activacion;
ALTER TABLE gestor_banco.solicitud_activacion AUTO_INCREMENT = 1;
delete from  gestor_banco.solicitud_alta;
ALTER TABLE gestor_banco.solicitud_alta AUTO_INCREMENT = 1;
delete from  gestor_banco.operacion_bancaria;
ALTER TABLE gestor_banco.operacion_bancaria AUTO_INCREMENT = 1;
delete from  gestor_banco.cuenta_bancaria;
ALTER TABLE gestor_banco.cuenta_bancaria AUTO_INCREMENT = 1;
delete from  gestor_banco.divisa;
ALTER TABLE gestor_banco.divisa AUTO_INCREMENT = 1;
delete from  gestor_banco.empleado;
ALTER TABLE gestor_banco.empleado AUTO_INCREMENT = 1;
delete from  gestor_banco.usuario;
ALTER TABLE gestor_banco.usuario AUTO_INCREMENT = 1;




INSERT INTO gestor_banco.usuario VALUES('11111111A', 'Fernando', 'Calvo Díaz', 'algo@gmail.com', '123', 0, 'cliente', 'direc', '95155555');
INSERT INTO gestor_banco.usuario VALUES('11111111B', 'Alba', 'Sanchez Ibañez', 'algo1@gmail.com', '123', 1, 'cliente', 'direc', '95155555');
INSERT INTO gestor_banco.usuario VALUES('11111111C', 'Miguel', 'Moya Castillo', 'algo2@gmail.com', '123', 1, 'empresa', 'direc', '95155555'); 
INSERT INTO gestor_banco.usuario VALUES('11111111D', 'Jose', 'Torres Postigo', 'algo3@gmail.com', '123', 1, 'empresa', 'direc', '95155555'); 
INSERT INTO gestor_banco.usuario VALUES('11111111E', 'Oscar', 'Hidalgo Puertas', 'algo4@gmail.com', '123', 1, 'empresa', 'direc', '95155555');
INSERT INTO gestor_banco.usuario VALUES('33333333A', 'Fernando', 'Alonso Díaz', 'aporla33@gmail.com', '123', 0, 'cliente', 'direc', '333333333');

INSERT INTO gestor_banco.empleado(nombre,email,rol,contrasena)  VALUES('Fernando Calvo', 'algo@gmail.com', 'gestor', '123');
INSERT INTO gestor_banco.empleado(nombre,email,rol,contrasena)  VALUES('Alba Sanchez', 'algo1@gmail.com', 'gestor', '123');
INSERT INTO gestor_banco.empleado(nombre,email,rol,contrasena)  VALUES('Miguel Moya', 'algo2@gmail.com', 'cajero', '123');
INSERT INTO gestor_banco.empleado(nombre,email,rol,contrasena)  VALUES('Jose Torres', 'algo3@gmail.com', 'asistente', '123');
INSERT INTO gestor_banco.empleado(nombre,email,rol,contrasena)  VALUES('Oscar Hidalgo', 'algo4@gmail.com', 'asistente', '123');


INSERT INTO gestor_banco.solicitud_alta(id_gestor,fecha_solicitud,estado,usuario_id) VALUES (1, '2023/03/25', 'pendiente', '11111111A');
UPDATE gestor_banco.usuario SET gestor_banco.usuario.activo = 0 WHERE gestor_banco.usuario.nombre = 'Alba';
INSERT INTO gestor_banco.solicitud_alta(id_gestor,fecha_solicitud,estado,usuario_id) VALUES (1, '2023/03/25', 'pendiente', '11111111B');
INSERT INTO gestor_banco.solicitud_alta(id_gestor,fecha_solicitud,estado,usuario_id) VALUES (1, '2023/03/25', 'pendiente', '11111111C');
INSERT INTO gestor_banco.solicitud_alta(id_gestor,fecha_solicitud,estado,usuario_id) VALUES (1, '2023/03/25', 'activo', '11111111D');
INSERT INTO gestor_banco.solicitud_alta(id_gestor,fecha_solicitud,estado,usuario_id) VALUES (1, '2023/03/25', 'activo', '11111111E');
INSERT INTO gestor_banco.solicitud_alta(id_gestor,fecha_solicitud,estado,usuario_id) VALUES (1, '2023/03/25', 'activo', '33333333A');

INSERT INTO gestor_banco.solicitud_activacion (fecha_solicitud, aprobada, usuario_id, empleado_id_gestor) VALUES ('2023/03/25', 0, '11111111E', 1);
INSERT INTO gestor_banco.solicitud_activacion (fecha_solicitud, aprobada, usuario_id, empleado_id_gestor) VALUES ('2023/03/28', 0, '11111111E', 1);
INSERT INTO gestor_banco.solicitud_activacion (fecha_solicitud, aprobada, usuario_id, empleado_id_gestor) VALUES ('2023/03/25', 0, '33333333A', 1);
INSERT INTO gestor_banco.solicitud_activacion (fecha_solicitud, aprobada, usuario_id, empleado_id_gestor) VALUES ('2023/03/28', 0, '11111111D', 1);

INSERT INTO gestor_banco.conversacion(numero_mensaje, empleado_id_gestor, usuario_id) VALUES(2, 5, '11111111E');
INSERT INTO gestor_banco.conversacion(numero_mensaje, empleado_id_gestor, usuario_id)  VALUES(2, 5, '11111111E');

INSERT INTO gestor_banco.mensaje(longitud, texto, conversacion_id_conver) VALUES(4, 'Hola', 1);
INSERT INTO gestor_banco.mensaje(longitud, texto, conversacion_id_conver)  VALUES(5, 'Adios', 1);
INSERT INTO gestor_banco.mensaje(longitud, texto, conversacion_id_conver)  VALUES(20, 'Me cobraron comision', 2);
INSERT INTO gestor_banco.mensaje(longitud, texto, conversacion_id_conver)  VALUES(15, 'Denme el dinero', 2);

INSERT INTO gestor_banco.divisa(nombre, simbolo, ratio_de_cambio) VALUES('Euro', '€', 1.0);
INSERT INTO gestor_banco.divisa(nombre, simbolo, ratio_de_cambio) VALUES('Dolar', '$', 1.8);
INSERT INTO gestor_banco.divisa(nombre, simbolo, ratio_de_cambio) VALUES('Libra', '£', 0.88);

ALTER TABLE `gestor_banco`.`cuenta_bancaria` 
DROP COLUMN `id_cliente`,
DROP INDEX `id_cliente` ;
;

ALTER TABLE gestor_banco.cuenta_bancaria ADD COLUMN usuario_id VARCHAR(11) NOT NULL;
ALTER TABLE gestor_banco.cuenta_bancaria ADD CONSTRAINT fk_usuario_id FOREIGN KEY (usuario_id) REFERENCES gestor_banco.usuario(id);

INSERT INTO gestor_banco.cuenta_bancaria (tipo, moneda, saldo, divisa_id, usuario_id) VALUES ('Crédito', 'Euro', 1000.0, 1, '11111111D');
INSERT INTO gestor_banco.cuenta_bancaria (tipo, moneda, saldo, divisa_id, usuario_id) VALUES ('Crédito', 'Euro', 1000.0, 1, '11111111D');
INSERT INTO gestor_banco.cuenta_bancaria (tipo, moneda, saldo, divisa_id, usuario_id) VALUES ('Crédito', 'Euro', 1000.0, 1, '33333333A');

INSERT INTO gestor_banco.operacion_bancaria (id_cuenta_origen, id_cuenta_destino, fecha, cantidad) VALUES (1, 2, '2023/03/28', 500.0);
INSERT INTO gestor_banco.operacion_bancaria (id_cuenta_origen, id_cuenta_destino, fecha, cantidad) VALUES (2, 1, '2023/03/28', 500.0);
INSERT INTO gestor_banco.operacion_bancaria (id_cuenta_origen, id_cuenta_destino, fecha, cantidad) VALUES (1, 3, '2023/03/28', 99.75);
INSERT INTO gestor_banco.operacion_bancaria (id_cuenta_origen, id_cuenta_destino, fecha, cantidad) VALUES (3, 1, '2023/03/28', 99.75);
