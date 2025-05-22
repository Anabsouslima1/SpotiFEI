package view;

import controller.FuncaoMusica;
import java.awt.*;
import javax.swing.*;
import model.Playlist;
import model.Musica;
import model.MusicaDAO;
import model.PlaylistDAO;

public class EditarPlaylist extends JFrame {
    
    private Playlist playlist;
    private JTextField campoNome;
    private JTextArea campoDescricao;
    private JList<String> listaMusicas;
    private FuncaoMusica funcaoMusica;

    public EditarPlaylist(Playlist playlist) {
        this.playlist = playlist;
        this.funcaoMusica = new FuncaoMusica(new MusicaDAO());
        
        setTitle("Editar Playlist: " + playlist.getNome());
        setSize(500, 400);
        setLayout(new BorderLayout());

        campoNome = new JTextField(playlist.getNome(), 20);
        campoDescricao = new JTextArea(playlist.getDescricao(), 5, 20);
        listaMusicas = new JList<>();

        carregarMusicas();

        JPanel painelTopo = new JPanel();
        painelTopo.setLayout(new GridLayout(4, 1));
        painelTopo.add(new JLabel("Nome:"));
        painelTopo.add(campoNome);
        painelTopo.add(new JLabel("Descrição:"));
        painelTopo.add(new JScrollPane(campoDescricao));

        add(painelTopo, BorderLayout.NORTH);
        add(new JScrollPane(listaMusicas), BorderLayout.CENTER);

        JButton salvar = new JButton("Salvar Alterações");
        add(salvar, BorderLayout.SOUTH);

        salvar.addActionListener(e -> {
            playlist.setNome(campoNome.getText());
            playlist.setDescricao(campoDescricao.getText());

            PlaylistDAO dao = new PlaylistDAO();
            boolean sucesso = dao.atualizarPlaylist(playlist);

            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Playlist atualizada com sucesso!");
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao atualizar a playlist.");
            }
        });
        
        carregarMusicas();
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
    
    private void carregarMusicas() {
        try {
            PlaylistDAO dao = new PlaylistDAO();
            playlist.setMusicas(dao.listarMusicasDaPlaylist(playlist.getId()));
            DefaultListModel<String> model = new DefaultListModel<>();
            for (Musica musica : playlist.getMusicas()) {
                model.addElement(musica.getNome() + " - " + musica.getArtista());
            }
            listaMusicas.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar músicas: " + e.getMessage());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tituloCriarPlaylist = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tituloCriarPlaylist.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        tituloCriarPlaylist.setText("Editar Playlists");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addComponent(tituloCriarPlaylist)
                .addContainerGap(91, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(tituloCriarPlaylist)
                .addContainerGap(213, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel tituloCriarPlaylist;
    // End of variables declaration//GEN-END:variables
}
