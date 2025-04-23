package com.example.demo;

class Suplemento extends Produto {
    private String sabor;
    private String tipo;

    public Suplemento(String nome, double preco, int quantidadeEstoque, String sabor, String tipo){
        super( nome, preco, quantidadeEstoque);
        this.sabor = sabor;
        this.tipo = tipo;
    }

    @Override
    public String obterInformacoesAdicionais(){
        return "Sabor: " + sabor + ", Tipo: " + tipo;
    }
    @Override
    public String toString() {
        return "Suplemento{sabor='" + sabor + "', tipo='" + tipo + "'}";
    }

    public String getSabor() {
        return sabor;
    }

    public void setSabor(String sabor) {
        this.sabor = sabor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}

