package view;

import controller.FuncaoMusica;
import java.awt.*;
import java.awt.event.*;
import model.MusicaDAO;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import view.frames.CriarPlaylistFrame;


import view.componentes.BotaoEditor;
import view.componentes.BotaoRenderizar;

public class Home extends JFrame {
    
    private FuncaoMusica funcaoMusica;
    
    public Home(String nomeUsuario) {
        initComponents();
        
        linkPlaylists.setText("Criar nova playlist");
        linkPlaylists.setForeground(Color.BLUE);
        linkPlaylists.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // evento de clique
        linkPlaylists.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                CriarPlaylistFrame criarFrame = new CriarPlaylistFrame();
                criarFrame.setVisible(true);
            }
    });

        
        try {
            funcaoMusica = new FuncaoMusica(new MusicaDAO());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao buscar.");
        }

        // Alteração das opções de seleção para pesquisa
        ComboSelecao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Título", "Artista", "Gênero"}));
        
        // Evento de busca do botão
        BotaoBuscar.addActionListener(evt -> buscarMusicas());
        
        carregarMusicas();
        
        MensagemBoasVindas.setText("Bem-Vindo ao SpotiFEI, " + nomeUsuario + "!");
    }
    
    private void carregarMusicas() {
        try {
            DefaultTableModel model = funcaoMusica.obterMusicas();
            tabelaMusicas.setModel(model);
            
            int colunaBotao = 6; //índice da coluna "Curtir"
            
            // Define o renderizador para mostrar o botão de curtida
            tabelaMusicas.getColumnModel().getColumn(colunaBotao).setCellRenderer(new BotaoRenderizar());
            // Define o editor para mostrar o botão de curtida
            tabelaMusicas.getColumnModel().getColumn(colunaBotao)
                .setCellEditor(new BotaoEditor(new JCheckBox(), linha -> {
                    String titulo = (String) tabelaMusicas.getValueAt(linha, 1);
                    JOptionPane.showMessageDialog(Home.this, "Você clicou na música: " + titulo);
            }));

          // Escuta alterações para atualizar mapa de curtidas
            model.addTableModelListener(new TableModelListener() {
                @Override
                public void tableChanged(TableModelEvent e) {
                    if (e.getType() == TableModelEvent.UPDATE) {
                        int linha = e.getFirstRow();
                        int coluna = e.getColumn();
                        
                        if (coluna == colunaBotao) { // coluna Curtir
                            Object value = model.getValueAt(linha, coluna);
                            boolean valor = false;
                            
                            if (value instanceof Boolean) {
                                valor = (Boolean) value;
                            } else if (value instanceof String) {
                                valor = Boolean.parseBoolean((String) value);
                            } else {
                                System.err.println("Valor inesperado na coluna 6: " + value);
                            }

                            funcaoMusica.atualizarCurtida(linha, Boolean.valueOf(valor), model);
                            String msg = valor ? "Você curtiu a música da linha " + linha : "Você descurtiu a música da linha " + linha;
                            JOptionPane.showMessageDialog(Home.this, msg);
                        }
                    }
                }
            }); 
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
                    case "Título":
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
            
            // Reaplica o renderizador/editor ao mudar modelo da tabela
            int colunaBotao = 6;
            tabelaMusicas.getColumnModel().getColumn(colunaBotao).setCellRenderer(new BotaoRenderizar());
            tabelaMusicas.getColumnModel().getColumn(colunaBotao).setCellEditor(new BotaoEditor(new JCheckBox(), linha -> {
                String titulo = (String) tabelaMusicas.getValueAt(linha, 1);
                JOptionPane.showMessageDialog(Home.this, "Você clicou na música: " + titulo);
            }));
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
        linkMusicasCurtidas = new javax.swing.JLabel();
        linkPlaylists = new javax.swing.JLabel();

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

        linkMusicasCurtidas.setText("Músicas Curtidas");

        linkPlaylists.setText("Playlists");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(MensagemBoasVindas)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(602, 602, 602)
                                .addComponent(ComboSelecao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(BotaoBuscar))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(linkMusicasCurtidas)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(linkPlaylists)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CampoInserir, javax.swing.GroupLayout.PREFERRED_SIZE, 447, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 615, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(linkMusicasCurtidas)
                        .addGap(33, 33, 33)
                        .addComponent(linkPlaylists)))
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
    private javax.swing.JLabel linkMusicasCurtidas;
    private javax.swing.JLabel linkPlaylists;
    private javax.swing.JTable tabelaMusicas;
    // End of variables declaration//GEN-END:variables
}
