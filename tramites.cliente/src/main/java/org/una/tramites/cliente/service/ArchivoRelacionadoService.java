/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.cliente.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.GenericType;
import org.una.tramites.cliente.dto.ArchivoRelacionadoDTO;
import org.una.tramites.cliente.util.Request;
import org.una.tramites.cliente.util.Respuesta;

/**
 *
 * @author Pablo-VE
 */
public class ArchivoRelacionadoService {
    public Respuesta getAll(){
        try{
            Request request = new Request("archivos_relacionados");
            request.get();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "Error al obtener todos los archivos relacionados");
            }
            List<ArchivoRelacionadoDTO> result = (List<ArchivoRelacionadoDTO>) request.readEntity(new GenericType<List<ArchivoRelacionadoDTO>>(){});
            return new Respuesta(true, "ArchivosRelacionados",result);
        }catch(Exception ex){

            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta getById(Long id){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("archivos_relacionados", "/{id}", parametros);
            request.get();
            if(request.isError()){
                System.out.println(request.getError());
                return new Respuesta(false, request.getError(), "Error al obtener el archivo relacionado");
            }
            ArchivoRelacionadoDTO result = (ArchivoRelacionadoDTO) request.readEntity(ArchivoRelacionadoDTO.class);
            return new Respuesta(true, "ArchivoRelacionado", result);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
        public Respuesta guardar(ArchivoRelacionadoDTO archivoRelacionado){
        try{
            Request request = new Request("archivos_relacionados");
            request.post(archivoRelacionado);
            if(request.isError()){
                return new Respuesta(false, request.getError(), "No se pudo guardar el archivo relacionado");
            }
            ArchivoRelacionadoDTO result = (ArchivoRelacionadoDTO) request.readEntity(ArchivoRelacionadoDTO.class);
            return new Respuesta(true, "ArchivoRelacionado", result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta modificar(Long id, ArchivoRelacionadoDTO archivoRelacionado){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("archivos_relacionados", "/{id}", parametros);
            request.put(archivoRelacionado);
            if(request.isError()){
                return new Respuesta(false, request.getError(), "No se pudo modificar el archivo relacionado");
            }
            ArchivoRelacionadoDTO result = (ArchivoRelacionadoDTO) request.readEntity(ArchivoRelacionadoDTO.class);
            return new Respuesta(true, "ArchivoRelacionado", result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta delete(Long id){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("archivos_relacionados", "/{id}", parametros);
            request.delete();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "No se pudo eliminar el archivo relacionado");
            }
            return new Respuesta(true, "El archivo relacionado fue eliminado exitosamente");
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta deleteAll(){
        try{
            Request request = new Request("archivos_relacionados");
            request.delete();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "Error al eliminar todos los archivos relacionados");
            }
            return new Respuesta(true, "Todos los archivos relacionados fueron eliminados con exito");
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No pudo establecerce conexion con el servidor");
        }
    }
    
    
    public Respuesta getByFechaDeRegistro(Date fechaRegistro){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("fechaRegistro", fechaRegistro);
            Request request = new Request("archivos_relacionados", "/{fechaRegistro}", parametros);
            request.get();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "Error al obtener los archivos relacionados por fecha de registro");
            }
            List<ArchivoRelacionadoDTO> result = (List<ArchivoRelacionadoDTO>) request.readEntity(new GenericType<List<ArchivoRelacionadoDTO>>(){});
            return new Respuesta(true, "ArchivosRelacionados",result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    
    public Respuesta getByTramiteRegistrado(Long id){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("archivos_relacionados/tramite_registrado", "/{id}", parametros);
            request.get();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "Error al obtener archivos relacionados por tramite registrado");
            }
            List<ArchivoRelacionadoDTO> result = (List<ArchivoRelacionadoDTO>) request.readEntity(new GenericType<List<ArchivoRelacionadoDTO>>(){});
            return new Respuesta(true, "ArchivosRelacionados",result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
}
