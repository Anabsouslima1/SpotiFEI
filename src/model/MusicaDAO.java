package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MusicaDAO {
    
    public List<Object[]> listarMusicas() throws SQLException {
        List<Object[]> lista = new ArrayList<>();

        String sql = "SELECT musicas.id, musicas.titulo, artistas.nome, albuns.titulo, musicas.genero, musicas.duracao, musicas.ano_lancamento " +
                     "FROM musicas " +
                     "JOIN artistas ON musicas.id_artista = artistas.id " +
                     "JOIN albuns ON musicas.id_album = albuns.id";

        try (Connection conn = Conexao_bd.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new Object[] {
                    rs.getInt(1), // id
                    rs.getString(2), // titulo
                    rs.getString(3), // artista
                    rs.getString(4), // album
                    rs.getString(5), // genero
                    rs.getTime(6).toString(), // duracao
                    rs.getString(7) // ano                 
                });
            }
        }

        return lista;
    }
    
    // Método privado genérico para busca em qualquer campo
    private List<Object[]> buscarPorCampo(String campo, String valor) throws SQLException {
        List<Object[]> lista = new ArrayList<>();
        String sql = "SELECT musicas.id, musicas.titulo, artistas.nome, albuns.titulo, musicas.genero, musicas.duracao, musicas.ano_lancamento " +
                     "FROM musicas " +
                     "JOIN artistas ON musicas.id_artista = artistas.id " +
                     "JOIN albuns ON musicas.id_album = albuns.id " +
                     "WHERE LOWER( " + campo + ") LIKE ?";
        try (Connection conn = Conexao_bd.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + valor.toLowerCase() + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(new Object[] {
                        rs.getInt(1), // id
                        rs.getString(2), // titulo
                        rs.getString(3), // artista
                        rs.getString(4), // album
                        rs.getString(5), // genero
                        rs.getTime(6).toString(), // duracao
                        rs.getString(7) // ano
                    });
                }
            }
        }
        return lista;
    }
    
    public int buscarIdMusicaPorNome(String nomeMusica) throws SQLException {
        String sql = "SELECT id FROM musicas WHERE titulo = ?";
        try (Connection conn = Conexao_bd.conectar();

        PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, nomeMusica);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }
        }
        throw new SQLException("Música não encontrada: " + nomeMusica);
    }

    
    public List<Object[]> buscarPorNome(String nome) throws SQLException {
        return buscarPorCampo("musicas.titulo", nome);
    }

    public List<Object[]> buscarPorArtista(String artista) throws SQLException {
        return buscarPorCampo("artistas.nome", artista);
    }

    public List<Object[]> buscarPorGenero(String genero) throws SQLException {
        return buscarPorCampo("musicas.genero", genero);
    } 
}
