package controller;

import model.UsuarioDAO;

public class FuncaoLogin {
    private UsuarioDAO usuarioDAO;

    public FuncaoLogin(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }
    
    public boolean autenticar(String email, String senha) throws Exception {
        return usuarioDAO.validarLogin(email, senha);
    }
    
}
