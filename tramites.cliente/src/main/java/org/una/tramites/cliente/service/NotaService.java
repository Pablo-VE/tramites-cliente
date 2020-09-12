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
import org.una.tramites.cliente.util.Request;
import org.una.tramites.cliente.util.Respuesta;

/**
 *
 * @author Pablo-VE
 */
public class NotaService {
    public Respuesta getAll(){
        try{
            Request request = new Request("notas");
            request.get();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "Error al obtener todas las notas");
            }
            List<NotaDTO> result = (List<NotaDTO>) request.readEntity(new GenericType<List<NotaDTO>>(){});
            return new Respuesta(true, "Notas",result);
        }catch(Exception ex){

            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta getById(Long id){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("notas", "/{id}", parametros);
            request.get();
            if(request.isError()){
                System.out.println(request.getError());
                return new Respuesta(false, request.getError(), "Error al obtener la nota");
            }
            NotaDTO result = (NotaDTO) request.readEntity(NotaDTO.class);
            return new Respuesta(true, "Nota", result);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
        public Respuesta guardar(NotaDTO nota){
        try{
            Request request = new Request("notas");
            request.post(nota);
            if(request.isError()){
                return new Respuesta(false, request.getError(), "No se pudo guardar la nota");
            }
            NotaDTO result = (NotaDTO) request.readEntity(NotaDTO.class);
            return new Respuesta(true, "Nota", result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta modificar(Long id, NotaDTO nota){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("notas", "/{id}", parametros);
            request.put(nota);
            if(request.isError()){
                return new Respuesta(false, request.getError(), "No se pudo modificar la nota");
            }
            NotaDTO result = (NotaDTO) request.readEntity(NotaDTO.class);
            return new Respuesta(true, "Nota", result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta delete(Long id){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("notas", "/{id}", parametros);
            request.delete();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "No se pudo eliminar la nota");
            }
            return new Respuesta(true, "La nota fue eliminada exitosamente");
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta deleteAll(){
        try{
            Request request = new Request("notas");
            request.delete();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "Error al eliminar todas las notas");
            }
            return new Respuesta(true, "Todas las notas fueron eliminadas con exito");
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No pudo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta getByTitulo(String titulo){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("titulo", titulo);
            Request request = new Request("notas", "/{titulo}", parametros);
            request.get();
            if(request.isError()){
                System.out.println(request.getError());
                return new Respuesta(false, request.getError(), "Error al obtener la nota por el titulo");
            }
            NotaDTO result = (NotaDTO) request.readEntity(NotaDTO.class);
            return new Respuesta(true, "Nota", result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
}
