// Classe para acessar banco de dados
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class UsuarioDAO {

    public boolean emailExiste(String email) throws SQLException {
        String sql = "SELECT COUNT(*) FROM usuarios WHERE email = ?";
        try (Connection conexao = Conexao_bd.conectar();
            PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return rs.getInt(1) > 0;
        }
    }
    
    public Usuario buscarPorEmail(String email) throws SQLException {
        String sql = "SELECT id, nome, email, senha FROM usuarios WHERE email = ?";
        try (Connection conexao = Conexao_bd.conectar();
            PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Usuario usuario = new Usuario(
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("senha"),
                    rs.getInt("id")
                );
                return usuario;       
            } else {
                return null; // usuário não encontrado
            }
        }
    }

    public boolean cadastrarUsuario(Usuario usuario) throws SQLException {
        String nome = usuario.getNome();
        String email = usuario.getEmail();
        String senha = usuario.getSenha();
        
        if(nome.isEmpty() || email.isEmpty() || senha.isEmpty()){
            JOptionPane.showMessageDialog(null,"Preencha todos os campos!");
            return false;
        }
        
        try (Connection conexao = Conexao_bd.conectar()){
            if(conexao != null){
                if(emailExiste(email)){
                    JOptionPane.showMessageDialog(null,"Já existe um usuário com esse e-mail!");
                    return false;
                }
                String sql = "INSERT INTO usuarios (nome, email, senha) VALUES (?, ?, ?)";
                PreparedStatement stmt = conexao.prepareStatement(sql);
                stmt.setString(1, nome);
                stmt.setString(2, email);
                stmt.setString(3, senha); // usar hash depois

                int linhasAfetadas = stmt.executeUpdate();
                return linhasAfetadas > 0;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro no banco de dados: " + ex.getMessage());
        }

        return false;
    }
    
    public int obterIdUsuario(String email, String senha) throws SQLException {
        String sql = "SELECT id FROM usuarios WHERE email = ? AND senha = ?";
        try (Connection conexao = Conexao_bd.conectar();
             PreparedStatement pst = conexao.prepareStatement(sql)) {
            pst.setString(1, email);
            pst.setString(2, senha);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getInt("id"); 
            } else {
                return -1;
            }
        }
    }

    public boolean validarLogin(String email, String senha) throws SQLException {
      String sql = "SELECT * FROM usuarios WHERE email = ? AND senha = ?";
      try (Connection conexao = Conexao_bd.conectar();
          PreparedStatement pst = conexao.prepareStatement(sql)) {
          pst.setString(1, email);
          pst.setString(2, senha);
          ResultSet rs = pst.executeQuery();
          return rs.next(); // true se encontrou, false se não
      }
  }
} 


