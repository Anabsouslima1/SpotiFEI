package model;

public class Playlist {
   private int id;
   private String nome;
   private String descricao;
   private int idUsuario; 

    public Playlist(int id, String nome, String descricao, int idUsuario) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.idUsuario = idUsuario;
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
}
