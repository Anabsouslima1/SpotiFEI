package Main;

import model.Conexao_bd;
import java.sql.Connection;

public class Spotify_Ana {
    public static void main(String[] args) {
        Connection conn = Conexao_bd.conectar();
        if (conn != null) {
            System.out.println("Tudo certo! Pode usar essa conexão no projeto.");
        }
    }
}
