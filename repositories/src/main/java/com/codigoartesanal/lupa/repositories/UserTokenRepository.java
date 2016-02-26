package com.codigoartesanal.lupa.repositories;

import com.codigoartesanal.lupa.model.UserToken;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by betuzo on 10/02/16.
 */
public interface UserTokenRepository extends CrudRepository<UserToken, String> {
}
