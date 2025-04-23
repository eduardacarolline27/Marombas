package com.example.demo;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Marombas {
    private static Estoque estoque = new Estoque();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            mostrarMenu();
            int opcao = lerOpcao(scanner);

            try {
                switch (opcao) {
                    case 1:
                        adicionarNovoProduto();
                        break;
                    case 2:
                        removerProduto();
                        break;
                    case 3:
                        buscarProduto();
                        break;
                    case 4:
                        atualizarProduto();
                        break;
                    case 5:
                        listarProdutos();
                        break;
                    case 6:
                        System.out.println("Saindo do sistema de estoque.");
                        scanner.close();
                        return;
                }
            } catch (InputMismatchException e) {
               System.err.println("Erro: Entrada inválida. Por favor, digite um número inteiro.");
            } catch (Throwable e) {
                System.err.println("Ocorreu um erro inesperado: " + e.getMessage());
            }
        }
    }

    private static void mostrarMenu() {
        System.out.println("\n--- Sistema de Estoque de Suplementos ---");
        System.out.println("1. Adicionar Novo Produto");
        System.out.println("2. Remover Produto");
        System.out.println("3. Buscar Produto");
        System.out.println("4. Atualizar Produto");
        System.out.println("5. Listar Produtos");
        System.out.println("6. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static int lerOpcao(Scanner scanner) {
        while (true) {
            try {
                int opcao = scanner.nextInt();
                if (opcao >= 1 && opcao <= 6) {
                    scanner.nextLine();
                    return opcao;
                } else {
                    System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.err.println("Entrada inválida. Por favor, digite um número inteiro.");
                scanner.next();
            }
        }
    }

    private static void adicionarNovoProduto() {
        System.out.println("\n--- Adicionar Novo Produto ---");
        System.out.println("Tipo de produto (1 - Suplemento, 2 - Acessório):");
        int tipoProduto = lerOpcao(scanner);
        
        try{
            System.out.print("Nome: ");
            String nome = validarString(scanner.nextLine(), "Nome");
            System.out.print("Preço: ");
            double preco = scanner.nextDouble();
            System.out.print("Quantidade em Estoque: ");
            int quantidadeEstoque = scanner.nextInt();
            scanner.nextLine();
            if (tipoProduto == 1) {
                lerSuplemento(nome, preco, quantidadeEstoque);
            } else if (tipoProduto == 2) {
                lerAcessorio(nome, preco, quantidadeEstoque);
            } else {
                System.out.println("Tipo de produto inválido.");
            }
        }catch (InputMismatchException e){
             System.err.println("Erro: Tipo inválido. Por favor, digite um número inteiro.");
             scanner.next();
        }catch (IllegalArgumentException e){
            System.err.println("Erro: " + e.getMessage());
        }
        }
    
    private static void lerSuplemento(String nome, double preco, int quantidadeEstoque){
        try {
            System.out.print("Sabor: ");
            String sabor = scanner.nextLine();
            System.out.print("Tipo (Proteína, Creatina, Termogênico): ");
            String tipoSuplemento = validarString(scanner.nextLine(), "Tipo");
            estoque.adicionarProduto(new Suplemento(nome, preco, quantidadeEstoque, sabor, tipoSuplemento));
        } catch (IllegalArgumentException e){
            System.out.println("Sabor ou Tipo é inválido");
        }
    }

    private static void lerAcessorio(String nome, double preco, int quantidadeEstoque){
         try {
            System.out.print("Material: ");
            String material = scanner.nextLine();
            System.out.print("Utilidade: ");
            String utilidade = validarString(scanner.nextLine(), "Utilidade");
            estoque.adicionarProduto(new Acessorio(nome, preco, quantidadeEstoque, material, utilidade));
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
    private static String validarString(String input, String fieldName) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " não pode ser vazio.");
        }
        return input;}

    private static void removerProduto() throws ProdutoNaoEncontradoException {
        System.out.println("\n--- Remover Produto ---");
        System.out.print("Digite o ID do produto a ser removido: ");
        try {
            int idRemover = scanner.nextInt();
        } catch (InputMismatchException e) {
           System.err.println("Erro: ID inválido. Digite um número inteiro.");
            scanner.next(); // Limpar o buffer
        }
    }

    private static void buscarProduto() throws ProdutoNaoEncontradoException {
        System.out.println("\n--- Buscar Produto ---");
        System.out.print("Digite o ID do produto a ser buscado: ");
        try {
            int idBuscar = scanner.nextInt();
        } catch (InputMismatchException e) {
           System.err.println("Erro: ID inválido. Digite um número inteiro.");
        }
    }

    private static void atualizarProduto() {
        System.out.println("\n--- Atualizar Produto ---");
        System.out.print("Digite o ID do produto a ser atualizado: ");
        try {
            int idAtualizar = scanner.nextInt();
            Produto produtoExistente = estoque.buscarProduto(idAtualizar);
            atualizarDetalhesProduto(produtoExistente);
         } catch (InputMismatchException e) {
            System.err.println("Erro: Entrada inválida. Digite um número.");
        } catch (ProdutoNaoEncontradoException e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }

     private static void atualizarDetalhesProduto(Produto produtoExistente) throws ProdutoNaoEncontradoException{
        String novoNome;
        double novoPreco;
        int novaQuantidade;
        try{
             System.out.print("Novo nome (" + produtoExistente.getNome() + "): ");
             novoNome = validarString(scanner.nextLine(), "Nome");
             System.out.print("Novo preço (" + produtoExistente.getPreco() + "): ");
             novoPreco = scanner.nextDouble();
             System.out.print("Nova quantidade em estoque (" + produtoExistente.getQuantidadeEstoque() + "): ");
             novaQuantidade = scanner.nextInt();
             scanner.nextLine();
             if (produtoExistente instanceof Suplemento) {
                 atualizarSuplemento((Suplemento) produtoExistente);
             } else if (produtoExistente instanceof Acessorio) {
                 atualizarAcessorio((Acessorio) produtoExistente);
             } else{
                 produtoExistente.setNome(novoNome);
                 produtoExistente.setPreco(novoPreco);
                 produtoExistente.setQuantidadeEstoque(novaQuantidade);
             }
        }catch(InputMismatchException e){
            scanner.nextLine();
            throw new InputMismatchException();
        }catch(IllegalArgumentException e){
            throw new IllegalArgumentException();
        }
     }
        private static void atualizarSuplemento(Suplemento suplemento){
            System.out.print("Novo sabor (" + suplemento.getSabor() + "): ");
            String novoSabor = validarString(scanner.nextLine(), "Sabor");
            System.out.print("Novo tipo (" + suplemento.getTipo() + "): ");
            String novoTipo = validarString(scanner.nextLine(), "Tipo");
            suplemento.setSabor(novoSabor);
            suplemento.setTipo(novoTipo);
        }
        private static void atualizarAcessorio(Acessorio acessorio){
             System.out.print("Novo material (" + acessorio.getMaterial() + "): ");
             String novoMaterial = validarString(scanner.nextLine(), "Material");
             System.out.print("Nova utilidade (" + acessorio.getUtilidade() + "): ");
             String novaUtilidade = validarString(scanner.nextLine(), "Utilidade");
             acessorio.setMaterial(novoMaterial);
             acessorio.setUtilidade(novaUtilidade);
        }
    private static void listarProdutos() {
        estoque.listarProdutos();
    }
}
