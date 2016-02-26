INSERT INTO USUARIO (USERNAME, PASSWORD, ENABLED) VALUES
('jperez@tu.me', 'p4Ssword', 1),
('jsoto', '$2a$10$GqqtbEuDi8YXzI1n8Zoqv.Upp61NP/Jy1fvPiMAgtcsyFuwc7N.AK', 1),
('rolguin@grupobmv.com.mx', 'p4Ssword', 0),
('sgarcia', '123456', 1),
('jmolina', '123456', 1),
('snaranjo', '123456', 1),
('gduque', '123456', 1),
('jsaenz', '123456', 1),
('gloreto', '123456', 1),
('omurillo', '123456', 1),
('aosorno', '123456', 1),
('cpalacio', '123456', 1),
('hgonzalez', '123456', 1),
('cmontoya', '123456', 1),
('atabares', '123456', 1),
('jlopez', '123456', 1);

INSERT INTO USER_ROLE (ID, USERNAME, ROLE) VALUES
(1, 'jperez@tu.me', 'ADMIN'),
(2, 'jsoto', 'GERENTE'),
(3, 'rolguin@grupobmv.com.mx', 'JUGADOR'),
(4, 'sgarcia', 'JUGADOR'),
(5, 'jmolina', 'JUGADOR'),
(6, 'snaranjo', 'JUGADOR'),
(7, 'gduque', 'JUGADOR'),
(8, 'jsaenz', 'JUGADOR'),
(9, 'gloreto', 'JUGADOR'),
(10, 'omurillo', 'JUGADOR'),
(11, 'aosorno', 'JUGADOR'),
(12, 'cpalacio', 'JUGADOR'),
(13, 'hgonzalez', 'JUGADOR'),
(14, 'cmontoya', 'JUGADOR'),
(15, 'atabares', 'JUGADOR'),
(16, 'jlopez', 'JUGADOR');


INSERT INTO USER_TOKEN (TOKEN, USERNAME, TIPO, FECHA_VIGENCIA) VALUES
('ae3594d9-caf8-4563-9498-7096a4f08b5e', 'rolguin@grupobmv.com.mx', 'VALID_EMAIL', TIMESTAMP '2050-02-18 14:25:00.000');

INSERT INTO USER_TOKEN (TOKEN, USERNAME, TIPO, FECHA_VIGENCIA) VALUES
('ae3594d9-caf8-4563-9498-7096a4f08b5w', 'rolguin@grupobmv.com.mx', 'CHANGE_PASSWORD', TIMESTAMP '2050-02-18 14:25:00.000');