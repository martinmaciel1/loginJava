
package com.martinmaciel.login.persistencia;

import com.martinmaciel.login.logica.Rol;
import com.martinmaciel.login.logica.Usuario;
import com.martinmaciel.login.persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ControladoraPersistencia {
   
    
    UsuarioJpaController usuJpa = new UsuarioJpaController();
    RolJpaController rolJpa = new RolJpaController();

    public List<Usuario> traerUsuarios() {
        return usuJpa.findUsuarioEntities();
    }

    public List<Usuario> traerListaUsuarios() {
        return usuJpa.findUsuarioEntities();
    }

    public List<Rol> traerListaRoles() {
        return rolJpa.findRolEntities();
    
    }

    public void crearUsuario(Usuario usuario) {
        usuJpa.create(usuario);
    
    }

    public void borrarUsuario(int idUsuario) {
        try {
            usuJpa.destroy(idUsuario);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Usuario buscarUsuario(int idUsuario) {
       return  usuJpa.findUsuario(idUsuario);
    }

    public void editarUsuario(Usuario usu) {
        try {
            usuJpa.edit(usu);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
