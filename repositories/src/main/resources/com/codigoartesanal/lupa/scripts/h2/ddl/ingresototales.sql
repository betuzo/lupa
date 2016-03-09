DROP TABLE IF EXISTS Ingresos_Totales_View;

Create view Ingresos_Totales_View as

select
	1 as id, --row_number() over() as id, postgresql
	SUM(CASE WHEN status='REGISTRADA' THEN 1 ELSE 0 END) as totalPendientes,
	sum(monto) as totalMontoPendiente,
	SUM(CASE WHEN status='VALIDA' THEN monto ELSE 0 END) as totalMonto
from ingreso
where
	status in ('REGISTRADA', 'VALIDA');