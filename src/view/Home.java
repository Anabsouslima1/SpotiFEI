package view;

import controller.FuncaoMusica;
import java.awt.*;
import java.awt.event.*;
import model.MusicaDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Home extends JFrame {
    
    private FuncaoMusica funcaoMusica;
    JPopupMenu menuPlaylists = new JPopupMenu();

    
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
            new CriarPlaylist().setVisible(true);  // você vai criar essa classe
        });

        opcaoEditar.addActionListener(e -> {
            new EditarPlaylist().setVisible(true); // você vai criar essa classe
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
        linkCurtidas = new javax.swing.JLabel();
        linkPlaylist = new javax.swing.JLabel();

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

        linkCurtidas.setText("Músicas Curtidas");

        linkPlaylist.setText("Playlists");

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
                            .addComponent(linkCurtidas)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(linkPlaylist)))
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
                        .addGap(48, 48, 48)
                        .addComponent(linkCurtidas)
                        .addGap(48, 48, 48)
                        .addComponent(linkPlaylist)))
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
    private javax.swing.JLabel linkCurtidas;
    private javax.swing.JLabel linkPlaylist;
    private javax.swing.JTable tabelaMusicas;
    // End of variables declaration//GEN-END:variables
}
