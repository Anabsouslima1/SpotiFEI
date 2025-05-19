package view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import model.Playlist;
import controller.ControladorPlaylists;

public class AdicionarMusicaPlaylist extends JFrame {
    private JPanel painelCheckboxes;
    private JButton botaoAdicionar;
    private JButton botaoCriarPlaylist;
    private JLabel mensagemSemPlaylists;
    private List<JCheckBox> checkboxes = new ArrayList<>();
    private String nomeMusica;
    private int idUsuario;
    
    public AdicionarMusicaPlaylist(String nomeMusica, int idUsuario) {
        this.nomeMusica = nomeMusica;
        this.idUsuario = idUsuario;
        
        setTitle("Adicionar Música à Playlist");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        painelCheckboxes = new JPanel();
        painelCheckboxes.setLayout(new BoxLayout(painelCheckboxes, BoxLayout.Y_AXIS));

        List<Playlist> playlists = ControladorPlaylists.getPlaylists();

        if (playlists.isEmpty()) {
            mensagemSemPlaylists = new JLabel("Não há playlists criadas.");
            mensagemSemPlaylists.setHorizontalAlignment(SwingConstants.CENTER);

            botaoCriarPlaylist = new JButton("Criar Playlist");
            botaoCriarPlaylist.addActionListener(e -> {
                new CriarPlaylist(idUsuario).setVisible(true);
                dispose(); // fecha essa janela
            });

            JPanel painelCentral = new JPanel(new BorderLayout());
            painelCentral.add(mensagemSemPlaylists, BorderLayout.CENTER);
            painelCentral.add(botaoCriarPlaylist, BorderLayout.SOUTH);
            add(painelCentral, BorderLayout.CENTER);
        } else {
            for (Playlist p : playlists) {
                JCheckBox check = new JCheckBox(p.getNome());
                checkboxes.add(check);
                painelCheckboxes.add(check);
            }

            JScrollPane scrollPane = new JScrollPane(painelCheckboxes);
            add(scrollPane, BorderLayout.CENTER);

            botaoAdicionar = new JButton("Adicionar");
            botaoAdicionar.addActionListener(e -> {
                List<String> selecionadas = new ArrayList<>();
                for (int i = 0; i < checkboxes.size(); i++) {
                    if (checkboxes.get(i).isSelected()) {
                        selecionadas.add(playlists.get(i).getNome());
                    }
                }

                if (selecionadas.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Nenhuma playlist selecionada.");
                } else {
                    // aqui você adicionaria a música às playlists selecionadas
                    JOptionPane.showMessageDialog(this, "Música adicionada às playlists: " + selecionadas);
                    dispose(); // fecha a janela após ação
                }
            });
            add(botaoAdicionar, BorderLayout.SOUTH);
        }

        setLocationRelativeTo(null);
        setVisible(true);
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
