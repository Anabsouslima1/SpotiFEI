package model;

public class MusicaCurtida {
    private int idUsuario;
    private int idMusica;

    public MusicaCurtida(int idUsuario, int idMusica) {
        this.idUsuario = idUsuario;
        this.idMusica = idMusica;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdMusica() {
        return idMusica;
    }

    public void setIdMusica(int idMusica) {
        this.idMusica = idMusica;
    }
}
