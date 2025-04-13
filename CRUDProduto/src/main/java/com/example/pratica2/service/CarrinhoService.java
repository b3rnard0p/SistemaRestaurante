package com.example.pratica2.service;

import com.example.pratica2.models.CarrinhoDeCompra;
import com.example.pratica2.models.ItensCarrinho;
import com.example.pratica2.models.ItensCarrinhoId;
import com.example.pratica2.models.Produto;
import com.example.pratica2.repository.CarrinhoDeCompraRepository;
import com.example.pratica2.repository.ItensCarrinhoRepository;
import com.example.pratica2.repository.ProdutoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CarrinhoService {
    private final CarrinhoDeCompraRepository carrinhoRepository;
    private final ItensCarrinhoRepository itensCarrinhoRepository;
    private final ProdutoRepository produtoRepository;

    private final Long carrinhoId = 1L; // id fixo para testes

    public CarrinhoService(CarrinhoDeCompraRepository carrinhoRepository,
                           ItensCarrinhoRepository itensCarrinhoRepository,
                           ProdutoRepository produtoRepository) {
        this.carrinhoRepository = carrinhoRepository;
        this.itensCarrinhoRepository = itensCarrinhoRepository;
        this.produtoRepository = produtoRepository;
    }

    public CarrinhoDeCompra getCarrinho() {
        return carrinhoRepository.findById(carrinhoId)
                .orElseGet(() -> {
                   CarrinhoDeCompra carrinho = new CarrinhoDeCompra();
                   carrinho.setId(carrinhoId);
                   return carrinhoRepository.save(carrinho);
                });
    }

    public void adicionarProduto(Long produtoId) {
        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));

        CarrinhoDeCompra carrinho = getCarrinho();

        Optional<ItensCarrinho> existente = carrinho.getItensCarrinho().stream()
                .filter(i -> i.getProduto().getId().equals(produtoId))
                .findFirst();

        if (existente.isPresent()) {
            ItensCarrinho item = existente.get();
            item.setQuantidade(item.getQuantidade() + 1);
            itensCarrinhoRepository.save(item);
        } else {
            ItensCarrinho novoItem = new ItensCarrinho();
            novoItem.setProduto(produto);
            novoItem.setQuantidade(1);
            carrinho.getItensCarrinho().add(novoItem);
            itensCarrinhoRepository.save(novoItem);
        }
    }

    public void removerProduto(ItensCarrinhoId produtoId) {
        if (!itensCarrinhoRepository.existsById(produtoId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado");
        }
        itensCarrinhoRepository.deleteById(produtoId);
    }

    public void limparCarrinho() {
        CarrinhoDeCompra carrinho = getCarrinho();
        carrinho.getItensCarrinho().forEach(itensCarrinhoRepository::delete);
    }

    public List<ItensCarrinho> listarItensCarrinho() {
        return getCarrinho().getItensCarrinho();
    }

    public double calcularValorTotal() {
        return listarItensCarrinho().stream()
                .mapToDouble(item -> item.getProduto().getPreco() * item.getQuantidade())
                .sum();
    }
}
