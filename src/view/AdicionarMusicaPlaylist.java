package view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import model.*;
import controller.ControladorPlaylists;
import java.awt.event.ItemEvent;

public class AdicionarMusicaPlaylist extends JFrame {
    private JPanel painelCheckboxes;
    private JButton botaoAdicionar;
    private JButton botaoCriarPlaylist;
    private JLabel mensagemSemPlaylists;
    private List<JCheckBox> checkboxes = new ArrayList<>();
    private String nomeMusica;
    private int idUsuario;
    private JList<String> listaMusicas;
    private DefaultListModel<String> modeloListaMusicas;
    
    public AdicionarMusicaPlaylist(String nomeMusica, int idUsuario) {
        this.nomeMusica = nomeMusica;
        this.idUsuario = idUsuario;
        
        setTitle("Adicionar Música à Playlist");
        setSize(600, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        painelCheckboxes = new JPanel();
        painelCheckboxes.setLayout(new BoxLayout(painelCheckboxes, BoxLayout.Y_AXIS));

        modeloListaMusicas = new DefaultListModel<>();
        listaMusicas = new JList<>(modeloListaMusicas);
        JScrollPane scrollMusicas = new JScrollPane(listaMusicas);
        
        List<Playlist> playlists = ControladorPlaylists.getPlaylists();

        if (playlists.isEmpty()) {
            mensagemSemPlaylists = new JLabel("Não há playlists criadas.");
            mensagemSemPlaylists.setHorizontalAlignment(SwingConstants.CENTER);

            botaoCriarPlaylist = new JButton("Criar Playlist");
            botaoCriarPlaylist.addActionListener(e -> {
                new CriarPlaylist(idUsuario).setVisible(true);
                dispose(); 
            });

            JPanel painelCentral = new JPanel(new BorderLayout());
            painelCentral.add(mensagemSemPlaylists, BorderLayout.CENTER);
            painelCentral.add(botaoCriarPlaylist, BorderLayout.SOUTH);
            add(painelCentral, BorderLayout.CENTER);
        } else {
            for (Playlist p : playlists) {
                JCheckBox check = new JCheckBox(p.getNome());
                check.addItemListener(e -> {
                    if (e.getStateChange() == ItemEvent.SELECTED) {
                        mostrarMusicasDaPlaylist(p);
                    } else {
                        modeloListaMusicas.clear(); // Limpa a lista se a playlist for desmarcada
                    }
                });
                checkboxes.add(check);
                painelCheckboxes.add(check);
            }

            JPanel painelCentral = new JPanel(new GridLayout(1, 2));
            painelCentral.add(new JScrollPane(painelCheckboxes));
            painelCentral.add(scrollMusicas);

            add(painelCentral, BorderLayout.CENTER);

            botaoAdicionar = new JButton("Adicionar");
            botaoAdicionar.addActionListener(e -> {
                List<Playlist> selecionadas = new ArrayList<>();
                for (int i = 0; i < checkboxes.size(); i++) {
                    if (checkboxes.get(i).isSelected()) {
                        selecionadas.add(playlists.get(i));
                    }
                }

                if (selecionadas.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Nenhuma playlist selecionada.");
                } else {
                    try {
                        MusicaDAO musicaDAO = new MusicaDAO();
                        PlaylistDAO playlistDAO = new PlaylistDAO();
                        int idMusica = musicaDAO.buscarIdMusicaPorNome(nomeMusica);
            
                        for (Playlist p : selecionadas) {
                            playlistDAO.adicionarMusicaNaPlaylist(idMusica, p.getId());
                        }
                        
                        JOptionPane.showMessageDialog(this, "Música adicionada à playlist selecionada.");
                        dispose();                        
                    
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this, "Erro ao adicionar música: " + ex.getMessage());
                        ex.printStackTrace();
                    }
                }   
            });
            add(botaoAdicionar, BorderLayout.SOUTH);
        }

        setLocationRelativeTo(null);
        setVisible(true);
    }
    
     private void mostrarMusicasDaPlaylist(Playlist playlist) {
        try {
            PlaylistDAO dao = new PlaylistDAO();
            List<Musica> musicas = dao.listarMusicasDaPlaylist(playlist.getId());

            modeloListaMusicas.clear();
            for (Musica musica : musicas) {
                modeloListaMusicas.addElement(musica.getNome() + " - " + musica.getArtista());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar músicas da playlist: " + e.getMessage());
        }
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
