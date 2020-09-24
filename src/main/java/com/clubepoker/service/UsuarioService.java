package com.clubepoker.service;

import com.clubepoker.model.Usuario;
import com.clubepoker.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> listarTodosUsuarios(){
        return (List<Usuario>) usuarioRepository.findAll();
    }

    public void salvarUsuario(Usuario usuario){
        usuarioRepository.save(usuario);
    }
}
