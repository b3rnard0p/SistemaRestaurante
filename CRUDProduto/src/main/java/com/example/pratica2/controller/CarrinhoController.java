package com.example.pratica2.controller;

import com.example.pratica2.models.ItensCarrinhoId;
import com.example.pratica2.service.CarrinhoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/carrinho")
public class CarrinhoController {

    private final CarrinhoService carrinhoService;

    public CarrinhoController(CarrinhoService carrinhoService) {
        this.carrinhoService = carrinhoService;
    }

    @GetMapping("/carrinho")
    public String visualizarCarrinho(Model model) {
        var itens = carrinhoService.listarItensCarrinho();
        double total = carrinhoService.calcularValorTotal();

        model.addAttribute("itens", itens);
        model.addAttribute("total", total);
        return "carrinho";
    }

    @PostMapping("/adicionar/{produtoId}")
    public String adicionarAoCarrinho(@PathVariable Long produtoId) {
        carrinhoService.adicionarProduto(produtoId);
        return "redirect:/carrinho";
    }

    // Refactored code
    @PostMapping("/remover/{itensCarrinhoId}")
    public String removerItemDoCarrinho(@PathVariable ItensCarrinhoId itensCarrinhoId) {
        carrinhoService.removerProduto(itensCarrinhoId);
        return "redirect:/carrinho";
    }

    @PostMapping("/limpar")
    public String limparCarrinho() {
        carrinhoService.limparCarrinho();
        return "redirect:/carrinho";
    }

    @PostMapping("/finalizar")
    public String finalizarPedido() {
        // Puxar do comandaService quando fizer
        carrinhoService.limparCarrinho();
        return "redirect:/carrinho?finalizado=true";
    }
}
