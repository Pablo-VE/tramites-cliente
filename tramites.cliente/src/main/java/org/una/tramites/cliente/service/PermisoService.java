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
import org.una.tramites.cliente.dto.PermisoDTO;
import org.una.tramites.cliente.util.Request;
import org.una.tramites.cliente.util.Respuesta;

/**
 *
 * @author Pablo-VE
 */
public class PermisoService {
    public Respuesta getAll(){
        try{
            Request request = new Request("permisos/");
            request.get();
            if(request.isError()){
                System.out.println("Error permisos:¨"+request.getError());
                return new Respuesta(false, request.getError(), "Error al obtener todos los permisos");
            }
            List<PermisoDTO> result = (List<PermisoDTO>) request.readEntity(new GenericType<List<PermisoDTO>>(){});
            return new Respuesta(true, "Permisos",result);
        }catch(Exception ex){
            System.out.println("Error permisos:¨"+ex.getMessage());
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta getById(Long id){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("permisos", "/{id}", parametros);
            request.get();
            if(request.isError()){
                System.out.println(request.getError());
                return new Respuesta(false, request.getError(), "Error al obtener el permiso");
            }
            PermisoDTO result = (PermisoDTO) request.readEntity(PermisoDTO.class);
            return new Respuesta(true, "Permiso", result);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta guardar(PermisoDTO permiso){
        try{
            Request request = new Request("permisos/");
            request.post(permiso);
            if(request.isError()){
                System.out.println(request.getError());
                return new Respuesta(false, request.getError(), "No se pudo guardar el permiso");
            }
            PermisoDTO result = (PermisoDTO) request.readEntity(PermisoDTO.class);
            return new Respuesta(true, "Permiso", result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta modificar(Long id, PermisoDTO permiso){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("permisos", "/{id}", parametros);
            request.put(permiso);
            if(request.isError()){
                return new Respuesta(false, request.getError(), "No se pudo modificar el permiso");
            }
            PermisoDTO result = (PermisoDTO) request.readEntity(PermisoDTO.class);
            return new Respuesta(true, "Permiso", result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta delete(Long id){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("permisos", "/{id}", parametros);
            request.delete();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "No se pudo eliminar el permiso");
            }
            return new Respuesta(true, "Permiso eliminado exitosamente");
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta deleteAll(){
        try{
            Request request = new Request("permisos");
            request.delete();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "Error al eliminar los permisos");
            }
            return new Respuesta(true, "Todos los permisos fueron eliminados con exito");
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    
        public Respuesta getPermisoByEstado(Boolean estado){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("estado", estado);
            Request request = new Request("permisos/estado", "/{estado}", parametros);
            request.get();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "Error al obtener los departamentos");
            }
            List<PermisoDTO> result = (List<PermisoDTO>) request.readEntity(new GenericType<List<PermisoDTO>>(){});
            return new Respuesta(true, "Permisos",result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    
}

