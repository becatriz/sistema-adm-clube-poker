package com.clubepoker.controller;

import com.clubepoker.exception.InvalidLoginException;
import com.clubepoker.model.Cliente;
import com.clubepoker.repository.ClienteRepository;
import com.clubepoker.service.AutenticacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private AutenticacaoService autenticacaoService;


    @GetMapping(value = "/listaClientes", produces = "application/json")
    public ResponseEntity<List<Cliente>> listaTodosClientes(@RequestHeader String Authorization){
        try {
            boolean autenticado = autenticacaoService.validate(Authorization);
            if(autenticado) {
                List<Cliente> listaDeClientes = (List<Cliente>) clienteRepository.findAll();
                return new ResponseEntity<List<Cliente>>(listaDeClientes, HttpStatus.OK);
            }
        }catch (InvalidLoginException e){
            return new ResponseEntity<List<Cliente>>(HttpStatus.UNAUTHORIZED);
        } catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<List<Cliente>>(HttpStatus.NOT_FOUND);

    }

    @PostMapping(value = "/cadastrarCliente", produces = "application/json")
    public ResponseEntity<Cliente>cadastrarCliente(@RequestBody Cliente cliente, @RequestHeader String Authorization){

        try {
            boolean autenticado = autenticacaoService.validate(Authorization);
            if(autenticado) {
                cliente.setPlataforma(cliente.getPlataforma());
                Cliente clienteSalvo = clienteRepository.save(cliente);
                return new ResponseEntity<Cliente>(clienteSalvo, HttpStatus.resolve(201));
            }
        }catch (InvalidLoginException e){
            return new ResponseEntity<Cliente>(HttpStatus.UNAUTHORIZED);
        }
        catch (Exception e){
            e.getMessage();
        }
        return new ResponseEntity<Cliente>(HttpStatus.NOT_FOUND);

    }
    @PutMapping(value = "/atualizarCliente", produces = "application/json")
    public ResponseEntity<Cliente>atualizarCliente(@RequestBody Cliente cliente, @RequestHeader String Authorization){

        try {
            boolean autenticado = autenticacaoService.validate(Authorization);
            if(autenticado) {
                cliente.setPlataforma(cliente.getPlataforma());
                Cliente clienteAtualizado = clienteRepository.save(cliente);
                return new ResponseEntity<Cliente>(clienteAtualizado, HttpStatus.OK);
            }
        }catch (InvalidLoginException e){
            return new ResponseEntity<Cliente>(HttpStatus.UNAUTHORIZED);
        }
        catch (Exception e){
            e.getMessage();
        }
        return new ResponseEntity<Cliente>( HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/deletarCliente/{idCliente}", produces = "application/json")
    public ResponseEntity<Cliente>deletarCliente(@PathVariable Integer idCliente, @RequestHeader String Authorization){

        try {
            boolean autenticado = autenticacaoService.validate(Authorization);
            if(autenticado) {
                clienteRepository.deleteById(idCliente);
                return new ResponseEntity<Cliente>(HttpStatus.OK);
            }
        }catch (InvalidLoginException e){
            return new ResponseEntity<Cliente>(HttpStatus.UNAUTHORIZED);
        }
        catch (Exception e){
            e.getMessage();
        }
        return  new ResponseEntity<Cliente>( HttpStatus.NOT_FOUND);
    }


}
