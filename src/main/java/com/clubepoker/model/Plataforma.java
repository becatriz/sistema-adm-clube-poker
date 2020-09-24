package com.clubepoker.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PLATAFORMA", schema = "seguranca")
public class Plataforma implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="genPlataforma", allocationSize = 1,  sequenceName = "seguranca.plataforma_id_seq")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "genPlataforma" )
    @Column(name="ID", nullable=false)
    private Integer id;

    @Column(name="NOME_APLICATIVO", nullable=false)
    private String nome_aplicativo;




    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome_aplicativo() {
        return nome_aplicativo;
    }

    public void setNome_aplicativo(String nome_aplicativo) {
        this.nome_aplicativo = nome_aplicativo;
    }


}
