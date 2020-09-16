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
import org.una.tramites.cliente.dto.RequisitoDTO;
import org.una.tramites.cliente.util.Request;
import org.una.tramites.cliente.util.Respuesta;

/**
 *
 * @author Pablo-VE
 */
public class RequisitoService {
    public Respuesta getAll(){
        try{
            Request request = new Request("requisitos");
            request.get();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "Error al obtener todos los requisitos");
            }
            List<RequisitoDTO> result = (List<RequisitoDTO>) request.readEntity(new GenericType<List<RequisitoDTO>>(){});
            return new Respuesta(true, "Requisitos",result);
        }catch(Exception ex){

            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta getById(Long id){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("requisitos", "/{id}", parametros);
            request.get();
            if(request.isError()){
                System.out.println(request.getError());
                return new Respuesta(false, request.getError(), "Error al obtener el requisito");
            }
            RequisitoDTO result = (RequisitoDTO) request.readEntity(RequisitoDTO.class);
            return new Respuesta(true, "Requisito", result);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
        public Respuesta guardar(RequisitoDTO requisito){
        try{
            Request request = new Request("requisitos");
            request.post(requisito);
            if(request.isError()){
                return new Respuesta(false, request.getError(), "No se pudo guardar el requisito");
            }
            RequisitoDTO result = (RequisitoDTO) request.readEntity(RequisitoDTO.class);
            return new Respuesta(true, "Variacion", result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta modificar(Long id, RequisitoDTO requisito){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("requisitos", "/{id}", parametros);
            request.put(requisito);
            if(request.isError()){
                return new Respuesta(false, request.getError(), "No se pudo modificar el requisito");
            }
            RequisitoDTO result = (RequisitoDTO) request.readEntity(RequisitoDTO.class);
            return new Respuesta(true, "Requisito", result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta delete(Long id){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("requisitos", "/{id}", parametros);
            request.delete();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "No se pudo eliminar el requisito");
            }
            return new Respuesta(true, "Requisito eliminado exitosamente");
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta deleteAll(){
        try{
            Request request = new Request("requisitos");
            request.delete();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "Error al eliminar todos los requisitos");
            }
            return new Respuesta(true, "Todos los requisitos fueron eliminados con exito");
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
     
     public Respuesta getByDescripcion(String descripcion){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("descripcion", descripcion);
            Request request = new Request("requisitos/pordescripcion", "/{descripcion}", parametros);
            request.get();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "Error al obtener requisitos por su descripcion");
            }
            List<RequisitoDTO> result = (List<RequisitoDTO>) request.readEntity(new GenericType<List<RequisitoDTO>>(){});
            return new Respuesta(true, "Requisitos",result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    /*public Respuesta getByVariacion(Long id){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("varId", id);
            Request request = new Request("requisitos/usuarios_en_departamento", "/{depaId}", parametros);
            request.get();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "Error al obtener tipos de tramites por departamento");
            }
            List<RequisitoDTO> result = (List<RequisitoDTO>) request.readEntity(new GenericType<List<RequisitoDTO>>(){});
            return new Respuesta(true, "TramitesTipos",result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }*/
}
