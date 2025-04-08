package com.example.pratica2.service;

import com.example.pratica2.models.Produto;
import com.example.pratica2.records.CreateProdutoDTO;
import com.example.pratica2.records.GetProdutoDTO;
import com.example.pratica2.records.UpdateProdutoDTO;
import com.example.pratica2.repository.ProdutoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Produto createProduto(CreateProdutoDTO produtoDTO) {

        Produto novoProduto = new Produto();
        novoProduto.setNome(produtoDTO.nome());
        novoProduto.setDescricao(produtoDTO.descricao());
        novoProduto.setPreco(produtoDTO.preco());
        novoProduto.setCategoria(produtoDTO.categoria());
        novoProduto.setStatus(produtoDTO.status());
        novoProduto.setImagemUrl(produtoDTO.imagemUrl());

        return produtoRepository.save(novoProduto);
    }

    public List<GetProdutoDTO> getAllProdutos() {
        return produtoRepository.findAll().stream()
                .map(produto -> new GetProdutoDTO(
                        produto.getNome(),
                        produto.getDescricao(),
                        produto.getPreco(),
                        produto.getCategoria(),
                        produto.isStatus(),
                        produto.getImagemUrl()
                ))
                .toList();
    }

    public Optional<GetProdutoDTO> getProdutoById(Long id) {
        return produtoRepository.findById(id)
                .map(produto -> new GetProdutoDTO(
                        produto.getNome(),
                        produto.getDescricao(),
                        produto.getPreco(),
                        produto.getCategoria(),
                        produto.isStatus(),
                        produto.getImagemUrl()
                ));
    }

    public GetProdutoDTO updateUser(Long id, UpdateProdutoDTO produtoDTO) {
        return produtoRepository.findById(id)
                .map(produto -> {
                    produto.setNome(produtoDTO.nome());
                    produto.setDescricao(produtoDTO.descricao());
                    produto.setPreco(produtoDTO.preco());
                    produto.setCategoria(produtoDTO.categoria());
                    produto.setStatus(produtoDTO.status());
                    produto.setImagemUrl(produtoDTO.imagemUrl());

                    Produto updatedProduto = produtoRepository.save(produto);
                    return new GetProdutoDTO(
                            updatedProduto.getNome(),
                            updatedProduto.getDescricao(),
                            updatedProduto.getPreco(),
                            updatedProduto.getCategoria(),
                            updatedProduto.isStatus(),
                            updatedProduto.getImagemUrl()
                    );
                })
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Produto não encontrado"
                ));
    }
}
