package view;

import controller.FuncaoMusica;
import model.MusicaDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Home extends JFrame {
    
    private FuncaoMusica funcaoMusica;
    
    public Home(String nomeUsuario) {
        initComponents();
        
        try {
            funcaoMusica = new FuncaoMusica(new MusicaDAO());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao buscar.");
        }

        // Alteração das opções de seleção para pesquisa
        ComboSelecao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Nome", "Artista", "Gênero"}));
        
        // Evento de busca do botão
        BotaoBuscar.addActionListener(evt -> buscarMusicas());
        
        carregarMusicas();
        
        MensagemBoasVindas.setText("Bem-Vindo ao SpotiFEI, " + nomeUsuario + "!");
        
        
        try {
            tabelaMusicas.setModel(funcaoMusica.obterMusicas());
        }catch (Exception e){
            JOptionPane.showMessageDialog(this, "Erro ao carregar músicas: " + e.getMessage());
        }
    }
    
    private void carregarMusicas() {
        try {
            DefaultTableModel model = funcaoMusica.obterMusicas();
            tabelaMusicas.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar músicas: " + e.getMessage());
        }
    }
    
    private void buscarMusicas() {
        try {
            String textoBusca = CampoInserir.getText().trim();
            String criterio = (String) ComboSelecao.getSelectedItem();

            DefaultTableModel model;

            if (textoBusca.isEmpty()) {
                model = funcaoMusica.obterMusicas();
            } else {
                switch (criterio) {
                    case "Nome":
                        model = funcaoMusica.buscarPorNome(textoBusca);
                        break;
                    case "Artista":
                        model = funcaoMusica.buscarPorArtista(textoBusca);
                        break;
                    case "Gênero":
                        model = funcaoMusica.buscarPorGenero(textoBusca);
                        break;
                    default:
                        model = funcaoMusica.obterMusicas();
                }
            }
            tabelaMusicas.setModel(model);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro na busca: " + e.getMessage());
        }
    } 
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MensagemBoasVindas = new javax.swing.JLabel();
        CampoInserir = new javax.swing.JTextField();
        BotaoBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaMusicas = new javax.swing.JTable();
        ComboSelecao = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        MensagemBoasVindas.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        MensagemBoasVindas.setText("Bem-Vindo ao SpotiFEI!");

        BotaoBuscar.setText("Busca");

        tabelaMusicas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tabelaMusicas);

        ComboSelecao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(MensagemBoasVindas)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(CampoInserir, javax.swing.GroupLayout.PREFERRED_SIZE, 584, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ComboSelecao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(BotaoBuscar))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 615, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 34, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(MensagemBoasVindas)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CampoInserir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BotaoBuscar)
                    .addComponent(ComboSelecao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotaoBuscar;
    private javax.swing.JTextField CampoInserir;
    private javax.swing.JComboBox<String> ComboSelecao;
    private javax.swing.JLabel MensagemBoasVindas;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelaMusicas;
    // End of variables declaration//GEN-END:variables
}
