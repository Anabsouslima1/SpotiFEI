package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.table.DefaultTableModel;
import model.MusicaDAO;

public class FuncaoMusica {
    private MusicaDAO musicaDAO;
    
    // Mapa para curtidas
    private Map<String, Boolean> mapaCurtidas = new HashMap<>();

    public FuncaoMusica(MusicaDAO musicaDAO) {
        this.musicaDAO = musicaDAO;
    }
    
    // Exibe músicas disponíveis na Home
    public DefaultTableModel obterMusicas() throws Exception {
        List<Object[]> musicas = musicaDAO.listarMusicas();
        return criarModeloTabela(musicas);
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
        String[] colunas = {"Título", "Artista", "Álbum", "Gênero", "Duração", "Lançamento", "Curtir"};
        
        DefaultTableModel model = new DefaultTableModel(colunas, 0){
           @Override
            public Class<?> getColumnClass(int columnIndex){
                if (columnIndex == 6) return Boolean.class;
                return String.class;
            }
            
           @Override
            public boolean isCellEditable(int lin, int col){
                return col == 6; // Só coluna de curtir é editável
            }
        };

        for (Object[] linha : dados) {
            Object[] linhaComCurtir = new Object[7];
            System.arraycopy(linha, 0, linhaComCurtir, 0, linha.length);
            
            String chave = gerarChave(linha);
            Boolean curtida = mapaCurtidas.getOrDefault(chave, false);
            linhaComCurtir[6] = curtida;
            
            model.addRow(linhaComCurtir);
        }
        return model;
    }
    
    private String gerarChave(Object[] musica) {
        return musica[0].toString() + "-" + musica[1].toString();
    }
    
    public void atualizarCurtida(int linha, boolean valor, DefaultTableModel model) {
        String titulo = model.getValueAt(linha, 0).toString();
        String artista = model.getValueAt(linha, 1).toString();
        String chave = titulo + "-" + artista;
        mapaCurtidas.put(chave, valor);
    }
}
