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
import org.una.tramites.cliente.dto.TramiteCambioEstadoDTO;
import org.una.tramites.cliente.util.Request;
import org.una.tramites.cliente.util.Respuesta;

/**
 *
 * @author Pablo-VE
 */
public class TramiteCambioEstadoService {
    public Respuesta getAll(){
        try{
            Request request = new Request("tramites_cambio_estado");
            request.get();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "Error al obtener todos los tramites cambio de estado");
            }
            List<TramiteCambioEstadoDTO> result = (List<TramiteCambioEstadoDTO>) request.readEntity(new GenericType<List<TramiteCambioEstadoDTO>>(){});
            return new Respuesta(true, "TramitesCambioEstado",result);
        }catch(Exception ex){

            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta getById(Long id){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("tramites_cambio_estado", "/{id}", parametros);
            request.get();
            if(request.isError()){
                System.out.println(request.getError());
                return new Respuesta(false, request.getError(), "Error al obtener el tramite cambio estado");
            }
            TramiteCambioEstadoDTO result = (TramiteCambioEstadoDTO) request.readEntity(TramiteCambioEstadoDTO.class);
            return new Respuesta(true, "TramiteCambioEstado", result);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
        public Respuesta guardar(TramiteCambioEstadoDTO tramiteCambioEstado){
        try{
            Request request = new Request("tramites_cambio_estado");
            request.post(tramiteCambioEstado);
            if(request.isError()){
                return new Respuesta(false, request.getError(), "No se pudo guardar el tramite cambio estado");
            }
            TramiteCambioEstadoDTO result = (TramiteCambioEstadoDTO) request.readEntity(TramiteCambioEstadoDTO.class);
            return new Respuesta(true, "TramiteCambioEstado", result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta modificar(Long id, TramiteCambioEstadoDTO tramiteCambioEstado){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("tramites_cambio_estado", "/{id}", parametros);
            request.put(tramiteCambioEstado);
            if(request.isError()){
                return new Respuesta(false, request.getError(), "No se pudo modificar el tramite cambio estado");
            }
            TramiteCambioEstadoDTO result = (TramiteCambioEstadoDTO) request.readEntity(TramiteCambioEstadoDTO.class);
            return new Respuesta(true, "TramiteCambioEstado", result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta delete(Long id){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("tramites_cambio_estado", "/{id}", parametros);
            request.delete();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "No se pudo eliminar el Tramite Cambio Estado");
            }
            return new Respuesta(true, "Tramite Cambio Estado eliminado exitosamente");
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta deleteAll(){
        try{
            Request request = new Request("tramites_cambio_estado");
            request.delete();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "Error al eliminar todos los Tramites Cambio Estado");
            }
            return new Respuesta(true, "Todos los tramites cambio estado fueron eliminados con exito");
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No pudo establecerce conexion con el servidor");
        }
    }
}
