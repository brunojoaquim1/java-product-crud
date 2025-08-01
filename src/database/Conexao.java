package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.File;




public class Conexao {
    private static final String URL = "jdbc:sqlite:C:/Users/Ideapad3i/Documents/NetBeansProjects/java_product_crud/database/database.db";

    public static Connection conectar() throws SQLException {
        System.out.println("Conectando em: " + URL);
        return DriverManager.getConnection(URL);
    }
    public static void initBanco() throws SQLException{
        String sql = "CREATE TABLE IF NOT EXISTS produtos (\n" +
                    "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "    nome TEXT NOT NULL,\n" +
                    "    preco REAL NOT NULL\n" +
                    ");" ;
        try(Connection conn = Conexao.conectar(); 
        PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.executeUpdate(); 
        }
    };
}
