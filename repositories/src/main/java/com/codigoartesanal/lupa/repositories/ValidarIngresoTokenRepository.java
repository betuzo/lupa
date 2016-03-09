package com.codigoartesanal.lupa.repositories;

import com.codigoartesanal.lupa.model.Ingreso;
import com.codigoartesanal.lupa.model.ValidarIngresoToken;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

/**
 * Created by betuzo on 26/02/16.
 */
public interface ValidarIngresoTokenRepository extends CrudRepository<ValidarIngresoToken, String> {
    Set<ValidarIngresoToken> findAllByIngreso(Ingreso ingreso);
}
