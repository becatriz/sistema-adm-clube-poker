package com.clubepoker.dto;

import com.clubepoker.model.Usuario;


public class UsuarioAutenticadoDTO {


    private String login;
    private String tipo;
    private String token;
    private String tipoUsuario;
    private String clube;

    public UsuarioAutenticadoDTO(String login, String tipoUsuario, String tipo, String token, String clube) {
        this.login = login;
        this.tipoUsuario = tipoUsuario;
        this.tipo = tipo;
        this.token = token;
        this.clube = clube;
    }

    public UsuarioAutenticadoDTO() {
    }

    public String getLogin() {
        return login;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public String getTipo() {
        return tipo;
    }


    public String getToken() {
        return token;
    }

    public String getClube() {
        return clube;
    }

    public static UsuarioAutenticadoDTO userDTO(Usuario usuario, String tipo){
        String nomeClube;
        if(usuario.getCliente() == null){
            nomeClube = "";
        }else{
            nomeClube = usuario.getCliente().getNome_clube();
        }


        return  new UsuarioAutenticadoDTO(usuario.getLogin(), usuario.getTipo_usuario().getTipo(), tipo, usuario.getToken(),nomeClube );
    }
}
