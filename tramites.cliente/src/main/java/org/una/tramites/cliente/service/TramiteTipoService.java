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
import org.una.tramites.cliente.dto.TramiteTipoDTO;
import org.una.tramites.cliente.util.Request;
import org.una.tramites.cliente.util.Respuesta;

/**
 *
 * @author Pablo-VE
 */
public class TramiteTipoService {
     public Respuesta getAll(){
        try{
            Request request = new Request("tramites_tipos");
            request.get();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "Error al obtener todos los tipos de tramite");
            }
            List<TramiteTipoDTO> result = (List<TramiteTipoDTO>) request.readEntity(new GenericType<List<TramiteTipoDTO>>(){});
            return new Respuesta(true, "TramitesTipos",result);
        }catch(Exception ex){

            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta getById(Long id){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("tramites_tipos", "/{id}", parametros);
            request.get();
            if(request.isError()){
                System.out.println(request.getError());
                return new Respuesta(false, request.getError(), "Error al obtener el tipo de tramite");
            }
            TramiteTipoDTO result = (TramiteTipoDTO) request.readEntity(TramiteTipoDTO.class);
            return new Respuesta(true, "TramiteTipo", result);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
        public Respuesta guardar(TramiteTipoDTO tramiteTipo){
        try{
            Request request = new Request("tramites_tipos/");
            request.post(tramiteTipo);
            if(request.isError()){
                return new Respuesta(false, request.getError(), "No se pudo guardar el tipo de tramite");
            }
            TramiteTipoDTO result = (TramiteTipoDTO) request.readEntity(TramiteTipoDTO.class);
            return new Respuesta(true, "TramiteTipo", result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta modificar(Long id, TramiteTipoDTO tramiteTipo){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("tramites_tipos", "/{id}", parametros);
            request.put(tramiteTipo);
            if(request.isError()){
                return new Respuesta(false, request.getError(), "No se pudo modificar el tipo de tramite");
            }
            TramiteTipoDTO result = (TramiteTipoDTO) request.readEntity(TramiteTipoDTO.class);
            return new Respuesta(true, "TramiteTipo", result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta delete(Long id){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("tramites_tipos", "/{id}", parametros);
            request.delete();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "No se pudo eliminar el tipo de tramite");
            }
            return new Respuesta(true, "Tipo de tramite eliminada exitosamente");
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta deleteAll(){
        try{
            Request request = new Request("tramites_tipos");
            request.delete();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "Error al eliminar los tipos de tramites");
            }
            return new Respuesta(true, "Todas los tipos de tramite fueron eliminados con exito");
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    
     public Respuesta getByDepartamento(Long id){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("tramites_tipos/usuarios_en_departamento", "/{id}", parametros);
            request.get();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "Error al obtener tipos de tramites por departamento");
            }
            List<TramiteTipoDTO> result = (List<TramiteTipoDTO>) request.readEntity(new GenericType<List<TramiteTipoDTO>>(){});
            return new Respuesta(true, "TramitesTipos",result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
     
     public Respuesta getByDescripcion(String descripcion){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("descripcion", descripcion);
            Request request = new Request("tramites_tipos", "/{descripcion}", parametros);
            request.get();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "Error al obtener tipos de tramites por su descripcion");
            }
            List<TramiteTipoDTO> result = (List<TramiteTipoDTO>) request.readEntity(new GenericType<List<TramiteTipoDTO>>(){});
            return new Respuesta(true, "TramitesTipos",result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
}
