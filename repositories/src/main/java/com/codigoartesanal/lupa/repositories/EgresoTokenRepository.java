package com.codigoartesanal.lupa.repositories;

import com.codigoartesanal.lupa.model.EgresoToken;
import com.codigoartesanal.lupa.model.Egreso;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

/**
 * Created by betuzo on 26/02/16.
 */
public interface EgresoTokenRepository extends CrudRepository<EgresoToken, String> {
    Set<EgresoToken> findAllByEgreso(Egreso egreso);
}
