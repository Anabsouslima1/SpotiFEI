package Main;

import model.Conexao_bd;
import java.sql.Connection;
import javax.swing.SwingUtilities;
import view.LoginUsuario;

public class Spotify_Ana {
    public static void main(String[] args) {
        Connection conn = Conexao_bd.conectar();
        if (conn != null) {
            System.out.println("Tudo certo! Pode usar essa conexão no projeto.");
        } else {
            System.out.println("Erro na conexão com o banco.");
            return; // para não abrir a tela se não conectou
        }
        
        // Agora abre a tela de cadastro (na thread do Swing)
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LoginUsuario().setVisible(true);
            }
        });
    }
}
