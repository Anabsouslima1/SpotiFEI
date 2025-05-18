package model;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private String nome;
    private List<String> musicas;

    public Playlist(String nome) {
        this.nome = nome;
        this.musicas = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public List<String> getMusicas() {
        return musicas;
    }

    public void adicionarMusica(String musica) {
        musicas.add(musica);
    }

    public void removerMusica(String musica) {
        musicas.remove(musica);
    }
}
