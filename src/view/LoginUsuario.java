package view;

import javax.swing.*;
import java.awt.event.*;
import controller.FuncaoLogin;
import model.Playlist;
import model.UsuarioDAO;
import model.Usuario;

public class LoginUsuario extends javax.swing.JFrame {
    
    Playlist playlistVazia = new Playlist();
    
    public LoginUsuario() {
        initComponents();
        adicionarEventos();
    }
    
    private void adicionarEventos() {
    botaoLogin.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String email = txtEmailLogin.getText();
            String senha = new String(txtSenhaCadastro.getPassword());

            if(email.isEmpty() || senha.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
                return;
            }

            try {
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                FuncaoLogin login = new FuncaoLogin(usuarioDAO);
                boolean loginValido = login.autenticar(email, senha);
                if (loginValido) {
                    Usuario usuarioLogado = usuarioDAO.buscarPorEmail(email);
                    if (usuarioLogado != null){
                        JOptionPane.showMessageDialog(null, "Login realizado com sucesso!");
                        dispose(); // fecha a tela de login
                        new Home(usuarioLogado, playlistVazia).setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Erro ao caregar dados do usuário.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "E-mail ou senha incorretos!");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao tentar logar: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    });
    
    botaoCadastroLogin.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
            new CadastroUsuario().setVisible(true); // Abre a tela de cadastro ao clicar
            dispose(); // Fecha a tela de login ao cadastrar
        }
    });
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtEmailLogin = new javax.swing.JTextField();
        txtSenhaCadastro = new javax.swing.JPasswordField();
        botaoLogin = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        botaoCadastroLogin = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel1.setText("Login");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("Senha:");

        txtEmailLogin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEmailLoginKeyPressed(evt);
            }
        });

        txtSenhaCadastro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSenhaCadastroKeyPressed(evt);
            }
        });

        botaoLogin.setText("Entrar");
        botaoLogin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                botaoLoginKeyPressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("E-mail:");

        botaoCadastroLogin.setText("Cadastrar");
        botaoCadastroLogin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                botaoCadastroLoginKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(194, 194, 194)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(167, 167, 167)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtEmailLogin, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSenhaCadastro, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(204, 204, 204)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(botaoCadastroLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(botaoLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(188, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmailLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSenhaCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(botaoLogin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botaoCadastroLogin)
                .addContainerGap(53, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoLoginKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_botaoLoginKeyPressed
    }//GEN-LAST:event_botaoLoginKeyPressed

    private void botaoCadastroLoginKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_botaoCadastroLoginKeyPressed
    }//GEN-LAST:event_botaoCadastroLoginKeyPressed

    private void txtSenhaCadastroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSenhaCadastroKeyPressed
         if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            botaoLogin.doClick();
        }
    }//GEN-LAST:event_txtSenhaCadastroKeyPressed

    private void txtEmailLoginKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailLoginKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtSenhaCadastro.requestFocus(); // move o foco para o campo da senha
        }
    }//GEN-LAST:event_txtEmailLoginKeyPressed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoCadastroLogin;
    private javax.swing.JButton botaoLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField txtEmailLogin;
    private javax.swing.JPasswordField txtSenhaCadastro;
    // End of variables declaration//GEN-END:variables
}
