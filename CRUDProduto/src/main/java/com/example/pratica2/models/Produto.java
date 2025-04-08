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

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    private boolean status;

    private String imagemUrl;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    private List<ItensComanda> ItensComanda;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    private List<ItensCarrinho> ItensCarrinho;

    public Produto() {
    }

    public Produto(Long id, String descricao, double preco, Categoria categoria, boolean status, String imagemUrl, List<com.example.pratica2.models.ItensComanda> itensComanda, List<com.example.pratica2.models.ItensCarrinho> itensCarrinho) {
        this.id = id;
        this.descricao = descricao;
        this.preco = preco;
        this.categoria = categoria;
        this.status = status;
        this.imagemUrl = imagemUrl;
        ItensComanda = itensComanda;
        ItensCarrinho = itensCarrinho;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getImagemUrl() {
        return imagemUrl;
    }

    public void setImagemUrl(String imagemUrl) {
        this.imagemUrl = imagemUrl;
    }

    public List<ItensComanda> getItensComanda() {
        return ItensComanda;
    }

    public void setItensComanda(List<ItensComanda> itensComanda) {
        ItensComanda = itensComanda;
    }

    public List<ItensCarrinho> getItensCarrinho() {
        return ItensCarrinho;
    }

    public void setItensCarrinho(List<ItensCarrinho> itensCarrinho) {
        ItensCarrinho = itensCarrinho;
    }
}
