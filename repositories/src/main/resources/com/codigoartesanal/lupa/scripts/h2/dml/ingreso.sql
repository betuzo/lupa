INSERT INTO INGRESO (ID, DONADOR_ID, RECAUDADOR_ID, MONTO, COMENTARIO, FICHA_PAGO, FECHA_REGISTRO, VISIBILIDAD, STATUS) VALUES
(1, 1, 1, 500, 'Kermes gelatinas', '', TIMESTAMP '2050-02-18 14:25:00.000', 'ANONIMA', 'VALIDA'),
(2, 3, 1, 750, 'Kermes tamales', '', TIMESTAMP '2050-02-18 14:25:00.000', 'PUBLICA', 'REGISTRADA');

INSERT INTO VALIDAR_INGRESO_TOKEN (TOKEN, INGRESO_ID, FECHA_VIGENCIA) VALUES
('ae3594d9-caf8-4563-9498-7096a4f08b5e', 2, TIMESTAMP '2050-02-18 14:25:00.000');
