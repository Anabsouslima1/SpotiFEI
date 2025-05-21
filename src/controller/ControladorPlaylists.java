package controller;

import java.sql.*;
import java.util.*;

import model.Conexao_bd;
import model.Playlist;

public class ControladorPlaylists {

    public static List<Playlist> getPlaylists() {
        List<Playlist> lista = new ArrayList<>();

        String sql = "SELECT id, nome, descricao, id_usuario FROM playlist";

        try (Connection conexao = Conexao_bd.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String descricao = rs.getString("descricao");
                int idUsuario = rs.getInt("id_usuario");

                Playlist p = new Playlist(id, nome, descricao, idUsuario);
               lista.add(p);
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar playlists: " + e.getMessage());
        }

        return lista;
    }

    public static String[] getNomesDasPlaylists() {
        return getPlaylists().stream()
                .map(Playlist::getNome)
                .toArray(String[]::new);
    }
}

