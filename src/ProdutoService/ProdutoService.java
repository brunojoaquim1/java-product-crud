package ProdutoService;

import java.util.ArrayList;
import java.util.List;
import Produto.Produto;


public class ProdutoService {

    private List<Produto> produtos = new ArrayList<>();
    private int proximoID = 1;

    public void adicionar(String nome, double preco) {
        Produto p = new Produto(proximoID++, nome, preco);
        produtos.add(p);
        System.out.println("Produto adicionado com sucesso.");
    }

    public void listar() {
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }
        produtos.forEach(System.out::println);
    }
    
    public void atualizar (int id, String nome, double preco){
        Produto p = buscarPorID(id);
        if (p != null){
            p.setNome(nome);
            p.setPreco(preco);
            System.out.println("Produto Atualizado");
        } else {
            System.out.println("Produto não encontrado");
        }
    }
    
    public void remover(int id){
        Produto p = buscarPorID(id);
        if (p != null){
            produtos.remove(p);
            System.out.println();
        }
    }
    
    private Produto buscarPorID(int id){
        return produtos.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
