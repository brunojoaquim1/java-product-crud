/*-- Autor....: Bruno Joaquim
 *-- Data.....: 31/07/2025
 *-- Objetivo.: Classe main correspondete ao acesso principal da aplicação 
*/

package view;

import java.util.Scanner;
import service.ProdutoService;
import java.sql.Connection;
import database.databaseUtil;
import java.sql.SQLException;
import database.databaseUtil;


public class Main {


    public static void main(String[] args) {
        ProdutoService service = new ProdutoService();
        try {
            databaseUtil.initBanco();
        } catch (SQLException e) {
            System.out.println("Erro ao inicializar o banco: " + e.getMessage());
        }
        
        executarMenu(service);
    }

    public static void executarMenu(ProdutoService service) {
        Scanner sc = new Scanner(System.in);
        int opcao;
        
        try {
            databaseUtil.initBanco();
        } catch (SQLException e) {
            System.out.println("Erro ao iniciar banco: " + e.getMessage());
        }

        do {
            exibirMenu();
            System.out.print("Escolha: ");
            opcao = sc.nextInt();
            sc.nextLine(); // limpar quebra de linha

            switch (opcao) {
                case 1 -> adicionarProduto(sc, service);
                case 2 -> service.listar();
                case 3 -> atualizarProduto(sc, service);
                case 4 -> removerProduto(sc, service);
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida.");
            }

        } while (opcao != 0);

        sc.close();
    }

    public static void exibirMenu() {
        System.out.println("\n--- MENU PRODUTOS ---");
        System.out.println("1 - Adicionar");
        System.out.println("2 - Listar");
        System.out.println("3 - Atualizar");
        System.out.println("4 - Remover");
        System.out.println("0 - Sair");
    }

    public static void adicionarProduto(Scanner sc, ProdutoService service) {
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Preço: ");
        double preco = sc.nextDouble();
        System.out.print("Quantidade: ");
        int quantidade = sc.nextInt();

        service.adicionar(nome, preco, quantidade);
    }

    public static void atualizarProduto(Scanner sc, ProdutoService service) {
        System.out.print("ID do produto: ");
        int id = sc.nextInt();
        sc.nextLine(); // limpar buffer
        System.out.print("Novo nome: ");
        String novoNome = sc.nextLine();
        System.out.print("Novo preço: ");
        double novoPreco = sc.nextDouble();
        System.out.print("Nova quantidade: ");
        int novaQuantidade = sc.nextInt();
        service.atualizar(id, novoNome, novoPreco, novaQuantidade);
    }

    public static void removerProduto(Scanner sc, ProdutoService service) {
        System.out.print("ID do produto: ");
        int id = sc.nextInt();
        service.remover(id);
    }
}
