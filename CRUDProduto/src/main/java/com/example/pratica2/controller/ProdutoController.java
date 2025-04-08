package com.example.pratica2.controller;

import com.example.pratica2.models.Produto;
import com.example.pratica2.records.GetProdutoDTO;
import com.example.pratica2.records.UpdateProdutoDTO;
import com.example.pratica2.repository.ProdutoRepository;
import com.example.pratica2.service.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/produto")
public class ProdutoController {

    private final ProdutoService produtoService;
    private final ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoService produtoService, ProdutoRepository produtoRepository) {
        this.produtoService = produtoService;
        this.produtoRepository = produtoRepository;
    }

    @GetMapping("/list")
    public String listar(Model model) {
        model.addAttribute("produtos", produtoRepository.findAll());
        return "list";
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetProdutoDTO> getById(@PathVariable Long id) {
        return produtoService.getProdutoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public String create(@ModelAttribute Produto produto) {
        System.out.println(produto.toString());
        produtoRepository.save(produto);a
        return "redirect:/produto/list";
    }

    @GetMapping("/editar/{id}")
    public String formEditar (@PathVariable Long id, Model model) {
        Produto produto = produtoRepository.findById(id).get();
        model.addAttribute("produto", produto);
        return "insert";
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (produtoRepository.existsById(id)) {
            produtoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}