package ProdutoService;

import repository.ProdutoRepository;

public class ProdutoService {
    private final ProdutoRepository repo = new ProdutoRepository();
    
    public void adicionar(String nome, double preco){
        repo.salvar(nome, preco);
        System.out.println("Produto adicionado com sucesso");
    }
    
    public void listar(){
        if(repo.isVazio()){
            System.out.println("Nenhum produto cadastrado.");
            return;
        }
        repo.listarTodos().forEach(System.out::println);
    }
    
    public void atualizar(int id, String nome, double preco){
        boolean sucesso = repo.atualizar(id, nome, preco);
        
        System.out.println(sucesso ? "Produto atualizado." : "Produto não encontrado.");
    }

    public void remover(int id) {
        repo.remover(id);
        System.out.println("Produto removido.");
    }
}
