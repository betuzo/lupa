package com.codigoartesanal.lupa.repositories;

import com.codigoartesanal.lupa.model.Ingreso;
import com.codigoartesanal.lupa.model.dto.EstadisticaIngresoDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by betuzo on 26/02/16.
 */
public interface IngresoRepository extends CrudRepository<Ingreso, Long> {

    @Transactional
    @Modifying
    @Query("update Ingreso i set i.fichaPago = :rutaFichaPago where i.id = :id")
    int updateFichaPagoByIdIngreso(@Param("rutaFichaPago") String fichaPago, @Param("id") Long idIngreso);

    @Transactional
    @Modifying
    @Query("update Ingreso i set i.status = :status where i.id = :id")
    int updateStatusByIngreso(@Param("status") String status, @Param("id") Long idIngreso);
}
