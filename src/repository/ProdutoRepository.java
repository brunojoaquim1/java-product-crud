/*-- Autor....: Bruno Joaquim
 *-- Data.....: 31/07/2025
 *-- Objetivo.: Classe de manipulação de dados do objeto Produto 
*/

package repository;
import model.Produto;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import database.databaseUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;


public class ProdutoRepository {
    
    private final String arquivo = "produtos.json";
    private final Gson gson = new Gson();
    //private  List<Produto> produtos = new ArrayList<>();
    
    private int proximoID = 1;
    
    /*
    public void salvar(String nome, double preco){
        Produto p = new Produto(proximoID++, nome, preco);
        produtos.add(p);
        salvarNoArquivo();
    }*/
    
    public void salvar(String nome, double preco){
        inserirDB(nome, preco);
    }

    public ProdutoRepository() {
        //carregarDoArquivo();
    }
    
    public List<Produto> listarTodos() {
        return listarDB();
    }
    
   /* public Produto buscarPorID(int id){
        return produtos.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }*/
    /*
    public void remover(int id){
        produtos.removeIf(p -> p.getId() == id);
        salvarNoArquivo();
    }*/
    
    public void remover(int id){
        deletaDB(id);
    }
    
    /*public boolean atualizar(int id, String nome, double preco){
        Produto p = buscarPorID(id);
        if (p == null) return false;
        p.setNome(nome);
        p.setPreco(preco);
        salvarNoArquivo();
        return true;
    }*/
    
    public boolean atualizar(int id, String nome, double preco){
        atualizaDB(id,nome,preco);
        return true;
    }
    
    /*private void carregarDoArquivo() {
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            StringBuilder json = new StringBuilder();
            String linha;
            while ((linha = reader.readLine()) != null) {
                json.append(linha);
            }
            produtos = gson.fromJson(json.toString(), new TypeToken<List<Produto>>(){}.getType());
            if (produtos == null) produtos = new ArrayList<>();

            // Atualiza o próximo ID baseado no maior ID já salvo
            proximoID = produtos.stream()
                .mapToInt(Produto::getId)
                .max()
                .orElse(0) + 1;

        } catch (IOException e) {
            // Se o arquivo não existir ainda, tudo bem
            produtos = new ArrayList<>();
        }
    }*/
    
   /* private void salvarNoArquivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo))) {
            String json = gson.toJson(produtos);
            writer.write(json);
        } catch (IOException e) {
          System.out.println("Erro ao salvar os dados: " + e.getMessage());
        }
    };*/
    
    public void inserirDB(String nome, double preco){
        String sql = "INSERT INTO produtos (nome, preco) VALUES (?, ?)";
        try (Connection conn = databaseUtil.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            
            stmt.setString(1, nome);
            stmt.setDouble(2, preco);
            stmt.executeUpdate();
            System.out.println("Produto inserido com sucesso!");
        } catch (SQLException e){
            System.out.println("Erro ao inserir produto: " + e.getMessage());
        }
    };
    
    public List<Produto> listarDB() {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produtos";

        try (Connection conn = databaseUtil.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                double preco = rs.getDouble("preco");

                produtos.add(new Produto(id, nome, preco));
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar produtos: " + e.getMessage());
        }

        return produtos;
    };
    
    public void atualizaDB(int id, String nome, double preco){
        String sql = "UPDATE produtos SET nome = ?, preco = ? WHERE id = ?";

        try (Connection conn = databaseUtil.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)
                ){
            
            stmt.setString(1, nome);
            stmt.setDouble(2, preco);
            stmt.setInt(3, id);
            stmt.executeUpdate();
            System.out.println("Produto atualizado com sucesso!");
        } catch (SQLException e){
            System.out.println("Erro ao atualizado produto: " + e.getMessage());
        }
    };

    
    public void deletaDB(int id){
        String sql = "DELETE FROM produtos WHERE id = ?";
        try (Connection conn = databaseUtil.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Produto deletado com sucesso!");
        } catch (SQLException e){
            System.out.println("Erro ao deletar produto: " + e.getMessage());
        }
    };

    
    public boolean isVazio(){
        return listarDB().isEmpty();
    }
}
