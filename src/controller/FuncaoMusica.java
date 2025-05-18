package controller;

import model.MusicaDAO;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class FuncaoMusica {
    private MusicaDAO musicaDAO;

    public FuncaoMusica(MusicaDAO musicaDAO) {
        this.musicaDAO = musicaDAO;
    }
    
    // Exibe músicas disponíveis na Home
    public DefaultTableModel obterMusicas() throws Exception {
        List<Object[]> musicas = musicaDAO.listarMusicas();

        String[] colunas = {"Título", "Nome", "Artista", "Gênero", "Duração", "Lançamento"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0);

        for (Object[] linha : musicas) {
            model.addRow(linha);
        }
        return model;
    }
    
    // Busca músicas por título
    public DefaultTableModel buscarPorNome(String nome) throws Exception {
        List<Object[]> dados = musicaDAO.buscarPorNome(nome);
        return criarModeloTabela(dados);
    }
    
    // Busca músicas por artistas
    public DefaultTableModel buscarPorArtista(String artista) throws Exception {
        List<Object[]> dados = musicaDAO.buscarPorArtista(artista);
        return criarModeloTabela(dados);
    }
    
    // Busca músicas por gênero
    public DefaultTableModel buscarPorGenero(String genero) throws Exception {
        List<Object[]> dados = musicaDAO.buscarPorGenero(genero);
        return criarModeloTabela(dados);
    }
    
    // Método usado para auxiliar buscas
    private DefaultTableModel criarModeloTabela(List<Object[]> dados) {
        String[] colunas = {"Título", "Nome", "Artista", "Gênero", "Duração", "Lançamento"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0);
        for (Object[] linha : dados) {
            model.addRow(linha);
        }
        return model;
    }
    
}
