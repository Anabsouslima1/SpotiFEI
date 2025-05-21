package view;

import java.awt.BorderLayout;
import java.util.*;
import javax.swing.*;
import model.Playlist;
import model.PlaylistDAO;
import java.sql.*;

public class SelecaoPlaylist extends JFrame {
    private JList<String> listaPlaylists;
    private DefaultListModel<String> modeloLista;
    private List<Playlist> playlistsDoUsuario;
    private int idUsuario;

    public SelecaoPlaylist(int idUsuario) {
        this.idUsuario = idUsuario;
        setTitle("Editar Playlist");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        modeloLista = new DefaultListModel<>();
        listaPlaylists = new JList<>(modeloLista);
        add(new JScrollPane(listaPlaylists), BorderLayout.CENTER);

        JButton botaoEditar = new JButton("Editar Playlist");
        add(botaoEditar, BorderLayout.SOUTH);

        carregarPlaylists();

        botaoEditar.addActionListener(e -> {
            int selecionado = listaPlaylists.getSelectedIndex();
            if (selecionado >= 0) {
                Playlist escolhida = playlistsDoUsuario.get(selecionado);
                new EditarPlaylist(escolhida).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Selecione uma playlist para editar.");
            }
        });
    }
        
    private void carregarPlaylists() {
        PlaylistDAO dao = new PlaylistDAO();
        try {
            playlistsDoUsuario = dao.listarPlaylistsPorUsuario(idUsuario);
            for (Playlist p : playlistsDoUsuario) {
                modeloLista.addElement(p.getNome());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao carregar playlists.");
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
