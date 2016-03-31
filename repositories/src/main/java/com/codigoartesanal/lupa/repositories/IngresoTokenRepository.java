package com.codigoartesanal.lupa.repositories;

import com.codigoartesanal.lupa.model.Ingreso;
import com.codigoartesanal.lupa.model.IngresoToken;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

/**
 * Created by betuzo on 26/02/16.
 */
public interface IngresoTokenRepository extends CrudRepository<IngresoToken, String> {
    Set<IngresoToken> findAllByIngreso(Ingreso ingreso);
}
