
// -------------------- 1. CLASSES E OBJETOS --------------------

// Classe base para todos os produtos de estoque

abstract class Produto{
    private int id;
     private String nome;
    private double preco;
    private int quantidadeEstoque;

    public Produto( String nome, double preco, int quantidadeEstoque) {
        this.nome = nome;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
    }

    // Getters (Encapsulamento)
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    // Setters (Encapsulamento)
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public void setId(int id) {
        this.id = id;
    }

    public abstract String obterInformacoesAdicionais();
}
