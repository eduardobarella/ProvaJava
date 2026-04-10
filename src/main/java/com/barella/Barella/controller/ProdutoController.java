package com.barella.Barella.controller;

import com.barella.Barella.models.ProdutoModel;
import com.barella.Barella.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    @Autowired
    private ProdutoService produtoService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<ProdutoModel> buscarProdutoPorId(@PathVariable Long id) {
        ProdutoModel produto = produtoService.buscarProdutoPorId(id);
        return ResponseEntity.ok().body(produto);
    }
    @GetMapping
    public ResponseEntity<List<ProdutoModel>> listarProdutos() {
        List<ProdutoModel> produtos = produtoService.listarProdutos();
        return ResponseEntity.ok().body(produtos);
    }
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
        produtoService.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping
    public ResponseEntity<ProdutoModel> salvarProduto(@RequestBody ProdutoModel produtoModel) {
        ProdutoModel requeste = produtoService.salvarProduto(produtoModel);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/produtos/{id}")
                .buildAndExpand(requeste.getId())
                .toUri();
        return ResponseEntity.created(uri).body(requeste);
    }
    @PutMapping(path = "/{id}")
    public ResponseEntity<ProdutoModel> atualizarProduto(@RequestBody ProdutoModel produtoModel, @PathVariable Long id) {
        ProdutoModel produto = produtoService.atualizarProduto(id, produtoModel);
        return ResponseEntity.ok().body(produto);
    }
}
