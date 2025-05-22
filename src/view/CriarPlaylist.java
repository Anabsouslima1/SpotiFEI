package view;

import javax.swing.*;
import model.Playlist;
import model.PlaylistDAO;

public class CriarPlaylist extends JFrame {

    private int idUsuarioLogado;
    
    public CriarPlaylist(int idUsuario) {
        this.idUsuarioLogado = idUsuario;
        initComponents();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        botaoCriarNovaPlaylist.setText("Criar!");
        botaoCriarNovaPlaylist.addActionListener(e -> {
            String nome = campoNomePlaylist.getText().trim();
            String descricao = campoDescricaoPlaylist.getText().trim();

            if (nome.isEmpty()) {
                JOptionPane.showMessageDialog(this, "O nome da playlist não pode estar em branco!");
                return;
            }

            Playlist novaPlaylist = new Playlist(nome, descricao, idUsuarioLogado);
            boolean sucesso = new PlaylistDAO().salvarPlaylist(novaPlaylist);
            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Playlist criada com sucesso!");
                campoNomePlaylist.setText("");
                campoDescricaoPlaylist.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao salvar playlist.");
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tituloCriarPlaylist = new javax.swing.JLabel();
        campoNomePlaylist = new javax.swing.JTextField();
        txtNomeNovaPlaylist = new javax.swing.JLabel();
        txtDescricaoPlaylist = new javax.swing.JLabel();
        campoDescricaoPlaylist = new javax.swing.JTextField();
        botaoCriarNovaPlaylist = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tituloCriarPlaylist.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        tituloCriarPlaylist.setText("Criar Nova Playlist");

        txtNomeNovaPlaylist.setText("Nome:");

        txtDescricaoPlaylist.setText("Descrição:");

        botaoCriarNovaPlaylist.setText("Criar!");
        botaoCriarNovaPlaylist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCriarNovaPlaylistActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tituloCriarPlaylist)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(txtNomeNovaPlaylist)
                                .addGap(18, 18, 18)
                                .addComponent(campoNomePlaylist, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(txtDescricaoPlaylist)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(campoDescricaoPlaylist, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(164, 164, 164))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botaoCriarNovaPlaylist)
                .addGap(189, 189, 189))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(tituloCriarPlaylist)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoNomePlaylist, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNomeNovaPlaylist))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoDescricaoPlaylist, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDescricaoPlaylist))
                .addGap(18, 18, 18)
                .addComponent(botaoCriarNovaPlaylist)
                .addContainerGap(59, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoCriarNovaPlaylistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCriarNovaPlaylistActionPerformed
    }//GEN-LAST:event_botaoCriarNovaPlaylistActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoCriarNovaPlaylist;
    private javax.swing.JTextField campoDescricaoPlaylist;
    private javax.swing.JTextField campoNomePlaylist;
    private javax.swing.JLabel tituloCriarPlaylist;
    private javax.swing.JLabel txtDescricaoPlaylist;
    private javax.swing.JLabel txtNomeNovaPlaylist;
    // End of variables declaration//GEN-END:variables
}
