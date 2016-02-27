package com.codigoartesanal.lupa.repositories;

import com.codigoartesanal.lupa.model.Persona;
import com.codigoartesanal.lupa.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by betuzo on 22/05/15.
 */
public interface PersonaRepository extends CrudRepository<Persona, Long> {
    List<Persona> findAllByUser(User user);

    @Query("select pr from Persona pr where pr.user.username = :username")
    Persona findByUsername(@Param("username") String username);

    @Transactional
    @Modifying
    @Query("update Persona e set e.rutaFoto = :rutaFotoJugador where e.id = :id")
    int updateFotoByIdJugador(@Param("rutaFotoJugador") String rutaFotoJugador, @Param("id") Long id);
}
