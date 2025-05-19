package model;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private String nome;
    private String descricao;
    private int idUsuario;
    private List<String> musicas;

    public Playlist(String nome, String descricao, int idUsuario) {
        this.nome = nome;
        this.descricao = descricao;
        this.idUsuario = idUsuario;
        this.musicas = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
   
    public List<String> getMusicas() {
        return musicas;
    }

    public void setMusicas(List<String> musicas) {
        this.musicas = musicas;
    }

    public void adicionarMusica(String musica) {
        musicas.add(musica);
    }

    public void removerMusica(String musica) {
        musicas.remove(musica);
    }
}
