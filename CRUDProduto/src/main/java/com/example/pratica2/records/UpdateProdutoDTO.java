package com.example.pratica2.records;

import com.example.pratica2.models.Categoria;

public record UpdateProdutoDTO (String nome, String descricao, double preco, Categoria categoria, boolean status, String imagemUrl){
}
