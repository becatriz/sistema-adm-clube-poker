package com.clubepoker.controller;

import com.clubepoker.model.Plataforma;
import com.clubepoker.model.Tipo_usuario;
import com.clubepoker.repository.PlataformaRepository;
import com.clubepoker.repository.Tipo_usuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/tipo")
public class TipoUsuarioController {

    @Autowired
    private Tipo_usuarioRepository tipo_usuarioRepository;

    @GetMapping(value = "/usuario", produces = "application/json")
    public ResponseEntity<List<Tipo_usuario>> listaTipoUsuarios() {
        try {
            List<Tipo_usuario> listaTipoUsuarios = (List<Tipo_usuario>) tipo_usuarioRepository.findAll();
            return new ResponseEntity<List<Tipo_usuario>>(listaTipoUsuarios, HttpStatus.resolve(200));
        } catch (Exception e) {
            e.getMessage();
        }
        return new ResponseEntity<List<Tipo_usuario>>(HttpStatus.resolve(400));

    }
}

