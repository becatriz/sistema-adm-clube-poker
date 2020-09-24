package com.clubepoker.service;

import com.clubepoker.dto.DadosLogin;
import com.clubepoker.exception.ExpiredTokenException;
import com.clubepoker.exception.InvalidLoginException;
import com.clubepoker.model.Usuario;
import com.clubepoker.repository.UsuarioRepository;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AutenticacaoService {

    private final UsuarioRepository usuarioRepository;
    private TokenService tokenService;

    public AutenticacaoService(UsuarioRepository usuarioRepository, TokenService tokenService){
        this.usuarioRepository = usuarioRepository;
        this.tokenService = tokenService;
    }


    public Usuario autenticar(DadosLogin user){
        Usuario usuario = usuarioRepository.findByUsuario(user.getLogin(), user.getSenha());
        if(user.getLogin().equals(usuario.getLogin()) &&
        user.getSenha().equals(usuario.getSenha())){
            String tokenUsuario = tokenService.geraToken(usuario);
            usuario.setToken(tokenUsuario);
            return usuario;
        }else{
            return null;
        }
    }


    public boolean validate(String token) throws InvalidLoginException {
        try {
            String tokenTratado = token.replace("Bearer","");
            Claims claims = tokenService.decodeToken(tokenTratado);
            if(claims.getExpiration().after(new Date(System.currentTimeMillis())))
                return true;
        }catch (ExpiredTokenException et){
            et.printStackTrace();
            throw et;
        }catch (Exception e){
            e.printStackTrace();
            throw  new InvalidLoginException();
        }
        return false;
    }

}
