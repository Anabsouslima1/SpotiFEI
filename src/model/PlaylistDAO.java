package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PlaylistDAO {

    private Connection conn;

    public PlaylistDAO(Connection conn) {
        this.conn = conn;
    }

    public void criar(Playlist playlist) throws SQLException {
        String sql = "INSERT INTO playlists (nome, descricao, id_usuario) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, playlist.getNome());
            stmt.setString(2, playlist.getDescricao());
            stmt.setInt(3, playlist.getIdUsuario());
            stmt.executeUpdate();
        }
    }

    // os outros métodos seguirão uma lógica parecida
}
