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
import org.una.tramites.cliente.dto.NotaDTO;
import org.una.tramites.cliente.dto.ParametrosGeneralesDTO;
import org.una.tramites.cliente.dto.PermisoDTO;
import org.una.tramites.cliente.util.Request;
import org.una.tramites.cliente.util.Respuesta;

/**
 *
 * @author Luis
 */
public class ParametrosGeneralesService {
    public Respuesta getAll(){
        try{
            Request request = new Request("parametros_generales");
            request.get();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "Error al obtener todos los parametros generales");
            }
            List<NotaDTO> result = (List<NotaDTO>) request.readEntity(new GenericType<List<NotaDTO>>(){});
            return new Respuesta(true, "ParametrosGeneral",result);
        }catch(Exception ex){

            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta getById(Long id){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("parametros_generales", "/{id}", parametros);
            request.get();
            if(request.isError()){
                System.out.println(request.getError());
                return new Respuesta(false, request.getError(), "Error al obtener los parametros generales");
            }
            NotaDTO result = (NotaDTO) request.readEntity(NotaDTO.class);
            return new Respuesta(true, "ParametrosGeneral", result);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta getByNombre(String nombre){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("nombre", nombre);
            Request request = new Request("parametros_generales", "/{nombre}", parametros);
            request.get();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "Error al obtener los parametros generales");
            }
            List<ParametrosGeneralesDTO> result = (List<ParametrosGeneralesDTO>) request.readEntity(new GenericType<List<ParametrosGeneralesDTO>>(){});
            return new Respuesta(true, "ParametrosGeneral",result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta getByEstado(Boolean estado){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("estado", estado);
            Request request = new Request("parametros_generales/", "/{estado}", parametros);
            request.get();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "Error al obtener los parametros generales");
            }
            List<PermisoDTO> result = (List<PermisoDTO>) request.readEntity(new GenericType<List<PermisoDTO>>(){});
            return new Respuesta(true, "ParametrosGenerales",result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }    

   
    public Respuesta guardar(NotaDTO nota){
        try{
            Request request = new Request("parametros_generales");
            request.post(nota);
            if(request.isError()){
                return new Respuesta(false, request.getError(), "No se pudo guardar los parametros generales");
            }
            NotaDTO result = (NotaDTO) request.readEntity(NotaDTO.class);
            return new Respuesta(true, "ParametrosGeneral", result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta modificar(Long id, NotaDTO nota){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("parametros_generales", "/{id}", parametros);
            request.put(nota);
            if(request.isError()){
                return new Respuesta(false, request.getError(), "No se pudo modificar los parametros generales");
            }
            NotaDTO result = (NotaDTO) request.readEntity(NotaDTO.class);
            return new Respuesta(true, "ParametrosGeneral", result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta delete(Long id){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("parametros_generales", "/{id}", parametros);
            request.delete();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "No se pudo eliminar los parametros generales");
            }
            return new Respuesta(true, "La nota fue eliminada exitosamente");
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta deleteAll(){
        try{
            Request request = new Request("parametros_generales");
            request.delete();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "Error al eliminar todos los parametros generales");
            }
            return new Respuesta(true, "Todas las notas fueron eliminadas con exito");
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No pudo establecerce conexion con el servidor");
        }
    }
    
   
    
}
