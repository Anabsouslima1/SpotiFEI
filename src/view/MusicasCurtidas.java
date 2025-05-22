package view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.*;
import model.*;
import java.sql.*;

public class MusicasCurtidas extends JFrame {
    
    private int idUsuario;
    private JPopupMenu popupMenu;
    private JMenuItem menuItemDescurtir;

    public MusicasCurtidas(int idUsuario) {
        this.idUsuario = idUsuario;
        initComponents();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        criarPopupMenu();
        adicionarMouseListenerPopup();
        carregarMusicasCurtidas(idUsuario);
    }
        
    private void criarPopupMenu() {
        popupMenu = new JPopupMenu();
        menuItemDescurtir = new JMenuItem("Descurtir");
        popupMenu.add(menuItemDescurtir);

        menuItemDescurtir.addActionListener(e -> {
            int index = listaMusicasCurtidas.getSelectedIndex();
            if (index != -1) {
                String nomeMusica = listaMusicasCurtidas.getSelectedValue();
                try {
                    MusicaCurtidaDAO dao = new MusicaCurtidaDAO();
                    int idMusica = dao.buscarIdMusicaPorNome(nomeMusica);
                    descurtirMusica(idUsuario, idMusica);
                    carregarMusicasCurtidas(idUsuario); // Atualiza a lista após remoção
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao descurtir a música: " + ex.getMessage());
                }
            }
        });
    }
    
    private void adicionarMouseListenerPopup() {
      listaMusicasCurtidas.addMouseListener(new MouseAdapter() {
          @Override
          public void mousePressed(MouseEvent e) {
              if (e.isPopupTrigger()) {
                  mostrarPopup(e);
              }
          }
          @Override
          public void mouseReleased(MouseEvent e) {
              if (e.isPopupTrigger()) {
                  mostrarPopup(e);
              }
          }
          private void mostrarPopup(MouseEvent e) {
              int index = listaMusicasCurtidas.locationToIndex(e.getPoint());
              if (index != -1) {
                  listaMusicasCurtidas.setSelectedIndex(index);
                  popupMenu.show(listaMusicasCurtidas, e.getX(), e.getY());
              }
          }
      });
    }

    public void descurtirMusica(int idUsuario, int idMusica) throws SQLException {
        String sql = "DELETE FROM musicas_curtidas WHERE id_usuario = ? AND id_musica = ?";
        try (Connection conn = Conexao_bd.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            ps.setInt(2, idMusica);
            ps.executeUpdate();
        }
    }
        public void carregarMusicasCurtidas(int idUsuario) {
        try {
            MusicaCurtidaDAO dao = new MusicaCurtidaDAO();
            List<String> musicas = dao.listarMusicasCurtidas(idUsuario);
            DefaultListModel<String> model = new DefaultListModel<>();
            for (String musica : musicas) {
                model.addElement(musica);
            }
            listaMusicasCurtidas.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar músicas curtidas: " + e.getMessage());
        }   
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtMusicasCurtidas = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaMusicasCurtidas = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtMusicasCurtidas.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        txtMusicasCurtidas.setText("Músicas Curtidas");

        listaMusicasCurtidas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        listaMusicasCurtidas.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(listaMusicasCurtidas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(134, 134, 134)
                        .addComponent(txtMusicasCurtidas))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(149, 149, 149)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(142, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(txtMusicasCurtidas)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(81, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> listaMusicasCurtidas;
    private javax.swing.JLabel txtMusicasCurtidas;
    // End of variables declaration//GEN-END:variables
}
