package model;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private int id;
    private String nome;
    private String descricao;
    private int idUsuario;
    private List<Musica> musicas;
    
    public Playlist(){}
    
    // Criar objetos novos (sem playlist)
    public Playlist(String nome, String descricao, int idUsuario) {
        this.nome = nome;
        this.descricao = descricao;
        this.idUsuario = idUsuario;
        this.musicas = new ArrayList<>();
    }    
    
    // Busca no banco
    public Playlist(int id, String nome, String descricao, int idUsuario) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.idUsuario = idUsuario;
        this.musicas = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
   
    public List<Musica> getMusicas() {
        if (musicas == null) {
            musicas = new ArrayList<>();
        }
        return musicas;
    }

    public void setMusicas(List<Musica> musicas) {
        this.musicas = musicas;
    }

    public void adicionarMusica(Musica musica) {
        this.musicas.add(musica);
    }

    public void removerMusica(Musica musica) {
        this.musicas.remove(musica);
    }
}
