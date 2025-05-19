package controller;

import model.Usuario;
import model.UsuarioDAO;

public class FuncaoCadastro {
    private UsuarioDAO usuarioDAO;

    public FuncaoCadastro(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }
    
     public boolean cadastrarUsuario(String nome, String email, String senha, int id) throws Exception {
        Usuario usuario = new Usuario(nome, email, senha, id);
        return usuarioDAO.cadastrarUsuario(usuario);
    }
}
