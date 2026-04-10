package com.barella.Barella.services;

import com.barella.Barella.models.ProdutoModel;
import com.barella.Barella.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    public ProdutoModel buscarProdutoPorId(Long id) {
        return produtoRepository.findById(id).orElse(null);
    }

    public List<ProdutoModel> listarProdutos() {
        return produtoRepository.findAll();
    }

    public void deletarProduto(Long id) {
        produtoRepository.deleteById(id);
    }

    public ProdutoModel salvarProduto(ProdutoModel produtoModel) {
        return produtoRepository.save(produtoModel);
    }

    public ProdutoModel atualizarProduto(Long id, ProdutoModel produtoModel) {
        ProdutoModel produto = produtoRepository.findById(id).orElse(null);
        produto.setNome(produtoModel.getNome());
        produto.setPreco(produtoModel.getPreco());
        produto.setDescricao(produtoModel.getDescricao());
        produto.setStatus(produtoModel.getStatus());
        return produtoRepository.save(produto);
    }
}
