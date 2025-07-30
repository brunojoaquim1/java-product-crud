package repository;
import model.Produto;
import java.util.ArrayList;
import java.util.List;

public class ProdutoRepository {
    private final List<Produto> produtos = new ArrayList<>();
    private int proximoID = 1;
    
    public void salvar(String nome, double preco){
        Produto p = new Produto(proximoID++, nome, preco);
        produtos.add(p);
    }
    
    public List<Produto> listarTodos(){
        return produtos;
    }
    
    public Produto buscarPorID(int id){
        return produtos.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }
    
    public void remover(int id){
        produtos.removeIf(p -> p.getId() == id);
    }
    
    public boolean atualizar(int id, String nome, double preco){
        Produto p = buscarPorID(id);
        if (p == null) return false;
        p.setNome(nome);
        p.setPreco(preco);
        return true;
    }
    
    public boolean isVazio(){
        return produtos.isEmpty();
    }
}
