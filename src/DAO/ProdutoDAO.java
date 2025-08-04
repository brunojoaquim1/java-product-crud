package DAO;

import database.databaseUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import DAO.RetornoDAO;
import DAO.RetornoListaDAO;
import java.util.ArrayList;
import java.util.List;
import model.Produto;
import java.sql.ResultSet;

public class ProdutoDAO {
    
    protected RetornoDAO retorno ;
    
    public RetornoDAO insert(String nome, double preco, int quantidade){
        String sql = "INSERT INTO produtos (nome, preco, quantidade) VALUES (?, ?, ?)";
        try (Connection conn = databaseUtil.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setDouble(2, preco);
            stmt.setInt(3, quantidade);
            stmt.executeUpdate();
            return new RetornoDAO(true, "Inserido com sucesso!");

        } catch (SQLException e) {
            return new RetornoDAO(false, "Erro ao inserir produto: " + e.getMessage());
        }
    }
    
    public RetornoDAO update(int ID, String nome, double preco, int quantidade){
        String sql = "UPDATE produtos SET nome = ?, preco = ? , quantidade ? WHERE id = ?";
        try(Connection conn = databaseUtil.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)){
        
            stmt.setString(1,nome);
            stmt.setDouble(2,preco);
            stmt.setInt(3, quantidade);
            stmt.setInt(4, ID);           
            stmt.executeUpdate();
            return new RetornoDAO(true, "Alterado com sucesso!");
        } catch (SQLException e) {
            return new RetornoDAO(false, "Erro ao alterar produto: " + e.getMessage());
        } 
    };
    
    public RetornoDAO delete(int ID){
        String sql = "DELETE FROM produtos WHERE id = ?";
        try (Connection conn = databaseUtil.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            
            stmt.setInt(1, ID);
            stmt.executeUpdate();
            return new RetornoDAO(true, "Deletado com sucesso!");
        } catch (SQLException e){
            return new RetornoDAO(false, "Erro ao deletar produto: " + e.getMessage());
        }
    };
    
    public RetornoListaDAO<Produto> listAll() {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produtos";

        try (Connection conn = databaseUtil.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                double preco = rs.getDouble("preco");
                int quantidade = rs.getInt("quantidade");
                
                produtos.add(new Produto(id, nome, preco, quantidade));
            }

            return new RetornoListaDAO<>(true, "Produtos encontrados", produtos);

        } catch (SQLException e) {
            return new RetornoListaDAO<>(false, "Erro ao buscar produtos: " + e.getMessage(), new ArrayList<>());
        }
    }
    
    public RetornoListaDAO<Produto> listLike(String like) {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produtos where nome like ?";

        try (Connection conn = databaseUtil.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)
             ) {
            
            stmt.setString(1, like);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                double preco = rs.getDouble("preco");
                int quantidade = rs.getInt("quantidade");
                
                produtos.add(new Produto(id, nome, preco, quantidade));
            }

            return new RetornoListaDAO<>(true, "Produtos encontrados", produtos);

        } catch (SQLException e) {
            return new RetornoListaDAO<>(false, "Erro ao buscar produtos: " + e.getMessage(), new ArrayList<>());
        }
    }
    
}
