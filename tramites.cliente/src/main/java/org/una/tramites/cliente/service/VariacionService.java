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
import org.una.tramites.cliente.dto.VariacionDTO;
import org.una.tramites.cliente.util.Request;
import org.una.tramites.cliente.util.Respuesta;

/**
 *
 * @author Pablo-VE
 */
public class VariacionService {
    public Respuesta getAll(){
        try{
            Request request = new Request("variaciones");
            request.get();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "Error al obtener todas las variaciones");
            }
            List<VariacionDTO> result = (List<VariacionDTO>) request.readEntity(new GenericType<List<VariacionDTO>>(){});
            return new Respuesta(true, "Variaciones",result);
        }catch(Exception ex){

            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta getById(Long id){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("variaciones", "/{id}", parametros);
            request.get();
            if(request.isError()){
                System.out.println(request.getError());
                return new Respuesta(false, request.getError(), "Error al obtener la variacion");
            }
            VariacionDTO result = (VariacionDTO) request.readEntity(VariacionDTO.class);
            return new Respuesta(true, "Variacion", result);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
        public Respuesta guardar(VariacionDTO variacion){
        try{
            Request request = new Request("variaciones/");
            request.post(variacion);
            if(request.isError()){
                return new Respuesta(false, request.getError(), "No se pudo guardar la variacion");
            }
            VariacionDTO result = (VariacionDTO) request.readEntity(VariacionDTO.class);
            return new Respuesta(true, "Variacion", result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta modificar(Long id, VariacionDTO variacion){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("variaciones", "/{id}", parametros);
            request.put(variacion);
            if(request.isError()){
                return new Respuesta(false, request.getError(), "No se pudo modificar la variacion");
            }
            VariacionDTO result = (VariacionDTO) request.readEntity(VariacionDTO.class);
            return new Respuesta(true, "Variacion", result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta delete(Long id){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("variaciones", "/{id}", parametros);
            request.delete();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "No se pudo eliminar la variacion");
            }
            return new Respuesta(true, "Variacion eliminada exitosamente");
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta deleteAll(){
        try{
            Request request = new Request("variaciones");
            request.delete();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "Error al eliminar todas las variaciones");
            }
            return new Respuesta(true, "Todas las variaciones fueron eliminados con exito");
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    
     public Respuesta getByGrupo(Long id){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("variaciones", "/{id}", parametros);
            request.get();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "Error al obtener variaciones por grupo");
            }
            List<VariacionDTO> result = (List<VariacionDTO>) request.readEntity(new GenericType<List<VariacionDTO>>(){});
            return new Respuesta(true, "Variaciones",result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
     
     public Respuesta getByDescripcion(String descripcion){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("descripcion", descripcion);
            Request request = new Request("variaciones", "/{descripcion}", parametros);
            request.get();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "Error al obtener variaciones por su descripcion");
            }
            List<VariacionDTO> result = (List<VariacionDTO>) request.readEntity(new GenericType<List<VariacionDTO>>(){});
            return new Respuesta(true, "Variaciones",result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
}
