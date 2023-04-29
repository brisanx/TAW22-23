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
delete from  gestor_banco.empleado;
ALTER TABLE gestor_banco.empleado AUTO_INCREMENT = 1;
delete from  gestor_banco.asignacion;
ALTER TABLE gestor_banco.asignacion AUTO_INCREMENT = 1;
delete from  gestor_banco.cuenta_bancaria;
ALTER TABLE gestor_banco.cuenta_bancaria AUTO_INCREMENT = 1;
delete from  gestor_banco.divisa;
ALTER TABLE gestor_banco.divisa AUTO_INCREMENT = 1;
delete from  gestor_banco.usuario;
ALTER TABLE gestor_banco.usuario AUTO_INCREMENT = 1;



INSERT INTO gestor_banco.usuario(identificacion,nombre,apellido,email,contrasena,rol,subRol,direccion,telefono)
 VALUES('11111111A', 'Fernando', 'Calvo Díaz', 'algo@gmail.com', '123', 'Particular', null, 'direc', '95155555');
INSERT INTO gestor_banco.usuario(identificacion,nombre,apellido,email,contrasena,rol,subRol,direccion,telefono)
 VALUES('11111111B', 'Alba', 'Sanchez Ibañez', 'algo1@gmail.com', '123', 'Particular', null, 'direc', '91234567');
INSERT INTO `gestor_banco`.`usuario` (`identificacion`, `nombre`, `email`, `contrasena`, `rol`, `subRol`, `direccion`, `telefono`) 
 VALUES('11111111C', 'Miguel', 'algo2@gmail.com', '123', 'Empresa', 'socio', 'direc', '92345678'); 
INSERT INTO `gestor_banco`.`usuario` (`identificacion`, `nombre`, `email`, `contrasena`, `rol`, `subRol`, `direccion`, `telefono`) 
VALUES ('11111111C', 'Jose', 'algo3@gmail.com', '123', 'Empresa', 'autorizado', 'direcr', '93456789');
INSERT INTO `gestor_banco`.`usuario` (`identificacion`, `nombre`, `email`, `contrasena`, `rol`, `subRol`, `direccion`, `telefono`) 
VALUES ('11111111C', 'Alvaro', 'algo4@gmail.com', '123', 'Empresa', 'cliente', 'direcr', '93456789');
INSERT INTO `gestor_banco`.`usuario` (`identificacion`, `nombre`, `email`, `contrasena`, `rol`, `subRol`, `direccion`, `telefono`) 
VALUES ('11111111D', 'Cartel Cali', 'cali1@gmail.com', '123', 'Empresa', 'socio', 'direcr', '999456789');
INSERT INTO `gestor_banco`.`usuario` (`identificacion`, `nombre`, `email`, `contrasena`, `rol`, `subRol`, `direccion`, `telefono`) 
VALUES ('11111111D', 'Cartel Medellin', 'medellin@gmail.com', '123', 'Empresa', 'socio', 'direcr', '999456780');
INSERT INTO `gestor_banco`.`usuario` (`identificacion`, `nombre`, `email`, `contrasena`, `rol`, `subRol`, `direccion`, `telefono`) 
VALUES ('11111111E', 'FIA', 'fia@gmail.com', '123', 'Empresa', 'socio', 'direcr', '999456780');
INSERT INTO `gestor_banco`.`usuario` (`identificacion`, `nombre`, `email`, `contrasena`, `rol`, `subRol`, `direccion`, `telefono`) 
VALUES ('11111111E', 'Libery Media', 'lb@gmail.com', '123', 'Empresa', 'socio', 'direcr', '999456780');
INSERT INTO gestor_banco.usuario(identificacion,nombre,apellido,email,contrasena,rol,subRol,direccion,telefono)
 VALUES('11111111F', 'Oscar', 'Hidalgo Puertas', 'oscarHidalgo@gmail.com', '123', 'Particular', null, 'direc', '95155555');
INSERT INTO gestor_banco.usuario(identificacion,nombre,apellido,email,contrasena,rol,subRol,direccion,telefono)
 VALUES('33333333A', 'Fernando', 'Alonso Díaz', 'aporla33@gmail.com', '123', 'Particular', null, 'direc', '333333333');
 INSERT INTO gestor_banco.usuario(identificacion,nombre,apellido,email,contrasena,rol,subRol,direccion,telefono)
 VALUES('11111111G', 'Vinicius', 'Junior', 'aporla15@gmail.com', '123', 'Particular', null, 'direc', '333333333');
 INSERT INTO gestor_banco.usuario(identificacion,nombre,apellido,email,contrasena,rol,subRol,direccion,telefono)
 VALUES('11111111H', 'Carletto', 'Anceloti', 'aporla15ya@gmail.com', '123', 'Particular', null, 'direc', '333333333');


INSERT INTO gestor_banco.empleado(nombre,email,rol,contrasena)  VALUES('Fernando Calvo', 'algo@gmail.com', 'gestor', '123');
INSERT INTO gestor_banco.empleado(nombre,email,rol,contrasena)  VALUES('Alba Sanchez', 'algo1@gmail.com', 'gestor', '123');
INSERT INTO gestor_banco.empleado(nombre,email,rol,contrasena)  VALUES('Miguel Moya', 'algo2@gmail.com', 'cajero', '123');
INSERT INTO gestor_banco.empleado(nombre,email,rol,contrasena)  VALUES('Jose Torres', 'algo3@gmail.com', 'asistente', '123');
INSERT INTO gestor_banco.empleado(nombre,email,rol,contrasena)  VALUES('Oscar Hidalgo', 'algo4@gmail.com', 'asistente', '123');

INSERT INTO gestor_banco.divisa(nombre, simbolo, ratio_de_cambio) VALUES('Euro', '€', 1.0);
INSERT INTO gestor_banco.divisa(nombre, simbolo, ratio_de_cambio) VALUES('Dolar', '$', 1.8);
INSERT INTO gestor_banco.divisa(nombre, simbolo, ratio_de_cambio) VALUES('Libra', '£', 0.88);

INSERT INTO gestor_banco.solicitud_alta (fecha_solicitud,id_gestor, usuario_id, divisa_id) VALUES ('2023/03/25', 1, 4, 1);
INSERT INTO gestor_banco.solicitud_alta (fecha_solicitud,id_gestor, usuario_id, divisa_id) VALUES ('2023/03/25', 1, 5, 1);
INSERT INTO gestor_banco.solicitud_alta (fecha_solicitud,id_gestor, usuario_id, divisa_id) VALUES ('2023/03/25', 1, 3, 2);
INSERT INTO gestor_banco.solicitud_alta (fecha_solicitud,id_gestor, usuario_id, divisa_id) VALUES ('2023/03/22', 1, 11, 3);

INSERT INTO gestor_banco.cuenta_bancaria (saldo, moneda, activo, divisa_id) VALUES (278.14, 'Euro', 1, 1);
INSERT INTO asignacion VALUES (1,1);
INSERT INTO gestor_banco.cuenta_bancaria (saldo, moneda, activo, divisa_id) VALUES (73742.69, 'Libra', 1, 3);
INSERT INTO asignacion VALUES (2,1);
INSERT INTO gestor_banco.cuenta_bancaria (saldo, moneda, activo, divisa_id) VALUES (1000.50, 'Euro', 1, 1);
INSERT INTO asignacion VALUES (3,2);
INSERT INTO gestor_banco.cuenta_bancaria (saldo, moneda, activo, divisa_id) VALUES (500000.33, 'Euro', 1, 1);
INSERT INTO asignacion VALUES (4,10);
INSERT INTO gestor_banco.cuenta_bancaria (saldo, moneda, activo, divisa_id) VALUES (1000.50, 'Dolar', 0, 2);
INSERT INTO asignacion VALUES (5,6);
INSERT INTO gestor_banco.cuenta_bancaria (saldo, moneda, activo, divisa_id) VALUES (3331.33, 'Dolar', 0, 2);
INSERT INTO asignacion VALUES (6,7);
INSERT INTO gestor_banco.cuenta_bancaria (saldo, moneda, activo, divisa_id) VALUES (1000.50, 'Dolar', 0, 2);
INSERT INTO asignacion VALUES (7,8);
INSERT INTO gestor_banco.cuenta_bancaria (saldo, moneda, activo, divisa_id) VALUES (3333.33, 'Dolar', 0, 2);
INSERT INTO asignacion VALUES (8,9);
INSERT INTO gestor_banco.cuenta_bancaria (saldo, moneda, activo, divisa_id) VALUES (55555.50, 'Libra', 1, 3);
INSERT INTO asignacion VALUES (9,12);
INSERT INTO gestor_banco.cuenta_bancaria (saldo, moneda, activo, divisa_id) VALUES (5555.33, 'Libra', 1, 3);
INSERT INTO asignacion VALUES (10,13);

INSERT INTO gestor_banco.solicitud_activacion (fecha_solicitud, usuario_id, empleado_id_gestor,cuenta_bancaria_id) VALUES ('2023/04/22', 6, 1, 5);
INSERT INTO gestor_banco.solicitud_activacion (fecha_solicitud, usuario_id, empleado_id_gestor,cuenta_bancaria_id) VALUES ('2023/04/23', 7, 1, 6);
INSERT INTO gestor_banco.solicitud_activacion (fecha_solicitud, usuario_id, empleado_id_gestor,cuenta_bancaria_id) VALUES ('2023/04/22', 8, 1, 7);
INSERT INTO gestor_banco.solicitud_activacion (fecha_solicitud, usuario_id, empleado_id_gestor,cuenta_bancaria_id) VALUES ('2023/04/23', 9, 1, 8);


INSERT INTO gestor_banco.conversacion(numero_mensaje, empleado_id_gestor, usuario_id) VALUES(2, 5, 1);
INSERT INTO gestor_banco.conversacion(numero_mensaje, empleado_id_gestor, usuario_id)  VALUES(2, 5, 2);

INSERT INTO gestor_banco.mensaje(longitud, texto, conversacion_id_conver) VALUES(4, 'Hola', 1);
INSERT INTO gestor_banco.mensaje(longitud, texto, conversacion_id_conver)  VALUES(5, 'Adios', 1);
INSERT INTO gestor_banco.mensaje(longitud, texto, conversacion_id_conver)  VALUES(20, 'Me cobraron comision', 2);
INSERT INTO gestor_banco.mensaje(longitud, texto, conversacion_id_conver)  VALUES(15, 'Denme el dinero', 2);


-- Cuando se crea una cuenta bancaria se hace una operacion bancaria a si mismo del dinero con el que empieza la cuenta
INSERT INTO gestor_banco.operacion_bancaria (fecha, cantidad, id_cuenta_origen, id_cuenta_destino) VALUES ('2023/04/04', 500.0, 1, 1);
INSERT INTO gestor_banco.operacion_bancaria (fecha, cantidad, id_cuenta_origen, id_cuenta_destino) VALUES ('2023/04/04', 500.0, 2, 2);
INSERT INTO gestor_banco.operacion_bancaria (fecha, cantidad, id_cuenta_origen, id_cuenta_destino) VALUES ('2023/04/04', 500.0, 3, 3);
INSERT INTO gestor_banco.operacion_bancaria (fecha, cantidad, id_cuenta_origen, id_cuenta_destino) VALUES ('2022/06/24', 500.0, 4, 4);
INSERT INTO gestor_banco.operacion_bancaria (fecha, cantidad, id_cuenta_origen, id_cuenta_destino) VALUES ('2022/06/24', 500.0, 5, 5);
INSERT INTO gestor_banco.operacion_bancaria (fecha, cantidad, id_cuenta_origen, id_cuenta_destino) VALUES ('2022/03/28', 500.0, 6, 6);
INSERT INTO gestor_banco.operacion_bancaria (fecha, cantidad, id_cuenta_origen, id_cuenta_destino) VALUES ('2022/03/28', 500.0, 7, 7);
INSERT INTO gestor_banco.operacion_bancaria (fecha, cantidad, id_cuenta_origen, id_cuenta_destino) VALUES ('2021/03/28', 500.0, 8, 8);
INSERT INTO gestor_banco.operacion_bancaria (fecha, cantidad, id_cuenta_origen, id_cuenta_destino) VALUES ('2021/03/28', 500.0, 9, 9);
INSERT INTO gestor_banco.operacion_bancaria (fecha, cantidad, id_cuenta_origen, id_cuenta_destino) VALUES ('2021/03/28', 500.0, 10, 10);

-- Al hacer transferencia el que recibe dinero tambien cuenta como una de ellas
INSERT INTO gestor_banco.operacion_bancaria (fecha, cantidad, id_cuenta_origen, id_cuenta_destino) VALUES ('2023/04/15', 500.0, 1, 2);
INSERT INTO gestor_banco.operacion_bancaria (fecha, cantidad, id_cuenta_origen, id_cuenta_destino) VALUES ('2023/04/15', -500.0, 2, 1);
INSERT INTO gestor_banco.operacion_bancaria (fecha, cantidad, id_cuenta_origen, id_cuenta_destino) VALUES ('2022/04/16', 333.33, 2, 3);
INSERT INTO gestor_banco.operacion_bancaria (fecha, cantidad, id_cuenta_origen, id_cuenta_destino) VALUES ('2022/04/16', -333.33, 3, 2);
INSERT INTO gestor_banco.operacion_bancaria (fecha, cantidad, id_cuenta_origen, id_cuenta_destino) VALUES ('2022/01/20', 500.0, 6, 7);
INSERT INTO gestor_banco.operacion_bancaria (fecha, cantidad, id_cuenta_origen, id_cuenta_destino) VALUES ('2022/01/20', -500.0, 7, 6);
INSERT INTO gestor_banco.operacion_bancaria (fecha, cantidad, id_cuenta_origen, id_cuenta_destino) VALUES ('2022/01/20', 123.0, 4, 5);
INSERT INTO gestor_banco.operacion_bancaria (fecha, cantidad, id_cuenta_origen, id_cuenta_destino) VALUES ('2022/01/20', -123.0, 5, 4);
INSERT INTO gestor_banco.operacion_bancaria (fecha, cantidad, id_cuenta_origen, id_cuenta_destino) VALUES ('2021/05/20', 123.0, 8, 9);
INSERT INTO gestor_banco.operacion_bancaria (fecha, cantidad, id_cuenta_origen, id_cuenta_destino) VALUES ('2021/05/20', -123.0, 9, 8);
INSERT INTO gestor_banco.operacion_bancaria (fecha, cantidad, id_cuenta_origen, id_cuenta_destino) VALUES ('2023/04/23', 10.0, 2, 6);
INSERT INTO gestor_banco.operacion_bancaria (fecha, cantidad, id_cuenta_origen, id_cuenta_destino) VALUES ('2023/04/23', -10.0, 6, 2);
