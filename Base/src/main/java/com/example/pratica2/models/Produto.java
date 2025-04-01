package com.example.pratica2.models;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String descricao;

    private double preco;

    // @Embedded
    private String categoria;

    private boolean status;

    private String imagemUrl;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    private List<ItensComanda> ItensComanda;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    private List<ItensCarrinho> ItensCarrinho;
}
