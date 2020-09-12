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
import org.una.tramites.cliente.dto.TramiteRegistradoDTO;
import org.una.tramites.cliente.util.Request;
import org.una.tramites.cliente.util.Respuesta;

/**
 *
 * @author Pablo-VE
 */
public class TramiteRegistradoService {
    public Respuesta getAll(){
        try{
            Request request = new Request("tramites_registrados");
            request.get();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "Error al obtener todos los tramites registrados");
            }
            List<TramiteRegistradoDTO> result = (List<TramiteRegistradoDTO>) request.readEntity(new GenericType<List<TramiteRegistradoDTO>>(){});
            return new Respuesta(true, "TramitesRegistrados",result);
        }catch(Exception ex){

            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta getById(Long id){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("tramites_registrados", "/{id}", parametros);
            request.get();
            if(request.isError()){
                System.out.println(request.getError());
                return new Respuesta(false, request.getError(), "Error al obtener el tramite registrado");
            }
            TramiteRegistradoDTO result = (TramiteRegistradoDTO) request.readEntity(TramiteRegistradoDTO.class);
            return new Respuesta(true, "TramiteRegistrado", result);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
        public Respuesta guardar(TramiteRegistradoDTO tramiteRegistrado){
        try{
            Request request = new Request("tramites_registrados");
            request.post(tramiteRegistrado);
            if(request.isError()){
                return new Respuesta(false, request.getError(), "No se pudo guardar el tramite registrado");
            }
            TramiteRegistradoDTO result = (TramiteRegistradoDTO) request.readEntity(TramiteRegistradoDTO.class);
            return new Respuesta(true, "TramiteRegistrado", result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta modificar(Long id, TramiteRegistradoDTO tramiteRegistrado){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("tramites_registrados", "/{id}", parametros);
            request.put(tramiteRegistrado);
            if(request.isError()){
                return new Respuesta(false, request.getError(), "No se pudo modificar el tramite estado");
            }
            TramiteRegistradoDTO result = (TramiteRegistradoDTO) request.readEntity(TramiteRegistradoDTO.class);
            return new Respuesta(true, "TramiteRegistrado", result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta delete(Long id){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("tramites_registrados", "/{id}", parametros);
            request.delete();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "No se pudo eliminar el Tramite Registrado");
            }
            return new Respuesta(true, "Tramite registrado fue eliminado exitosamente");
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta deleteAll(){
        try{
            Request request = new Request("tramites_registrados");
            request.delete();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "Error al eliminar todos los Tramites registrados");
            }
            return new Respuesta(true, "Todos los tramites registrados fueron eliminados con exito");
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No pudo establecerce conexion con el servidor");
        }
    }
}
