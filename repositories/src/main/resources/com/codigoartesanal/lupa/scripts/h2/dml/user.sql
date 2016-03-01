INSERT INTO USUARIO (USERNAME, PASSWORD, ENABLED) VALUES
('jperez@tu.me', 'p4Ssword', 1),
('jsoto@tu.me', 'p4Ssword', 1),
('betotsol@gmail.com', 'p4Ssword', 1),
('rolguin@tu.me', 'p4Ssword', 0),
('sgarcia@tu.me', 'p4Ssword', 1),
('jmolina@tu.me', 'p4Ssword', 1),
('snaranjo@tu.me', 'p4Ssword', 1),
('gduque@tu.me', 'p4Ssword', 1),
('jsaenz@tu.me', 'p4Ssword', 1),
('gloreto@tu.me', 'p4Ssword', 1),
('omurillo@tu.me', 'p4Ssword', 1),
('aosorno@tu.me', 'p4Ssword', 1),
('cpalacio@tu.me', 'p4Ssword', 1),
('hgonzalez@tu.me', 'p4Ssword', 1),
('cmontoya@tu.me', 'p4Ssword', 1),
('atabares@tu.me', 'p4Ssword', 1),
('jlopez@tu.me', 'p4Ssword', 1);

INSERT INTO USER_ROLE (ID, USERNAME, ROLE) VALUES
(1, 'jperez@tu.me', 'DONADOR'),
(2, 'jsoto@tu.me', 'DONADOR'),
(3, 'rolguin@tu.me', 'DONADOR'),
(4, 'sgarcia@tu.me', 'DONADOR'),
(5, 'jmolina@tu.me', 'DONADOR'),
(6, 'snaranjo@tu.me', 'DONADOR'),
(7, 'gduque@tu.me', 'DONADOR'),
(8, 'jsaenz@tu.me', 'DONADOR'),
(9, 'gloreto@tu.me', 'DONADOR'),
(10, 'omurillo@tu.me', 'DONADOR'),
(11, 'aosorno@tu.me', 'DONADOR'),
(12, 'cpalacio@tu.me', 'DONADOR'),
(13, 'hgonzalez@tu.me', 'DONADOR'),
(14, 'cmontoya@tu.me', 'DONADOR'),
(15, 'atabares@tu.me', 'DONADOR'),
(16, 'jlopez@tu.me', 'DONADOR'),
(17, 'betotsol@gmail.com', 'DONADOR'),
(18, 'jperez@tu.me', 'RECAUDADOR'),
(19, 'betotsol@gmail.com', 'VALIDADOR');


INSERT INTO USER_TOKEN (TOKEN, USERNAME, TIPO, FECHA_VIGENCIA) VALUES
('ae3594d9-caf8-4563-9498-7096a4f08b5e', 'rolguin@tu.me', 'VALID_EMAIL', TIMESTAMP '2050-02-18 14:25:00.000');

INSERT INTO USER_TOKEN (TOKEN, USERNAME, TIPO, FECHA_VIGENCIA) VALUES
('ae3594d9-caf8-4563-9498-7096a4f08b5w', 'rolguin@tu.me', 'CHANGE_PASSWORD', TIMESTAMP '2050-02-18 14:25:00.000');

INSERT INTO PERSONA (ID, USERNAME, NOMBRE, PATERNO, MATERNO, RUTA_FOTO, SEXO, FECHA_REGISTRO) VALUES
(1, 'jperez@tu.me', 'Jorge', 'Perez', 'Cruz', '', 'MASCULINO', TIMESTAMP '2050-02-18 14:25:00.000'),
(2, 'betotsol@gmail.com', 'Beto', 'Olguin', 'Lozano', '', 'MASCULINO', TIMESTAMP '2050-02-18 14:25:00.000'),
(3, 'jsoto@tu.me', 'Jaime', 'Soto', 'Molina', '', 'MASCULINO', TIMESTAMP '2050-02-18 14:25:00.000');
