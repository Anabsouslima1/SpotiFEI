package model;

import java.sql.*;
import java.util.*;

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
    
    public List<Playlist> listarPlaylistsPorUsuario(int idUsuario) throws SQLException {
        List<Playlist> playlists = new ArrayList<>();
        String sql = "SELECT * FROM playlist WHERE id_usuario = ?";

        try (Connection conexao = Conexao_bd.conectar();
            PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setInt(1, idUsuario);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    String nome = rs.getString("nome");
                    String descricao = rs.getString("descricao");
                    int idPlaylist = rs.getInt("id");

                    Playlist playlist = new Playlist(idPlaylist, nome, descricao, idUsuario);
                    
                    // Carregar músicas
                    List<Musica> musicas = listarMusicasDaPlaylist(idPlaylist);
                    playlist.setMusicas(musicas);
                    
                    playlists.add(playlist);
                }
            }
        return playlists;
    }
    
    public boolean adicionarMusicaNaPlaylist(int idPlaylist, int idMusica) {
        String sql = "INSERT INTO musica_playlist (id_playlist, id_musica) VALUES (?, ?)";
        try (Connection conexao = Conexao_bd.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idPlaylist);
            stmt.setInt(2, idMusica);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean removerMusicaDaPlaylist(int idPlaylist, int idMusica) {
        String sql = "DELETE FROM playlist_musicas WHERE playlist_id = ? AND musica_id = ?";
        try (Connection conexao = Conexao_bd.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idPlaylist);
            stmt.setInt(2, idMusica);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao remover música da playlist: " + e.getMessage());
            return false;
        }
    }
    
    public List<Musica> listarMusicasDaPlaylist(int idPlaylist) throws SQLException {
        List<Musica> musicas = new ArrayList<>();
        String sql = "SELECT m.id, m.titulo, a.nome as artista " +
             "FROM musicas m " +
             "JOIN musica_playlist mp ON m.id = mp.id_musica " +
             "JOIN artistas a ON m.id_artista = a.id " +  
             "WHERE mp.id_playlist = ?";

        try (Connection conexao = Conexao_bd.conectar();
            PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, idPlaylist);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Musica musica = new Musica(
                  rs.getInt("id"),
                  rs.getString("titulo"),
                  rs.getString("artista")
                );             
                musicas.add(musica);
            }
        }
        return musicas;
    }   
    
    public boolean atualizarPlaylist(Playlist playlist) {
        Connection conexao = Conexao_bd.conectar();
        if (conexao == null) return false;

        String sql = "UPDATE playlist SET nome = ?, descricao = ? WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, playlist.getNome());
            stmt.setString(2, playlist.getDescricao());
            stmt.setInt(3, playlist.getId());  
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar playlist: " + e.getMessage());
            return false;
        }
    }
    
    public List<Playlist> buscarTodas() {
        List<Playlist> listas = new ArrayList<>();
        try (Connection conn = Conexao_bd.conectar();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM playlist");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Playlist p = new Playlist(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("descricao"),
                    rs.getInt("id_usuario")
                );
                listas.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listas;
    }


}
