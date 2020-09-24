package com.clubepoker.controller;

import com.clubepoker.dto.DadosLogin;
import com.clubepoker.dto.UsuarioAutenticadoDTO;
import com.clubepoker.exception.InvalidLoginException;
import com.clubepoker.model.Usuario;
import com.clubepoker.repository.UsuarioRepository;
import com.clubepoker.service.AutenticacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AutenticacaoService autenticacaoService;


    @GetMapping(value = "/listaUsuarios", produces = "application/json")
    public ResponseEntity<List<Usuario>> listaTodosUsuarios(@RequestHeader String Authorization) {

        try {
            boolean autenticado = autenticacaoService.validate(Authorization);
            if(autenticado) {
                List<Usuario> listaDeUsuarios = (List<Usuario>) usuarioRepository.findAll();
                return new ResponseEntity<List<Usuario>>(listaDeUsuarios, HttpStatus.OK);
            }
        }catch (InvalidLoginException e){
            return new ResponseEntity<List<Usuario>>(HttpStatus.UNAUTHORIZED);
        }
        catch (Exception e){
            e.getMessage();
        }

        return new ResponseEntity<List<Usuario>>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/cadastrarUsuario", produces = "application/json")
    public ResponseEntity<Usuario>cadastrarUsuario(@RequestBody Usuario usuario, @RequestHeader String Authorization){

        try {
            boolean autenticado = autenticacaoService.validate(Authorization);
            if(autenticado) {
                usuario.setCliente(usuario.getCliente());
                usuario.setTipo_usuario(usuario.getTipo_usuario());
                Usuario usuarioSalvo = usuarioRepository.save(usuario);
                return new ResponseEntity<Usuario>(usuarioSalvo, HttpStatus.resolve(201));
            }
        }catch (InvalidLoginException e){
            return new ResponseEntity<Usuario>(HttpStatus.UNAUTHORIZED);
        }
        catch (Exception e){
            e.getMessage();
        }

        return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
    }


    @PutMapping(value = "/atualizarUsuario", produces = "application/json")
    public ResponseEntity<Usuario>atualizarUsuario(@RequestBody Usuario usuario, @RequestHeader String Authorization){

        try {
            boolean autenticado = autenticacaoService.validate(Authorization);
            if(autenticado) {
                usuario.setCliente(usuario.getCliente());
                usuario.setTipo_usuario(usuario.getTipo_usuario());
                Usuario usuarioAtualizado = usuarioRepository.save(usuario);
                return new ResponseEntity<Usuario>(usuarioAtualizado, HttpStatus.OK);
            }
        }catch (InvalidLoginException e){
            return new ResponseEntity<Usuario>(HttpStatus.UNAUTHORIZED);
        }
        catch (Exception e){
            e.getMessage();
        }


        return new ResponseEntity<Usuario>( HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/deletarUsuario/{idUsuario}", produces = "application/json")
    public ResponseEntity<Usuario>deletarUsuario(@PathVariable Integer idUsuario, @RequestHeader String Authorization){

        try {
            boolean autenticado = autenticacaoService.validate(Authorization);
            if(autenticado) {
                usuarioRepository.deleteById(idUsuario);
                return new ResponseEntity<Usuario>(HttpStatus.OK);
            }

        }catch (InvalidLoginException e){
            return new ResponseEntity<Usuario>(HttpStatus.UNAUTHORIZED);
        }
        catch (Exception e){
            e.getMessage();
        }
        return  new ResponseEntity<Usuario>( HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/login", produces = "application/json")
    public ResponseEntity<UsuarioAutenticadoDTO> loginUsuario(@RequestBody DadosLogin usuario) {

        Usuario user =  autenticacaoService.autenticar(usuario);

        return new ResponseEntity<>(UsuarioAutenticadoDTO.userDTO(user, "Bearer"), HttpStatus.ACCEPTED);
    }
}
