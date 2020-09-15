/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.cliente.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.GenericType;
import org.una.tramites.cliente.dto.AuthenticationRequest;
import org.una.tramites.cliente.dto.AuthenticationResponse;
import org.una.tramites.cliente.dto.UsuarioDTO;
import org.una.tramites.cliente.util.AppContext;
import org.una.tramites.cliente.util.Request;
import org.una.tramites.cliente.util.Respuesta;

/**
 *
 * @author Pablo-VE
 */
public class UsuarioService {
    public Respuesta LogIn(String userName, String userPassword){
        try{
            AuthenticationRequest authetication = new AuthenticationRequest(userName, userPassword);
            Request request = new Request("usuarios/login");
            request.post(authetication);
            if(request.isError()){
                System.out.println(request.getError());
                return new Respuesta(false, request.getError(), "Error al iniciar Sesion");
            }
            AuthenticationResponse usuario = (AuthenticationResponse) request.readEntity(AuthenticationResponse.class);
            AppContext.getInstance().set("UsuarioAutenticado", usuario);
            AppContext.getInstance().set("TokenJwt", "bearer "+usuario.getJwt());
            return new Respuesta(true, "Usuario", usuario);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta getAll(){
        try{
            Request request = new Request("usuarios");
            request.get();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "Error al obtener todos los usuarios");
            }
            List<UsuarioDTO> result = (List<UsuarioDTO>) request.readEntity(new GenericType<List<UsuarioDTO>>(){});
            return new Respuesta(true, "Usuarios",result);
        }catch(Exception ex){

            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta getUserById(Long id){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("usuarios", "/{id}", parametros);
            request.get();
            if(request.isError()){
                System.out.println(request.getError());
                return new Respuesta(false, request.getError(), "Error al obtener los usuarios");
            }
            UsuarioDTO result = (UsuarioDTO) request.readEntity(UsuarioDTO.class);
            return new Respuesta(true, "Usuario", result);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta getUsersByCedula(String cedula){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("term", cedula);
            Request request = new Request("usuarios/cedula", "/{term}", parametros);
            request.get();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "Error al obtener los usuarios");
            }
            List<UsuarioDTO> result = (List<UsuarioDTO>) request.readEntity(new GenericType<List<UsuarioDTO>>(){});
            return new Respuesta(true, "Usuarios",result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta getUsersByNombre(String nombre){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("term", nombre);
            Request request = new Request("usuarios/nombre", "/{term}", parametros);
            request.get();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "Error al obtener los usuarios");
            }
            List<UsuarioDTO> result = (List<UsuarioDTO>) request.readEntity(new GenericType<List<UsuarioDTO>>(){});
            return new Respuesta(true, "Usuarios",result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta getUserByDepartamento(Long id){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("usuarios/usuarios_en_departamento", "/{id}", parametros);
            request.get();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "Error al obtener los usuarios");
            }
            List<UsuarioDTO> result = (List<UsuarioDTO>) request.readEntity(new GenericType<List<UsuarioDTO>>(){});
            return new Respuesta(true, "Usuarios",result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta getJefeDepartamento(Long id){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("usuarios/jefe", "/{id}", parametros);
            request.get();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "Error al obtener el jefe del departamento");
            }
            UsuarioDTO result = (UsuarioDTO) request.readEntity(UsuarioDTO.class);
            return new Respuesta(true, "Usuario", result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta guardarUsuario(UsuarioDTO usuario){
        try{
            Request request = new Request("usuarios/");
            request.post(usuario);
            if(request.isError()){
                System.out.println("Error creacion de usuario: "+request.getError());
                return new Respuesta(false, request.getError(), "No se pudo guardar el usuario");
            }
            UsuarioDTO result = (UsuarioDTO) request.readEntity(UsuarioDTO.class);
            return new Respuesta(true, "Usuario", result);
        }catch(Exception ex){
            System.out.println("Excepcion creacion de usuario: "+ex.getMessage());
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta modificarUsuario(Long id, UsuarioDTO usuario){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("usuarios", "/{id}", parametros);
            request.put(usuario);
            if(request.isError()){
                return new Respuesta(false, request.getError(), "No se pudo modificar el usuario");
            }
            UsuarioDTO result = (UsuarioDTO) request.readEntity(UsuarioDTO.class);
            return new Respuesta(true, "Usuario", result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta deleteUsuario(Long id){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("usuarios", "/{id}", parametros);
            request.delete();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "No se pudo eliminar el usuario");
            }
            return new Respuesta(true, "Usuario eliminado exitosamente");
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta deleteAll(){
        try{
            Request request = new Request("usuarios");
            request.delete();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "Error al eliminar los usuarios");
            }
            return new Respuesta(true, "Todos los usuarios fueron eliminados con exito");
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
}
