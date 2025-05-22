package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MusicaCurtidaDAO {
    private Connection conexao;

    public MusicaCurtidaDAO() throws Exception {
        this.conexao = Conexao_bd.conectar();
    }

    public void curtirMusica(int idUsuario, int idMusica) throws SQLException {
        String sql = "INSERT INTO musicas_curtidas (id_usuario, id_musica, ativa) " +
                             "VALUES (?, ?, TRUE) " +
                             "ON CONFLICT (id_usuario, id_musica) DO UPDATE SET ativa = TRUE";
        PreparedStatement ps = conexao.prepareStatement(sql);
        ps.setInt(1, idUsuario);
        ps.setInt(2, idMusica);
        ps.executeUpdate();
        ps.close();
    }

    public boolean jaCurtida(int idUsuario, int idMusica) throws SQLException {
        String sql = "SELECT 1 FROM musicas_curtidas WHERE id_usuario = ? AND id_musica = ? AND ativa = TRUE";
        PreparedStatement ps = conexao.prepareStatement(sql);
        ps.setInt(1, idUsuario);
        ps.setInt(2, idMusica);
        ResultSet rs = ps.executeQuery();
        boolean curtida = rs.next();
        rs.close();
        ps.close();
        return curtida;
    }
    
    public List<String> listarMusicasCurtidas(int idUsuario) throws SQLException {
        List<String> musicas = new ArrayList<>();
        String sql = "SELECT m.titulo FROM musicas_curtidas mc " +
                     "JOIN musicas m ON mc.id_musica = m.id " +
                     "WHERE mc.id_usuario = ? AND mc.ativa = TRUE";
        PreparedStatement ps = conexao.prepareStatement(sql);
        ps.setInt(1, idUsuario);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            musicas.add(rs.getString("titulo"));
        }
        rs.close();
        ps.close();
        return musicas;
    }
    
    public Set<Integer> buscarIdsMusicasCurtidas(int idUsuario) throws SQLException {
        Set<Integer> ids = new HashSet<>();
        String sql = "SELECT id_musica FROM musicas_curtidas WHERE id_usuario = ? AND ativa = true";
        PreparedStatement ps = conexao.prepareStatement(sql);
        ps.setInt(1, idUsuario);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            ids.add(rs.getInt("id_musica"));
        }
        rs.close();
        ps.close();
        return ids;
    } 
    
    public void descurtirMusica(int idUsuario, int idMusica) throws SQLException {
        String sql = "UPDATE musicas_curtidas SET ativa = FALSE WHERE id_usuario = ? AND id_musica = ?";
        PreparedStatement ps = conexao.prepareStatement(sql);
        ps.setInt(1, idUsuario);
        ps.setInt(2, idMusica);
        ps.executeUpdate();
        ps.close();
    }
    
     public int buscarIdMusicaPorNome(String nomeMusica) throws SQLException {
        String sql = "SELECT id FROM musicas WHERE titulo = ?";
        try (Connection conn = Conexao_bd.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nomeMusica);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            } else {
                throw new SQLException("Música não encontrada: " + nomeMusica);
            }
        }
    }
}
