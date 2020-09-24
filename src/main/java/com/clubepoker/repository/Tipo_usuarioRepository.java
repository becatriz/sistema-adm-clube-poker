package com.clubepoker.repository;

import com.clubepoker.model.Tipo_usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Tipo_usuarioRepository extends CrudRepository<Tipo_usuario, Integer> {
}
