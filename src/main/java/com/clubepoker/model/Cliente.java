package com.clubepoker.model;

import javassist.bytecode.ByteArray;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "CLIENTE", schema = "seguranca")
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="genCliente", allocationSize = 1,  sequenceName = "seguranca.cliente_id_seq")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "genCliente")
    @Column(name="ID", nullable=false)
    private Integer id;

    @Column(name="NOME_CLUBE", nullable=false)
    private String nome_clube;

    @Column(name="ID_CLUBE", nullable=false)
    private Integer id_clube;


//    @Column(name="LOGO", nullable=true)
//    private byte logo;

    @Column(name="RESPONSAVEL", nullable=false)
    private String responsavel;

    @Column(name="EMAIL", nullable=false)
    private String email;


    @Column(name="CELULAR", nullable=false)
    private String celular;

    @OneToOne
    @JoinColumn(name = "ID_PLATAFORMA" , referencedColumnName = "ID", nullable = true, foreignKey = @ForeignKey(name = "CLIENTE_FK") )
    private Plataforma plataforma;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome_clube() {
        return nome_clube;
    }

    public void setNome_clube(String nome_clube) {
        this.nome_clube = nome_clube;
    }

//    public byte getLogo() {
//        return logo;
//    }
//
//    public void setLogo(byte logo) {
//        this.logo = logo;
//    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public void setPlataforma(Plataforma plataforma) {
        this.plataforma = plataforma;
    }

    public Plataforma getPlataforma() {
        return plataforma;
    }

    public void setId_clube(Integer id_clube) {
        this.id_clube = id_clube;
    }

    public Integer getId_clube() {
        return id_clube;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cliente)) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(getId(), cliente.getId()) &&
                Objects.equals(getNome_clube(), cliente.getNome_clube());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNome_clube());
    }
}
