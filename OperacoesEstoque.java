package com.example.demo;

interface OperacoesEstoque {
    void adicionarProduto(Produto produto);
    void removerProduto(int id) throws ProdutoNaoEncontradoException;
    Produto buscarProduto(int id) throws ProdutoNaoEncontradoException;
    void atualizarProduto(Produto produto) throws ProdutoNaoEncontradoException;
    void listarProdutos();
}
