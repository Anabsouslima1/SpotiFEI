package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String URL = "jdbc:postgresql://localhost:5432/spotify_clone"; // Altere se necessário
    private static final String USUARIO = "postgres"; // seu usuário
    private static final String SENHA = "sua_senha_aqui"; // sua senha

    public static Connection conectar() {
        try {
            Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            System.out.println("✅ Conectado com sucesso ao banco de dados!");
            return conexao;
        } catch (SQLException e) {
            System.out.println("❌ Erro na conexão: " + e.getMessage());
            return null;
        }
    }
}
