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
import org.una.tramites.cliente.dto.TramiteEstadoDTO;
import org.una.tramites.cliente.util.Request;
import org.una.tramites.cliente.util.Respuesta;

/**
 *
 * @author Pablo-VE
 */
public class TramiteEstadoService {
    public Respuesta getAll(){
        try{
            Request request = new Request("tramites_estados");
            request.get();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "Error al obtener todos los tramites estado");
            }
            List<TramiteEstadoDTO> result = (List<TramiteEstadoDTO>) request.readEntity(new GenericType<List<TramiteEstadoDTO>>(){});
            return new Respuesta(true, "TramitesEstado",result);
        }catch(Exception ex){

            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta getById(Long id){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("tramites_estados", "/{id}", parametros);
            request.get();
            if(request.isError()){
                System.out.println(request.getError());
                return new Respuesta(false, request.getError(), "Error al obtener el tramite estado");
            }
            TramiteEstadoDTO result = (TramiteEstadoDTO) request.readEntity(TramiteEstadoDTO.class);
            return new Respuesta(true, "TramiteEstado", result);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
        public Respuesta guardar(TramiteEstadoDTO tramiteEstado){
        try{
            Request request = new Request("tramites_estados");
            request.post(tramiteEstado);
            if(request.isError()){
                return new Respuesta(false, request.getError(), "No se pudo guardar el tramite estado");
            }
            TramiteEstadoDTO result = (TramiteEstadoDTO) request.readEntity(TramiteEstadoDTO.class);
            return new Respuesta(true, "TramiteEstado", result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta modificar(Long id, TramiteEstadoDTO tramiteEstado){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("tramites_estados", "/{id}", parametros);
            request.put(tramiteEstado);
            if(request.isError()){
                return new Respuesta(false, request.getError(), "No se pudo modificar el tramite estado");
            }
            TramiteEstadoDTO result = (TramiteEstadoDTO) request.readEntity(TramiteEstadoDTO.class);
            return new Respuesta(true, "TramiteEstado", result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta delete(Long id){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("tramites_estados", "/{id}", parametros);
            request.delete();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "No se pudo eliminar el Tramite Estado");
            }
            return new Respuesta(true, "Tramite Estado eliminado exitosamente");
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta deleteAll(){
        try{
            Request request = new Request("tramites_estados");
            request.delete();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "Error al eliminar todos los Tramites Estado");
            }
            return new Respuesta(true, "Todos los tramites estado fueron eliminados con exito");
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No pudo establecerce conexion con el servidor");
        }
    }
}
