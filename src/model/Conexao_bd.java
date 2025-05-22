package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexao_bd { /** Classe responsável por estabelecer a conexão com o banco de dados PostgreSQL.*/
    private static final String URL = "jdbc:postgresql://localhost:5432/spotifei";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "070588";

    public static Connection conectar() {
        try {
            Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            System.out.println("Conectado com sucesso ao banco de dados!");
            return conexao;
        } catch (SQLException e) {
            System.out.println("Erro na conexão: " + e.getMessage());
            return null;
        }
    }
}
