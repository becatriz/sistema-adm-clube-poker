package com.clubepoker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "USUARIO", schema = "seguranca")
public class Usuario {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="genUsuario", allocationSize = 1,  sequenceName = "seguranca.usuario_id_seq")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "genUsuario" )
    private Integer id;

    @Column(name="LOGIN", nullable=false)
    private String login;

    @Column(name="SENHA", nullable=false)
    private String senha;


    @JoinColumn(name = "ID_CLIENTE" , referencedColumnName = "ID", nullable = true, foreignKey = @ForeignKey(name = "ID_CLIENTE") )
    @ManyToOne(optional = false)
    private Cliente cliente;


    @OneToOne
    @JoinColumn(name = "ID_TIPO_USUARIO" , referencedColumnName = "ID", nullable = true, foreignKey = @ForeignKey(name = "USUARIO_FK") )
    private Tipo_usuario tipo_usuario;

    @Transient
    private String token;
    @Transient
    private String tipo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Tipo_usuario getUsuario() {
        return tipo_usuario;
    }

    public void setUsuario(Tipo_usuario usuario) {
        this.tipo_usuario = usuario;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setTipo_usuario(Tipo_usuario tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }

    public Tipo_usuario getTipo_usuario() {
        return tipo_usuario;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario)) return false;
        Usuario usuario = (Usuario) o;
        return getId().equals(usuario.getId()) &&
                getLogin().equals(usuario.getLogin()) &&
                getSenha().equals(usuario.getSenha());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLogin(), getSenha());
    }
}
