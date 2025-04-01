package com.example.pratica2.models;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String Nome;

    // @Embedded
    private String cargo;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Comanda comanda;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private CarrinhoDeCompra carrinhoDeCompra;
}