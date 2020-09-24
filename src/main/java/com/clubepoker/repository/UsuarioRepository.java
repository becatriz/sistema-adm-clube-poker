package com.clubepoker.repository;

import com.clubepoker.model.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {

    @Query("select u from Usuario u where u.login = ?1 and u.senha = ?2")
    public Usuario findByUsuario(String login, String senha);

}
