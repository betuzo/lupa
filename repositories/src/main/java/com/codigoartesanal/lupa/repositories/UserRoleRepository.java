package com.codigoartesanal.lupa.repositories;

import com.codigoartesanal.lupa.model.UserRole;
import com.codigoartesanal.lupa.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

/**
 * Created by betuzo on 27/01/15.
 */
public interface UserRoleRepository extends CrudRepository<UserRole, String> {
    Set<UserRole> findAllByUser(User user);
}