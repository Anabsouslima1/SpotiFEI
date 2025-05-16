package Main;

import model.Conexao;
import java.sql.Connection;

public class Spotify_Ana {
    public static void main(String[] args) {
        Connection conn = Conexao.conectar();
        if (conn != null) {
            System.out.println("Tudo certo! Pode usar essa conexão no projeto.");
        }
    }
}
