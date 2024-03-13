
package com.martinmaciel.login.logica;

import com.martinmaciel.login.persistencia.ControladoraPersistencia;
import java.util.List;


public class ControladoraLogica {
    
    
    ControladoraPersistencia controlPersis;
    
    public ControladoraLogica(){
        controlPersis = new ControladoraPersistencia();
    }

    public Usuario validarUsuario(String usuario, String contrasenia) {
        
        Usuario user = null;
        List<Usuario> listaUsuario = controlPersis.traerUsuarios();
        for (Usuario usu : listaUsuario){
            if (usu.getNombreUsuario().equals(usuario)){
                if (usu.getContrasenia().equals(contrasenia)){
                     user = usu;
                     return user;
                }else{
                    return user;
                }
            }                
        }
       return user;
    }

    public String validarRol(Usuario user) {
       return user.getUnRol().getNombreRol();
    }

    public List<Usuario> traerListaUsuarios() {
        return controlPersis.traerListaUsuarios();
    }

    public List<Rol> traerRoles() {
        return controlPersis.traerListaRoles();
    }

    public void crearUsuario(String nombreUser, String contrasenia, String rolRecibido) {
        Usuario usuario = new Usuario();
        
        usuario.setNombreUsuario(nombreUser);
        usuario.setContrasenia(contrasenia);
        
        
        Rol rolEncontrado = new Rol();
        rolEncontrado = this.traerRol(rolRecibido);
        
        if (rolEncontrado!=null){
            usuario.setUnRol(rolEncontrado);
        }
        int id = buscarUltimoIdUsuario();
        usuario.setId(id+1);
        
        controlPersis.crearUsuario(usuario);
    }

    private Rol traerRol(String rolRecibido) {
            List<Rol> listaRoles = traerRoles();
            
            for (Rol rol : listaRoles){
                if(rol.getNombreRol().equals(rolRecibido)){
                    return rol;
                }
            }
            return null;
    }

    private int buscarUltimoIdUsuario() {
        List <Usuario> listaUsuarios = traerListaUsuarios();
        
        Usuario usu =listaUsuarios.get(listaUsuarios.size()-1);
        return usu.getId();
    }

    public void borrarUsuario(int idUsuario) {
        controlPersis.borrarUsuario(idUsuario);
    }

    public Usuario buscarUsuario(int idUsuario) {
        return controlPersis.buscarUsuario(idUsuario);
    }

    public void editarUsuario(Usuario usu, String nombreUser, String contrasenia, String rolRecibido) {
       usu.setNombreUsuario(nombreUser);
       usu.setContrasenia(contrasenia);
       
       Rol rolEncontrado = new Rol();
        rolEncontrado = this.traerRol(rolRecibido);
        
        if (rolEncontrado!=null){
            usu.setUnRol(rolEncontrado);
        }
       controlPersis.editarUsuario(usu);
    }
    
    
}
