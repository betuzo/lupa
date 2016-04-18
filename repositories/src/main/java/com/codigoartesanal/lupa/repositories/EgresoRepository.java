package com.codigoartesanal.lupa.repositories;

import com.codigoartesanal.lupa.model.Egreso;
import com.codigoartesanal.lupa.model.StatusEgreso;
import com.codigoartesanal.lupa.model.dto.GraficaDTO;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by betuzo on 26/02/16.
 */
public interface EgresoRepository extends CrudRepository<Egreso, Long> {

    @Transactional
    @Modifying
    @Query("update Egreso i set i.factura = :factura where i.id = :id")
    int updateFacturaByIdEgreso(@Param("factura") String factura, @Param("id") Long idEgreso);

    @Transactional
    @Modifying
    @Query("update Egreso i set i.status = :status where i.id = :id")
    int updateStatusByEgreso(@Param("status") StatusEgreso status, @Param("id") Long idEgreso);

    @Query("Select new com.codigoartesanal.lupa.model.dto.GraficaDTO(eg.evento.nombre, sum(eg.monto), count(*)) " +
            "from Egreso eg " +
            "group by eg.evento.nombre ")
    List<GraficaDTO> findAllDetailGroupEvent();
}
