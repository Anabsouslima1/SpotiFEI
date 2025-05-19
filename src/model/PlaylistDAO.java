package model;

import java.sql.*;

public class PlaylistDAO {
    public boolean salvarPlaylist(Playlist playlist) {
        Connection conexao = Conexao_bd.conectar();
        if (conexao == null) return false;

        String sql = "INSERT INTO playlist (nome, descricao, id_usuario) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, playlist.getNome());
            stmt.setString(2, playlist.getDescricao());
            stmt.setInt(3, playlist.getIdUsuario());

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao salvar playlist: " + e.getMessage());
            return false;
        }
    }
}
