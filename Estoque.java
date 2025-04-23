import java.util.ArrayList;
import java.util.List;

public class Estoque implements OperacoesEstoque {
    private List<Produto> produtos = new ArrayList<>();
    private int proximoId = 1;

    @Override
    public void adicionarProduto(Produto produto) {
        produto.setId(proximoId++);
        produtos.add(produto);
        System.out.println("Produto adicionado ao estoque.");
    }

    @Override
    public void removerProduto(int id) throws ProdutoNaoEncontradoException {
        Produto produtoParaRemover = null;
        for (Produto produto : produtos) {
            if (produto.getId() == id) {
                produtoParaRemover = produto;
                break;
            }
        }
        if(produtoParaRemover != null) produtos.remove(produtoParaRemover);
        else throw new ProdutoNaoEncontradoException("Produto com ID " + id + " não encontrado no estoque.");
    }

    @Override
    public Produto buscarProduto(int id) throws ProdutoNaoEncontradoException {
        for (Produto produto : produtos) {
            if (produto.getId() == id) {
                return produto;
            }
        }
        throw new ProdutoNaoEncontradoException("Produto com ID " + id + " não encontrado no estoque.");
    }

    @Override
    public void atualizarProduto(Produto produtoAtualizado) throws ProdutoNaoEncontradoException {
            Produto produtoExistente = buscarProduto(produtoAtualizado.getId());

        produtoExistente.setNome(produtoAtualizado.getNome());
        produtoExistente.setPreco(produtoAtualizado.getPreco());
        produtoExistente.setQuantidadeEstoque(produtoAtualizado.getQuantidadeEstoque());

        if(produtoExistente instanceof Suplemento && produtoAtualizado instanceof Suplemento){
            ((Suplemento) produtoExistente).setSabor(((Suplemento) produtoAtualizado).getSabor());
            ((Suplemento) produtoExistente).setTipo(((Suplemento) produtoAtualizado).getTipo());
        } else if(produtoExistente instanceof Acessorio && produtoAtualizado instanceof Acessorio){
            ((Acessorio) produtoExistente).setMaterial(((Acessorio) produtoAtualizado).getMaterial());
            ((Acessorio) produtoExistente).setUtilidade(((Acessorio) produtoAtualizado).getUtilidade());
        }

        System.out.println("Produto com ID " + produtoAtualizado.getId() + " atualizado.");
    }

    @Override
    public void listarProdutos() {
        if (produtos.isEmpty()) {
            System.out.println("O estoque está vazio.");
        } else {
            System.out.println("--- Produtos no Estoque ---");
            for (Produto produto : produtos) {
                System.out.println(produto.toString() + " - " + produto.obterInformacoesAdicionais());
            }
        }
    }
}
