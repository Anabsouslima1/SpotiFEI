package view;

import controller.FuncaoMusica;
import java.awt.*;
import java.awt.event.*;
import model.MusicaDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.Playlist;
import model.Usuario;

public class Home extends JFrame {

    private Usuario usuarioLogado;
    private FuncaoMusica funcaoMusica;
    private Playlist playlistSelecionada;
    JPopupMenu menuPlaylists = new JPopupMenu();
    JPopupMenu menuMusica = new JPopupMenu();
   
    public Home(Usuario usuario, Playlist playlist){
        this.playlistSelecionada = playlist;
        this.usuarioLogado = usuario;
        initComponents();
        atualizarContadorMusicas();        
        labelTotalMusicas.setText("Total de Músicas: ");

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
        
        MensagemBoasVindas.setText("Bem-Vindo ao SpotiFEI, " + usuarioLogado.getNome() + "!");
        
        
        try {
            tabelaMusicas.setModel(funcaoMusica.obterMusicas());
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Erro ao carregar músicas: " + e.getMessage());
        }
        
        // Configurações da Label para Playlist
        linkPlaylist.setForeground(Color.BLACK); //cor normal
        linkPlaylist.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        linkPlaylist.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                linkPlaylist.setForeground(Color.BLUE);  // azul quando hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                linkPlaylist.setForeground(Color.BLACK); // volta para a cor normal
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e) || SwingUtilities.isLeftMouseButton(e)) {
                    menuPlaylists.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
        
        JMenuItem opcaoCriar = new JMenuItem("Criar Playlist");
        JMenuItem opcaoEditar = new JMenuItem("Editar Playlist");
        JMenuItem opcaoExcluir = new JMenuItem("Excluir Playlist");

        menuPlaylists.add(opcaoCriar);
        menuPlaylists.add(opcaoEditar);
        menuPlaylists.add(opcaoExcluir);

        // Ações para cada item do menu
        opcaoCriar.addActionListener(e -> {
            new CriarPlaylist(usuarioLogado.getId()).setVisible(true);  // você vai criar essa classe
        });

        opcaoEditar.addActionListener(e -> {
            new SelecaoPlaylist(usuarioLogado.getId()).setVisible(true); // você vai criar essa classe
        });

        opcaoExcluir.addActionListener(e -> {
            new ExcluirPlaylist().setVisible(true); // você vai criar essa classe
        });
        
        // Configuração de Label para Curtidas
        linkCurtidas.setCursor(new Cursor(Cursor.HAND_CURSOR));
        linkCurtidas.setForeground(Color.BLACK);

        linkCurtidas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                linkCurtidas.setForeground(Color.BLUE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                linkCurtidas.setForeground(Color.BLACK);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                new MusicasCurtidas().setVisible(true);
            }
        });
        
            JPopupMenu menuMusica = new JPopupMenu(); // menu específico da tabela de músicas

        // Configurar menu popup da tabelaMusicas
        JMenuItem adicionarPlaylist = new JMenuItem("Adicionar à Playlist");
        menuMusica.add(adicionarPlaylist);

        // Evento do item do menu
        adicionarPlaylist.addActionListener(e -> {
            int linhaSelecionada = tabelaMusicas.getSelectedRow();
            if (linhaSelecionada != -1) {
                // Obter dados da música selecionada (exemplo: nome da música na coluna 0)
                String nomeMusica = tabelaMusicas.getValueAt(linhaSelecionada, 0).toString();
                // Abrir a janela para selecionar playlist e adicionar essa música
                new AdicionarMusicaPlaylist(nomeMusica, usuarioLogado.getId()).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Selecione uma música para adicionar.");
            }
        });

        // Adicionar mouse listener para o clique direito na tabela
        tabelaMusicas.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    abrirMenuPopup(e);
                }
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    abrirMenuPopup(e);
                }
            }
            private void abrirMenuPopup(MouseEvent e) {
                int linha = tabelaMusicas.rowAtPoint(e.getPoint());
                if (linha >= 0 && linha < tabelaMusicas.getRowCount()) {
                    tabelaMusicas.setRowSelectionInterval(linha, linha); // seleciona a linha onde clicou
                    menuMusica.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });

    }
    
    // Função carregar
    private void carregarMusicas() {
        try {
            DefaultTableModel model = funcaoMusica.obterMusicas();
            tabelaMusicas.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar músicas: " + e.getMessage());
        }
        atualizarContadorMusicas();
    }
    
    // Função Buscar
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
        atualizarContadorMusicas();
    }
    
    private void atualizarContadorMusicas(){
        int total = tabelaMusicas.getRowCount();
        labelTotalMusicas.setText("Total de Músicas: " + total);
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
        linkCurtidas = new javax.swing.JLabel();
        linkPlaylist = new javax.swing.JLabel();
        labelTotalMusicas = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        MensagemBoasVindas.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        MensagemBoasVindas.setText("Bem-Vindo ao SpotiFEI!");

        CampoInserir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CampoInserirKeyPressed(evt);
            }
        });

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

        linkCurtidas.setText("Músicas Curtidas");

        linkPlaylist.setText("Playlists");

        labelTotalMusicas.setText("Total de Músicas:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(MensagemBoasVindas)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(linkCurtidas)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(23, 23, 23)
                                        .addComponent(linkPlaylist)))
                                .addGap(20, 20, 20))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(labelTotalMusicas)
                                .addGap(18, 18, 18)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 661, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(CampoInserir)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ComboSelecao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BotaoBuscar)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(MensagemBoasVindas)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(CampoInserir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BotaoBuscar)
                            .addComponent(ComboSelecao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(labelTotalMusicas)
                        .addGap(35, 35, 35)
                        .addComponent(linkCurtidas)
                        .addGap(48, 48, 48)
                        .addComponent(linkPlaylist)))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CampoInserirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CampoInserirKeyPressed
            if (evt.getKeyCode() == KeyEvent.VK_ENTER){
                BotaoBuscar.doClick();
            }
    }//GEN-LAST:event_CampoInserirKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotaoBuscar;
    private javax.swing.JTextField CampoInserir;
    private javax.swing.JComboBox<String> ComboSelecao;
    private javax.swing.JLabel MensagemBoasVindas;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelTotalMusicas;
    private javax.swing.JLabel linkCurtidas;
    private javax.swing.JLabel linkPlaylist;
    private javax.swing.JTable tabelaMusicas;
    // End of variables declaration//GEN-END:variables
}
