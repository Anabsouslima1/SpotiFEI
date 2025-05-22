package view;

import java.util.List;
import javax.swing.*;
import model.MusicaCurtidaDAO;

public class MusicasCurtidas extends JFrame {

    public MusicasCurtidas(int idUsuario) {
        initComponents();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        carregarMusicasCurtidas(idUsuario);
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
