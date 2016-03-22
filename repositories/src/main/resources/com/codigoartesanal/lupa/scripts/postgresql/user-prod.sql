
INSERT INTO USUARIO (USERNAME, PASSWORD, ENABLED) VALUES
('lily_aft@hotmail.com', 'p4Sswordlft', true),
('eddavid17@hotmail.com', 'p4Sswordedfb', true),
('antauri.7@gmail.com', 'p4Sswordmafb', true),
('nady_vaflolie@hotmail.com', 'p4Ssword', true),
('tomas_floresgarcia@hotmail.com', 'p4Sswordtfg', true),
('betotsol@gmail.com', 'p4Ssword', true),
('rulis82@hotmail.com', 'p4Sswordrmf', true);

INSERT INTO USER_ROLE (ID, USERNAME, ROLE) VALUES
(1, 'lily_aft@hotmail.com', 'DONADOR'),
(2, 'eddavid17@hotmail.com', 'DONADOR'),
(3, 'antauri.7@gmail.com', 'DONADOR'),
(4, 'nady_vaflolie@hotmail.com', 'DONADOR'),
(5, 'tomas_floresgarcia@hotmail.com', 'DONADOR'),
(6, 'betotsol@gmail.com', 'DONADOR'),
(7, 'nady_vaflolie@hotmail.com', 'RECAUDADOR'),
(8, 'tomas_floresgarcia@hotmail.com', 'VALIDADOR'),
(9, 'lily_aft@hotmail.com', 'VALIDADOR'),
(10, 'betotsol@gmail.com', 'VALIDADOR'),
(11, 'rulis82@hotmail.com', 'DONADOR');

INSERT INTO PERSONA (ID, USERNAME, NOMBRE, PATERNO, MATERNO, RUTA_FOTO, SEXO, FECHA_REGISTRO) VALUES
(1, 'lily_aft@hotmail.com', 'Lilian', 'Flores', 'Tovar', '', 'FEMENINO', TIMESTAMP '2016-03-10 14:25:00.000'),
(2, 'eddavid17@hotmail.com', 'Eduardo David', 'Flores', 'Bautista', '', 'MASCULINO', TIMESTAMP '2016-03-10 14:25:00.000'),
(3, 'antauri.7@gmail.com', 'Miguel Angel', 'Flores', 'Bautista', '', 'MASCULINO', TIMESTAMP '2016-03-10 14:25:00.000'),
(4, 'nady_vaflolie@hotmail.com', 'Nadia Elizabeth', 'Flores', 'Tovar', '', 'FEMENINO', TIMESTAMP '2016-03-10 14:25:00.000'),
(5, 'tomas_floresgarcia@hotmail.com', 'Tomas', 'Flores', 'Garcia', '', 'MASCULINO', TIMESTAMP '2016-03-10 14:25:00.000'),
(6, 'betotsol@gmail.com', 'Roberto Salvador', 'Olguín', 'Lozano', '', 'MASCULINO', TIMESTAMP '2016-03-10 14:25:00.000'),
(7, 'rulis82@hotmail.com', 'Rubén', 'Mendoza', 'Flores', '', 'MASCULINO', TIMESTAMP '2016-03-10 14:25:00.000');