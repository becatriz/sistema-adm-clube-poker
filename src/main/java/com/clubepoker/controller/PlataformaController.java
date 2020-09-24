package com.clubepoker.controller;


import com.clubepoker.model.Cliente;
import com.clubepoker.model.Plataforma;
import com.clubepoker.repository.PlataformaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/plataforma")
public class PlataformaController {

    @Autowired
    private PlataformaRepository plataformaRepository;

    @GetMapping(value = "/listaPlataformas", produces = "application/json")
    public ResponseEntity<List<Plataforma>> listaPlataformas(){
        try {
            List<Plataforma> listaPlataformas = (List<Plataforma>) plataformaRepository.findAll();
            return new ResponseEntity<List<Plataforma>>(listaPlataformas, HttpStatus.resolve(200));
        }catch (Exception e){
            e.getMessage();
        }
        return new ResponseEntity<List<Plataforma>>(HttpStatus.resolve(400));

    }
}
